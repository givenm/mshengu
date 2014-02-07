/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.OperatingCostFacade;
import zm.hashcode.mshengu.app.facade.procurement.AnnualDataFleetMaintenanceMileageFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util.TrackerUtil;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;

/**
 *
 * @author Colin
 */
public class MileageUtil implements Serializable {

    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private final TrackerUtil trackerUtil = new TrackerUtil();
    private final List<AnnualDataFleetMaintenanceMileage> annualDataFleetMaintenanceMileageList = new ArrayList<>();
    private final Date liveDataStartDate = resetMonthToFirstDay(dateTimeFormatHelper.getDate(1, 0, 2014)); // 1, 0, 2014    Test Live ON LOCAL: 1, 11, 2013 // 10 = Nov, 11 = Dec, 0 = Jan
    // from December 1st 2013, MaintenanceCost is collected from persisted life data captured from UI (Procurement > Request)
    private final Date staticDataStartDate = resetMonthToFirstDay(dateTimeFormatHelper.getDate(1, 7, 2012));
    private final Date staticDataEndDate = this.resetMonthToLastDay(dateTimeFormatHelper.getDate(31, 11, 2013)); // 31, 11, 2013    Test Live ON LOCAL: 31, 10, 2013   // 10 = Nov, 11 = Dec
    private static List<Truck> serviceTrucks;

