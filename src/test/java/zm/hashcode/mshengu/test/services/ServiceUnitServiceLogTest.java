/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.services.fieldservices.ServiceSiteUnits;
import zm.hashcode.mshengu.services.fieldservices.ServiceUnit;
import zm.hashcode.mshengu.services.products.SiteServiceLogService;
import zm.hashcode.mshengu.services.products.SiteUnitService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author boniface
 */
public class ServiceUnitServiceLogTest extends AppTest {

    @Autowired
    private ServiceUnit serviceUnit;
    @Autowired
    private SiteUnitService siteUnitService;
    @Autowired
    private ServiceSiteUnits serviceSiteUnits;
    @Autowired
    SiteServiceLogService siteServiceLogService;
    DateTimeFormatWeeklyHelper dtfwh = new DateTimeFormatWeeklyHelper();
    

    @Test
    public void testVistDateQuery() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
        
        Date date = dtfwh.getDate(05,02,2014);
        dtfwh.setDate(date);
//        serviceUnit = ctx.getBean(ServiceUnit.class);
System.out.println("Date " + dtfwh.getTodaysDate_No_HTMSM());
        Date serviceDateStart = dtfwh.getTodaysDate_No_HTMSM();
        Date serviceDateEnd = dtfwh.getTomorrowsDate_No_HTMSM();        
        String message = "WITHIN";//User Away From UNIT"; //WITHIN
        long countBaySidePTI = siteServiceLogService.getTotalUnitsServiced("Bay Side PTI", message, serviceDateStart, serviceDateEnd);
        System.out.println(" \n Bay Side PTI logs Count ==== >Count  = " + countBaySidePTI);
        long countBarcelona = siteServiceLogService.getTotalUnitsServiced("Barcelona", message, serviceDateStart, serviceDateEnd);
        System.out.println(" \n Barcelona logs Count ==== >Count  = " + countBarcelona);
        long countVygekraal = siteServiceLogService.getTotalUnitsServiced("Vygekraal", message, serviceDateStart, serviceDateEnd);
        System.out.println(" \n Vygekraal logs Count ==== >Count  = " + countVygekraal);        
        long countJoeSlovo = siteServiceLogService.getTotalUnitsServiced("Joe Slovo", message, serviceDateStart, serviceDateEnd);
        System.out.println(" \n Joe Slovo logs Count ==== >Count  = " + countJoeSlovo);
//        return calendar;
    }

//    @Test(dependsOnMethods = {"testVistDateQuery"})
    public void testbeforeVistDateQuery() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        serviceUnit = ctx.getBean(ServiceUnit.class);

        Date serviceDateStart = dtfwh.getYesterdayDateFull();
        Date serviceDateEnd = dtfwh.getTomorrowsDate_No_HTMSM();
        long count = siteServiceLogService.getTotalUnitsServiced("Du Noon - Doornbach", "WITHIN", serviceDateStart, serviceDateEnd);
        System.out.println("testbeforeVistDateQuery ==== > \n Count  = " + count);
//        return calendar;
    }

//    @Test(dependsOnMethods = {"testbeforeVistDateQuery"})
    public void testStringVistDates21_21() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        serviceUnit = ctx.getBean(ServiceUnit.class);

        Date serviceDateStart = dtfwh.getDate_No_HMSM("2013/11/21 06:43:28 AM");
        Date serviceDateEnd = dtfwh.getDate_No_HMSM("2013/11/21 07:27:19 AM");
        long count = siteServiceLogService.getTotalUnitsServiced("Du Noon - Doornbach", "WITHIN", serviceDateStart, serviceDateEnd);
        System.out.println("testStringVistDates21_21 ==== > \n Count  = " + count);
//        return calendar;
    }

//    @Test(dependsOnMethods = {"testStringVistDates21_21"})
    public void testStringVistDates21_22() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        serviceUnit = ctx.getBean(ServiceUnit.class);

        Date serviceDateStart = dtfwh.getDate_No_HMSM("2013/11/21 06:43:28 AM");
        Date serviceDateEnd = dtfwh.getDate_No_HMSM("2013/11/22 07:27:19 AM");
        long count = siteServiceLogService.getTotalUnitsServiced("Du Noon - Doornbach", "WITHIN", serviceDateStart, serviceDateEnd);
        System.out.println("testStringVistDates21_22 ==== > \n Count  = " + count);
//        return calendar;
    }

