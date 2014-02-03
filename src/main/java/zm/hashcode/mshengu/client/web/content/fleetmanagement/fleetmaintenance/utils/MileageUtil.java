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
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.facade.procurement.AnnualDataFleetMaintenanceMileageFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util.TrackerUtil;
import static zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils.FleetMaintenanceUtil.serviceTrucks;
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
    private final Date liveDataBeginDate = resetMonthToFirstDay(dateTimeFormatHelper.getDate(1, 0, 2014)); // 1, 0, 2014    Test Live: 1, 11, 2013 // 10 = Nov, 11 = Dec, 0 = Jan
    // from December 1st 2013, MaintenanceCost is collected from persisted life data captured from UI (Procurement > Request)
    private final Date staticDataEndDate = this.resetMonthToFirstDay(dateTimeFormatHelper.getDate(31, 11, 2013)); // 31, 11, 2013    Test Live: 31, 10, 2013   // 10 = Nov, 11 = Dec

    public List<AnnualDataFleetMaintenanceMileage> findMaintenanceMileageBetweenTwoDates(Date startDate, Date endDate) {
        Date liveDataEndDate = this.resetMonthToLastDay(endDate);
        Date newEndDate = resetMonthToLastDay(endDate);

        // if endDate is before Dec 1st, 2013, ELSE Collect live data
        if (newEndDate.before(staticDataEndDate) || newEndDate.equals(staticDataEndDate)) {
            return AnnualDataFleetMaintenanceMileageFacade.getAnnualDataFleetMaintenanceMileageService().getMonthlyMileageCostBtnTwoDates(startDate, endDate);
        } else {
            if (startDate.before(staticDataEndDate)) {
                // get MaintenanceMileage static Data
                annualDataFleetMaintenanceMileageList.addAll(AnnualDataFleetMaintenanceMileageFacade.getAnnualDataFleetMaintenanceMileageService().getMonthlyMileageCostBtnTwoDates(startDate, staticDataEndDate));

                // get MaintenanceMileage live data from Dec 1st 2013 till EndDate from OperatingCost (DailyInputs) domain
                List<OperatingCost> operatingCostList = OperatingCostFacade.getOperatingCostService().getOperatingCostBtnTwoDates(liveDataBeginDate, liveDataEndDate);
                // Aggregate the request list and Add to MaintenanceMileageList (i.e. Monthly summary per Truck throughout the date range)

                if (operatingCostList.size() > 0) {
                    serviceTrucks = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
                    Integer counter = new Integer("0");
                    // Date Loop OR Calendar Loop from startDate till endDate
                    Calendar startCalendar = Calendar.getInstance();
                    for (startCalendar.setTime(liveDataBeginDate); startCalendar.getTime().before(liveDataEndDate); startCalendar.add(Calendar.MONTH, 1)) {
                        Calendar endOfMonth = Calendar.getInstance();
                        endOfMonth.setTime(this.resetMonthToLastDay(startCalendar.getTime()));
                        for (Truck truck : serviceTrucks) {

                            List<OperatingCost> truckOperatingCostList = OperatingCostFacade.getOperatingCostService().getOperatingCostByTruckByMonth(truck, startCalendar.getTime());
                            Integer truckClosingMileage = new Integer("0");
                            if (truckOperatingCostList.size() > 0) {

//                                for (OperatingCost operatingCost : truckOperatingCostList) {
//                                    System.out.println(dateTimeFormatHelper.getMonthYearMonthAsMediumString(operatingCost.getTransactionDate().toString()) + " | " + truck.getId() + ", " + operatingCost.getDriverName() + ", Mileage: " + operatingCost.getSpeedometer() + ", Fuel Cost: " + operatingCost.getFuelCost() + ", Litres: " + operatingCost.getFuelLitres() + ", RealDate: " + operatingCost.getTransactionDate());
//                                }
//                                System.out.println("======================================================");


                                // Calculate the Mileage for current Truck for current Month
                                // These three steps must be considered
                                trackerUtil.setOperatingCostList(truck.getOperatingCosts());
                                trackerUtil.setQueriedDate(startCalendar.getTime());
                                truckClosingMileage = trackerUtil.doMileageCalculation(truckOperatingCostList, truck);

                                // Build the AnnualDataFleetMaintenanceMileage for current Truck for current Month
                                counter++;
                                final AnnualDataFleetMaintenanceMileage annualDataFleetMaintenanceMileage = new AnnualDataFleetMaintenanceMileage.Builder(startCalendar.getTime())
                                        .driverPersonId(truck.getDriver().getId())
                                        .id(counter + "")
                                        .monthlyMileage(truckClosingMileage)
                                        .truckId(truck.getId())
                                        .build();

                                // ADD to maintenanceCostList List
                                annualDataFleetMaintenanceMileageList.add(annualDataFleetMaintenanceMileage);
                            }
                        }
                    }
                }
            } else {
                // get all MaintenanceCost from StartDate till EndDate from Request domain (i.e. live data)
                // get MaintenanceMileage live data from Dec 1st 2013 till EndDate from OperatingCost (DailyInputs) domain
                List<OperatingCost> operatingCostList = OperatingCostFacade.getOperatingCostService().getOperatingCostBtnTwoDates(liveDataBeginDate, liveDataEndDate);
                // Aggregate the request list and Add to MaintenanceMileageList (i.e. Monthly summary per Truck throughout the date range)

                if (operatingCostList.size() > 0) {
                    serviceTrucks = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
                    Integer counter = new Integer("0");
                    // Date Loop OR Calendar Loop from startDate till endDate
                    Calendar startCalendar = Calendar.getInstance();
                    for (startCalendar.setTime(this.resetMonthToFirstDay(liveDataBeginDate)); startCalendar.getTime().before(liveDataEndDate); startCalendar.add(Calendar.MONTH, 1)) {
                        Calendar endOfMonth = Calendar.getInstance();
                        endOfMonth.setTime(this.resetMonthToLastDay(startCalendar.getTime()));

                        for (Truck truck : serviceTrucks) {
                            List<OperatingCost> truckOperatingCostList = OperatingCostFacade.getOperatingCostService().getOperatingCostByTruckByMonth(truck, startCalendar.getTime());
                            Integer truckClosingMileage = new Integer("0");

                            if (truckOperatingCostList.size() > 0) {

                                //                                for (OperatingCost operatingCost : truckOperatingCostList) {
//                                    System.out.println(dateTimeFormatHelper.getMonthYearMonthAsMediumString(operatingCost.getTransactionDate().toString()) + " | " + truck.getId() + ", " + operatingCost.getDriverName() + ", Mileage: " + operatingCost.getSpeedometer() + ", Fuel Cost: " + operatingCost.getFuelCost() + ", Litres: " + operatingCost.getFuelLitres());
//                                }
//                                System.out.println("======================================================");



                                // Calculate the Mileage for current Truck for current Month
                                // These three steps must be considered
                                trackerUtil.setOperatingCostList(truck.getOperatingCosts());
                                trackerUtil.setQueriedDate(startCalendar.getTime());
                                truckClosingMileage = trackerUtil.doMileageCalculation(truckOperatingCostList, truck);

                                // Build the AnnualDataFleetMaintenanceMileage for current Truck for current Month
                                counter++;
                                final AnnualDataFleetMaintenanceMileage annualDataFleetMaintenanceMileage = new AnnualDataFleetMaintenanceMileage.Builder(startCalendar.getTime())
                                        .driverPersonId(truck.getDriver().getId())
                                        .id(counter + "")
                                        .monthlyMileage(truckClosingMileage)
                                        .truckId(truck.getId())
                                        .build();

                                // ADD to maintenanceCostList List
                                annualDataFleetMaintenanceMileageList.add(annualDataFleetMaintenanceMileage);
                            }
                        }
                    }
                }
            }
        }
        return annualDataFleetMaintenanceMileageList;
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
