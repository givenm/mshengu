/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.OperationalAllowanceFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.OperationalAllowance;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author geek
 */
public class TrackerUtil implements Serializable {

    private static List<OperatingCost> operatingCostList = new ArrayList<>();
    private static Date queriedDate;
    private DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();

    /**
     * Retrieves the Rands per Liter (R/Ltr) value by dividing Fuel Cost over
     * litres.
     *
     * @param fuelCost BigDecimal
     * @param litres Double
     * @return BigDecimal
     */
    public BigDecimal getRandPerLitre(BigDecimal fuelCost, Double litres) {
        if (fuelCost.compareTo(new BigDecimal("0.00")) == 0) {
            return new BigDecimal("0.00");
        }
        fuelCost = fuelCost.divide(new BigDecimal(litres.toString()), 2, RoundingMode.HALF_UP);
//        fuelCost.setScale(2, RoundingMode.HALF_UP);
        return fuelCost.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Retrieves the Rands per Kilometre (R/Km) value by dividing Fuel Cost over
     * Trip.
     *
     * @param fuelCost BigDecimal
     * @param trip Integer
     * @return BigDecimal
     */
    public BigDecimal getRandPerKilometre(BigDecimal fuelCost, Integer trip) {
        if (fuelCost.compareTo(new BigDecimal("0.00")) == 1 && trip > 0) {
            fuelCost = fuelCost.divide(new BigDecimal(trip.toString()), 2, RoundingMode.HALF_UP);
            return fuelCost.setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    public Double getLitrePerKilometer(Double fuelLitres, Integer trip) {
        if (fuelLitres > 0 && trip > 0) {
            return fuelLitres / trip;
        }
        return 0.0;
    }

    /**
     * Calculates (OPerating Spec)the Rands per Kilometre (R/Km) value. Step 1)
     * Get the Percentage of the fuelSpec. (i.e. Divide fuelSpec by 100). Step
     * 2) Multiply randPerLiter by the value gotten in Step 1.
     *
     * @param fuelSpec an Integer
     * @param randPerLiter a BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getFuelSpecRandPerKilometre(BigDecimal fuelSpec, BigDecimal randPerLiter) {
        BigDecimal percentageCalc = fuelSpec.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
//        System.out.println("\n\nFuel Spec Percent is: " + percentageCalc + ". Highest petrol price for month : " + randPerLiter + "\n\n");

        randPerLiter = randPerLiter.multiply(percentageCalc);
        return randPerLiter.setScale(2, BigDecimal.ROUND_UP);
    }

    /**
     * Calculates the target by adding both parameters
     *
     * @param randPerLitre BigDecimal
     * @param operationalAllowance BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getTarget(BigDecimal randPerLitre, BigDecimal operationalAllowance) {
        randPerLitre = randPerLitre.add(operationalAllowance);
        return randPerLitre.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Retrieves a List of queried month's Operating Costs for each Truck
     *
     * @param operatingCostList List OperatingCost
     * @param date Date
     * @return List<OperatingCost>
     */
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

    public BigDecimal getMtdAct(List<OperatingCost> monthOperatingCostList, Truck truck) {
        BigDecimal fuelCostSum = sumOfFuelCostCalculation(monthOperatingCostList);
        Integer mileageCalc = doMileageCalculation(monthOperatingCostList, truck);
        Integer tripMileageSum = calculateTripMileageSum(monthOperatingCostList, truck);
        // Calculate Sum of Trips
        if (mileageCalc > 0 && (fuelCostSum.compareTo(new BigDecimal("0.00")) == 1)) { // if(Monthly Mileage >0 && Monthly Amount > 0)
            return fuelCostSum.divide(BigDecimal.valueOf(mileageCalc), 2, RoundingMode.HALF_UP);
        } else {
            // DO TEH mtdAct CALCULATION
            return fuelCostSum.divide(new BigDecimal(tripMileageSum.toString()), 2, RoundingMode.HALF_UP);
        }
    }

    public BigDecimal sumOfFuelCostCalculation(List<OperatingCost> queriedMonthOperatingCostList) {
        BigDecimal fuelCostSum = BigDecimal.ZERO;
        // Calculate SUM of Fuel Cost for Month
        for (OperatingCost operatingCost : queriedMonthOperatingCostList) {
            fuelCostSum = fuelCostSum.add(operatingCost.getFuelCost());
            // System.out.println("\n\nFuel Cost Sum is Now: " + fuelCostSum + "  " + operatingCost.getDriver().getFirstname() + "===" + operatingCost.getTransactionDate() + "\n\n");
        }
        return fuelCostSum;
    }

    public Integer doMileageCalculation(List<OperatingCost> queriedMonthOperatingCostList, Truck truck) {
        Integer lastClosingMileage = 0;
        Integer previousClosingMileage = calculatePreviousMonthClosingMileage(truck);

        lastClosingMileage = queriedMonthOperatingCostList.get(queriedMonthOperatingCostList.size() - 1).getSpeedometer();
        if (!previousClosingMileage.equals(Double.parseDouble("0.0")) && !lastClosingMileage.equals(Double.parseDouble("0.0"))) {
            return lastClosingMileage - previousClosingMileage;

        }
        return 0;
    }

    private Integer calculateTripMileageSum(List<OperatingCost> monthOperatingCostList, Truck truck) {
        Integer tripMileageSum = 0;
        Integer previousClosingMileage = calculatePreviousMonthClosingMileage(truck);
        for (OperatingCost operatingCost : monthOperatingCostList) {
            tripMileageSum += operatingCost.getSpeedometer() - previousClosingMileage;
//            previousClosingMileage = operatingCost.getSpeedometer();
        }
        return tripMileageSum;
    }

    /**
     * Calculate the Trip for Current Operating Cost. if operatingCost is first
     * record of month, retrieve last closing Mileage of previous month for use
     * in Calculation. Else retrieve previous day's closing mileage and subtract
     * from current operatingCost closingMileage
     *
     * @param monthOperatingCostList List OperatingCost
     * @param operatingCost OperatingCost
     * @return Integer
     */
    public Integer calculateOperatingCostTrip(List<OperatingCost> monthOperatingCostList, OperatingCost operatingCost, Truck truck) {
        Integer tripMileageCalc = 0;
        Integer previousClosingMileage = 0;
        Integer currentClosingMileage = 0;

        OperatingCost operatingCostt = monthOperatingCostList.get(0);
        if (operatingCost.getId().equals(operatingCostt.getId())) { // if ids are equal then this is the current Month's first record
            previousClosingMileage = calculatePreviousMonthClosingMileage(truck);
        } else {
            int index = monthOperatingCostList.indexOf(operatingCost);
            if (index != -1) {
                previousClosingMileage = monthOperatingCostList.get(index - 1).getSpeedometer();
            }
        }

        if (previousClosingMileage != 0) {
            currentClosingMileage = operatingCost.getSpeedometer();
            if (currentClosingMileage != 0) {
                if (currentClosingMileage == previousClosingMileage) {
                    return currentClosingMileage;
                } else {
                    return currentClosingMileage - previousClosingMileage;
                }
            }
        } else {
            return operatingCost.getSpeedometer();
        }

        return tripMileageCalc;
    }

    /**
     * Calculate the Trip for Current Operating Cost. if operatingCost is first
     * record of month, retrieve last closing Mileage of previous month for use
     * in Calculation. Else retrieve previous day's closing mileage and subtract
     * from current operatingCost closingMileage
     *
     * @param monthOperatingCostList List OperatingCost
     * @param dailyTrackerTableData OperatingCost
     * @return Integer
     */
    public Integer calculateTrip(List<DailyTrackerTableData> dailyTrackerTableDataList, DailyTrackerTableData dailyTrackerTableData, Truck truck) {
        Integer tripMileageCalc = 0;
        Integer previousClosingMileage = 0;
        Integer currentClosingMileage = 0;

        DailyTrackerTableData dailyTrackerTableDatat = dailyTrackerTableDataList.get(0);
        if (dailyTrackerTableData.getId().equals(dailyTrackerTableDatat.getId())) { // if ids are equal then this is the current Month's first record
            previousClosingMileage = calculatePreviousMonthClosingMileage(truck);
        } else {
            int index = dailyTrackerTableDataList.indexOf(dailyTrackerTableData);
            if (index != -1) {
                previousClosingMileage = dailyTrackerTableDataList.get(index - 1).getClosingMileage();
            }
        }
        currentClosingMileage = dailyTrackerTableData.getClosingMileage();
        if (previousClosingMileage > 0 && currentClosingMileage > 0) {
            return currentClosingMileage - previousClosingMileage;
        }
//        if (previousClosingMileage != 0) {
//            currentClosingMileage = dailyTrackerTableData.getClosingMileage();
//            if (currentClosingMileage != 0) {
//                if (currentClosingMileage == previousClosingMileage) {
//                    return currentClosingMileage;
//                } else {
//                    return currentClosingMileage - previousClosingMileage;
//                }
//            }
//        } else {
//            return dailyTrackerTableData.getClosingMileage();
//        }

        return tripMileageCalc;
    }

    /**
     * Determines the previous MOnth's closing MILEAGE. It retrieves the closing
     * mileage from the last entry for previous month or the start Mileage of
     * the Vehicle if no record exist for previous month
     *
     * @param value Truck
     * @return Integer
     */
    public Integer calculatePreviousMonthClosingMileage(Truck truck) {
        //
        Integer previousMonth = Integer.parseInt(dateTimeFormatHelper.getPreviousMonthNumber(queriedDate)); // Why "-1" ? bc Jan =0, Dec = 11
        Integer previousYear = Integer.parseInt(dateTimeFormatHelper.getPreviousMonthYearNumber(queriedDate)); // Jan wc is 0 -1 would be Dec of previous year

//        Date previousMonthDate = dateTimeFormatHelper.getDate(previousYear, previousMonth);
////        System.out.println("\n\nPrev Month " + previousMonthDate + "\n\n");

        // Get the last Closing Mileage for previous Month
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

    /**
     * Determines the previous closing MILEAGE. (1) If you are entering for a
     * date for which records exist after it should get the last date mileage
     * previous to this record date as CLOSING MILEAGE. (2) It retrieves the
     * closing mileage from the last entry for current month IF you were
     * entering records sequentially. (3) it retrieves closing mileage from
     * previous month IF AND ONLY IF current entry is the first entry for the
     * month. (4) If no record exist for previous month it retrieves vehicle's
     * start mileage
     *
     * @param value Truck
     * @param date Date
     * @param transactionDate Date
     * @return mileage Integer
     */
    public Integer getPreviousDailyInputClosingMileage(Truck truck, Date date, Date transactionDate) {
        Integer previousMonth = Integer.parseInt(dateTimeFormatHelper.getPreviousMonthNumber(transactionDate)); // Why "-1" ? bc Jan =0, Dec = 11
        Integer previousYear = Integer.parseInt(dateTimeFormatHelper.getPreviousMonthYearNumber(transactionDate)); // Jan wc is 0 -1 would be Dec of previous year

        List<OperatingCost> currentMonthOperatingCostList = getQueriedMonthOperatingCostList(truck.getOperatingCosts(), transactionDate);

        // Check if they are updating Records
        if (!date.equals(transactionDate)) {
            // Begin with previous month Mileage and then navigate the current List
            Integer mileage = getPreviousMonthMileage(truck, previousMonth, previousYear);

            for (OperatingCost operatingCost : currentMonthOperatingCostList) {
                if (dateTimeFormatHelper.resetTimeOfDate(operatingCost.getTransactionDate()).compareTo(dateTimeFormatHelper.resetTimeOfDate(transactionDate)) == 0) {
                    break;
                }
                mileage = operatingCost.getSpeedometer();
            }
            System.out.println("Previous Mileage before this is: " + mileage);
            return mileage;
        }


        // 01. Check if record being entered was for date that was missed and currently records exist after said date
        Date lastDateEntry = new Date();
        if (currentMonthOperatingCostList.size() >= 1) {
            lastDateEntry = currentMonthOperatingCostList.get(currentMonthOperatingCostList.size() - 1).getTransactionDate();
        }

        if (dateTimeFormatHelper.resetTimeOfDate(lastDateEntry).after(dateTimeFormatHelper.resetTimeOfDate(transactionDate))) {
            Integer mileage = 0;
            try {
                // Check if new Entry will be first entry of Month
                if (currentMonthOperatingCostList.get(0).getTransactionDate().after(transactionDate)) {
                    mileage = getPreviousMonthMileage(truck, previousMonth, previousYear);
                    System.out.println("FIRST OF MONTH ENTRY - Previous Mileage before this is: " + mileage);
                    return mileage;
                }
            } catch (java.lang.IndexOutOfBoundsException ex) {
                mileage = getPreviousMonthMileage(truck, previousMonth, previousYear);
                System.out.println("FIRST OF MONTH ENTRY - Previous Mileage before this is: " + mileage);
                return mileage;
            }
            // Check if new Entry will be in between the month
            if (currentMonthOperatingCostList.get(0).getTransactionDate().before(transactionDate) && currentMonthOperatingCostList.get(currentMonthOperatingCostList.size() - 1).getTransactionDate().after(transactionDate)) {
                for (OperatingCost operatingCost : currentMonthOperatingCostList) {
                    if (dateTimeFormatHelper.resetTimeOfDate(operatingCost.getTransactionDate()).after(dateTimeFormatHelper.resetTimeOfDate(transactionDate))) {
                        break;
                    }
                    mileage = operatingCost.getSpeedometer();
                }
                System.out.println("BETWEEN FIRST DAY OF MONTH N LAST DAY OF MONTH - Previous Mileage before this is: " + mileage);
                return mileage;
            }
        }

        // 02. Check if current month has records
        if (!currentMonthOperatingCostList.isEmpty()) {
            return currentMonthOperatingCostList.get(currentMonthOperatingCostList.size() - 1).getSpeedometer();
        }
////////////////////////
        // 03. in case current month does not have records, Check if Previous month had records
        return getPreviousMonthMileage(truck, previousMonth, previousYear);
//////////////////////////////
    }

    public Integer getPreviousMonthMileage(Truck truck, Integer previousMonth, Integer previousYear) {
        // in case current month does not have records, Check if Previous month had records
        List<OperatingCost> previousMonthOperatingCostList = getQueriedMonthOperatingCostList(truck.getOperatingCosts(), dateTimeFormatHelper.getDate(previousYear, previousMonth));

        if (!previousMonthOperatingCostList.isEmpty()) {
            try {
                return previousMonthOperatingCostList.get(previousMonthOperatingCostList.size() - 1).getSpeedometer();
//            System.out.println("\n\nPrev Month-END closing Mileage: " + closingMileage + "\n\n");
            } catch (Exception ex) {
                // meaning This is the first Month of Data Capture
                // There for return current Month First Mileage
                return truck.getStartMileage();
            }

        } else {
            return truck.getStartMileage();
        }
    }

    /**
     * Determines the previous closing MILEAGE. It retrieves the closing mileage
     * from the last entry for current month or from previous month IF AND ONLY
     * IF current entry is the first entry for the month. If no record exist for
     * previous month the retrieve vehicle's start mileage
     *
     * @param value Truck
     * @return Integer
     */
    public boolean isThereDuplicateDailyInput(Truck truck, Date date) {
        List<OperatingCost> currentMonthOperatingCostList = getQueriedMonthOperatingCostList(truck.getOperatingCosts(), date);
        if (!currentMonthOperatingCostList.isEmpty()) {
            for (OperatingCost operatingCost : currentMonthOperatingCostList) {
                if (dateTimeFormatHelper.resetTimeOfDate(date).equals(dateTimeFormatHelper.resetTimeOfDate(operatingCost.getTransactionDate()))) {
                    return true;
                }
            }
        }
        return false;
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

        if (val.compareTo(new Double("7.9")) > 0) {
            return redImage;
        } else if (val.compareTo(new Double("6.0")) > 0) {
            return yellowImage;
        }

        return greenImage;
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

        if (val.compareTo(new Double("7.9")) > 0) {
            return redImage;
        } else if (val.compareTo(new Double("6.0")) > 0) {
            return yellowImage;
        }

        return greenImage;
    }

    /**
     * Determines and retrieves the highest Fuel Price i.e. in Rands Per Litre
     * for the queried month
     *
     * @param operatingCosts List OperatingCost
     * @param date Date
     * @return BigDecimal
     */
    public BigDecimal getHighestRandPerLiter(List<OperatingCost> operatingCosts, Date date) {
//        System.out.println("\n entering getHighestRandPerLiter method \n");
        BigDecimal randPerLitre = BigDecimal.ZERO;
        List<BigDecimal> randPerLitreList = new ArrayList<>();
        List<OperatingCost> operatingCostsList = getQueriedMonthOperatingCostList(operatingCosts, date);
        if (!operatingCostsList.isEmpty()) {
            for (OperatingCost operatingCost : operatingCostsList) {
                randPerLitreList.add(operatingCost.getRandPerLitre());
            }
            // find highest randPerLitre
            randPerLitre = randPerLitreList.get(0);
            for (BigDecimal value : randPerLitreList) {
//                System.out.println("\n\nCurrently checking dieselPrice " + value + " against " + randPerLitre + "\n");
                if (value.compareTo(randPerLitre) == 1) {
                    randPerLitre = value;
                }
            }
            return randPerLitre;
        }


        return randPerLitre;
    }

    /**
     * Retrieves Operational Allowance
     *
     * @return BigDecimal
     */
    public BigDecimal getOperationalAllowance() {
        List<OperationalAllowance> operationalAllowanceList = OperationalAllowanceFacade.getOperationalAllowanceService().findAll();
        if (!operationalAllowanceList.isEmpty()) {
            return operationalAllowanceList.get(0).getOperationalAllowance();
        }
        return BigDecimal.ZERO;
    }

    /**
     * Round a Double or double figure to the amount of decimal places specified
     *
     * @param value double
     * @param decimalPlaces int
     * @return double
     */
    public double round(double value, int decimalPlaces) { //static
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    /**
     * @return the operatingCostList
     */
    public List<OperatingCost> getOperatingCostList() {
        return operatingCostList;
    }

    /**
     * @param operatingCostList the operatingCostList to set
     */
    public void setOperatingCostList(List<OperatingCost> operatingCostList) {
        TrackerUtil.operatingCostList = operatingCostList;
    }

    /**
     * @return the queriedDate
     */
    public Date getQueriedDate() {
        return queriedDate;
    }

    /**
     * @param aQueriedDate the queriedDate to set
     */
    public void setQueriedDate(Date aQueriedDate) {
        queriedDate = aQueriedDate;
    }
}
