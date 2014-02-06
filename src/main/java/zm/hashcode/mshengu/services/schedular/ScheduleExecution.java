/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.schedular;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.facade.products.SiteFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.services.fieldservices.CreateSiteServiceLogsService;

/**
 *
 * @author boniface
 */
@Service
public class ScheduleExecution {

    private DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();

    private void setTodaysDate(Date date) {
        dtfwh.setDate(date);
//        dtfwh.resetDayOfWeek();
    }

    /**
     * * * * * command to be executed - - - - - | | | | | | | | | ----- Day of
     * week (0 - 7) (Sunday=0 or 7) | | | ------- Month (1 - 12) | | ---------
     * Day of month (1 - 31) | ----------- Hour (0 - 23) ------------- Minute (0
     * - 59)
     *
     */
//                       * * *  * * *
//    @Scheduled(cron = "* 15 21-23 * * MON-SAT")// Run 15 Minutes Past every Hour between 21 hrs and 23 hours from Monday to Saturday
    @Scheduled(cron = "* * 20 * * *")// Run 15 Minutes Past every Hour between 21 hrs and 23 hours from Monday to Saturday                     
    public void runthisCode() {
        // Code to be execute duering Schedule

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        setTodaysDate(calendar.getTime());
        List<Site> sitesList = SiteFacade.getSiteService().findAllWithVisitToday(dtfwh.getDateToday());

        int count = 0;
        int size = sitesList.size();
        System.out.println("Count " + size);
        System.out.println("\n\n================= DAY [ " + dtfwh.getDayOfWeekTodayStr() + " ] - VISIT DATE : " + calendar.getTime());
        for (Site site : sitesList) {
            count++;
            System.out.println("\n\n --- Site No" + count + "/" + size + "---");
        }

    }

}
