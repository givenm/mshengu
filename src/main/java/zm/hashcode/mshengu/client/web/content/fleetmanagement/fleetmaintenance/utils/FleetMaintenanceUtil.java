/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util.TrackerUtil;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.MonthlyMileageData;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.MonthlySpendData;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceMileage;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendByVehicle;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendKmTraveled;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendMonthly;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;

/**
 *
 * @author ColinWa
 */
public class FleetMaintenanceUtil implements Serializable {

    private DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private TrackerUtil trackerUtil = new TrackerUtil();
    private static Date startDate = null;
    private static Date endDate = null;
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
        FleetMaintenanceUtil.setEndDate(resetMonthToLastDay(calendarEndDate.getTime()));

        calendarStartDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(endDate));
        calendarStartDate.add(Calendar.MONTH, -dateRange);
        setStartDate(resetMonthToFirstDay(calendarStartDate.getTime()));
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
        MaintenanceCostUtil maintenanceCostUtil = new MaintenanceCostUtil();
        return maintenanceCostUtil.findMaintenanceCostBetweenTwoDates(startDate, endDate);
    }

    public List<AnnualDataFleetMaintenanceMileage> findMaintenanceMileageBetweenTwoDates(Date startDate, Date endDate) {
        MileageUtil mileageUtil = new MileageUtil();
        return mileageUtil.findMaintenanceMileageBetweenTwoDates(startDate, endDate);
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
                    System.out.println("Monthy: " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(annualDataFleetMaintenanceCost.getTransactionMonth() + "") + ", Total: " + annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
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
            total = BigDecimal.ZERO;
        }
        Collections.sort(maintenanceSpendByVehicleList); /// Sort by Total Ascending for CHARTS
        return maintenanceSpendByVehicleList;
    }

    public List<TotalMaintenanceMileage> buildTotalMaintenanceMileageList(List<AnnualDataFleetMaintenanceMileage> annualDataFleetMaintenanceMileageList, Date startDate, Integer monthCount) {
        Collections.sort(annualDataFleetMaintenanceMileageList); // Sort by TruckId. Later use this id to get Number Plate
        Integer total = 0;
        String truckId = null;
        String numberPlate = null;
        int counter = 0;

        List<TotalMaintenanceMileage> totalMaintenanceMileageList = new ArrayList<>();

        List<Truck> allTrucks = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
        for (Truck truckk : allTrucks) {
            truckId = truckk.getId();
            numberPlate = truckk.getNumberPlate();

            for (AnnualDataFleetMaintenanceMileage annualDataFleetMaintenanceMileage : annualDataFleetMaintenanceMileageList) {
                if (annualDataFleetMaintenanceMileage.getTruckId().equals(truckId)) {
                    total += annualDataFleetMaintenanceMileage.getMonthlyMileage();
                }
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

    public List<MonthlySpendData> buildAnnualDataMaintenanceCost(List<AnnualDataFleetMaintenanceCost> annualDataFleetMaintenanceCostList) {
        BigDecimal truckMonthlyTotal = BigDecimal.ZERO;
        String month = null;
        int iD = 1;
        List<MonthlySpendData> monthlySpendDataList = new ArrayList<>();

        Collections.sort(annualDataFleetMaintenanceCostList, AnnualDataFleetMaintenanceCost.AscOrderTruckAscOrderDateComparator);
        //
        Truck truck = TruckFacade.getTruckService().findById(annualDataFleetMaintenanceCostList.get(0).getTruckId());
        String vehicleNumber = truck.getVehicleNumber();
        Date transactionDate = this.resetMonthToFirstDay(annualDataFleetMaintenanceCostList.get(0).getTransactionMonth());
        String latestTruckId = annualDataFleetMaintenanceCostList.get(0).getTruckId();

        for (AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost : annualDataFleetMaintenanceCostList) {
            if (this.resetMonthToFirstDay(annualDataFleetMaintenanceCost.getTransactionMonth()).compareTo(transactionDate) == 0) {
                Truck truck2 = TruckFacade.getTruckService().findById(annualDataFleetMaintenanceCost.getTruckId());
                if (!vehicleNumber.equals(truck2.getVehicleNumber())) { // TRUCK change
                    // SUBTOTAL FOR LAST MONTH FOR PREVIOUS TRUCK
                    month = dateTimeFormatHelper.getMonthYearMonthAsMediumString(transactionDate.toString());
                    monthlySpendDataList.add(createTotalMonthlySpendList(truckMonthlyTotal, month, transactionDate, latestTruckId, iD));
                    iD++;

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
        return monthlySpendDataList;
    }

    public List<MonthlyMileageData> buildAnnualDataMileage(List<AnnualDataFleetMaintenanceMileage> annualDataFleetMaintenanceMileageList) {
        Integer truckMonthlyMileageTotal = Integer.parseInt("0");
        String month = null;
        int iD = 1;
        List<MonthlyMileageData> monthlyMileageDataList = new ArrayList<>();

        Collections.sort(annualDataFleetMaintenanceMileageList, AnnualDataFleetMaintenanceMileage.AscOrderTruckAscOrderDateComparator);
        //
        Truck truck = TruckFacade.getTruckService().findById(annualDataFleetMaintenanceMileageList.get(0).getTruckId());
        String vehicleNumber = truck.getVehicleNumber();
        Date transactionDate = this.resetMonthToFirstDay(annualDataFleetMaintenanceMileageList.get(0).getTransactionMonth());
        String latestTruckId = annualDataFleetMaintenanceMileageList.get(0).getTruckId();

        for (AnnualDataFleetMaintenanceMileage annualDataFleetMaintenanceMileage : annualDataFleetMaintenanceMileageList) {
            if (this.resetMonthToFirstDay(annualDataFleetMaintenanceMileage.getTransactionMonth()).compareTo(transactionDate) == 0) {
                Truck truck2 = TruckFacade.getTruckService().findById(annualDataFleetMaintenanceMileage.getTruckId());
                if (!vehicleNumber.equals(truck2.getVehicleNumber())) { // TRUCK change
                    // SUBTOTAL FOR LAST MONTH FOR PREVIOUS TRUCK
                    month = dateTimeFormatHelper.getMonthYearMonthAsMediumString(transactionDate.toString());
                    monthlyMileageDataList.add(createMonthlyMileageList(truckMonthlyMileageTotal, month, transactionDate, latestTruckId, iD));
                    iD++;

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
                    // resets
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
     * @return the startDate
     */
    public static Date getStartDate() {
        return startDate;
    }

    /**
     * @param aStartDate the startDate to set
     */
    public static void setStartDate(Date aStartDate) {
        startDate = aStartDate;
    }

    /**
     * @return the endDate
     */
    public static Date getEndDate() {
        return endDate;
    }

    /**
     * @param aEndDate the endDate to set
     */
    public static void setEndDate(Date aEndDate) {
        endDate = aEndDate;
    }
}
