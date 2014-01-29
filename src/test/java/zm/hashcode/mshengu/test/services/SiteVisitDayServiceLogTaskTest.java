/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.services.fieldservices.CreateSiteServiceLogsService;
import zm.hashcode.mshengu.services.fieldservices.LogSheduledSiteServices;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Ferox
 */
public class SiteVisitDayServiceLogTaskTest extends AppTest {

    @Autowired
    private LogSheduledSiteServices logSheduledSiteServices;
    @Autowired
    private CreateSiteServiceLogsService createSiteServiceLogsService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private CreateSiteServiceLogsService siteServiceScheduleLogsService;

    private DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();
//    private SiteServiceLogsStatusHelper statusHelper = new SiteServiceLogsStatusHelper();
    private final DateTimeFormatHelper dtfh = new DateTimeFormatHelper();
//    Date startDate = dtfh.getDate(18,10, 2013);
    Date endDate = dtfh.getDate(27, 0, 2014);

    private void setTodaysDate(Date date) {
        dtfwh.setDate(date);
//        dtfwh.resetDayOfWeek();
    }

    @Test
    public void createLogs() {
        siteService = ctx.getBean(SiteService.class);
        siteServiceScheduleLogsService = ctx.getBean(CreateSiteServiceLogsService.class);
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

//    @Test
    public void updateLogs(Date date) {
//        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(startDate);
//        setTodaysDate(calendar.getTime());
//        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
//        logSheduledSiteServices.updateOpensSiteServicesLogs(date);
    }

//    @Test
    public void closeLogs(Date date) {
//        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(startDate);
//        setTodaysDate(calendar.getTime());
//        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
//        logSheduledSiteServices.closeOutdatedSiteServicesLogs(date);
    }

//    @Test
    public void createLogsDirectly() {
//        createSiteServiceLogsService = ctx.getBean(CreateSiteServiceLogsService.class);
//        siteService = ctx.getBean(SiteService.class);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(startDate);
//        setTodaysDate(calendar.getTime());
//
//        List<Site> sitesList = siteService.findAllWithVisitToday(calendar.getTime());
//        int count = 0;
//        int size = sitesList.size();
//        System.out.println("\n\n================= TEST VISIT DATE : " + calendar.getTime());
//        for (Site site : sitesList) {
//            count++;
//            System.out.println("\n\n --- Site No" + count + "/" + size + "---");
//            System.out.println("Site :" + site.getName());
//
//            createSiteServiceLogsService.createSiteServiceLog(site, calendar.getTime());
//        }
    }
}
