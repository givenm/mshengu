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
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.products.LogSiteEvents;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.UnitServiceLog;
import zm.hashcode.mshengu.services.fieldservices.LogSiteEventService;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.services.products.UnitServiceLogService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author boniface
 */
public class SiteServiceLogsUpdate extends AppTest {

    @Autowired
    private TruckService truckService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private UnitServiceLogService unitServiceLogService;
    @Autowired
    private LogSiteEventService logSiteEventService;

    @Test
    public void tesAddService() {
        logSiteEventService = ctx.getBean(LogSiteEventService.class);
        siteService = ctx.getBean(SiteService.class);
        unitServiceLogService = ctx.getBean(UnitServiceLogService.class);
        truckService = ctx.getBean(TruckService.class);
        

        List<LogSiteEvents> siteEvents = logSiteEventService.findAll();

        List<UnitServiceLog> unitServiceLogs = unitServiceLogService.findAll();
        int counter = 0;
        for (UnitServiceLog unitServiceLog : unitServiceLogs) {
            String unitServiceSiteName = unitServiceLog.getSiteName();
            if(counter == 15){
                break;
            }
            counter++;
            System.out.println(unitServiceLog.toString());
            
            //there are many unitServiceLogs that do not have the siteName value.
            //the siteName is used to compare with the logSiteEvent site name.
            
//            if (unitServiceSiteName != null) { 
//                for (LogSiteEvents logSiteEvent : siteEvents) {
//                    Date serviceDate = logSiteEvent.getDate();
//                    String siteId = logSiteEvent.getSiteId();
//                    Site site = siteService.findById(siteId);
//
//                    System.out.println("\nServiceLog Site Name: " + unitServiceSiteName);
//                    System.out.println("Site Name: " + site.getName() + "\n");
//
//                    //initialise bothe dates for comparison
//                    Calendar serviceCalendar = Calendar.getInstance();
//                    serviceCalendar.setTime(serviceDate);
//                    serviceCalendar.set(serviceCalendar.get(Calendar.YEAR), serviceCalendar.get(Calendar.MONTH), serviceCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
//
//                    Calendar unitServiceLogCalendar = Calendar.getInstance();
//                    unitServiceLogCalendar.setTime(unitServiceLog.getServiceDate());
//                    unitServiceLogCalendar.set(unitServiceLogCalendar.get(Calendar.YEAR), unitServiceLogCalendar.get(Calendar.MONTH), unitServiceLogCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
//
//                    System.out.println("DAY_OF_MONTH: \t" + serviceCalendar.get(Calendar.DAY_OF_MONTH) + " - " + unitServiceLogCalendar.get(Calendar.DAY_OF_MONTH));
//                    System.out.println("MONTH: \t\t" + serviceCalendar.get(Calendar.MONTH) + " - " + unitServiceLogCalendar.get(Calendar.MONTH));
//                    System.out.println("YEAR: \t\t" + serviceCalendar.get(Calendar.YEAR) + " - " + unitServiceLogCalendar.get(Calendar.YEAR));
//                    
//                    if (serviceCalendar.get(Calendar.YEAR) != unitServiceLogCalendar.get(Calendar.YEAR)) {
//                        System.out.println("Breakxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx because of different year");
//                        break; //when year is different, there is no need to keep looping and comparing. Just exit the inner loop.
//                    }
//                    
//                    if (unitServiceSiteName.equals(site.getName())
//                            && serviceCalendar.get(Calendar.DAY_OF_MONTH) == unitServiceLogCalendar.get(Calendar.DAY_OF_MONTH)
//                            && serviceCalendar.get(Calendar.MONTH) == unitServiceLogCalendar.get(Calendar.MONTH)
//                            && serviceCalendar.get(Calendar.YEAR) == unitServiceLogCalendar.get(Calendar.YEAR)) {
//                        
//                         //PERFORM THE UPDATE OF THE UnitServiceLog
//                        
////                        Truck truck = truckService.findById(logSiteEvent.getTruckId());
////                        UnitServiceLog newUnitServiceLog = new UnitServiceLog.Builder(unitServiceLog.getServiceDate())
////                                .unitServiceLog(unitServiceLog)
////                                .servicedBy(truck)
////                                .build();
////                        unitServiceLogService.merge(newUnitServiceLog);
//                        System.out.println("Serviced on site*************************************");
//                    }
//
//                    System.out.println("\n\n\n--------------------------------------------------------------------------------------------");
//                }
//                System.out.println("After loop+++++++++++++++++++++++++++++++++++++++");
//            }
        }

    }

}
