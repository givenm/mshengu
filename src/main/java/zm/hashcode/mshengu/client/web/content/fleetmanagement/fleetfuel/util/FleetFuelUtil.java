/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.OperatingCostFacade;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util.TrackerUtil;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Colin
 */
public class FleetFuelUtil implements Serializable {

    private DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private TrackerUtil trackerUtil = new TrackerUtil();
    private static Date startDate = null;
    private static Date endDate = null;
    public static List<Truck> serviceTrucks;
    public static List<Truck> allTrucks;
    public static List<OperatingCost> operatingCostList = new ArrayList<>();

    public void getTrucks() {
        serviceTrucks = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
        allTrucks = TruckFacade.getTruckService().findAll();
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
                    for (OperatingCost operatingCost : truckOperatingCosts) {
//                        System.out.println("" + truck.getVehicleNumber()
//                                + ", Id= " + operatingCost.getTruckId()
//                                + ", Date= " + operatingCost.getTransactionDate()
//                                + ", month= " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(operatingCost.getTransactionDate().toString())
//                                + ", Fuel= " + operatingCost.getFuelCost()
//                                + ", R/Ltr= " + operatingCost.getRandPerLitre());
                    }
                } else {
//                    System.out.println("No Daily INPUTS found for: " + truck.getVehicleNumber());
                    counter++;
                    operatingCostList.add(buildZeroOperatingCostObject(counter, calendar.getTime()));
                }
            }
        }
        return operatingCostList;
    }

    private OperatingCost buildZeroOperatingCostObject(Integer counter, Date transactionDate) {
        final OperatingCost operatingCosts = new OperatingCost.Builder(transactionDate)
                .fuelCost(BigDecimal.ZERO)
                .fuelLitres(new Double("0.00"))
                .oilCost(BigDecimal.ZERO)
                .oilLitres(new Double("0.00"))
                .speedometer(new Integer("0"))
                .slipNo("0000")
                .randPerLitre(BigDecimal.ZERO)
                .driver(null)
                .truckId(null)
                .id(counter + "")
                .build();

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
