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
 * @author Tiwana Siyabonga
 */
public class SiteServiceLogTaskTest extends AppTest {

    @Autowired
    private LogSheduledSiteServices logSheduledSiteServices;
    @Autowired
    private CreateSiteServiceLogsService createSiteServiceLogsService;
    @Autowired
    private SiteService siteService;
    private DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();
//    private SiteServiceLogsStatusHelper statusHelper = new SiteServiceLogsStatusHelper();
    private DateTimeFormatHelper dtfh = new DateTimeFormatHelper();
    Date startDate = dtfh.getDate(20, 10, 2013);
    Date endDate = dtfh.getDate(26, 10, 2013);

    private void setTodaysDate(Date date) {
        dtfwh.setDate(date);
//        dtfwh.resetDayOfWeek();
    }

//    @Test
    public void createLogs() {
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        setTodaysDate(calendar.getTime());
//        logSheduledSiteServices.createTodaysSiteServicesLogs2(calendar.getTime());
    }

    public void updateLogs(Date date) {
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        setTodaysDate(calendar.getTime());
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        logSheduledSiteServices.updateOpensSiteServicesLogs(date);
    }

    public void closeLogs(Date date) {
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        setTodaysDate(calendar.getTime());
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        logSheduledSiteServices.closeOutdatedSiteServicesLogs(date);
    }

//    @Test
    public void createLogsDirectly() {
        createSiteServiceLogsService = ctx.getBean(CreateSiteServiceLogsService.class);
        siteService = ctx.getBean(SiteService.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        setTodaysDate(calendar.getTime());

        List<Site> sitesList = siteService.findAllWithVisitToday(calendar.getTime());
        int count = 0;
        int size = sitesList.size();
        System.out.println("\n\n================= TEST VISIT DATE : " + calendar.getTime());
        for (Site site : sitesList) {
            count++;
            System.out.println("\n\n --- Site No" + count + "/" + size + "---");
            System.out.println("Site :" + site.getName());

            createSiteServiceLogsService.createSiteServiceLog(site, calendar.getTime());
        }
    }
}
