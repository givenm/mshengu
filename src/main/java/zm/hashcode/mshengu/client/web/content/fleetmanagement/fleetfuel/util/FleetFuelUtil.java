/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.util;

import com.vaadin.ui.Notification;
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
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Colin
 */
public class FleetFuelUtil implements Serializable {

    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    public static Date startDate = new Date();
    public static Date endDate = new Date();
    public static List<Truck> allTrucks = new ArrayList<>();
    public static List<Truck> serviceTrucks = new ArrayList<>();
//    public static List<Truck> movTrucks = new ArrayList<>();
//    public static List<Truck> mmvTrucks = new ArrayList<>();
    public static List<Truck> msvTrucks = new ArrayList<>();
    public static List<OperatingCost> operatingCostList = new ArrayList<>();

    public void getTrucks() {
        if (allTrucks.isEmpty()) {
            allTrucks = TruckFacade.getTruckService().findAll();
            //        serviceTrucks = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
            serviceTrucks.clear();
            msvTrucks.clear();
//            movTrucks.clear();
//            mmvTrucks.clear();
            for (Truck truck : allTrucks) {
//            if (truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MOV")) {
//                movTrucks.add(truck);
//            } else if (truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MMV")) {
//                mmvTrucks.add(truck);
//            }
                if (truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MSV") || truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MUV")) {
                    serviceTrucks.add(truck);
                }
                if (truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MSV")) {
                    msvTrucks.add(truck);
                }
            }
        }
    }

    public void determineDateRange(Date endDatee, int dateRange) {
        // Range is calculated as ending with Month before current month,
        // and counting into the past number of months specified in dateRange
        Calendar calendarEndDate = Calendar.getInstance();
        calendarEndDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(endDatee));
//        calendarEndDate.add(Calendar.MONTH, -1);
        endDate = calendarEndDate.getTime();

        Calendar calendarStartDate = Calendar.getInstance();
        calendarStartDate.setTime(dateTimeFormatHelper.resetTimeAndMonthStart(endDatee));
        calendarStartDate.add(Calendar.MONTH, -dateRange);
        startDate = calendarStartDate.getTime();
