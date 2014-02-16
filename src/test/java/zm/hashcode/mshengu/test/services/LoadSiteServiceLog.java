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
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.services.products.SiteServiceLogService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Ferox
 */
public class LoadSiteServiceLog extends AppTest {

    @Autowired
    private SiteServiceLogService siteServiceLogService;
    @Autowired
    private SiteService siteService;
    private final DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();
    Date startDate = dtfwh.getDate(01,01, 2014);
    Date endDate = dtfwh.getDate(17, 01, 2014);

    private void setTodaysDate(Date date) {
        dtfwh.setDate(date);
//        dtfwh.resetDayOfWeek();
    }

//    @Test
    public void loadSiteServceLogs(String siteName) {
        siteService = ctx.getBean(SiteService.class);
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        SiteService siteService;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        setTodaysDate(calendar.getTime());
        List<SiteServiceLog> siteServiceLogList = siteServiceLogService.getAllSiteServiceLogs(siteName, startDate, endDate);
        

        int count = 0;
        int size = siteServiceLogList.size();
        System.out.println(siteName + " : Service Log Count " + size);
        System.out.println(": Start Date :  " + startDate);
        System.out.println("End Date : " + endDate);
//        System.out.println("\n\n================= DAY [ " + dtfwh.getDayOfWeekTodayStr() + " ] - VISIT DATE : " + calendar.getTime());
        for (SiteServiceLog siteServiceLog : siteServiceLogList) {
            count++;
            
            System.out.println("\n\n --- Service Log No" + count + "/" + size + "---");
            System.out.println("Site " + siteServiceLog.getParentId());
//        System.out.println("Action : CREATE LOG");
        System.out.println("Service Date " + siteServiceLog.getServiceDate());
        System.out.println("Tommorrows Date" + dtfwh.getTomorrowsDate_No_HTMSM());
        System.out.println("totalNumberOfUnits Not Serviced " + siteServiceLog.getNumberOfUnitsNotServiced());
        System.out.println("totalNumberOfUnits Serviced " + siteServiceLog.getNumberOfUnitsServiced());
        System.out.println("totalNumberOfUnits ");
        System.out.println("servicedBy " );
        System.out.println("completionStatus " + siteServiceLog.getCompletionStatus());
        System.out.println("serviceStatus " + siteServiceLog.getServiceStatus());
        System.out.println("status " + siteServiceLog.getStatus());
        System.out.println("Contract type " + siteServiceLog.getContractType());
        }

//        logSheduledSiteServices.createTodaysSiteServicesLogs2(calendar.getTime());
    }

    @Test
    public void findAllWithVistTodayTest(){
      
        siteService = ctx.getBean(SiteService.class);
        setTodaysDate(new Date());
         List<Site> sitesList = siteService.findAllWithVisitToday(dtfwh.getDateToday());
         
        int count = 0;
        int size = sitesList.size();
        System.out.println("Count " + size);
        System.out.println("\n\n================= DAY [ " + dtfwh.getDayOfWeekTodayStr() + " ] - VISIT DATE : "  + endDate);
        for (Site site : sitesList) {
            count++;
            System.out.println("\n\n --- Site No" + count + "/" + size + "---" +site.getName());
             loadSiteServceLogs(site.getName());
        }
    }
}
