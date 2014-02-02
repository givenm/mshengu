/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.OperatingCostFacade;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.facade.procurement.AnnualDataFleetMaintenanceCostFacade;
import zm.hashcode.mshengu.app.facade.procurement.AnnualDataFleetMaintenanceMileageFacade;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util.TrackerUtil;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.MonthlyMileageData;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.MonthlySpendData;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceMileage;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendByVehicle;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendKmTraveled;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendMonthly;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TruckAnnualDataSpend;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author ColinWa
 */
public class FleetMaintenanceUtil implements Serializable {

    private DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private TrackerUtil trackerUtil = new TrackerUtil();
    public static Date startDate = null;
    public static Date endDate = null;
    public static BigDecimal grandTotalMaintenanceSpend = BigDecimal.ZERO;
    public static BigDecimal grandTotalMonthlySpend = BigDecimal.ZERO;
    public static List<Truck> serviceTrucks;

    public FleetMaintenanceUtil() {
    }

    /**
     * pass the current date and the amount of months to be calculated backward
     * and the start date and end date will be set
     *
     * @param endDate Date
     * @param dateRange int
     *
     */
    public void determineDateRange(Date endDate, int dateRange) {
        // Range is calculated as ending with Month before current month,
        // and counting into the past number of months specified in dateRange

        Calendar calendarEndDate = Calendar.getInstance();
        Calendar calendarStartDate = Calendar.getInstance();

        calendarEndDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(endDate));
        calendarEndDate.add(Calendar.MONTH, -1);
        FleetMaintenanceUtil.endDate = resetMonthToLastDay(calendarEndDate.getTime());

        calendarStartDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(endDate));
        calendarStartDate.add(Calendar.MONTH, -dateRange);
        startDate = resetMonthToFirstDay(calendarStartDate.getTime());
    }

    /**
     * Retrieves a List of AnnualDataFleetMaintenanceCost between a specified
     * start date and and end date range
     *
     * @param startDate Date
     * @param endDate Date
     * @return List AnnualDataFleetMaintenanceCost
     */
    public List<AnnualDataFleetMaintenanceCost> findMaintenanceCostBetweenTwoDates(Date startDate, Date endDate) {
        List<AnnualDataFleetMaintenanceCost> maintenanceCostList = new ArrayList<>();
        Date liveDataBeginDate = resetMonthToFirstDay(dateTimeFormatHelper.getDate(1, 0, 2014)); // 1, 0, 2014    Test Live: 1, 11, 2013 // 10 = Nov, 11 = Dec, 0 = Jan
        Date liveDataEndDate = this.resetMonthToLastDay(endDate);
        //  Calendar calendarEndDate = Calendar.getInstance();
        //  calendarEndDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(endDate));
        Date newEndDate = resetMonthToLastDay(endDate);
        // from December 1st 2013, MaintenanceCost is collected from persisted life data captured from UI (Procurement > Request)
        Date staticDataEndDate = this.resetMonthToFirstDay(dateTimeFormatHelper.getDate(31, 11, 2013)); // 31, 11, 2013    Test Live: 31, 10, 2013   // 10 = Nov, 11 = Dec

        // if endDate is before Dec 1st, 2013, ELSE Collect live data
        if (newEndDate.before(staticDataEndDate) || newEndDate.equals(staticDataEndDate)) {
            return AnnualDataFleetMaintenanceCostFacade.getAnnualDataFleetMaintenanceCostService().getMonthlyMaintenanceCostBtnTwoDates(startDate, endDate);
        } else {
            if (startDate.before(staticDataEndDate)) {

                // get MaintenanceCost static Data
                maintenanceCostList.addAll(AnnualDataFleetMaintenanceCostFacade.getAnnualDataFleetMaintenanceCostService().getMonthlyMaintenanceCostBtnTwoDates(startDate, staticDataEndDate));

                // get MaintenanceCost live data from Dec 1st 2013 till EndDate from Request domain
                List<Request> requestList = RequestFacade.getRequestService().getTransactedRequestsBtnTwoDates(liveDataBeginDate, liveDataEndDate);
                // Aggregate the request list and Add to maintenanceCostList (i.e. Monthly summary per Truck throughout the date range)
                if (requestList.size() > 0) {
                    serviceTrucks = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
                    Integer counter = new Integer("0");
                    // Date Loop OR Calendar Loop from startDate till endDate
                    Calendar startCalendar = Calendar.getInstance();
                    for (startCalendar.setTime(liveDataBeginDate); startCalendar.getTime().before(liveDataEndDate); startCalendar.add(Calendar.MONTH, 1)) {
                        Calendar endOfMonth = Calendar.getInstance();
                        endOfMonth.setTime(this.resetMonthToLastDay(startCalendar.getTime()));
                        for (Truck truck : serviceTrucks) {
                            BigDecimal accumulatedTotal = BigDecimal.ZERO;
                            for (Request request : requestList) {
                                if (truck.getId().equals(request.getTruck().getId())
                                        && (request.getDeliveryDate().compareTo(startCalendar.getTime()) == 0
                                        || request.getDeliveryDate().compareTo(endOfMonth.getTime()) == 0
                                        || (request.getDeliveryDate().after(startCalendar.getTime()) && request.getDeliveryDate().before(endOfMonth.getTime())))) {
                                    // Accumulate the Total for each Request for current Truck for current Month
                                    accumulatedTotal = accumulatedTotal.add(request.getTotal());
                                }
                            }
                            // Build the AnnualDataFleetMaintenanceCost for current Truck for current Month
                            counter++;
                            final AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost = new AnnualDataFleetMaintenanceCost.Builder(startCalendar.getTime())
                                    .driverPersonId(truck.getDriver().getId())
                                    .id(counter + "")
                                    .monthlyMaintenanceCost(accumulatedTotal)
                                    .truckId(truck.getId())
                                    .build();

                            // ADD to maintenanceCostList List
                            maintenanceCostList.add(annualDataFleetMaintenanceCost);
                        }
                    }
                }
            } else {
                // get all MaintenanceCost from StartDate till EndDate from Request domain (i.e. live data)
                // Aggregate the request list and Add to maintenanceCostList  (i.e. Monthly summary per Truck throughout the date range)
                // get MaintenanceCost live data from Dec 1st 2013 till EndDate from Request domain
                List<Request> requestList = RequestFacade.getRequestService().getTransactedRequestsBtnTwoDates(startDate, endDate);
                // Aggregate the request list and Add to maintenanceCostList (i.e. Monthly summary per Truck throughout the date range)
                if (requestList.size() > 0) {
                    serviceTrucks = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
                    Integer counter = new Integer("0");
                    // Date Loop OR Calendar Loop from startDate till endDate
                    Calendar startCalendar = Calendar.getInstance();
                    for (startCalendar.setTime(this.resetMonthToFirstDay(liveDataBeginDate)); startCalendar.getTime().before(this.resetMonthToLastDay(endDate)); startCalendar.add(Calendar.MONTH, 1)) {
                        Calendar endOfMonth = Calendar.getInstance();
                        endOfMonth.setTime(this.resetMonthToLastDay(startCalendar.getTime()));
                        for (Truck truck : serviceTrucks) {
                            BigDecimal accumulatedTotal = BigDecimal.ZERO;
                            for (Request request : requestList) {
                                if (truck.getId().equals(request.getTruck().getId())
                                        && (request.getDeliveryDate().compareTo(startCalendar.getTime()) == 0
                                        || request.getDeliveryDate().compareTo(endOfMonth.getTime()) == 0
                                        || (request.getDeliveryDate().after(startCalendar.getTime()) && request.getDeliveryDate().before(endOfMonth.getTime())))) {
                                    // Accumulate the Total for each Request for current Truck for current Month
                                    accumulatedTotal = accumulatedTotal.add(request.getTotal());
                                }
                            }
                            // Build the AnnualDataFleetMaintenanceCost for current Truck for current Month
                            counter++;
                            final AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost = new AnnualDataFleetMaintenanceCost.Builder(startCalendar.getTime())
                                    .driverPersonId(truck.getDriver().getId())
                                    .id(counter + "")
                                    .monthlyMaintenanceCost(accumulatedTotal)
                                    .truckId(truck.getId())
                                    .build();

                            // ADD to maintenanceCostList List
                            maintenanceCostList.add(annualDataFleetMaintenanceCost);
                        }
                    }
                }
            }
        }
        return maintenanceCostList;
    }

    public List<AnnualDataFleetMaintenanceMileage> findMaintenanceMileageBetweenTwoDates(Date startDate, Date endDate) {
        List<AnnualDataFleetMaintenanceMileage> annualDataFleetMaintenanceMileageList = new ArrayList<>();
        Date liveDataBeginDate = resetMonthToFirstDay(dateTimeFormatHelper.getDate(1, 0, 2014)); // 1, 0, 2014    Test Live: 1, 11, 2013 // 10 = Nov, 11 = Dec, 0 = Jan
        Date liveDataEndDate = this.resetMonthToLastDay(endDate);
        //  Calendar calendarEndDate = Calendar.getInstance();
        //  calendarEndDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(endDate));
        Date newEndDate = resetMonthToLastDay(endDate);
        // from December 1st 2013, MaintenanceCost is collected from persisted life data captured from UI (Procurement > Request)
        Date staticDataEndDate = this.resetMonthToFirstDay(dateTimeFormatHelper.getDate(31, 11, 2013)); // 31, 11, 2013    Test Live: 31, 10, 2013   // 10 = Nov, 11 = Dec

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
//                            List<OperatingCost> truckOperatingCostList = new ArrayList<>();// List for Truck for Month in LOOP
                            Integer truckClosingMileage = new Integer("0");
//

//                            String trucKiD = null;
//                            for (OperatingCost operatingCost : operatingCostList) {
//                                // Find Truck that has this operatingCosts
//                                for (Truck truckk : serviceTrucks) {
//                                    List<OperatingCost> operatingCostsList = truckk.getOperatingCosts();
//                                    for (OperatingCost operatingCostt : operatingCostsList) {
//                                        if (operatingCostt.getId().equals(operatingCost.getId())) {
//                                            trucKiD = truckk.getId();
//                                            break;
//                                        }
//                                    }
//                                }
//
//                                if (truck.getId().equals(trucKiD)
//                                        && (operatingCost.getTransactionDate().compareTo(startCalendar.getTime()) == 0
//                                        || operatingCost.getTransactionDate().compareTo(endOfMonth.getTime()) == 0
//                                        || (operatingCost.getTransactionDate().after(startCalendar.getTime()) && operatingCost.getTransactionDate().before(endOfMonth.getTime())))) {
//                                    // Add to Trucks OperatingCost List for this month
//                                    truckOperatingCostList.add(operatingCost);
//                                }
//                            }

                            if (truckOperatingCostList.size() > 0) {

//
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
//                            List<OperatingCost> truckOperatingCostList = new ArrayList<>();// List for Truck for Month in LOOP
                            Integer truckClosingMileage = new Integer("0");
//

//                            String trucKiD = null;
//                            for (OperatingCost operatingCost : operatingCostList) {
//                                // Find Truck that has this operatingCosts
//                                for (Truck truckk : serviceTrucks) {
//                                    List<OperatingCost> operatingCostsList = truckk.getOperatingCosts();
//                                    for (OperatingCost operatingCostt : operatingCostsList) {
//                                        if (operatingCostt.getId().equals(operatingCost.getId())) {
//                                            trucKiD = truckk.getId();
//                                            break;
//                                        }
//                                    }
//                                }
//
//                                if (truck.getId().equals(trucKiD)
//                                        && (operatingCost.getTransactionDate().compareTo(startCalendar.getTime()) == 0
//                                        || operatingCost.getTransactionDate().compareTo(endOfMonth.getTime()) == 0
//                                        || (operatingCost.getTransactionDate().after(startCalendar.getTime()) && operatingCost.getTransactionDate().before(endOfMonth.getTime())))) {
//                                    // Add to Trucks OperatingCost List for this month
//                                    truckOperatingCostList.add(operatingCost);
//                                }
//                            }

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

    public List<TotalMaintenanceSpendMonthly> getMaintenanceSpendMonthlyChartData(List<AnnualDataFleetMaintenanceCost> annualDataFleetMaintenanceCostList, Date startDate, int monthCount) {
        return buildMaintenanceSpendMonthlyList(annualDataFleetMaintenanceCostList, startDate, monthCount);
    }

    public List<TotalMaintenanceSpendMonthly> buildMaintenanceSpendMonthlyList(List<AnnualDataFleetMaintenanceCost> annualDataFleetMaintenanceCostList, Date startDate, int monthCount) {
        grandTotalMaintenanceSpend = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;
        String month = null;
        String truckId = null;
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        List<TotalMaintenanceSpendMonthly> maintenanceSpendMonthlyList = new ArrayList<>();

        for (int j = 1; j <= monthCount; j++) {
            for (AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost : annualDataFleetMaintenanceCostList) {
                if (dateTimeFormatHelper.resetTimeAndMonthStart(startCalendar.getTime()).equals(dateTimeFormatHelper.resetTimeAndMonthStart(annualDataFleetMaintenanceCost.getTransactionMonth()))) {
                    month = dateTimeFormatHelper.getMonthYearMonthAsMediumString(startCalendar.getTime().toString());
                    total = total.add(annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
                    truckId = annualDataFleetMaintenanceCost.getTruckId();
//
//                    // Delete ff 3 lines: Used for debuggin purpose ONLY
//                    Truck eTruck = TruckFacade.getTruckService().findById(annualDataFleetMaintenanceCost.getTruckId());
//                    Person driver = PersonFacade.getPersonService().findById(annualDataFleetMaintenanceCost.getDriverPersonId());
//                    System.out.println("Truck Num is: " + eTruck.getVehicleNumber() + ", Driver is: " + driver.getFirstname() + " " + driver.getLastname() + ", Maintenance cost is: " + annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
//                    System.out.println("Month: " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(annualDataFleetMaintenanceCost.getTransactionMonth() + "") + ", Number Plate: " + eTruck.getNumberPlate() + ", Total: " + annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
                    System.out.println("Month: " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(annualDataFleetMaintenanceCost.getTransactionMonth() + "") + ", Total: " + annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
                }
            }
            grandTotalMaintenanceSpend = grandTotalMaintenanceSpend.add(total);
            maintenanceSpendMonthlyList.add(getTotalMaintenanceSpendMonthly(total, j, month, startCalendar.getTime(), truckId));

            //increment the month by 1
            startCalendar.add(Calendar.MONTH, 1);
            total = BigDecimal.ZERO;
        }
        // Get

        Collections.sort(maintenanceSpendMonthlyList); // Sort by Date Ascending FOR CHARTS
        return maintenanceSpendMonthlyList;
    }

    public TotalMaintenanceSpendMonthly getTotalMaintenanceSpendMonthly(BigDecimal total, int j, String month, Date date, String truckId) {
        TotalMaintenanceSpendMonthly totalMaintenanceSpendMonthly = new TotalMaintenanceSpendMonthly();
        totalMaintenanceSpendMonthly.setId(j + "");
        totalMaintenanceSpendMonthly.setMonth(date);
        totalMaintenanceSpendMonthly.setMonthYear(month);
        totalMaintenanceSpendMonthly.setTotal(total);

        Truck eTruck = TruckFacade.getTruckService().findById(truckId);
        totalMaintenanceSpendMonthly.setVehicleNumber(eTruck.getVehicleNumber());
        totalMaintenanceSpendMonthly.setNumberPlate(eTruck.getNumberPlate());

        System.out.println("Id is: " + totalMaintenanceSpendMonthly.getId() + ". Month: " + totalMaintenanceSpendMonthly.getMonthYear() + ", Total: " + totalMaintenanceSpendMonthly.getTotal());
        System.out.println("Grand Total is: " + grandTotalMaintenanceSpend);

        return totalMaintenanceSpendMonthly;
    }

    public int countMonthsInRange(Date startDate, Date endDate) {
        int monthCount = 0;
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        startCalendar.setTime(dateTimeFormatHelper.resetTimeAndMonthStart(startDate));
        endCalendar.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(endDate));

        while (!startCalendar.getTime().after(endCalendar.getTime())) {
            monthCount++;
            startCalendar.add(Calendar.MONTH, 1);
        }
        return monthCount;
    }

    public List<TotalMaintenanceSpendByVehicle> getMaintenanceSpendByVehicleChartData(List<AnnualDataFleetMaintenanceCost> annualDataFleetMaintenanceCostList, Date startDate, int monthCount) {
        return buildTotalMaintenanceSpendByVehicleList(annualDataFleetMaintenanceCostList, startDate, monthCount);
    }

    public List<TotalMaintenanceMileage> getMaintenanceMileageList(List<AnnualDataFleetMaintenanceMileage> annualDataFleetMaintenanceMileageList, Date startDate, int monthCount) {
        return buildTotalMaintenanceMileageList(annualDataFleetMaintenanceMileageList, startDate, monthCount);

    }

    public List<TotalMaintenanceSpendByVehicle> buildTotalMaintenanceSpendByVehicleList(List<AnnualDataFleetMaintenanceCost> annualDataFleetMaintenanceCostList, Date startDate, int monthCount) {
        Collections.sort(annualDataFleetMaintenanceCostList); // Sort by TruckId. Later use this id to get Number Plate

        BigDecimal total = BigDecimal.ZERO;
        String truckId = null;
        Truck truck = null;
        String numberPlate = null;
        int counter = 0;

        List<TotalMaintenanceSpendByVehicle> maintenanceSpendByVehicleList = new ArrayList<>();

        List<Truck> allTrucks = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
        for (Truck truckk : allTrucks) {
//            System.out.println("Service/Utility Truck: " + truckk.getNumberPlate());

            truckId = truckk.getId();
            numberPlate = truckk.getNumberPlate();

            for (AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost : annualDataFleetMaintenanceCostList) {
                if (annualDataFleetMaintenanceCost.getTruckId().equals(truckId)) {
                    total = total.add(annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
                }
            }

//         Add to List
            counter += 1;
            TotalMaintenanceSpendByVehicle totalMaintenanceSpendByVehicle = new TotalMaintenanceSpendByVehicle();
            totalMaintenanceSpendByVehicle.setId(counter + "");
            totalMaintenanceSpendByVehicle.setNumberPlate(numberPlate);
            totalMaintenanceSpendByVehicle.setTotal(total);
            totalMaintenanceSpendByVehicle.setVehicleNumber(truckk.getVehicleNumber());
            maintenanceSpendByVehicleList.add(totalMaintenanceSpendByVehicle);
//                    System.out.println("I AM  at Counter: " + counter);
            total = BigDecimal.ZERO;
        }
        Collections.sort(maintenanceSpendByVehicleList); /// Sort by Total Ascending for CHARTS
        return maintenanceSpendByVehicleList;
    }

    public List<TotalMaintenanceMileage> buildTotalMaintenanceMileageList(List<AnnualDataFleetMaintenanceMileage> annualDataFleetMaintenanceMileageList, Date startDate, Integer monthCount) {
        Collections.sort(annualDataFleetMaintenanceMileageList); // Sort by TruckId. Later use this id to get Number Plate
        Integer total = 0;
        String truckId = null;
//        Truck truck = null;
        String numberPlate = null;
        int counter = 0;

        List<TotalMaintenanceMileage> totalMaintenanceMileageList = new ArrayList<>();

        List<Truck> allTrucks = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
        for (Truck truckk : allTrucks) {
//            System.out.println("Service/Utility Truck: " + truckk.getNumberPlate());

            truckId = truckk.getId();
            numberPlate = truckk.getNumberPlate();


            for (AnnualDataFleetMaintenanceMileage annualDataFleetMaintenanceMileage : annualDataFleetMaintenanceMileageList) {
                if (annualDataFleetMaintenanceMileage.getTruckId().equals(truckId)) {
                    total += annualDataFleetMaintenanceMileage.getMonthlyMileage();

//                // Check if the current annualDataFleetMaintenanceCost is the last in the List
//                if (annualDataFleetMaintenanceMileageList.indexOf(annualDataFleetMaintenanceMileage) == annualDataFleetMaintenanceMileageList.size() - 1) {
//                    // Add to List
//                    counter += 1;
//                    TotalMaintenanceMileage TotalMaintenanceMileage = new TotalMaintenanceMileage();
//                    TotalMaintenanceMileage.setId(counter + "");
//                    TotalMaintenanceMileage.setNumberPlate(numberPlate);
//                    TotalMaintenanceMileage.setTruckMileagetotal(total);
//                    totalMaintenanceMileageList.add(TotalMaintenanceMileage);
////                    System.out.println("I AM  at Counter: " + counter);
//                }

                }

//            else {
//                // Add to List
//                counter += 1;
//                TotalMaintenanceMileage TotalMaintenanceMileage = new TotalMaintenanceMileage();
//                TotalMaintenanceMileage.setId(counter + "");
//                TotalMaintenanceMileage.setNumberPlate(numberPlate);
//                TotalMaintenanceMileage.setTruckMileagetotal(total);
//                totalMaintenanceMileageList.add(TotalMaintenanceMileage);
////                    System.out.println("I AM  at Counter: " + counter);
//                // Reset values
//                truckId = annualDataFleetMaintenanceMileage.getTruckId();
//                truck = TruckFacade.getTruckService().findById(truckId);
//                numberPlate = truck.getNumberPlate();
//                total = annualDataFleetMaintenanceMileage.getMonthlyMileage();
//            }

            }

            counter += 1;
            TotalMaintenanceMileage TotalMaintenanceMileage = new TotalMaintenanceMileage();
            TotalMaintenanceMileage.setId(counter + "");
            TotalMaintenanceMileage.setNumberPlate(numberPlate);
            TotalMaintenanceMileage.setTruckMileagetotal(total);
            TotalMaintenanceMileage.setVehicleNumber(truckk.getVehicleNumber());
            totalMaintenanceMileageList.add(TotalMaintenanceMileage);
//                    System.out.println("I AM  at Counter: " + counter);
            total = 0;

        }
        Collections.sort(totalMaintenanceMileageList); /// Sort by Total Ascending for CHARTS
        return totalMaintenanceMileageList;
    }

    /*
     *
     * next calculate the chart data using data in spendMaintenanceMileageList and spendByVehicleChartDataList
     * Do this by Dividing SpendByVehicle (spendByVehicleChartDataList) over vehicle Mileage Total (spendMaintenanceMileageList)
     *
     * for each element in spendByVehicleChartDataList, get its "numberPlate" and its corresponding "total"
     * the search element in spendMaintenanceMileageList, for the same "numberPlate" as above and DIVIDE "total" above
     * over corresponding "truckMileagetotal"
     */
    public List<TotalMaintenanceSpendKmTraveled> getMaintenanceMileageChartData(List<TotalMaintenanceMileage> spendMaintenanceMileageList, List<TotalMaintenanceSpendByVehicle> spendByVehicleChartDataList) {
        List<TotalMaintenanceSpendKmTraveled> spendByKmTravelledChartDataList = new ArrayList<>();
        int counter = 0;

        for (TotalMaintenanceMileage totalMaintenanceMileage : spendMaintenanceMileageList) {
            String numberPlate = totalMaintenanceMileage.getNumberPlate();
            Integer truckMileageTotal = totalMaintenanceMileage.getTruckMileagetotal();
            BigDecimal randPerKilometre = BigDecimal.ZERO;
            counter += 1;
            for (TotalMaintenanceSpendByVehicle totalMaintenanceSpendByVehicle : spendByVehicleChartDataList) {
                if (totalMaintenanceSpendByVehicle.getNumberPlate().equalsIgnoreCase(numberPlate)) {
                    try {
                        randPerKilometre = totalMaintenanceSpendByVehicle.getTotal().divide(new BigDecimal(truckMileageTotal.toString()), 2, RoundingMode.HALF_UP);
                    } catch (java.lang.ArithmeticException ex) {
                        // Trying to divide by Zero for Trucks that have no entry
                    }
                    TotalMaintenanceSpendKmTraveled totalMaintenanceSpendKmTraveled = new TotalMaintenanceSpendKmTraveled();
                    totalMaintenanceSpendKmTraveled.setId(counter + "");
                    totalMaintenanceSpendKmTraveled.setNumberPlate(numberPlate);
                    totalMaintenanceSpendKmTraveled.setVehicleNumber(totalMaintenanceMileage.getVehicleNumber());
                    totalMaintenanceSpendKmTraveled.setRandPerKilometre(randPerKilometre);

                    spendByKmTravelledChartDataList.add(totalMaintenanceSpendKmTraveled);

                }
            }

        }

        Collections.sort(spendByKmTravelledChartDataList); /// Sort by Total Ascending for CHARTS
        return spendByKmTravelledChartDataList;
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

    public List<MonthlySpendData> buildTwelvethMonthMaintenanceSpend(List<AnnualDataFleetMaintenanceCost> annualDataFleetMaintenanceCostList) {
        grandTotalMonthlySpend = BigDecimal.ZERO;
        BigDecimal totalMonthlySpend = BigDecimal.ZERO;
        String month = null;
        int iD = 1;
        Calendar calendar = Calendar.getInstance();
        List<MonthlySpendData> monthlySpendDataList = new ArrayList<>();

        // Sort AnnualDataFleetMaintenanceCost List in DESC Order by Date. Extract last month data ONLY
        Collections.sort(annualDataFleetMaintenanceCostList, AnnualDataFleetMaintenanceCost.DescOrderDateAscOrderTruckComparator);

        Date latestDate = annualDataFleetMaintenanceCostList.get(0).getTransactionMonth(); // the 12th month is AT the TOP in the List
        String latestTruckId = annualDataFleetMaintenanceCostList.get(0).getTruckId();

        for (AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost : annualDataFleetMaintenanceCostList) {
            calendar.setTime(annualDataFleetMaintenanceCost.getTransactionMonth());
            if (resetMonthToLastDay(annualDataFleetMaintenanceCost.getTransactionMonth()).compareTo(resetMonthToLastDay(latestDate)) == 0) {
                if (annualDataFleetMaintenanceCost.getTruckId().equals(latestTruckId)) {
                    totalMonthlySpend = totalMonthlySpend.add(annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
                } else {
                    grandTotalMonthlySpend = grandTotalMonthlySpend.add(totalMonthlySpend);
                    month = dateTimeFormatHelper.getMonthYearMonthAsMediumString(calendar.getTime().toString());
                    monthlySpendDataList.add(createTotalMonthlySpendList(grandTotalMonthlySpend, month, calendar.getTime(), latestTruckId, iD));
                    iD++;
                    grandTotalMonthlySpend = BigDecimal.ZERO;
                    totalMonthlySpend = BigDecimal.ZERO;
                    latestTruckId = annualDataFleetMaintenanceCost.getTruckId();

                    totalMonthlySpend = totalMonthlySpend.add(annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
                }

            } else {
                break;
            }
        }

        return monthlySpendDataList;
    }

    private MonthlySpendData createTotalMonthlySpendList(BigDecimal totalMonthlySpend, String month, Date date, String truckId, int id) {
        Truck truck = TruckFacade.getTruckService().findById(truckId);
        MonthlySpendData monthlySpendData = new MonthlySpendData();
        monthlySpendData.setVehicleNumber(truck.getVehicleNumber());
        monthlySpendData.setNumberPlate(truck.getNumberPlate());
        monthlySpendData.setTransactionDate(month);
        monthlySpendData.setTransactDate(date);
        monthlySpendData.setTruckMonthlySpendTotal(totalMonthlySpend);
        monthlySpendData.setId(id + "");

        return monthlySpendData;
    }

    private TruckAnnualDataSpend createTruckAnnualDataSpendList(BigDecimal totalMonthlySpend, String month, Date date, String truckId, int id) {
        Truck truck = TruckFacade.getTruckService().findById(truckId);
        TruckAnnualDataSpend truckAnnualDataSpend = new TruckAnnualDataSpend();
        truckAnnualDataSpend.setVehicleNumber(truck.getVehicleNumber());
        truckAnnualDataSpend.setNumberPlate(truck.getNumberPlate());
        truckAnnualDataSpend.setTransactionDate(month);
        truckAnnualDataSpend.setTransactDate(date);
        truckAnnualDataSpend.setTruckAnnualSpendTotal(totalMonthlySpend);
        truckAnnualDataSpend.setId(id + "");

        return truckAnnualDataSpend;
    }

    public List<MonthlySpendData> buildAnnualDataMaintenanceCost(List<AnnualDataFleetMaintenanceCost> annualDataFleetMaintenanceCostList) {
        BigDecimal truckMonthlyTotal = BigDecimal.ZERO;
//        BigDecimal truckAnnualTotal = BigDecimal.ZERO;
        String month = null;
//        String numberPlate=null;
        int iD = 1;
//        int annualId = 1;
//        Calendar calendar = Calendar.getInstance();
        //
        List<MonthlySpendData> monthlySpendDataList = new ArrayList<>();
//        List<TruckAnnualDataSpend> truckAnnualDataSpendList = new ArrayList<>();

        // Sort AnnualDataFleetMaintenanceCost List in ASC Order by Date then in ASC Order Truck
        // SORRY. Sorted already by Services
        Collections.sort(annualDataFleetMaintenanceCostList, AnnualDataFleetMaintenanceCost.AscOrderTruckAscOrderDateComparator);
        //
        Truck truck = TruckFacade.getTruckService().findById(annualDataFleetMaintenanceCostList.get(0).getTruckId());
        String vehicleNumber = truck.getVehicleNumber();
        Date transactionDate = this.resetMonthToFirstDay(annualDataFleetMaintenanceCostList.get(0).getTransactionMonth());
        String latestTruckId = annualDataFleetMaintenanceCostList.get(0).getTruckId();

        for (AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost : annualDataFleetMaintenanceCostList) {
//            numberPlate = truck.getNumberPlate();
//            calendar.setTime(annualDataFleetMaintenanceCost.getTransactionMonth());
            if (this.resetMonthToFirstDay(annualDataFleetMaintenanceCost.getTransactionMonth()).compareTo(transactionDate) == 0) {
                Truck truck2 = TruckFacade.getTruckService().findById(annualDataFleetMaintenanceCost.getTruckId());
                if (!vehicleNumber.equals(truck2.getVehicleNumber())) { // TRUCK change
                    // SUBTOTAL FOR LAST MONTH FOR PREVIOUS TRUCK
                    month = dateTimeFormatHelper.getMonthYearMonthAsMediumString(transactionDate.toString());
                    monthlySpendDataList.add(createTotalMonthlySpendList(truckMonthlyTotal, month, transactionDate, latestTruckId, iD));
                    iD++;
                    //                    truckAnnualTotal = truckAnnualTotal.add(truckMonthlyTotal);
                    // truckAnnualDataSpendList.add(createTruckAnnualDataSpendList(truckAnnualTotal, month, calendar.getTime(), latestTruckId, annualId));
                    // annualId++;
                    // resets
//                    truckMonthlyTotal = BigDecimal.ZERO;
                    // truckAnnualTotal = BigDecimal.ZERO;
//                    truckMonthlyTotal = truckMonthlyTotal.add(annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
                    truckMonthlyTotal = annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost();
                    latestTruckId = annualDataFleetMaintenanceCost.getTruckId();
                    vehicleNumber = truck2.getVehicleNumber();
                    transactionDate = this.resetMonthToFirstDay(annualDataFleetMaintenanceCost.getTransactionMonth());

                } else {
                    // add to SUBTOTAL FOR CURRENT MONTH FOR CURRENT TRUCK
                    truckMonthlyTotal = truckMonthlyTotal.add(annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
                }
            } else { // if Month Changes
                Truck truck2 = TruckFacade.getTruckService().findById(annualDataFleetMaintenanceCost.getTruckId());
                if (!vehicleNumber.equals(truck2.getVehicleNumber())) {// Truck has Change
                    month = dateTimeFormatHelper.getMonthYearMonthAsMediumString(transactionDate.toString());
                    monthlySpendDataList.add(createTotalMonthlySpendList(truckMonthlyTotal, month, transactionDate, latestTruckId, iD));
                    iD++;

//                    truckAnnualTotal = truckAnnualTotal.add(truckMonthlyTotal);
//                    truckAnnualDataSpendList.add(createTruckAnnualDataSpendList(truckAnnualTotal, month, calendar.getTime(), latestTruckId, annualId));
//                    annualId++;
                    // resets
//                    truckMonthlyTotal = BigDecimal.ZERO;
//                    truckAnnualTotal = BigDecimal.ZERO;
                    truckMonthlyTotal = annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost();
                    latestTruckId = annualDataFleetMaintenanceCost.getTruckId();
                    vehicleNumber = truck2.getVehicleNumber();
                    transactionDate = this.resetMonthToFirstDay(annualDataFleetMaintenanceCost.getTransactionMonth());


                } else {
//                    // SUBTOTAL FOR PREVIOUS MONTH FOR CURRENT TRUCK
                    month = dateTimeFormatHelper.getMonthYearMonthAsMediumString(transactionDate.toString());
                    monthlySpendDataList.add(createTotalMonthlySpendList(truckMonthlyTotal, month, transactionDate, annualDataFleetMaintenanceCost.getTruckId(), iD));
                    iD++;
                    // resets
                    transactionDate = this.resetMonthToFirstDay(annualDataFleetMaintenanceCost.getTransactionMonth());
                    truckMonthlyTotal = annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost();
                }
            }
        }

//        // Sort AnnualDataFleetMaintenanceCost List in ASC Order by Vehicle Number ASC Order by Date.
//        Collections.sort(monthlySpendDataList, MonthlySpendData.AscOrderVehicleNumberAscOrderDateComparator);

        return monthlySpendDataList;
    }

    public List<MonthlyMileageData> buildAnnualDataMileage(List<AnnualDataFleetMaintenanceMileage> annualDataFleetMaintenanceMileageList) {
        Integer truckMonthlyMileageTotal = Integer.parseInt("0");
//        BigDecimal truckAnnualTotal = BigDecimal.ZERO;
        String month = null;
//        String numberPlate=null;
        int iD = 1;
//        int annualId = 1;
//        Calendar calendar = Calendar.getInstance();
        //
        List<MonthlyMileageData> monthlyMileageDataList = new ArrayList<>();
//        List<TruckAnnualDataSpend> truckAnnualDataSpendList = new ArrayList<>();

        // Sort AnnualDataFleetMaintenanceCost List in ASC Order by Date then in ASC Order Truck
        Collections.sort(annualDataFleetMaintenanceMileageList, AnnualDataFleetMaintenanceMileage.AscOrderTruckAscOrderDateComparator);
        //
        Truck truck = TruckFacade.getTruckService().findById(annualDataFleetMaintenanceMileageList.get(0).getTruckId());
        String vehicleNumber = truck.getVehicleNumber();
        Date transactionDate = this.resetMonthToFirstDay(annualDataFleetMaintenanceMileageList.get(0).getTransactionMonth());
        String latestTruckId = annualDataFleetMaintenanceMileageList.get(0).getTruckId();

        for (AnnualDataFleetMaintenanceMileage annualDataFleetMaintenanceMileage : annualDataFleetMaintenanceMileageList) {
//            numberPlate = truck.getNumberPlate();
//            calendar.setTime(annualDataFleetMaintenanceCost.getTransactionMonth());
            if (this.resetMonthToFirstDay(annualDataFleetMaintenanceMileage.getTransactionMonth()).compareTo(transactionDate) == 0) {
                Truck truck2 = TruckFacade.getTruckService().findById(annualDataFleetMaintenanceMileage.getTruckId());
                if (!vehicleNumber.equals(truck2.getVehicleNumber())) { // TRUCK change
                    // SUBTOTAL FOR LAST MONTH FOR PREVIOUS TRUCK
                    month = dateTimeFormatHelper.getMonthYearMonthAsMediumString(transactionDate.toString());
                    monthlyMileageDataList.add(createMonthlyMileageList(truckMonthlyMileageTotal, month, transactionDate, latestTruckId, iD));
                    iD++;
                    //                    truckAnnualTotal = truckAnnualTotal.add(truckMonthlyTotal);
                    // truckAnnualDataSpendList.add(createTruckAnnualDataSpendList(truckAnnualTotal, month, calendar.getTime(), latestTruckId, annualId));
                    // annualId++;
                    // resets
//                    truckMonthlyTotal = BigDecimal.ZERO;
                    // truckAnnualTotal = BigDecimal.ZERO;
//                    truckMonthlyTotal = truckMonthlyTotal.add(annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
                    truckMonthlyMileageTotal = annualDataFleetMaintenanceMileage.getMonthlyMileage();
                    latestTruckId = annualDataFleetMaintenanceMileage.getTruckId();
                    vehicleNumber = truck2.getVehicleNumber();
                    transactionDate = this.resetMonthToFirstDay(annualDataFleetMaintenanceMileage.getTransactionMonth());

                } else {
                    // add to SUBTOTAL FOR CURRENT MONTH FOR CURRENT TRUCK
                    truckMonthlyMileageTotal += annualDataFleetMaintenanceMileage.getMonthlyMileage();
                }
            } else { // if Month Changes
                Truck truck2 = TruckFacade.getTruckService().findById(annualDataFleetMaintenanceMileage.getTruckId());
                if (!vehicleNumber.equals(truck2.getVehicleNumber())) {// Truck has Change
                    month = dateTimeFormatHelper.getMonthYearMonthAsMediumString(transactionDate.toString());
                    monthlyMileageDataList.add(createMonthlyMileageList(truckMonthlyMileageTotal, month, transactionDate, latestTruckId, iD));
                    iD++;

//                    truckAnnualTotal = truckAnnualTotal.add(truckMonthlyTotal);
//                    truckAnnualDataSpendList.add(createTruckAnnualDataSpendList(truckAnnualTotal, month, calendar.getTime(), latestTruckId, annualId));
//                    annualId++;
                    // resets
//                    truckMonthlyMileageTotal = BigDecimal.ZERO;
//                    truckAnnualTotal = BigDecimal.ZERO;
                    truckMonthlyMileageTotal = annualDataFleetMaintenanceMileage.getMonthlyMileage();
                    latestTruckId = annualDataFleetMaintenanceMileage.getTruckId();
                    vehicleNumber = truck2.getVehicleNumber();
                    transactionDate = this.resetMonthToFirstDay(annualDataFleetMaintenanceMileage.getTransactionMonth());


                } else {
//                    // SUBTOTAL FOR PREVIOUS MONTH FOR CURRENT TRUCK
                    month = dateTimeFormatHelper.getMonthYearMonthAsMediumString(transactionDate.toString());
                    monthlyMileageDataList.add(createMonthlyMileageList(truckMonthlyMileageTotal, month, transactionDate, annualDataFleetMaintenanceMileage.getTruckId(), iD));
                    iD++;
                    // resets
                    transactionDate = this.resetMonthToFirstDay(annualDataFleetMaintenanceMileage.getTransactionMonth());
                    truckMonthlyMileageTotal = annualDataFleetMaintenanceMileage.getMonthlyMileage();
                }
            }
        }

//        // Sort AnnualDataFleetMaintenanceCost List in ASC Order by Vehicle Number ASC Order by Date.
//        Collections.sort(monthlyMileageDataList, MonthlyMileageData.AscOrderVehicleNumberAscOrderDateComparator);

        return monthlyMileageDataList;
    }

    private MonthlyMileageData createMonthlyMileageList(Integer monthlyMileageTotal, String month, Date date, String truckId, int id) {
        Truck truck = TruckFacade.getTruckService().findById(truckId);
        MonthlyMileageData monthlyMileageData = new MonthlyMileageData();
        monthlyMileageData.setVehicleNumber(truck.getVehicleNumber());
        monthlyMileageData.setNumberPlate(truck.getNumberPlate());
        monthlyMileageData.setTransactionDate(month);
        monthlyMileageData.setTransactDate(date);
        monthlyMileageData.setTruckMonthlyMileageTotal(monthlyMileageTotal);
        monthlyMileageData.setId(id + "");

        return monthlyMileageData;
    }

    /**
     * Determines Flagging based on parameter and return an image (a flag) with
     * the appropriate color
     *
     * @param value BigDecimal
     * @return Image
     */
    public Image determineImageFlag(BigDecimal value) {
        // Image as a tHEME Resource
        Image greenImage = new Image("", new ThemeResource("images/green_flag.png"));
        Image redImage = new Image("", new ThemeResource("images/red_flag.png"));
        Image yellowImage = new Image("", new ThemeResource("images/yellow_flag.png"));
        Double val = value.doubleValue();

        if (val.compareTo(new Double("0.0")) == 0) {
            return new Image();
        }
        if (val.compareTo(new Double("5.0")) > 0) {
            return redImage;
        } else if (val.compareTo(new Double("3.5")) > 0) {
            return yellowImage;
        }

        return greenImage;
    }

    /**
     * Determines Vehicle Ranking Flagging based on parameter and return an
     * image (a flag) with the appropriate color
     *
     * @param value BigDecimal
     * @return Image
     */
    public Image determineVehicleRankingFlag(BigDecimal value) {
        // Image as a tHEME Resource
        Image greenImage = new Image("", new ThemeResource("images/green_flag.png"));
        Image redImage = new Image("", new ThemeResource("images/red_flag.png"));
        Image yellowImage = new Image("", new ThemeResource("images/yellow_flag.png"));
        Double val = value.doubleValue();

        if (val.compareTo(new Double("0.0")) == 0) {
            return new Image();
        }

        if (val.compareTo(new Double("5.0")) > 0 || val.compareTo(new Double("5.0")) == 0) {
            return redImage;
        } else if (val.compareTo(new Double("3.5")) > 0 || val.compareTo(new Double("3.5")) == 0) {
            return yellowImage;
        }

        return greenImage;
    }

    /**
     * Determines Flagging based on parameter and return an Embedded type (a
     * flag) with the appropriate color
     *
     * @param value BigDecimal
     * @return Embedded
     */
    public Embedded determineFlag(BigDecimal value) {
        // Image as a tHEME Resource
        Embedded greenImage = new Embedded("", new ThemeResource("images/green_flag.png"));
        Embedded redImage = new Embedded("", new ThemeResource("images/red_flag.png"));
        Embedded yellowImage = new Embedded("", new ThemeResource("images/yellow_flag.png"));
        Double val = value.doubleValue();

        if (val.compareTo(new Double("0.0")) == 0) {
            return new Embedded();
        }

        if (val.compareTo(new Double("5.0")) > 0 || val.compareTo(new Double("5.0")) == 0) {
            return redImage;
        } else if (val.compareTo(new Double("3.5")) > 0 || val.compareTo(new Double("3.5")) == 0) {
            return yellowImage;
        }
        if (val.compareTo(new Double("0.0")) == 0) {
            return new Embedded();
        }
        return greenImage;
    }
}