//
//        System.out.println("StartDate: " + startDate + " | EndDate: " + this.endDate);

    }

    public int countMonthsInRange(Date startDate, Date endDate) {
        int monthCount = 0;
        final Calendar startCalendar = Calendar.getInstance();
        final Calendar endCalendar = Calendar.getInstance();

        startCalendar.setTime(dateTimeFormatHelper.resetTimeAndMonthStart(startDate));
        endCalendar.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(endDate));

        while (!startCalendar.getTime().after(endCalendar.getTime())) {
            monthCount++;
            startCalendar.add(Calendar.MONTH, 1);
        }
        return monthCount;
    }

    public List<OperatingCost> getOperatingCostByTruckBetweenTwoDates(Date startDate, Date endDate) {
        Integer counter = new Integer("0");
        operatingCostList.clear();
        Date nuStartDate = this.resetMonthToFirstDay(startDate);
        Date nuEndDate = this.resetMonthToLastDay(endDate);

        Calendar calendar = Calendar.getInstance();
        for (calendar.setTime(nuStartDate); calendar.getTime().before(nuEndDate) || calendar.getTime().compareTo(nuEndDate) == 0; calendar.add(Calendar.MONTH, 1)) {
            for (Truck truck : allTrucks) {
//                 List<OperatingCost> truckOperatingCostList = OperatingCostFacade.getOperatingCostService().getOperatingCostByTruckBetweenTwoDates(truck, calendarOneMonthBackward(startCalendar.getTime()), dateTimeFormatHelper.resetTimeAndMonthEnd(startCalendar.getTime()));
                List<OperatingCost> truckOperatingCosts = OperatingCostFacade.getOperatingCostService().getOperatingCostByTruckByMonth(truck, calendar.getTime());
                if (!truckOperatingCosts.isEmpty()) {
                    operatingCostList.addAll(truckOperatingCosts);
//                    for (OperatingCost operatingCost : truckOperatingCosts) {
//                        System.out.println("" + truck.getVehicleNumber()
//                                + ", Id= " + operatingCost.getTruckId()
//                                + ", Date= " + operatingCost.getTransactionDate()
//                                + ", month= " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(operatingCost.getTransactionDate().toString())
//                                + ", Fuel= " + operatingCost.getFuelCost()
//                                + ", R/Ltr= " + operatingCost.getRandPerLitre());
//                    }
                } else {
//                    System.out.println("No Daily INPUTS found for: " + truck.getVehicleNumber());
                    counter++;
                    operatingCostList.add(buildZeroOperatingCostObject(truck, counter, calendar.getTime()));
                }
            }
        }
        return operatingCostList;
    }

    private OperatingCost buildZeroOperatingCostObject(Truck truck, Integer counter, Date transactionDate) {
        final OperatingCost operatingCosts = new OperatingCost.Builder(transactionDate)
                .fuelCost(BigDecimal.ZERO)
                .fuelLitres(new Double("0.00"))
                .oilCost(BigDecimal.ZERO)
                .oilLitres(new Double("0.00"))
                .speedometer(new Integer("0"))
                .slipNo("0000")
                .randPerLitre(BigDecimal.ZERO)
                .driver(truck.getDriver())
                .truckId(truck.getId())
                .id(counter + "")
                .build();

        return operatingCosts;
    }

    public List<OperatingCost> getOperatingCostsForDateRange(Date startDate, Date endDate, List<OperatingCost> operatingCostTwentyFiveMonthsList) {
        boolean found = true;
        Date nuStartDate = dateTimeFormatHelper.resetTimeAndMonthStart(startDate);
        Date nuEndDate = dateTimeFormatHelper.resetTimeAndMonthEnd(endDate);
        List<OperatingCost> operatingCosts = new ArrayList<>();
//        Collections.sort(operatingCostTwentyFiveMonthsList, OperatingCost.DescOrderDateComparator);

        for (OperatingCost operatingCost : operatingCostTwentyFiveMonthsList) {
            Date operationDate = operatingCost.getTransactionDate();
            if (operationDate.compareTo(nuStartDate) == 0
                    || (operationDate.after(nuStartDate) && operationDate.before(nuEndDate))
                    || operationDate.compareTo(nuEndDate) == 0) {
                operatingCosts.add(operatingCost);
            } else {
                found = false;
            }
            if (!found) {
                break;
            }
        }
        return operatingCosts;
    }

    public Date resetMonthToFirstDay(Date date) {
        return dateTimeFormatHelper.resetTimeAndMonthStart(date);
    }

    public Date resetMonthToLastDay(Date date) {
        return dateTimeFormatHelper.resetTimeAndMonthEnd(date);
    }

    public BigDecimal getMtdAct(List<OperatingCost> truckMonthOperatingCostList, Truck truck) {
////        //=============== DELETE ================
////        System.out.println("1M efficiency. " + truck.getVehicleNumber() + " end month Operating Cost List");
////        for (OperatingCost operatingCost : truckMonthOperatingCostList) {
////            System.out.println(operatingCost.getTransactionDate() + ", Fuel Cost " + operatingCost.getFuelCost() + ", Mileage= " + operatingCost.getSpeedometer());
////        }
////        //=============== DELETE ================

        BigDecimal fuelCostSum = sumOfFuelCostCalculation(truckMonthOperatingCostList);
        Integer mileageCalc = doMileageCalculation(truckMonthOperatingCostList, truck);
        Integer tripMileageSum = calculateTripMileageSum(truckMonthOperatingCostList, truck);

        // Calculate Sum of Trips
        if (mileageCalc > 0 && (fuelCostSum.compareTo(BigDecimal.ZERO) > 0)) { // if(Monthly Mileage >0 && Monthly Amount > 0)
//            System.out.println(truck.getVehicleNumber() + "Fuel Cost Sum: " + fuelCostSum + "/ Total Mileage: " + mileageCalc + " = " + fuelCostSum.divide(new BigDecimal(mileageCalc + ""), 2, BigDecimal.ROUND_HALF_UP));
            return fuelCostSum.divide(new BigDecimal(mileageCalc + ""), 2, BigDecimal.ROUND_HALF_UP); // RoundingMode.HALF_UP
        } else if (tripMileageSum > 0 && (fuelCostSum.compareTo(BigDecimal.ZERO) > 0)) {
            // DO TEH mtdAct CALCULATION
            return fuelCostSum.divide(new BigDecimal(tripMileageSum.toString()), 2, BigDecimal.ROUND_HALF_UP); //RoundingMode.HALF_UP
        }
        System.out.println(truck.getVehicleNumber() + " " + truckMonthOperatingCostList.get(0).getTransactionDate()
                + ", Fuel Cost " + truckMonthOperatingCostList.get(0).getFuelCost() + ", Litres "
                + truckMonthOperatingCostList.get(0).getFuelLitres()
                + ", Mileage " + truckMonthOperatingCostList.get(0).getSpeedometer());
        return BigDecimal.ZERO;
    }

    public BigDecimal sumOfFuelCostCalculation(List<OperatingCost> truckMonthOperatingCostList) {
        BigDecimal fuelCostSum = BigDecimal.ZERO;
        // Calculate SUM of Fuel Cost for Month
        for (OperatingCost operatingCost : truckMonthOperatingCostList) {
            fuelCostSum = fuelCostSum.add(operatingCost.getFuelCost());
        }
        return fuelCostSum.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Integer doMileageCalculation(List<OperatingCost> truckMonthOperatingCostList, Truck truck) {
        Date queriedDate = this.resetMonthToLastDay(truckMonthOperatingCostList.get(0).getTransactionDate());
        Calendar previousCalendar = Calendar.getInstance();
        previousCalendar.setTime(queriedDate);
        previousCalendar.add(Calendar.MONTH, -1);

        Integer previousClosingMileage = calculatePreviousMonthClosingMileage(truck, previousCalendar.getTime());//
        Integer currentMonthClosingMileage = truckMonthOperatingCostList.get(0).getSpeedometer();

        if (previousClosingMileage.compareTo(new Integer("0")) > 0 && currentMonthClosingMileage.compareTo(new Integer("0")) > 0) {
////            System.out.println(truck.getVehicleNumber() + "- 1M Efficiency > Prev Month " + previousCalendar.getTime() + " closing Mileage: " + previousClosingMileage + " | Current Month " + queriedDate + " closing Mileage: " + currentMonthClosingMileage);
            return currentMonthClosingMileage - previousClosingMileage;
        }
        return 0;
    }

    private Integer calculateTripMileageSum(List<OperatingCost> truckMonthOperatingCostList, Truck truck) {
        Date queriedDate = this.resetMonthToLastDay(truckMonthOperatingCostList.get(0).getTransactionDate());
        Calendar previousCalendar = Calendar.getInstance();
        previousCalendar.setTime(queriedDate);
        previousCalendar.add(Calendar.MONTH, -1);

        Integer tripMileageSum = 0;
        Integer previousClosingMileage = calculatePreviousMonthClosingMileage(truck, previousCalendar.getTime());
        for (OperatingCost operatingCost : truckMonthOperatingCostList) {
            tripMileageSum += operatingCost.getSpeedometer() - previousClosingMileage;
//            previousClosingMileage = operatingCost.getSpeedometer(); // why was this line commented. SEEMS VALID TO ME. CHECK WITH Mr P
        }
        return tripMileageSum;
    }

    public Integer calculatePreviousMonthClosingMileage(Truck truck, Date previousMonthDate) {
        // Get the last Closing Mileage for previous Month
        //  EXAMPLE: The operatingCostList parameter in next line was derived from Table clsss may not apply here:  VehicleFuelUsageTable.java
        // but make sure it is set or else would yield undesireable results:  trackerUtil.setOperatingCostList(truck.getOperatingCosts());
        List<OperatingCost> previousMonthOperatingCostList = getPreviousMonthOperatingCostList(truck, operatingCostList, previousMonthDate);
        //openingMileage = Double.parseDouble(Float.toString(queriedMonthOperatingCostList.get(0).getSpeedometer()));
        if (previousMonthOperatingCostList.isEmpty()) {
            return truck.getStartMileage();
        }
        try {
            return previousMonthOperatingCostList.get(0).getSpeedometer();

        } catch (Exception ex) {
            // meaning This is the first Month of Data Capture
            // There for return current Month First Mileage
            return truck.getStartMileage();
        }
//        return 0;
    }

    public List<OperatingCost> getPreviousMonthOperatingCostList(Truck truck, List<OperatingCost> operatingCostList, Date date) {
        Collections.sort(operatingCostList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
        boolean found = false;
        List<OperatingCost> queriedMonthOperatingCostList = new ArrayList<>();
        for (OperatingCost operatingCost : operatingCostList) {
            if (!(operatingCost.getSpeedometer() == 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0 && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0 && operatingCost.getSlipNo().equals("0000") && operatingCost.getRandPerLitre().compareTo(BigDecimal.ZERO) == 0)) {
                if (truck.getId().equals(operatingCost.getTruckId()) && this.resetMonthToFirstDay(date).compareTo(this.resetMonthToFirstDay(operatingCost.getTransactionDate())) == 0) {
                    queriedMonthOperatingCostList.add(operatingCost);
                    found = true;
                }
            }
            if (found) {
                if (this.resetMonthToFirstDay(date).after(this.resetMonthToFirstDay(operatingCost.getTransactionDate()))) { // Dae has changed
                    break;
                }
            }
        }

        if (queriedMonthOperatingCostList.isEmpty()) {
            found = false;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -1);

            for (int i = 0; i < 2; i++) { // 2 more times to loop bc 15 months OperatingCosts were fetched
                for (OperatingCost operatingCost : operatingCostList) {
                    if (!(operatingCost.getSpeedometer() == 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0 && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0 && operatingCost.getSlipNo().equals("0000") && operatingCost.getRandPerLitre().compareTo(BigDecimal.ZERO) == 0)) {
                        if (truck.getId().equals(operatingCost.getTruckId()) && this.resetMonthToFirstDay(calendar.getTime()).compareTo(this.resetMonthToFirstDay(operatingCost.getTransactionDate())) == 0) {
                            queriedMonthOperatingCostList.add(operatingCost);
                            found = true;
                        }
                    }
                    if (found) {
                        if (this.resetMonthToFirstDay(date).after(this.resetMonthToFirstDay(operatingCost.getTransactionDate()))) { // Dae has changed
                            break;
                        }
                    }
                }
                if (!queriedMonthOperatingCostList.isEmpty()) {
                    return queriedMonthOperatingCostList;
                }
                found = false;
                calendar.add(Calendar.MONTH, -1);
            }
        }
        return queriedMonthOperatingCostList;
    }

    public BigDecimal performMtdActAverageCalc(BigDecimal mtdActAverageCalc, int counter) {
        try {
            mtdActAverageCalc = mtdActAverageCalc.divide(new BigDecimal(counter + ""), 2, BigDecimal.ROUND_HALF_UP);
        } catch (ArithmeticException a) {
            System.out.println("mtd Act Average Calc (" + mtdActAverageCalc + ") / counter (" + counter + ")  | A Divide By Zero exception (ArithmeticException) caught");
//            Notification.show("Error. A Calculation is trying to divide by ZERO. Reason for 0.00 per KM.", Notification.Type.TRAY_NOTIFICATION);
            return BigDecimal.ZERO;
        }
        return mtdActAverageCalc;
    }

    public static String truncate(String value, int length) {
        if (value != null && value.length() > length) {
            value = value.substring(0, length);
        }
        return value;
    }

    public BigDecimal performFuelSpendPercentage(BigDecimal grandTotal, BigDecimal fractionTotal) {
        BigDecimal percentageCalc = fractionTotal.divide(new BigDecimal(grandTotal.toString()), 2, BigDecimal.ROUND_HALF_UP);
        percentageCalc = percentageCalc.multiply(new BigDecimal("100"));
        return percentageCalc.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    public Truck findTruckFromAllTruckListById(String truckId) {
        for (Truck truck : allTrucks) {
            if (truck.getId().equals(truckId)) {
                return truck;
            }
        }
        return null;
    }

    public Date determineStartDate(Date endDate, int monthRange) {
        Calendar calendarStartDate = Calendar.getInstance();
        calendarStartDate.setTime(endDate);
        calendarStartDate.add(Calendar.MONTH, -(monthRange - 1));
        return resetMonthToFirstDay(calendarStartDate.getTime());
    }

    //=========================================== 3 and 12 Month Efficiency CALCULATIONS BEGINS ===================================================//
    public Integer calculateMonthMileageTotal(List<OperatingCost> truckCurrentMonthOperatingCostList, Truck truck) {
        // truckCurrentMonthOperatingCostList is SORTED in DESC Order
        Date queriedDate = truckCurrentMonthOperatingCostList.get(0).getTransactionDate();
        Integer previousClosingMileage = calculatePreviousMonthEndingMileage(truck, queriedDate);//
        Integer lastClosingMileage = truckCurrentMonthOperatingCostList.get(0).getSpeedometer();

        if (previousClosingMileage.compareTo(new Integer("0")) > 0 && lastClosingMileage.compareTo(new Integer("0")) > 0) {
//            System.out.println("Truck= " + truck.getVehicleNumber() + " Month =" + queriedDate + " Previous Month closing Mileage= " + previousClosingMileage + " Current Month closing Mileage= " + lastClosingMileage);
//            System.out.println("============================================ ");
            return lastClosingMileage - previousClosingMileage;
        }
        return 0;
    }

    public Integer calculatePreviousMonthEndingMileage(Truck truck, Date queriedDate) {
        Calendar previousMonthCalendar = Calendar.getInstance();
        previousMonthCalendar.setTime(this.resetMonthToFirstDay(queriedDate));
        previousMonthCalendar.add(Calendar.MONTH, -1);

        // Get the last Closing Mileage for previous Month
        //  EXAMPLE: The operatingCostList parameter in next line was derived from Table clsss may not apply here:  VehicleFuelUsageTable.java
        // but make sure it is set or else would yield undesireable results:  trackerUtil.setOperatingCostList(truck.getOperatingCosts());
        List<OperatingCost> previousMonthOperatingCostList = findPreviousMonthOperatingCostList(truck, previousMonthCalendar.getTime());
        if (previousMonthOperatingCostList.isEmpty()) {
            return truck.getStartMileage();
        }
        try {
            return previousMonthOperatingCostList.get(0).getSpeedometer();
        } catch (Exception ex) {
            // meaning This is the first Month of Data Capture
            // There for return current Month First Mileage
            return truck.getStartMileage();
        }
//        return 0;
    }

    private List<OperatingCost> findPreviousMonthOperatingCostList(Truck truck, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Collections.sort(operatingCostList, OperatingCost.DescOrderDateAscOrderTruckIdComparator); // DO FIX HERE // AscOrderDateAscOrderTruckIdComparator
        List<OperatingCost> queriedMonthOperatingCostList = new ArrayList<>();
        for (OperatingCost operatingCost : operatingCostList) {
            if (!(operatingCost.getSpeedometer() == 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0 && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0 && operatingCost.getSlipNo().equals("0000") && operatingCost.getRandPerLitre().compareTo(BigDecimal.ZERO) == 0)) {
                if (truck.getId().equals(operatingCost.getTruckId()) && date.compareTo(this.resetMonthToFirstDay(operatingCost.getTransactionDate())) == 0) {
                    queriedMonthOperatingCostList.add(operatingCost);
                }
            }
            if (date.after(resetMonthToFirstDay(operatingCost.getTransactionDate()))) { // Date has changed as by SORTING ORDER // DO FIX HERE
                break;
            }

        }

        if (queriedMonthOperatingCostList.isEmpty()) {
//            System.out.println("Start Date is " + startDate + ", Current Date is " + date + ", " + truck.getVehicleNumber() + " has NOT Daily Inputs for " + calendar.getTime());
            calendar.add(Calendar.MONTH, -1);

            Calendar loopCalendar = Calendar.getInstance();
            for (loopCalendar.setTime(calendar.getTime()); loopCalendar.getTime().after(this.resetMonthToFirstDay(startDate)) || loopCalendar.getTime().compareTo(this.resetMonthToFirstDay(startDate)) == 0; loopCalendar.add(Calendar.MONTH, -1)) {
                for (OperatingCost operatingCost : operatingCostList) {
                    if (!(operatingCost.getSpeedometer() == 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0 && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0 && operatingCost.getSlipNo().equals("0000") && operatingCost.getRandPerLitre().compareTo(BigDecimal.ZERO) == 0)) {
                        if (truck.getId().equals(operatingCost.getTruckId()) && loopCalendar.getTime().compareTo(this.resetMonthToFirstDay(operatingCost.getTransactionDate())) == 0) {
                            queriedMonthOperatingCostList.add(operatingCost);
                        }
                    }
                    if (loopCalendar.getTime().after(this.resetMonthToFirstDay(operatingCost.getTransactionDate()))) { // Date has changed // FIX .after NOW .before
//                        MSV-07 LOOP BREAK AT Sat Jun 01 00:00:00 CAT 2013, DAILY INPUT DATE = Tue Dec 31 22:41:12 CAT 201
//                        System.out.println(truck.getVehicleNumber() + " LOOP BREAK AT " + loopCalendar.getTime() + ", DAILY INPUT DATE = " + operatingCost.getTransactionDate());
                        break;
                    }
                }
                if (!queriedMonthOperatingCostList.isEmpty()) {
//                    System.out.println(truck.getVehicleNumber() + " has Daily Inputs for " + loopCalendar.getTime());
                    return queriedMonthOperatingCostList;
                } else {
//                    System.out.println(truck.getVehicleNumber() + " has NOT Daily Inputs for " + loopCalendar.getTime());
                }
//                found = false;
            }
        }
        return queriedMonthOperatingCostList;

    }
    //
    //=========================================== 3 and 12 Month Efficiency CALCULATIONS ENDS ===================================================//
}
