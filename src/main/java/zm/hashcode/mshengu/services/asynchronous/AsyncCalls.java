/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.asynchronous;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;
import zm.hashcode.mshengu.services.fieldservices.CreateSiteServiceLogsService;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.services.products.SiteServiceLogService;

/**
 *
 * @author boniface
 */
@Service
public class AsyncCalls {

    @Autowired
    private CreateSiteServiceLogsService siteServiceScheduleLogsService;
    @Autowired
    private SiteServiceLogService siteServiceLogService;
    @Autowired
    private CreateSiteServiceLogsService createSiteServiceLogsService;
    @Autowired
    private SiteService siteService;
    private final DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();
//    private SiteServiceLogsStatusHelper statusHelper = new SiteServiceLogsStatusHelper();
    private final DateTimeFormatHelper dtfh = new DateTimeFormatHelper();
//    Date startDate = dtfh.getDate(18,10, 2013);
    Date endDate = dtfh.getDate(12, 1, 2014);

    private void setTodaysDate(Date date) {
        dtfwh.setDate(date);
//        dtfwh.resetDayOfWeek();
    }

    public static ApplicationContext ctx;

    @Async
    public void doSomething(String s) {
        // this will be executed asynchronously
        System.out.println("" + s);
        for (int a = 1; a <= 5000; a++) {
            System.out.println("I'm line number " + a + "/10000\n");
        }
    }

    @Async
    public void createLogsNoRecursion(Site site, Date date) {
//        siteServiceScheduleLogsService = ctx.getBean(CreateSiteServiceLogsService.class);
//   
        siteServiceScheduleLogsService.createSiteServiceLog(site, date);

//        logSheduledSiteServices.createTodaysSiteServicesLogs2(calendar.getTime());
    }

//    @Test(dependsOnMethods = {"createLogsNoRecursion"})
    @Async
    public void updateLogsNoRecursion(Site site, Date date) {
//        siteServiceScheduleLogsService = ctx.getBean(CreateSiteServiceLogsService.class);
//   
        siteServiceScheduleLogsService.updateSiteServiceLog(site, date);

    }

//    @Test(dependsOnMethods = {"updateLogsNoRecursion"})
    @Async
    public void closeLogsNoRecursion(SiteServiceLog siteServiceLog, Date date) {

//        siteServiceScheduleLogsService = ctx.getBean(CreateSiteServiceLogsService.class);
        siteServiceScheduleLogsService.closeOutdatedSiteService(siteServiceLog, date);

    }

    @Async
    public void createLogsAsync() {
//        SiteService siteService;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        setTodaysDate(calendar.getTime());
        List<Site> sitesList = siteService.findAllWithVisitToday(dtfwh.getDateToday());

        int count = 0;
        int size = sitesList.size();
        System.out.println("Count " + size);
        System.out.println("\n\n================= DAY [ " + dtfwh.getDayOfWeekTodayStr() + " ] - VISIT DATE : " + calendar.getTime());
        for (Site site : sitesList) {
            count++;
            System.out.println("\n\n --- Site No" + count + "/" + size + "---");
            siteServiceScheduleLogsService.createSiteServiceLog(site, dtfwh.getDateToday());

        }

//        logSheduledSiteServices.createTodaysSiteServicesLogs2(calendar.getTime());
    }
}
