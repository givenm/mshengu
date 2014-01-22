/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.services;

import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.services.fieldservices.LogSheduledSiteServices;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Tiwana Siyabonga
 */
public class SiteServiceLogTaskTest extends AppTest {

    @Autowired
    private LogSheduledSiteServices logSheduledSiteServices;
    private DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();
//    private SiteServiceLogsStatusHelper statusHelper = new SiteServiceLogsStatusHelper();
    private DateTimeFormatHelper dtfh = new DateTimeFormatHelper();
    Date startDate = dtfh.getDate(20, 10, 2013);
    Date endDate = dtfh.getDate(26, 10, 2013);

    private void setTodaysDate(Date date) {
        dtfwh.setDate(date);
//        dtfwh.resetDayOfWeek();
    }

    @Test
    private void createLogs(Date date) {
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        setTodaysDate(calendar.getTime());
        logSheduledSiteServices.createTodaysSiteServicesLogs(date);
    }

    private void updateLogs(Date date) {       
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        setTodaysDate(calendar.getTime());
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        logSheduledSiteServices.updateOpensSiteServicesLogs(date);
    }

    private void closeLogs(Date date) {        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        setTodaysDate(calendar.getTime());
        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        logSheduledSiteServices.closeOutdatedSiteServicesLogs(date);
    }
}
