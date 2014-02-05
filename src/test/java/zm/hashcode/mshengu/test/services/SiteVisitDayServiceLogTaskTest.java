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
    private final DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();
//    private SiteServiceLogsStatusHelper statusHelper = new SiteServiceLogsStatusHelper();
    private final DateTimeFormatHelper dtfh = new DateTimeFormatHelper();
//    Date startDate = dtfh.getDate(18,10, 2013);
    Date endDate = dtfh.getDate(19, 10, 2013);

    private void setTodaysDate(Date date) {
        dtfwh.setDate(date);
//        dtfwh.resetDayOfWeek();
    }

//    @Test
    public void createLogsNoRecursion() {
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
    public void findAllWithVistTodayTest(){
      
        siteService = ctx.getBean(SiteService.class);
        setTodaysDate(endDate);
         List<Site> sitesList = siteService.findAllWithVisitToday(dtfwh.getDateToday());
         
        int count = 0;
        int size = sitesList.size();
        System.out.println("Count " + size);
        System.out.println("\n\n================= DAY [ " + dtfwh.getDayOfWeekTodayStr() + " ] - VISIT DATE : "  + endDate);
        for (Site site : sitesList) {
            count++;
            System.out.println("\n\n --- Site No" + count + "/" + size + "---" +site.getName());
        }
    }
//        @Test
    public void createLogsWithRecursion() {
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        setTodaysDate(calendar.getTime());
        logSheduledSiteServices.createTodaysSiteServicesLogs2(calendar.getTime());
    }

    public void updateLogs(Date date) {
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        setTodaysDate(calendar.getTime());
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        logSheduledSiteServices.updateOpensSiteServicesLogs(date);
    }

    public void closeLogs(Date date) {
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        setTodaysDate(calendar.getTime());
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        logSheduledSiteServices.closeOutdatedSiteServicesLogs(date);
    }
}
