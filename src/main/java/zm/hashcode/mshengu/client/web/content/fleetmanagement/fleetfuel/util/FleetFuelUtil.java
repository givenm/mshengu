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

    private DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    public static Date startDate = null;
    public static Date endDate = null;
    public static List<Truck> allTrucks = new ArrayList<>();
    public static List<Truck> serviceTrucks = new ArrayList<>();
//    public static List<Truck> movTrucks = new ArrayList<>();
//    public static List<Truck> mmvTrucks = new ArrayList<>();
    public static List<OperatingCost> operatingCostList = new ArrayList<>();

    public void getTrucks() {
        allTrucks = TruckFacade.getTruckService().findAll();
        //        serviceTrucks = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();

        for (Truck truck : allTrucks) {
//            if (truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MOV")) {
//                movTrucks.add(truck);
//            } else if (truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MMV")) {
//                mmvTrucks.add(truck);
//            }
            if (truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MSV") || truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MUV")) {
                serviceTrucks.add(truck);
            }
        }

    }

    public void determineDateRange(Date endDatee, int dateRange) {
        // Range is calculated as ending with Month before current month,
        // and counting into the past number of months specified in dateRange

        Calendar calendarEndDate = Calendar.getInstance();
        calendarEndDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(endDatee));
//        calendarEndDate.add(Calendar.MONTH, -1);
        endDate = resetMonthToLastDay(calendarEndDate.getTime());

        Calendar calendarStartDate = Calendar.getInstance();
        calendarStartDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(endDatee));
        calendarStartDate.add(Calendar.MONTH, -dateRange);
        startDate = resetMonthToFirstDay(calendarStartDate.getTime());
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
        startDate = this.resetMonthToFirstDay(startDate);
        endDate = this.resetMonthToLastDay(endDate);
        Calendar calendar = Calendar.getInstance();
        for (calendar.setTime(startDate); calendar.getTime().before(endDate) || calendar.getTime().compareTo(endDate) == 0; calendar.add(Calendar.MONTH, 1)) {
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

    public List<OperatingCost> getOperatingCostsForSpecDates(Date startDate, Date endDate, List<OperatingCost> operatingCostList) {
        boolean found = true;
        List<OperatingCost> operatingCosts = new ArrayList<>();
        Collections.sort(operatingCostList, OperatingCost.DescOrderDateComparator);

        for (OperatingCost operatingCost : operatingCostList) {
            Date operationDate = resetMonthToFirstDay(operatingCost.getTransactionDate());
            if (operationDate.compareTo(startDate) == 0
                    || (operationDate.after(startDate) && operationDate.before(endDate))
                    || operationDate.compareTo(endDate) == 0) {
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
        final Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(dateTimeFormatHelper.resetTimeAndMonthStart(date));
        calendarDate.set(Calendar.DAY_OF_MONTH, 1); // ! reset to 1ST of Month

        return calendarDate.getTime();
    }

    public Date resetMonthToLastDay(Date date) {
        final Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(date));
        calendarDate.set(Calendar.DAY_OF_MONTH, calendarDate.getActualMaximum(Calendar.DAY_OF_MONTH)); // ! reset to LAST of Month (28,29,30,31)

        return calendarDate.getTime();
    }
//    /**
//     * @return the startDate
//     */
//    public static Date getStartDate() {
//        return startDate;
//    }
//
//    /**
//     * @param aStartDate the startDate to set
//     */
//    public static void setStartDate(Date aStartDate) {
//        startDate = aStartDate;
//    }
//
//    /**
//     * @return the endDate
//     */
//    public static Date getEndDate() {
//        return endDate;
//    }
//
//    /**
//     * @param aEndDate the endDate to set
//     */
//    public static void setEndDate(Date aEndDate) {
//        endDate = aEndDate;
//    }

    public BigDecimal getMtdAct(List<OperatingCost> truckMonthOperatingCostList, Truck truck) {
        BigDecimal fuelCostSum = sumOfFuelCostCalculation(truckMonthOperatingCostList);
        Integer mileageCalc = doMileageCalculation(truckMonthOperatingCostList, truck);
        Integer tripMileageSum = calculateTripMileageSum(truckMonthOperatingCostList, truck);
        // Calculate Sum of Trips
        if (mileageCalc > 0 && (fuelCostSum.compareTo(new BigDecimal("0.00")) == 1)) { // if(Monthly Mileage >0 && Monthly Amount > 0)
            return fuelCostSum.divide(BigDecimal.valueOf(mileageCalc), 2, RoundingMode.HALF_UP);
        } else {
            // DO TEH mtdAct CALCULATION
            return fuelCostSum.divide(new BigDecimal(tripMileageSum.toString()), 2, RoundingMode.HALF_UP);
        }
    }

    public BigDecimal sumOfFuelCostCalculation(List<OperatingCost> truckMonthOperatingCostList) {
        BigDecimal fuelCostSum = BigDecimal.ZERO;
        // Calculate SUM of Fuel Cost for Month
        for (OperatingCost operatingCost : truckMonthOperatingCostList) {
            fuelCostSum = fuelCostSum.add(operatingCost.getFuelCost());
            // System.out.println("\n\nFuel Cost Sum is Now: " + fuelCostSum + "  " + operatingCost.getDriver().getFirstname() + "===" + operatingCost.getTransactionDate() + "\n\n");
        }
        return fuelCostSum;
    }

    public Integer doMileageCalculation(List<OperatingCost> truckMonthOperatingCostList, Truck truck) {
        Date queriedDate = truckMonthOperatingCostList.get(truckMonthOperatingCostList.size() - 1).getTransactionDate();
        Integer lastClosingMileage = 0;
        Integer previousClosingMileage = calculatePreviousMonthClosingMileage(truck, queriedDate);//

        lastClosingMileage = truckMonthOperatingCostList.get(truckMonthOperatingCostList.size() - 1).getSpeedometer();
        if (!previousClosingMileage.equals(Double.parseDouble("0.0")) && !lastClosingMileage.equals(Double.parseDouble("0.0"))) {
            return lastClosingMileage - previousClosingMileage;

        }
        return 0;
    }

    private Integer calculateTripMileageSum(List<OperatingCost> truckMonthOperatingCostList, Truck truck) {
        Date queriedDate = truckMonthOperatingCostList.get(truckMonthOperatingCostList.size() - 1).getTransactionDate();
        Integer tripMileageSum = 0;
        Integer previousClosingMileage = calculatePreviousMonthClosingMileage(truck, queriedDate);
        for (OperatingCost operatingCost : truckMonthOperatingCostList) {
            tripMileageSum += operatingCost.getSpeedometer() - previousClosingMileage;
//            previousClosingMileage = operatingCost.getSpeedometer();
        }
        return tripMileageSum;
    }

    public Integer calculatePreviousMonthClosingMileage(Truck truck, Date queriedDate) {
        //
        Integer previousMonth = Integer.parseInt(dateTimeFormatHelper.getPreviousMonthNumber(queriedDate)); // Why "-1" ? bc Jan =0, Dec = 11
        Integer previousYear = Integer.parseInt(dateTimeFormatHelper.getPreviousMonthYearNumber(queriedDate)); // Jan = 0, Jan -1 would be Dec of previous year

//        Date previousMonthDate = dateTimeFormatHelper.getDate(previousYear, previousMonth);
////        System.out.println("\n\nPrev Month " + previousMonthDate + "\n\n");

        // Get the last Closing Mileage for previous Month
        //  EXAMPLE: The operatingCostList parameter in next line was derived from Table clsss may not apply here:  VehicleFuelUsageTable.java
        // but make sure it is set or else would yield undesireable results:  trackerUtil.setOperatingCostList(truck.getOperatingCosts());
        List<OperatingCost> previousMonthOperatingCostList = getQueriedMonthOperatingCostList(operatingCostList, dateTimeFormatHelper.getDate(previousYear, previousMonth));
        //openingMileage = Double.parseDouble(Float.toString(queriedMonthOperatingCostList.get(0).getSpeedometer()));
        if (previousMonthOperatingCostList.isEmpty()) {
            return truck.getStartMileage();
        }
        try {
            return previousMonthOperatingCostList.get(previousMonthOperatingCostList.size() - 1).getSpeedometer();
//            System.out.println("\n\nPrev Month-END closing Mileage: " + closingMileage + "\n\n");
        } catch (Exception ex) {
            // meaning This is the first Month of Data Capture
            // There for return current Month First Mileage
            return truck.getStartMileage();
        }
//        return 0;
    }

    public List<OperatingCost> getQueriedMonthOperatingCostList(List<OperatingCost> operatingCostList, Date date) {
        List<OperatingCost> queriedOperatingCostList = new ArrayList<>();

        for (OperatingCost operatingCost : operatingCostList) {
            if ((Integer.parseInt(dateTimeFormatHelper.getMonthNumber(date)) == Integer.parseInt(dateTimeFormatHelper.getMonthNumber(operatingCost.getTransactionDate())))
                    && (Integer.parseInt(dateTimeFormatHelper.getYearNumber(date)) == Integer.parseInt(dateTimeFormatHelper.getYearNumber(operatingCost.getTransactionDate())))) {
                queriedOperatingCostList.add(operatingCost);
            }
        }
        Collections.sort(queriedOperatingCostList); // Sorting the ArrayList
        return queriedOperatingCostList;
    }

    public BigDecimal performMtdActAverageCalc(BigDecimal mtdActAverageCalc, int counter) {
        try {
            mtdActAverageCalc = mtdActAverageCalc.divide(new BigDecimal(counter + ""), 2, RoundingMode.HALF_UP);
        } catch (ArithmeticException a) {
            System.out.println("mtdActAverageCalc (" + mtdActAverageCalc + ") / counter (" + counter + ")");
            Notification.show("Error. A Calculation is trying to divide by ZERO. Reason for 0.00 per KM.", Notification.Type.TRAY_NOTIFICATION);
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
        BigDecimal percentageCalc = fractionTotal.divide(new BigDecimal(grandTotal.toString()), 2, RoundingMode.HALF_UP);
        percentageCalc = percentageCalc.multiply(new BigDecimal("100"));
        return percentageCalc.setScale(0, BigDecimal.ROUND_UP);
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
        calendarStartDate.setTime(dateTimeFormatHelper.resetTimeAndMonthEnd(endDate));
        calendarStartDate.add(Calendar.MONTH, -monthRange);
        return resetMonthToFirstDay(calendarStartDate.getTime());
    }
}