    public Date calendarOneMonthBackward(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    public List<AnnualDataFleetMaintenanceMileage> findMaintenanceMileageBetweenTwoDates(Date startDate, Date endDate, List<Truck> serviceTrucks) {
//        System.out.println("STATIC DATA StartDate: " + staticDataStartDate + " | STATIC DATA endDate: " + staticDataEndDate);
//        System.out.println("LIVE DATA StartDate: " + liveDataStartDate);
//        System.out.println("YOUR SEARCH StartDate: " + startDate + " | YOUR endDate: " + endDate);
        this.serviceTrucks = serviceTrucks;
        annualDataFleetMaintenanceMileageList.clear();
        if (endDate.before(staticDataEndDate) || endDate.compareTo(staticDataEndDate) == 0) {
            return AnnualDataFleetMaintenanceMileageFacade.getAnnualDataFleetMaintenanceMileageService().getAnnualDataMileageBetweenTwoDates(startDate, endDate);
        } else {
            if (startDate.before(staticDataEndDate) || startDate.compareTo(staticDataEndDate) == 0) {
                performStaticAndLiveDataHarvesting(startDate, endDate);
            } else {
                performLiveDataHarvesting(startDate, endDate);
            }
        }
        return annualDataFleetMaintenanceMileageList;
    }

    private void performStaticAndLiveDataHarvesting(Date startDate, Date endDate) {
        // get MaintenanceMileage static Data
        annualDataFleetMaintenanceMileageList.addAll(AnnualDataFleetMaintenanceMileageFacade.getAnnualDataFleetMaintenanceMileageService().getAnnualDataMileageBetweenTwoDates(startDate, staticDataEndDate));
        // get MaintenanceMileage live data from Dec 1st 2013 till EndDate from OperatingCost (DailyInputs) domain
        // Aggregate the request list and Add to MaintenanceMileageList (i.e. Monthly summary per Truck throughout the date range)
        Integer counter = new Integer("0");
        // Date Loop OR Calendar Loop from startDate till endDate
        Calendar startCalendar = Calendar.getInstance();
        for (startCalendar.setTime(liveDataStartDate); startCalendar.getTime().before(endDate) || startCalendar.getTime().compareTo(endDate) == 0; startCalendar.add(Calendar.MONTH, 1)) {
            for (Truck truck : serviceTrucks) {
                // get Daily Inputs for (NB)previous and current month so that (NB) previousMonthClosing mileage can be gotten
                List<OperatingCost> truckOperatingCostList = OperatingCostFacade.getOperatingCostService().getOperatingCostByTruckBetweenTwoDates(truck, calendarOneMonthBackward(startCalendar.getTime()), dateTimeFormatHelper.resetTimeAndMonthEnd(startCalendar.getTime()));
                Integer truckClosingMileage = new Integer("0");
                if (truckOperatingCostList.size() > 0) {
                    // Calculate the Mileage for current Truck for current Month // These three steps must be considered
                    trackerUtil.setOperatingCostList(truckOperatingCostList);
                    trackerUtil.setQueriedDate(startCalendar.getTime());
                    truckClosingMileage = trackerUtil.doMileageCalculation(truckOperatingCostList, truck);
                    // Build the AnnualDataFleetMaintenanceMileage for current Truck for current Month
                    annualDataFleetMaintenanceMileageList.add(buildAnnualDataFleetMaintenanceMileageList(counter, truck, startCalendar, truckClosingMileage));
                } else {
                    // Build ZERO Entry AnnualDataFleetMaintenanceMileage for current Truck for current Month
                    annualDataFleetMaintenanceMileageList.add(buildAnnualDataFleetMaintenanceMileageList(counter, truck, startCalendar, 0));
                    System.out.println("No Entry For Daily Input(Operating Cost) From live Data for this Month: " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(startCalendar.getTime().toString()) + " | Truck= " + truck.getVehicleNumber());
                }
            }
        }
    }

    private void performLiveDataHarvesting(Date startDate, Date endDate) {
        // Aggregate the request list and Add to MaintenanceMileageList (i.e. Monthly summary per Truck throughout the date range)
        Integer counter = new Integer("0");
        // Date Loop OR Calendar Loop from startDate till endDate
        Calendar startCalendar = Calendar.getInstance();
        for (startCalendar.setTime(startDate); startCalendar.getTime().before(endDate) || startCalendar.getTime().compareTo(endDate) == 0; startCalendar.add(Calendar.MONTH, 1)) {
            for (Truck truck : serviceTrucks) {
                // get Daily Inputs for previous and current month so that previousMonthClosing mileage can be gotten
                List<OperatingCost> truckOperatingCostList = OperatingCostFacade.getOperatingCostService().getOperatingCostByTruckBetweenTwoDates(truck, calendarOneMonthBackward(startCalendar.getTime()), dateTimeFormatHelper.resetTimeAndMonthEnd(startCalendar.getTime()));
                Integer truckClosingMileage = new Integer("0");

                if (truckOperatingCostList.size() > 0) {
                    // Calculate the Mileage for current Truck for current Month // These three steps must be considered
                    trackerUtil.setOperatingCostList(truckOperatingCostList);
                    trackerUtil.setQueriedDate(startCalendar.getTime());
                    truckClosingMileage = trackerUtil.doMileageCalculation(truckOperatingCostList, truck);
                    // Build the AnnualDataFleetMaintenanceMileage for current Truck for current Month
                    annualDataFleetMaintenanceMileageList.add(buildAnnualDataFleetMaintenanceMileageList(counter, truck, startCalendar, truckClosingMileage));
                } else {
                    // Build the AnnualDataFleetMaintenanceMileage for current Truck for current Month
                    annualDataFleetMaintenanceMileageList.add(buildAnnualDataFleetMaintenanceMileageList(counter, truck, startCalendar, 0));
                    System.out.println("No Entry For Daily Input(Operating Cost) From live Data for this Month: " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(startCalendar.getTime().toString()) + " | Truck= " + truck.getVehicleNumber());
                }
            }
        }
    }

    private AnnualDataFleetMaintenanceMileage buildAnnualDataFleetMaintenanceMileageList(int counter, Truck truck, Calendar startCalendar, int monthlyMileage) {
        // Build the AnnualDataFleetMaintenanceMileage for current Truck for current Month
        counter++;
        final AnnualDataFleetMaintenanceMileage annualDataFleetMaintenanceMileage = new AnnualDataFleetMaintenanceMileage.Builder(startCalendar.getTime())
                .driverPersonId(truck.getDriver().getId())
                .id(counter + "")
                .monthlyMileage(monthlyMileage)
                .truckId(truck.getId())
                .build();

        return annualDataFleetMaintenanceMileage;
    }

    public Date resetMonthToFirstDay(Date date) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(dateTimeFormatHelper.resetTimeAndMonthStart(date));
        calendarDate.set(Calendar.DAY_OF_MONTH, 1); // ! reset to 1ST of Month

        return calendarDate.getTime();
    }

    public Date resetMonthToLastDay(Date date) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(date));
        calendarDate.set(Calendar.DAY_OF_MONTH, calendarDate.getActualMaximum(Calendar.DAY_OF_MONTH)); // ! reset to LAST of Month (28,29,30,31)

        return calendarDate.getTime();
    }
}
