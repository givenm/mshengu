/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.asynchronous;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
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
    private SiteService siteService;
    private final DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();



    @Async
    public void createLogsAsync(Date date) {
        setTodaysDate(date);
        
        List<Site> sitesList = siteService.findAllWithVisitToday(dtfwh.getDateToday());

        int count = 0;
        int size = sitesList.size();
        System.out.println("createLogsAsync Count " + size);
        System.out.println("\n\n================= DAY [ " + dtfwh.getDayOfWeekTodayStr() + " ] - VISIT DATE : " + dtfwh.getDateToday());
        for (Site site : sitesList) {
            count++;
            System.out.println("\n\n --- Site No" + count + "/" + size + "---");
            siteServiceScheduleLogsService.createSiteServiceLog(site, dtfwh.getDateToday());

        }
    }

    @Async
    public void updateLogsAsync(Date date) {
        setTodaysDate(date);
        List<Site> todaysSitesList = siteService.findAllWithLastLogOpen();

        int count = 0;
        int size = todaysSitesList.size();
        System.out.println("updateLogsAsync count" +size);
        System.out.println("\n\n================= TEST VISIT DATE : " + date);
        for (Site site : todaysSitesList) {
            count++;
            System.out.println("\n\n --- Site No" + count + "/" + size + "---");
            System.out.println("Site :" + site.getName());
            siteServiceScheduleLogsService.updateSiteServiceLog(site, date);
        }

    }
    
    @Async
    public void closeLogsAsync(Date date) {
        setTodaysDate(date);

        List<SiteServiceLog> siteServiceLogList = siteServiceLogService.getOutdatedOpenLogs(dtfwh.getDateToday());
        int count = 0;
        int size = siteServiceLogList.size();
        System.out.println("closeLogsAsync Count " + size);
        System.out.println("\n\n================= DAY [ " + dtfwh.getDayOfWeekTodayStr() + " ] - VISIT DATE : " + dtfwh.getDateToday());
        for (SiteServiceLog siteServiceLog : siteServiceLogList) {
            count++;
            System.out.println("\n\n --- Site No" + count + "/" + size + "---");

            siteServiceScheduleLogsService.closeOutdatedSiteService(siteServiceLog, dtfwh.getDateToday());
        }

    }
    
        private void setTodaysDate(Date date) {
        dtfwh.setDate(date);
    }

    @Async
    public void doSomething(String s) {
        // this will be executed asynchronously
        System.out.println("" + s);
        for (int a = 1; a <= 5000; a++) {
            System.out.println("I'm line number " + a + "/10000\n");
        }
    }
}
