/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
public class SiteServiceTest extends AppTest {

    @Autowired
    private SiteServiceLogService siteServiceLogService;
    @Autowired
    private SiteService siteService;
    
    private MongoTemplate mongoTemplate;
    private final DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();
    Date startDate = dtfwh.getDate(1, 01, 2014);
    Date endDate = dtfwh.getDate(17, 01, 2014);

    private void setTodaysDate(Date date) {
        dtfwh.setDate(date);
//        dtfwh.resetDayOfWeek();
    }

        
         public void findAllWithVistTodayTest0() {

        siteService = ctx.getBean(SiteService.class);
        setTodaysDate(new Date());
        List<Site> sitesList = siteService.findAllWithVisitToday(dtfwh.getDateToday());

        int count = 0;
        int size = sitesList.size();
        System.out.println("Count " + size);
        System.out.println("\n\n================= DAY [ " + dtfwh.getDayOfWeekTodayStr() + " ] - VISIT DATE : " + endDate);
        for (Site site : sitesList) {
            count++;
            System.out.println("\n\n --- Site No" + count + "/" + size + "---" + site.getName());
            loadSiteServceLogs(site.getName());
        }
    }
//    @Test
    public void findAllWithVistTodayTest() {

        siteService = ctx.getBean(SiteService.class);
        setTodaysDate(new Date());
        List<Site> sitesList = siteService.findAllWithVisitToday(dtfwh.getDateToday());

        int count = 0;
        int size = sitesList.size();
        System.out.println("Count " + size);
        System.out.println("\n\n================= DAY [ " + dtfwh.getDayOfWeekTodayStr() + " ] - VISIT DATE : " + endDate);
        for (Site site : sitesList) {
            count++;
            System.out.println("\n\n --- Site No" + count + "/" + size + "---" + site.getName());
            loadSiteServceLogs(site.getName());
        }
    }

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
            System.out.println("servicedBy ");
            System.out.println("completionStatus " + siteServiceLog.getCompletionStatus());
            System.out.println("serviceStatus " + siteServiceLog.getServiceStatus());
            System.out.println("status " + siteServiceLog.getStatus());
            System.out.println("Contract type " + siteServiceLog.getContractType());
        }

//        logSheduledSiteServices.createTodaysSiteServicesLogs2(calendar.getTime());
    }

//    @Test
    public void updateSiteServceLogs() {
        siteService = ctx.getBean(SiteService.class);
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        SiteService siteService;
        setTodaysDate(new Date());
        List<Site> sitesList = siteService.findAllWithVisitToday(dtfwh.getDateToday());

        int count = 0;
        int size = sitesList.size();
        System.out.println("Count " + size);
        System.out.println("\n\n================= DAY [ " + dtfwh.getDayOfWeekTodayStr() + " ] - VISIT DATE : " + endDate);
        for (Site site : sitesList) {
            count++;
            System.out.println("\n\n --- Site No" + count + "/" + size + "---" + site.getName());
//            loadSiteServceLogs(site.getName());
            List<SiteServiceLog> siteServiceLogDBList = siteServiceLogService.getAllSiteServiceLogs(site.getName(), startDate, endDate);
            Set<SiteServiceLog> siteServiceLogList2 = site.getSiteServiceLog();
            int logsDBCount = siteServiceLogDBList.size();
            int logsSiteCount = siteServiceLogList2.size();
            System.out.println(site.getName() + " : Service Log Count  DB   : " + logsDBCount);
            System.out.println(site.getName() + " : Service Log Count  Site : " + logsSiteCount);
            
            printSiteServiceLogList(siteServiceLogDBList);
//            updateSite(site, siteServiceLogDBList);
//            deleteSiteServiceLogs(site.getName());
        }
         int count2 = 0;
            for (Site site : sitesList) {
                count2++;
                System.out.println(count2 + " " +site.getName());
        }

    }
    
    private void printSiteServiceLogList(List<SiteServiceLog> siteServiceLogList){
            int count = 0;
        int size = siteServiceLogList.size();
        System.out.println(" : Service Log Count " + size);
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
            System.out.println("servicedBy ");
            System.out.println("completionStatus " + siteServiceLog.getCompletionStatus());
            System.out.println("serviceStatus " + siteServiceLog.getServiceStatus());
            System.out.println("status " + siteServiceLog.getStatus());
            System.out.println("Contract type " + siteServiceLog.getContractType());
        }

//        logSheduledSiteServices.createTodaysSiteServicesLogs2(calendar.getTime());
    }
 
    private void deleteSiteServiceLogs(String siteName){
        mongoTemplate = ctx.getBean(MongoTemplate.class);
            dtfwh.setDate(endDate);
         Query query = new Query(Criteria
                .where("parentId").is(siteName)
                .andOperator(
                        Criteria.where("serviceDate").gte(startDate),
                        Criteria.where("serviceDate").lt(dtfwh.getTomorrowsDate_No_HTMSM())));
//                Criteria.where("serviceStatus").is(serviceStatus)));
//                Criteria.where("statusMessage").is("WITHIN")));
        query.with(new Sort(Sort.Direction.DESC, "serviceDate"));
        long count = mongoTemplate.count(query, "siteServiceLog");
        System.out.println("Count 1 : " + count);
        mongoTemplate.remove(query, SiteServiceLog.class, "siteServiceLog");
        
        long count2 = mongoTemplate.count(query, "siteServiceLog");
        System.out.println("Count 2 : " + count2);
        
        
    }
    
        private void updateSite(Site site, List<SiteServiceLog> siteServiceLog) {
        siteService = ctx.getBean(SiteService.class);
        Set<SiteServiceLog> siteServiceLogsList = new HashSet<>();

//        siteServiceLogsList.addAll(site.getSiteServiceLog());
        siteServiceLogsList.addAll(siteServiceLog);

        Site updatedSite = new Site.Builder(site.getName())
                .site(site)
                .siteServiceLog(siteServiceLogsList)
                .build();
        siteService.merge(updatedSite);
    }
}