//    @Test(dependsOnMethods = {"testStringVistDates21_22"})
    public void testStringVistDates21_23() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        serviceUnit = ctx.getBean(ServiceUnit.class);

        Date serviceDateStart = dtfwh.getDate_No_HMSM("2013/11/21 06:43:28 AM");
        Date serviceDateEnd = dtfwh.getDate_No_HMSM("2013/11/23 07:27:19 AM");
        long count = siteServiceLogService.getTotalUnitsServiced("Du Noon - Doornbach", "WITHIN", serviceDateStart, serviceDateEnd);
        System.out.println("testStringVistDates21_23 ==== > \n Count  = " + count);
//        return calendar;
    }

//    @Test(dependsOnMethods = {"testStringVistDates21_23"})
    public void testStringVistDates22_22() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        serviceUnit = ctx.getBean(ServiceUnit.class);

        Date serviceDateStart = dtfwh.getDate_No_HMSM("2013/11/22 06:43:28 AM");
        Date serviceDateEnd = dtfwh.getDate_No_HMSM("2013/11/22 07:27:19 AM");
        long count = siteServiceLogService.getTotalUnitsServiced("Du Noon - Doornbach", "WITHIN", serviceDateStart, serviceDateEnd);
        System.out.println("testStringVistDates22_22 ==== > \n Count  = " + count);
//        return calendar;
    }

//    @Test(dependsOnMethods = {"testStringVistDates22_22"})
    public void testStringVistDates22_23() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        serviceUnit = ctx.getBean(ServiceUnit.class);

        Date serviceDateStart = dtfwh.getDate_No_HMSM("2013/11/22 06:43:28 AM");
        Date serviceDateEnd = dtfwh.getDate_No_HMSM("2013/11/23 07:27:19 AM");
        long count = siteServiceLogService.getTotalUnitsServiced("Du Noon - Doornbach", "WITHIN", serviceDateStart, serviceDateEnd);
        System.out.println("testStringVistDates22_23 ==== > \n Count  = " + count);
//        return calendar;
    }

//    @Test(dependsOnMethods = {"testStringVistDates22_23"})
    public void testStringVistDates23_23() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        serviceUnit = ctx.getBean(ServiceUnit.class);

        Date serviceDateStart = dtfwh.getDate_No_HMSM("2013/11/23 06:43:28 AM");
        Date serviceDateEnd = dtfwh.getDate_No_HMSM("2013/11/23 07:27:19 AM");
        long count = siteServiceLogService.getTotalUnitsServiced("Du Noon - Doornbach", "WITHIN", serviceDateStart, serviceDateEnd);
        System.out.println("testStringVistDates23_23 ==== > \n Count  = " + count);
//        return calendar;
    }

//    @Test(dependsOnMethods = {"testStringVistDates23_23"})
    public void testStringVistDates23_24() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        serviceUnit = ctx.getBean(ServiceUnit.class);

        Date serviceDateStart = dtfwh.getDate_No_HMSM("2013/11/23 06:43:28 AM");
        Date serviceDateEnd = dtfwh.getDate_No_HMSM("2013/11/24 07:27:19 AM");
        long count = siteServiceLogService.getTotalUnitsServiced("Du Noon - Doornbach", "WITHIN", serviceDateStart, serviceDateEnd);
        System.out.println("testStringVistDates23_24 ==== > \n Count  = " + count);
//        return calendar;
    }
//   @Test(dependsOnMethods = {"testStringVistDates23_24"})

    public void testStringVistDates24_24() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        serviceUnit = ctx.getBean(ServiceUnit.class);

        Date serviceDateStart = dtfwh.getDate_No_HMSM("2013/11/24 06:43:28 AM");
        Date serviceDateEnd = dtfwh.getDate_No_HMSM("2013/11/24 07:27:19 AM");
        long count = siteServiceLogService.getTotalUnitsServiced("Du Noon - Doornbach", "WITHIN", serviceDateStart, serviceDateEnd);
        System.out.println("testStringVistDates24_24 ==== > \n Count  = " + count);
//        return calendar;
    }
//    @Test(dependsOnMethods = {"testStringVistDates24_24"})

    public void testStringVistDates24_25() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        serviceUnit = ctx.getBean(ServiceUnit.class);

        Date serviceDateStart = dtfwh.getDate_No_HMSM("2013/11/24 06:43:28 AM");
        Date serviceDateEnd = dtfwh.getDate_No_HMSM("2013/11/25 07:27:19 AM");
        long count = siteServiceLogService.getTotalUnitsServiced("Du Noon - Doornbach", "WITHIN", serviceDateStart, serviceDateEnd);
        System.out.println("testStringVistDates24_25 ==== > \n Count  = " + count);
