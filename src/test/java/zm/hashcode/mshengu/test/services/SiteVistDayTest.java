/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;
import zm.hashcode.mshengu.services.fieldservices.Impl.SiteServiceLogsStatusHelper;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.services.products.SiteServiceLogService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Ferox
 */
public class SiteVistDayTest extends AppTest {

//    @Autowired
//    private LogSheduledSiteServices logSheduledSiteServices;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private SiteService siteService;
    @Autowired
    private SiteServiceLogService siteServiceLogService;
    @Autowired
    private TruckService truckService;
    private DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();
    private SiteServiceLogsStatusHelper statusHelper = new SiteServiceLogsStatusHelper();
    private DateTimeFormatHelper dtfh = new DateTimeFormatHelper();

    @Test
    public void runServiceLogs() {

//        logSheduledSiteServices = ctx.getBean(LogSheduledSiteServices.class);
        siteService = ctx.getBean(SiteService.class);

        Date startDate = dtfh.getDate(18, 10, 2013);
        Date endDate = dtfh.getDate(27, 10, 2013);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        setTodaysDate(calendar.getTime());
        int day = 1;
        do {
            List<Site> siteList = siteService.findAllWithVisitToday(calendar.getTime());
            int count = 0;
            int size = siteList.size();
            System.out.println("\n\n================= DAY [ " + dtfwh.getDayOfWeekTodayStr() + " ] - VISIT DATE : " + calendar.getTime());
            for (Site site : siteList) {

                count++;
                System.out.println("\n\n --- Site No" + count + "/" + size + "---");
                System.out.println("Site :" + site.getName());

//                creatLogs(site, calendar.getTime());
            }

//            System.out.println("VISIT DATE Before INCREMENT: " + calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
//            System.out.println("VISIT DATE AFTER INCREMENT: " + calendar.getTime());            
//            System.out.println("End DATE : " + endDate);            
            setTodaysDate(calendar.getTime());
            /*   if(calendar.getTime().after(endDate)){                
             System.out.println("VISIT DATE ( " +calendar.getTime() + " IS AFTER END DATE: (" + endDate + " )");
             }else{
                
             System.out.println("VISIT DATE ( " +calendar.getTime() + " IS Before END DATE: (" + endDate + " )");
             }*/
            day++;
        } while (calendar.getTime().before(endDate));

    }

    private void setTodaysDate(Date date) {
        dtfwh.setDate(date);
        dtfwh.resetDayOfWeek();
    }

    private void creatLogs(Site site, Date date) {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
        truckService = ctx.getBean(TruckService.class);
        Date serviceDate = date;

        statusHelper.setDate(date);
        boolean isNextDayVisitDate = statusHelper.isNexDayAVistDay(site.getLastSiteServiceContractLifeCycle(), dtfwh.getDayOfWeekTomorrow());

        int totalNumberOfUnitsServiced = (int) siteServiceLogService.getTotalUnitsServiced(site.getName(), "WITHIN", dtfwh.getTodaysDate_No_HTMSM(), dtfwh.getTomorrowsDate_No_HTMSM());
        int totalNumberOfUnits = site.getNumberOfTotalUnits();
        int totalNumberOfUnitsNotServiced = totalNumberOfUnits - totalNumberOfUnitsServiced;

        statusHelper.setServiceLogsStatus(isNextDayVisitDate, totalNumberOfUnits, totalNumberOfUnitsServiced);

        Truck servicedBy = truckService.findBySiteName(site.getName());

        SiteServiceLog newSiteServiceLog = new SiteServiceLog.Builder(serviceDate)
                .serviceTime(serviceDate)
                .comment("")
                .numberOfUnitsNotServiced(totalNumberOfUnitsNotServiced)
                .numberOfUnitsServiced(totalNumberOfUnitsServiced)
                .servicedBy(servicedBy)
                .completionStatus(statusHelper.getCompletionStatus())
                .serviceStatus(statusHelper.getServiceStatus())
                .status(statusHelper.getStatus())
                .build();
        System.out.println("Site Name: " + site.getName());
//        if(totalNumberOfUnitsServiced > 0){
        System.out.println("Service Date " + serviceDate);
        System.out.println("Tommorrows Date" + dtfwh.getTomorrowsDate_No_HTMSM());
        System.out.println("totalNumberOfUnits Not Serviced " + newSiteServiceLog.getNumberOfUnitsNotServiced());
        System.out.println("totalNumberOfUnits Serviced " + newSiteServiceLog.getNumberOfUnitsServiced());
        System.out.println("totalNumberOfUnits " + totalNumberOfUnits);
        System.out.println("servicedBy " + getVehicleNumber(newSiteServiceLog.getServicedBy()));
        System.out.println("completionStatus " + newSiteServiceLog.getCompletionStatus());
        System.out.println("serviceStatus " + newSiteServiceLog.getServiceStatus());
        System.out.println("status " + newSiteServiceLog.getStatus());
//        }

//        siteServiceLogService.persist(newSiteServiceLog);
//        updateSite(site, newSiteServiceLog);
    }

    private String getVehicleNumber(Truck truck) {
        if (truck != null) {
            return truck.getVehicleNumber();
        } else {
            return "Truck Not Found";
        }

    }
}
