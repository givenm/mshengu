/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fieldservices.Impl;

import zm.hashcode.mshengu.services.fieldservices.CreateSiteServiceLogsService;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.services.products.*;

/**
 *
 * @author Ferox
 */
@Service
public class CreateSiteServiceLogsServiceImpl implements CreateSiteServiceLogsService {

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

    @Override
    public void createSiteServiceLog(Site site, Date date) {
        Date serviceDate = date;
        setDate(date);

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

        siteServiceLogService.persist(newSiteServiceLog);

        updateSite(site, newSiteServiceLog);

        System.out.println("Site Name: " + site.getName());
//        if(totalNumberOfUnitsServiced > 0){){
        System.out.println("Action : CREATE LOG");
        System.out.println("Service Date " + serviceDate);
        System.out.println("Tommorrows Date" + dtfwh.getTomorrowsDate_No_HTMSM());
        System.out.println("totalNumberOfUnits Not Serviced " + newSiteServiceLog.getNumberOfUnitsNotServiced());
        System.out.println("totalNumberOfUnits Serviced " + newSiteServiceLog.getNumberOfUnitsServiced());
        System.out.println("totalNumberOfUnits " + totalNumberOfUnits);
        System.out.println("servicedBy " + getVehicleNumber(newSiteServiceLog.getServicedBy()));
        System.out.println("completionStatus " + newSiteServiceLog.getCompletionStatus());
        System.out.println("serviceStatus " + newSiteServiceLog.getServiceStatus());
        System.out.println("status " + newSiteServiceLog.getStatus());
    }

    @Override
    public void updateSiteServiceLog(Site site, Date date) {
        Date visitDate = getServiceDate(site.getLastSiteServiceLog());
        setDate(date);

        boolean isNextDayVisitDate = statusHelper.isNexDayAVistDay(site.getLastSiteServiceContractLifeCycle(), dtfwh.getDayOfWeekTomorrow());

        int totalNumberOfUnitsServiced = (int) siteServiceLogService.getTotalUnitsServiced(site.getName(), "WITHIN", visitDate, dtfwh.getTomorrowsDate_No_HTMSM());
        int totalNumberOfUnits = site.getNumberOfTotalUnits();
        int totalNumberOfUnitsNotServiced = totalNumberOfUnits - totalNumberOfUnitsServiced;

        statusHelper.setServiceLogsStatus(isNextDayVisitDate, totalNumberOfUnits, totalNumberOfUnitsServiced);

        SiteServiceLog newSiteServiceLog = new SiteServiceLog.Builder(visitDate)
                .siteServiceLog(site.getLastSiteServiceLog())
                .numberOfUnitsNotServiced(totalNumberOfUnitsNotServiced)
                .numberOfUnitsServiced(totalNumberOfUnitsServiced)
                .completionStatus(statusHelper.getCompletionStatus())
                .serviceStatus(statusHelper.getServiceStatus())
                .status(statusHelper.getStatus())
                .build();

        siteServiceLogService.merge(newSiteServiceLog);


        System.out.println("Site Name: " + site.getName());
//        if(totalNumberOfUnitsServiced > 0){){
        System.out.println("Action : CREATE LOG");
        System.out.println("Service Date " + newSiteServiceLog.getServiceDate());
        System.out.println("Tommorrows Date" + dtfwh.getTomorrowsDate_No_HTMSM());
        System.out.println("totalNumberOfUnits Not Serviced " + newSiteServiceLog.getNumberOfUnitsNotServiced());
        System.out.println("totalNumberOfUnits Serviced " + newSiteServiceLog.getNumberOfUnitsServiced());
        System.out.println("totalNumberOfUnits " + totalNumberOfUnits);
        System.out.println("servicedBy " + getVehicleNumber(newSiteServiceLog.getServicedBy()));
        System.out.println("completionStatus " + newSiteServiceLog.getCompletionStatus());
        System.out.println("serviceStatus " + newSiteServiceLog.getServiceStatus());
        System.out.println("status " + newSiteServiceLog.getStatus());
    }

    @Override
    public void closeOutdatedSiteService(SiteServiceLog siteServiceLog, Date date) {

        SiteServiceLog newSiteServiceLog = new SiteServiceLog.Builder(siteServiceLog.getServiceDate())
                .siteServiceLog(siteServiceLog)
                .serviceStatus(statusHelper.getSiteServiceLogServiceStatus(siteServiceLog))
                .build();

        siteServiceLogService.merge(newSiteServiceLog);

        System.out.println("Site Name: " + "No Idea");
        System.out.println("Action : CREATE LOG");
        System.out.println("Service Date " + newSiteServiceLog.getServiceDate());
        System.out.println("Tommorrows Date" + dtfwh.getTomorrowsDate_No_HTMSM());
        System.out.println("totalNumberOfUnits Not Serviced " + newSiteServiceLog.getNumberOfUnitsNotServiced());
        System.out.println("totalNumberOfUnits Serviced " + newSiteServiceLog.getNumberOfUnitsServiced());
        System.out.println("totalNumberOfUnits " + "NO IDEA");
        System.out.println("servicedBy " + getVehicleNumber(newSiteServiceLog.getServicedBy()));
        System.out.println("completionStatus " + newSiteServiceLog.getCompletionStatus());
        System.out.println("serviceStatus " + newSiteServiceLog.getServiceStatus());
        System.out.println("status " + newSiteServiceLog.getStatus());
    }

    private Date getServiceDate(SiteServiceLog lastSiteServiceLog) {
        if (lastSiteServiceLog != null) {
            return lastSiteServiceLog.getServiceDate();
        } else {
            return dtfwh.getYesterdayssDate_No_HTMSM();
        }
    }

    /*
     * add annotation to discard cache
     */
    private void updateSite(Site site, SiteServiceLog siteServiceLog) {
        Set<SiteServiceLog> siteServiceLogsList = new HashSet<>();

        siteServiceLogsList.addAll(site.getSiteServiceLog());
        siteServiceLogsList.add(siteServiceLog);

        Site updatedSite = new Site.Builder(site.getName())
                .site(site)
                .siteServiceLog(siteServiceLogsList)
                .build();
        siteService.merge(updatedSite);
    }

    private void setDate(Date date) {
        dtfwh.setDate(date);
        statusHelper.setDate(date);
    }

    private String getVehicleNumber(Truck truck) {
        if (truck != null) {
            return truck.getVehicleNumber();
        } else {
            return "Truck Not Found";
        }

    }
}