//        return calendar;
    }

//    @Test(dependsOnMethods = {"testStringVistDates24_25"})
    public void testStringVistDates25_25() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        serviceUnit = ctx.getBean(ServiceUnit.class);

        Date serviceDateStart = dtfwh.getDate_No_HMSM("2013/11/25 06:43:28 AM");
        Date serviceDateEnd = dtfwh.getDate_No_HMSM("2013/11/25 07:27:19 AM");
        long count = siteServiceLogService.getTotalUnitsServiced("Du Noon - Doornbach", "WITHIN", serviceDateStart, serviceDateEnd);
        System.out.println("testStringVistDates25_25 ==== > \n Count  = " + count);
//        return calendar;
    }

//    @Test(dependsOnMethods = {"testStringVistDates25_25"})
    public void testStringVistDates3() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        serviceUnit = ctx.getBean(ServiceUnit.class);

        Date serviceDateStart = dtfwh.getDate_No_HMSM(2013, 11, 21);
        Date serviceDateEnd = dtfwh.getDate_No_HMSM(2013, 11, 22);
        long count = siteServiceLogService.getTotalUnitsServiced("Du Noon - Doornbach", "WITHIN", serviceDateStart, serviceDateEnd);
        System.out.println("testStringVistDates3 ==== > \n Count  = " + count);
//        return calendar;
    }

//    @Test(dependsOnMethods = {"testStringVistDates3"})
    public void testStringVistDates4() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
//        serviceUnit = ctx.getBean(ServiceUnit.class);

        Date serviceDateStart = dtfwh.getDate_No_HMSM(2013, 11, 21);
        Date serviceDateEnd = dtfwh.getDate_No_HMSM(2013, 11, 23);
        long count = siteServiceLogService.getTotalUnitsServiced("Du Noon - Doornbach", "WITHIN", serviceDateStart, serviceDateEnd);
        System.out.println("testStringVistDates4 ==== > \n Count  = " + count);
//        return calendar;
    }
//    @Test

    public void tesAddService() {
        siteUnitService = ctx.getBean(SiteUnitService.class);
        serviceUnit = ctx.getBean(ServiceUnit.class);

        SiteUnit unit = siteUnitService.findById("528af3ceb4c569a97484f91f");
        Map<String, Boolean> tasks = new HashMap<>();
        tasks.put("pumpOut", Boolean.TRUE);
        tasks.put("washBucket", Boolean.TRUE);
        tasks.put("suctionOut", Boolean.TRUE);
        tasks.put("scrubFloor", Boolean.FALSE);
        tasks.put("rechargeBacket", Boolean.TRUE);
        tasks.put("cleanPerimeter", Boolean.TRUE);
        UnitServiceResource resource = new UnitServiceResource();
        resource.setIncident("Nothing Wrong");
        resource.setLatitude("13123232132");
        resource.setLongitude("42353522525");
        resource.setServices(tasks);
        resource.setStatusMessage("WITHIN");
        resource.setTruckId("1221");
        resource.setUnitId(unit.getUnitId());
        resource.setUnitPosition("121");
        serviceUnit.serviceunit(resource, unit, resource.getStatusMessage());
    }

//    @Test
    public void testUnitRange() {
        siteUnitService = ctx.getBean(SiteUnitService.class);
        serviceUnit = ctx.getBean(ServiceUnit.class);
        serviceSiteUnits = ctx.getBean(ServiceSiteUnits.class);

        SiteUnit start = siteUnitService.findByUnitId("MTU-000376");

        SiteUnit finish = siteUnitService.findByUnitId("MTU-000380");

        System.out.println(" THE FIRST IS " + start.getUnitId());

        System.out.println(" THE FINISH IS " + finish.getUnitId());

        List<SiteUnit> unites = serviceSiteUnits.getSiteUnitsRange(start, finish);

        for (SiteUnit siteUnit : unites) {
            System.out.println(" THE UNIT IS " + siteUnit.getUnitId());

        }

        Map<String, Boolean> tasks = new HashMap<>();
        tasks.put("pumpOut", Boolean.TRUE);
        tasks.put("washBucket", Boolean.TRUE);
        tasks.put("suctionOut", Boolean.TRUE);
        tasks.put("scrubFloor", Boolean.FALSE);
        tasks.put("rechargeBacket", Boolean.TRUE);
        tasks.put("cleanPerimeter", Boolean.TRUE);


        UnitServiceResource resource = new UnitServiceResource();
        resource.setIncident("Nothing Wrong");
        resource.setLatitude("13123232132");
        resource.setLongitude("42353522525");
        resource.setServices(tasks);
        resource.setStatusMessage("WITHIN");
        resource.setTruckId("1221");
        resource.setUnitPosition("121");

        serviceSiteUnits.serviceBulkUnits(unites, resource, resource.getStatusMessage());


    }

    //@Test
    public void tesGetTodaysServiceQuery() {
        siteServiceLogService = ctx.getBean(SiteServiceLogService.class);
        //        serviceUnit = ctx.getBean(ServiceUnit.class);
        System.out.println("=======================================\nEreyesterday\n");
        System.out.println(dtfwh.getDateEreyesterday()+"\n");
        System.out.println(dtfwh.getDateEreyesterdayStr()+"\n");
        System.out.println(dtfwh.getDayOfWeekEreyesterday()+"\n");
        System.out.println(dtfwh.getDayOfWeekEreyesterdayStr()+"\n");
        
        System.out.println("=======================================\nYesterday\n");
        System.out.println(dtfwh.getDateYesterday()+"\n");
        System.out.println(dtfwh.getDateYesterdayStr()+"\n");
        System.out.println(dtfwh.getDayOfWeekYesterday()+"\n");
        System.out.println(dtfwh.getDayOfWeekYesterdayStr()+"\n");
        
        System.out.println("=======================================\nToday\n");
        System.out.println(dtfwh.getDateToday()+"\n");
         System.out.println(dtfwh.getDateTodayStr() +"\n");
        System.out.println(dtfwh.getDayOfWeekToday()+"\n");
        System.out.println(dtfwh.getDayOfWeekTodayStr()+"\n");
        
        System.out.println("=======================================\nYesterday\n");
        System.out.println(dtfwh.getDateYesterday()+"\n");
        System.out.println(dtfwh.getDateYesterdayStr()+"\n");
        System.out.println(dtfwh.getDayOfWeekYesterday()+"\n");
        System.out.println(dtfwh.getDayOfWeekYesterdayStr()+"\n");
        System.out.println(dtfwh.getYesterdayDateFull()+"\n");
        System.out.println(dtfwh.getYesterdayDateYYMMDD()+"\n");
        System.out.println(dtfwh.getYesterdayssDate_No_HTMSM()+"\n");
        
        System.out.println("=======================================\nToday\n");
        System.out.println(dtfwh.getDateToday()+"\n");
         System.out.println(dtfwh.getDateTodayStr() +"\n");
        System.out.println(dtfwh.getDayOfWeekToday()+"\n");
        System.out.println(dtfwh.getDayOfWeekTodayStr()+"\n");
        System.out.println(dtfwh.getTodaysDateFull()+"\n");
        System.out.println(dtfwh.getTodaysDateYYMMDD()+"\n");
        System.out.println(dtfwh.getTodaysDate_No_HTMSM()+"\n");
        
        
         System.out.println("=======================================\nTomorrow\n");
        System.out.println(dtfwh.getTomorrowDateFull()+"\n");
         System.out.println(dtfwh.getTomorrowDateYYMMDD() +"\n");
        System.out.println(dtfwh.getTomorrowsDate_No_HTMSM()+"\n");
        
        
        System.out.println("=======================================\nThursday");
        System.out.println(dtfwh.getThursdayDateFull()+"\n");
        System.out.println(dtfwh.getThursdayDateYYMMDD()+"\n");
        
        System.out.println("=======================================\nFriday\n");
        System.out.println(dtfwh.getFridayDateFull()+"\n");
        System.out.println(dtfwh.getFridayDateYYMMDD()+"\n");
        
        
        
        System.out.println("=======================================\nFriday\n");
        System.out.println(dtfwh.getSaturdayDateFull()+"\n");
        System.out.println(dtfwh.getSaturdayDateYYMMDD()+"\n");
        
        
        System.out.println("=======================================\nCalendar\n");
        System.out.println("DAY_OF_WEEK " + Calendar.DAY_OF_WEEK + "\n");
        System.out.println("DAY_OF_WEEK_IN_MONTH " + Calendar.DAY_OF_WEEK_IN_MONTH + "\n");
        System.out.println("DAY_OF_MONTH " + Calendar.DAY_OF_MONTH + "\n");
        System.out.println("DAY_OF_YEAR " + Calendar.DAY_OF_YEAR + "\n");
        System.out.println("DATE " + Calendar.DATE + "\n");
        System.out.println(Calendar.getInstance().getTime()+"\n");
        
//        siteServiceLogService.runLogTodaysSiteServices();
    }
}
