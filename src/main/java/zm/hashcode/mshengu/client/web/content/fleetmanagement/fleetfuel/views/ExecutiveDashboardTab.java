/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.dussan.vaadin.dcharts.DCharts;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.FlagImage;
import zm.hashcode.mshengu.app.util.panel.PanelEfficiency;
import zm.hashcode.mshengu.app.util.panel.PanelStyled;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.FleetFuelMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.FuelExecutiveDashboardChartUI;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.DieselPriceRandPerLitreLineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.TotalFleetFuelSpendBarChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.forms.FuelExecutiveDashboardForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.FuelSpendMonthlyCostBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.util.AnnualFuelSpendLayout;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.util.EfficiencylLayout;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.util.FleetFuelUtil;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.util.FuelSpendLayout;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Colin
 */
public class ExecutiveDashboardTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final FuelExecutiveDashboardForm form;
    private final FuelExecutiveDashboardChartUI chart;
    private final FleetFuelUtil fleetFuelUtil = new FleetFuelUtil();
    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private final FlagImage flagImage = new FlagImage();
    public static Date startDate = null;
    public static Date endDate = null;
    public static List<OperatingCost> operatingCostTwelveMonthsList = new ArrayList<>();
    public static List<OperatingCost> dateRangeOperatingCostList = new ArrayList<>();
    public static List<FuelSpendMonthlyCostBean> fuelSpendMonthlyCostBeanList = new ArrayList<>();
    public static BigDecimal grandTotalFuelSpend = BigDecimal.ZERO;
    //
    public static BigDecimal fuelSpendService = BigDecimal.ZERO; // PENDING
    public static BigDecimal fuelSpendUnit = BigDecimal.ZERO;// PENDING
    //
    public static BigDecimal annualTotalFuelSpend = BigDecimal.ZERO;
    public static BigDecimal serviceTotalFuelSpend = BigDecimal.ZERO;
    public static BigDecimal operationalTotalFuelSpend = BigDecimal.ZERO;
    public static BigDecimal nonOperationalTotalFuelSpend = BigDecimal.ZERO;
    //
    public static BigDecimal serviceFuelSpendPercentage = BigDecimal.ZERO;
    public static BigDecimal operationalFuelSpendPercentage = BigDecimal.ZERO;
    public static BigDecimal nonOperationalFuelSpendPercentage = BigDecimal.ZERO;
    //
    public static Integer annualMileageSumAllTrucks = new Integer("0");
    public static String chartPeriod = null;
    public static Integer chartPeriodCount = null;
    //
    private static BigDecimal oneMonthEfficiency = BigDecimal.ZERO;
    private static BigDecimal threeMonthEfficiency = BigDecimal.ZERO;
    private static BigDecimal twelveMonthEfficiency = BigDecimal.ZERO;
    private static Embedded oneMonthEfficiencyFlag = new Embedded();
    private static Embedded threeMonthEfficiencyFlag = new Embedded();
    private static Embedded twelveMonthEfficiencyFlag = new Embedded();
    //
    public static BigDecimal fuelSpendPerUnit = BigDecimal.ZERO;
    public static BigDecimal fuelSpendPerService = BigDecimal.ZERO;
    private static Embedded fuelSpendUnitFlag = new Embedded();// PENDING
    private static Embedded fuelSpendServiceFlag = new Embedded();// PENDING
    //
    private static boolean isThreeMonthOutOfRange = false;
    private static boolean isTwelveMonthOutOfRange = false;

    public ExecutiveDashboardTab(MshenguMain app) {
        main = app;
        form = new FuelExecutiveDashboardForm();
        chart = new FuelExecutiveDashboardChartUI(main);
        setSizeFull();
        addComponent(form);
        addComponent(chart);
        addListeners(); // for Dashboard chart sizing
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == form.startDate) {
            startDate = fleetFuelUtil.resetMonthToFirstDay(form.startDate.getValue()); // reset the choosen start date to 1st day
            try {
                endDate = fleetFuelUtil.resetMonthToLastDay(form.endDate.getValue());
            } catch (java.lang.NullPointerException ex) {
            }
            if (endDate != null) {
                getDataAndPerformCharts();
            }
        } else if (property == form.endDate) {
            endDate = fleetFuelUtil.resetMonthToLastDay(form.endDate.getValue());
            try {
                startDate = fleetFuelUtil.resetMonthToFirstDay(form.startDate.getValue());
            } catch (java.lang.NullPointerException ex) {
            }
            if (startDate != null) {
                getDataAndPerformCharts();
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void getDataAndPerformCharts() {
        dateRangeOperatingCostList.clear();
        operatingCostTwelveMonthsList.clear();
        if (endDate.before(fleetFuelUtil.resetMonthToFirstDay(new Date()))) {
            // Determine date range for other calculations on this Tab, extra 3 month for Previous month mileage
            // or get StartMileage for Car
            fleetFuelUtil.determineDateRange(endDate, 15);
            int monthCount = fleetFuelUtil.countMonthsInRange(startDate, endDate);
            if (monthCount > 0 && monthCount <= 12) {
                fleetFuelUtil.getTrucks();

                operatingCostTwelveMonthsList.addAll(fleetFuelUtil.getOperatingCostByTruckBetweenTwoDates(FleetFuelUtil.startDate, FleetFuelUtil.endDate));
                // Extract date Range for Charts only
                if (monthCount == 12) {
                    dateRangeOperatingCostList.addAll(operatingCostTwelveMonthsList);
                } else {
                    dateRangeOperatingCostList.addAll(fleetFuelUtil.getOperatingCostsForSpecDates(startDate, endDate, operatingCostTwelveMonthsList));
                }
                if (!dateRangeOperatingCostList.isEmpty()) {
                    buildFuelSpendMonthlyCostBeanList(dateRangeOperatingCostList); // used for both Bar n Line Charts
                    // Clear objects with zeros for  mileage, fuelCost, fuelLitres, speedometer, .slipNo("0000") in operatingCostTwelveMonthsList and dateRangeOperatingCostList
//                    clearZeroObjects();
                    oneMonthEfficiency = getOneMonthlyFleetEfficiency(operatingCostTwelveMonthsList); // dateRangeOperatingCostList, endDate
                    oneMonthEfficiencyFlag = flagImage.determineFuelUsageFlag(oneMonthEfficiency);
                    Collections.sort(operatingCostTwelveMonthsList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
                    threeMonthEfficiency = getThreeMonthlyFleetEfficiency(operatingCostTwelveMonthsList, endDate);
                    threeMonthEfficiencyFlag = flagImage.determineFuelUsageFlag(threeMonthEfficiency);
                    twelveMonthEfficiency = getTwelveMonthlyFleetEfficiency(operatingCostTwelveMonthsList, endDate);
                    twelveMonthEfficiencyFlag = flagImage.determineFuelUsageFlag(twelveMonthEfficiency);
                    // buildFuelSpendData(operatingCostTwelveMonthsList); // PENDING
                    buildAnnualFuelSpendData(operatingCostTwelveMonthsList);
                    //
                    displayCharts();
                } else {
                    Notification.show("No Daily input Found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
                }
            } else {
                Notification.show("Please Specify Date Range in Approprate Order and at most 12 months.", Notification.Type.TRAY_NOTIFICATION);
            }
        } else {
            Notification.show("Error. Select any month before Current Month as End Date.", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    public void buildFuelSpendMonthlyCostBeanList(List<OperatingCost> dateRangeOperatingCostList) {
        BigDecimal randPerLitre = BigDecimal.ZERO;
//        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        // SORT THE LIST BY DATE ASC
        Collections.sort(dateRangeOperatingCostList);// ,OperatingCost.AscOrderDateAscOrderTruckIdComparator
        // LOOP AND SUBTOTAL using operatingCostTwelveMonthsList BY dATE and add to fuelSpendMonthlyCostBeanList
        fuelSpendMonthlyCostBeanList.clear();
        BigDecimal monthTotal = BigDecimal.ZERO;
        Date transactionDate = fleetFuelUtil.resetMonthToFirstDay(dateRangeOperatingCostList.get(0).getTransactionDate());
        int counter = 0;
        for (OperatingCost operatingCost : dateRangeOperatingCostList) {
            if (fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()).equals(transactionDate)) {
                monthTotal = monthTotal.add(operatingCost.getFuelCost());
                for (Truck truck : FleetFuelUtil.serviceTrucks) {
                    if (truck.getId().equals(operatingCost.getTruckId())) {
                        if (FleetFuelUtil.truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MSV") && operatingCost.getRandPerLitre().compareTo(BigDecimal.ZERO) > 0) {
                            randPerLitre = operatingCost.getRandPerLitre();
                        }
                        break;
                    }
                }
                // Test for Last item in List and SubTotal
                if (dateRangeOperatingCostList.indexOf(operatingCost) == dateRangeOperatingCostList.size() - 1) {
                    counter++;
                    performSubTotal(monthTotal, dateRangeOperatingCostList, operatingCost, randPerLitre, counter);
                }

            } else {
                // Subtotal
                counter++;
                performSubTotal(monthTotal, dateRangeOperatingCostList, operatingCost, randPerLitre, counter);
                //Reset
                monthTotal = operatingCost.getFuelCost();
                transactionDate = fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate());
            }
        }
    }

    private void performSubTotal(BigDecimal monthTotal, List<OperatingCost> dateRangeOperatingCostList, OperatingCost operatingCost, BigDecimal randPerLitre, int counter) {
        // Subtotal
        monthTotal.setScale(2, BigDecimal.ROUND_UP);
        grandTotalFuelSpend = grandTotalFuelSpend.add(monthTotal);
        // Build FuelSpendMonthlyCostBean and add to ArrayList
        int currentIndex = dateRangeOperatingCostList.indexOf(operatingCost);
        fuelSpendMonthlyCostBeanList.add(buildFuelSpendMonthlyCostBeanObject(counter, monthTotal, dateRangeOperatingCostList.get(currentIndex - 1), randPerLitre));
    }

    private FuelSpendMonthlyCostBean buildFuelSpendMonthlyCostBeanObject(int counter, BigDecimal monthTotal, OperatingCost operatingCost, BigDecimal randPerLitre) {
        FuelSpendMonthlyCostBean fuelSpendMonthlyCostBean = new FuelSpendMonthlyCostBean();
        fuelSpendMonthlyCostBean.setMonthlyAmountSpend(monthTotal);
        fuelSpendMonthlyCostBean.setId(counter + "");
        fuelSpendMonthlyCostBean.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(operatingCost.getTransactionDate().toString()));
        fuelSpendMonthlyCostBean.setTransactionMonth(operatingCost.getTransactionDate());
        fuelSpendMonthlyCostBean.setMonthRandPerLiter(randPerLitre);

        return fuelSpendMonthlyCostBean;
    }

    public BigDecimal getOneMonthlyFleetEfficiency(List<OperatingCost> operatingCostTwelveMonthsList) { //, Date endDate
        BigDecimal mtdActTotal = BigDecimal.ZERO;
        List<OperatingCost> truckMonthOperatingCostList = new ArrayList<>();
        Collections.sort(operatingCostTwelveMonthsList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
        Date endDatee = fleetFuelUtil.resetMonthToFirstDay(operatingCostTwelveMonthsList.get(0).getTransactionDate());
        int counter = 0; // counter for Number of Truck with operating Cost data for the period specified

        for (Truck truck : FleetFuelUtil.serviceTrucks) { //  Not using allTrucks b/c Ops and Non-Ops Trucks dont have mileage entered
            truckMonthOperatingCostList.clear();
            for (OperatingCost operatingCost : operatingCostTwelveMonthsList) {
                if (!(operatingCost.getSpeedometer() <= 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0
                        && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0)) {
                    if (operatingCost.getTruckId().equals(truck.getId())
                            && endDatee.compareTo(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate())) == 0) {
                        truckMonthOperatingCostList.add(operatingCost);
                    }

                }
                // if Date changes, then it is no longer End Date as we sorted in Desc Order
                if (endDatee.compareTo(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate())) != 0) {
                    break;
                }
            }
            // getMTDAct for Truck
            if (truckMonthOperatingCostList.size() > 0) {
                mtdActTotal = mtdActTotal.add(fleetFuelUtil.getMtdAct(truckMonthOperatingCostList, truck));
                counter++; // Counter should increment per truck with Data for specified period
            }
        }
        System.out.println("MTDAct for all service Trucks for 1M Efficiency= " + mtdActTotal + ", No of Service Trucks with Data for last month in range= " + counter);
        // getMTDAct for ALL Truck
        return fleetFuelUtil.performMtdActAverageCalc(mtdActTotal, counter);
    }

    public BigDecimal getThreeMonthlyFleetEfficiency(List<OperatingCost> operatingCostTwelveMonthsList, Date endDate) {
        Date startDatee = fleetFuelUtil.determineStartDate(endDate, 3);
        List<OperatingCost> operatingCostThreeMonthsList = getOperatingCostforMonthsList(operatingCostTwelveMonthsList, endDate, 3);
        //
        List<OperatingCost> selectedThreeMonthsOperatingCostList = new ArrayList<>();
//        if (!isThreeMonthOutOfRange) {
        Integer totalMileageSumAllTrucks = new Integer("0");
        //
        List<OperatingCost> truckMonthOperatingCostList = new ArrayList<>();
        Collections.sort(operatingCostThreeMonthsList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
        for (Truck truck : FleetFuelUtil.serviceTrucks) {
            truckMonthOperatingCostList.clear();
            Calendar calendar = Calendar.getInstance();
            for (calendar.setTime(endDate); calendar.getTime().after(startDatee)
                    || calendar.getTime().compareTo(startDatee) == 0; calendar.add(Calendar.MONTH, -1)) {

                truckMonthOperatingCostList.addAll(getOneMonthlyOperatingCostForTruck(operatingCostThreeMonthsList, calendar.getTime(), truck));

                if (!truckMonthOperatingCostList.isEmpty()) {
                    totalMileageSumAllTrucks += fleetFuelUtil.calculateMonthMileageTotal(truckMonthOperatingCostList, truck);

////                    //======= DELETE =========
////                    for (OperatingCost operatingCost : truckMonthOperatingCostList) {
////                        System.out.println("Truck THREE Month Operating Cost: Truck= " + truck.getVehicleNumber() + " " + operatingCost.getTruckId() + " Month= " + operatingCost.getTransactionDate() + " Mileage= " + operatingCost.getSpeedometer());
////                    }
////                    System.out.println("//=============================//");
////                    //======= DELETE =========
                    selectedThreeMonthsOperatingCostList.addAll(truckMonthOperatingCostList);
                    truckMonthOperatingCostList.clear();
                }
            }
        }

        // Use this three Month list and get the EFFICIENCY
        BigDecimal totalFuelCostAllTrucks = fleetFuelUtil.sumOfFuelCostCalculation(selectedThreeMonthsOperatingCostList);
        //========== DELETE ==============
        System.out.println("SUM OF 3 Month FUEL COST: " + totalFuelCostAllTrucks);
        //========== DELETE ==============
        System.out.println("3 Month Efficiency: MILEAGE SUM= " + totalMileageSumAllTrucks);
        return totalFuelCostAllTrucks.divide(new BigDecimal(totalMileageSumAllTrucks + ""), 2, RoundingMode.HALF_UP);
//        }

//        isThreeMonthOutOfRange = false;
//        return BigDecimal.ZERO;
    }

    public BigDecimal getTwelveMonthlyFleetEfficiency(List<OperatingCost> operatingCostTwelveMonthsList, Date endDate) {
        annualMileageSumAllTrucks = new Integer("0");
        Date startDatee = fleetFuelUtil.determineStartDate(endDate, 12);
        List<OperatingCost> operatingCostTwelveMonthList = getOperatingCostforMonthsList(operatingCostTwelveMonthsList, endDate, 12);
        List<OperatingCost> selectedTwelveMonthsOperatingCostList = new ArrayList<>();
//        if (!isTwelveMonthOutOfRange) {
        List<OperatingCost> truckMonthOperatingCostList = new ArrayList<>();
        Collections.sort(operatingCostTwelveMonthList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
        for (Truck truck : FleetFuelUtil.serviceTrucks) {
            truckMonthOperatingCostList.clear();
            Calendar calendar = Calendar.getInstance();
            for (calendar.setTime(endDate); calendar.getTime().after(startDatee)
                    || calendar.getTime().compareTo(startDatee) == 0; calendar.add(Calendar.MONTH, -1)) {
                truckMonthOperatingCostList.addAll(getOneMonthlyOperatingCostForTruck(operatingCostTwelveMonthList, calendar.getTime(), truck));

                if (!truckMonthOperatingCostList.isEmpty()) {
                    annualMileageSumAllTrucks += fleetFuelUtil.calculateMonthMileageTotal(truckMonthOperatingCostList, truck);
////                    //======= DELETE =========
////                    for (OperatingCost operatingCost : truckMonthOperatingCostList) {
////                        System.out.println("Truck TWELVE Month Operating Cost: Truck= " + truck.getVehicleNumber() + " " + operatingCost.getTruckId() + " Month= " + operatingCost.getTransactionDate() + " Mileage= " + operatingCost.getSpeedometer());
////                    }
////                    System.out.println("//=============================//");
                    //======= DELETE =========
                    selectedTwelveMonthsOperatingCostList.addAll(truckMonthOperatingCostList);
                    truckMonthOperatingCostList.clear();
                }
            }
        }

        // Use this twelve Month list and get the EFFICIENCY  // annualTotalFuelSpend
        annualTotalFuelSpend = fleetFuelUtil.sumOfFuelCostCalculation(selectedTwelveMonthsOperatingCostList);
        //========== DELETE ==============
        System.out.println("SUM OF 12 Month FUEL COST: " + annualTotalFuelSpend);
        //========== DELETE ==============
        //
        System.out.println("12 Month Efficiency: MILEAGE SUM= " + annualMileageSumAllTrucks);
        return annualTotalFuelSpend.divide(new BigDecimal(annualMileageSumAllTrucks + ""), 2, RoundingMode.HALF_UP);
//        }
//        isTwelveMonthOutOfRange = false;
//        return BigDecimal.ZERO;
    }

    public List<OperatingCost> getOneMonthlyOperatingCostForTruck(List<OperatingCost> operatingCostList, Date date, Truck truck) {
        boolean found = false;
        List<OperatingCost> truckMonthOperatingCostList = new ArrayList<>();
        truckMonthOperatingCostList.clear();
        for (OperatingCost operatingCost : operatingCostList) {
            if (operatingCost.getTruckId().equals(truck.getId())
                    && fleetFuelUtil.resetMonthToFirstDay(date).compareTo(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate())) == 0) {
                truckMonthOperatingCostList.add(operatingCost);
                found = true;
            }
            // if Date changes, then it is no longer End Date as we sorted in Desc Order
            if (found && fleetFuelUtil.resetMonthToFirstDay(date).after(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()))) {
                break;
            }
        }

        return truckMonthOperatingCostList;
    }

    public List<OperatingCost> getOperatingCostforMonthsList(List<OperatingCost> operatingCostTwelveMonthsList, Date endDate, int monthRange) {
        Collections.sort(operatingCostTwelveMonthsList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
        Date startDatee = fleetFuelUtil.determineStartDate(endDate, monthRange);
        List<OperatingCost> monthsOperatingCostList = new ArrayList<>();
//        for (Truck truck : FleetFuelUtil.allTrucks) {
//            monthsOperatingCostList.clear();
        for (OperatingCost operatingCost : operatingCostTwelveMonthsList) {
//                if (operatingCost.getTruckId().equals(truck.getId())

            // Omit ZERO OBJECTS
            if (!(operatingCost.getSpeedometer() <= 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0 && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0)) {
                if (fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()).compareTo(fleetFuelUtil.resetMonthToFirstDay(startDatee)) == 0
                        || (fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()).after(fleetFuelUtil.resetMonthToFirstDay(startDatee))
                        && fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()).before(fleetFuelUtil.resetMonthToLastDay(endDate)))
                        || fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()).compareTo(fleetFuelUtil.resetMonthToFirstDay(endDate)) == 0) {
                    monthsOperatingCostList.add(operatingCost);
                }
            }

            // if Date is before startDate, then looping stops as we sorted operatingCostTwelveMonthsList in Desc Order
            if (fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()).after(fleetFuelUtil.resetMonthToFirstDay(startDatee))) {
                break;
            }
        }
        // getMTDAct for Truck
//            if (monthsOperatingCostList.size() > 0) {
//                mtdActTotal = mtdActTotal.add(fleetFuelUtil.getMtdAct(monthsOperatingCostList, truck));
//                counter++;
//            }
//        }
//        if (fleetFuelUtil.resetMonthToFirstDay(monthsOperatingCostList.get(monthsOperatingCostList.size() - 1).getTransactionDate()).after(fleetFuelUtil.resetMonthToFirstDay(startDate))) {
//            // Date range selected is out of DB data Range
//            if (monthRange == 3) {
//                isThreeMonthOutOfRange = true;
//                System.out.println("ThreeMonthOutOfRange: TRUE");
//            } else {
//                isTwelveMonthOutOfRange = true;
//                System.out.println("TwelveMonthOutOfRange: TRUE");
//            }
//        }

        return monthsOperatingCostList;
    }

    public void buildAnnualFuelSpendData(List<OperatingCost> operatingCostTwelveMonthsList) {
        List<OperatingCost> serviceTruckOperatingCostList = new ArrayList<>();
        List<OperatingCost> operationalTruckOperatingCostList = new ArrayList<>();
        List<OperatingCost> nonOperationalTruckOperatingCostList = new ArrayList<>();

        for (OperatingCost operatingCost : operatingCostTwelveMonthsList) {
            Truck truck = fleetFuelUtil.findTruckFromAllTruckListById(operatingCost.getTruckId());
            if (truck != null) {
                if (FleetFuelUtil.truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MMV")) {
                    nonOperationalTruckOperatingCostList.add(operatingCost);
                } else if (FleetFuelUtil.truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MOV")) {
                    operationalTruckOperatingCostList.add(operatingCost);
                } else {
                    serviceTruckOperatingCostList.add(operatingCost);
                }
            }
        }

        serviceTotalFuelSpend = fleetFuelUtil.sumOfFuelCostCalculation(serviceTruckOperatingCostList);
        operationalTotalFuelSpend = fleetFuelUtil.sumOfFuelCostCalculation(operationalTruckOperatingCostList);
        nonOperationalTotalFuelSpend = fleetFuelUtil.sumOfFuelCostCalculation(nonOperationalTruckOperatingCostList);
        //
        serviceFuelSpendPercentage = fleetFuelUtil.performFuelSpendPercentage(annualTotalFuelSpend, serviceTotalFuelSpend);
        operationalFuelSpendPercentage = fleetFuelUtil.performFuelSpendPercentage(annualTotalFuelSpend, operationalTotalFuelSpend);
        nonOperationalFuelSpendPercentage = fleetFuelUtil.performFuelSpendPercentage(annualTotalFuelSpend, nonOperationalTotalFuelSpend);
    }

    public void displayCharts() {
        chartPeriod = fuelSpendMonthlyCostBeanList.get(0).getMonth() + " - " + fuelSpendMonthlyCostBeanList.get(fuelSpendMonthlyCostBeanList.size() - 1).getMonth();
        chartPeriodCount = fuelSpendMonthlyCostBeanList.size();

        // Assuming it is sorted by Date in Asc Order
        DCharts dTotalFleetFuelSpendChart = createTotalFleetFuelSpendChart();
        DCharts dTotalDieselPriceRandPerLitreChart = createDieselPriceRandPerLitreChart();

        // Create a Panel
        Panel totalTotalFleetFuelSpendPanel = new Panel("Total Fleet Fuel Spend: " + chartPeriod); //
        totalTotalFleetFuelSpendPanel.setWidth("100%");
        totalTotalFleetFuelSpendPanel.setHeight("100%");
        totalTotalFleetFuelSpendPanel.setStyleName("bubble");
        totalTotalFleetFuelSpendPanel.setSizeUndefined(); // Shrink to fit content

        HorizontalLayout totalTotalFleetFuelSpendLayout = new HorizontalLayout();
        totalTotalFleetFuelSpendLayout.setMargin(true); //
        totalTotalFleetFuelSpendLayout.setSizeUndefined(); // Shrink to fit content
        totalTotalFleetFuelSpendLayout.addComponent(dTotalFleetFuelSpendChart);
        // Add item to the Panel
        totalTotalFleetFuelSpendPanel.setContent(totalTotalFleetFuelSpendLayout);
        // ========

        // Create a Panel
        Panel dieselPriceRandPerLitrePanel = new Panel("Diesel Price (R/Ltr): " + chartPeriod); //
        dieselPriceRandPerLitrePanel.setWidth("100%");
        dieselPriceRandPerLitrePanel.setHeight("100%");
        dieselPriceRandPerLitrePanel.setStyleName("bubble");
        dieselPriceRandPerLitrePanel.setSizeUndefined(); // Shrink to fit content

        HorizontalLayout dieselPriceRandPerLitreLayout = new HorizontalLayout();
        dieselPriceRandPerLitreLayout.setMargin(true); //
        dieselPriceRandPerLitreLayout.setSizeUndefined(); // Shrink to fit content
        dieselPriceRandPerLitreLayout.addComponent(dTotalDieselPriceRandPerLitreChart);
        // Add item to the Panel
        dieselPriceRandPerLitrePanel.setContent(dieselPriceRandPerLitreLayout);
        // ========

        PanelEfficiency oneMonthEfficiencyPanel = new PanelEfficiency("Total Fleet 1M Efficiency");
        PanelEfficiency threeMonthEfficiencyPanel = new PanelEfficiency("Total Fleet 3M Efficiency");
        PanelEfficiency twelveMonthEfficiencyPanel = new PanelEfficiency("Total Fleet 12M Efficiency");
        PanelEfficiency fuelSpendPanel = new PanelEfficiency("Fuel Spend");
        PanelEfficiency annualFuelSpendPanel = new PanelEfficiency("Annual Fuel Spend");
        //
        EfficiencylLayout efficiencylLayout = new EfficiencylLayout();
        oneMonthEfficiencyPanel.setContent(efficiencylLayout.getEfficiencyLayout(oneMonthEfficiency, oneMonthEfficiencyFlag));
        efficiencylLayout = new EfficiencylLayout();
        threeMonthEfficiencyPanel.setContent(efficiencylLayout.getEfficiencyLayout(threeMonthEfficiency, threeMonthEfficiencyFlag));
        efficiencylLayout = new EfficiencylLayout();
        twelveMonthEfficiencyPanel.setContent(efficiencylLayout.getEfficiencyLayout(twelveMonthEfficiency, twelveMonthEfficiencyFlag));
        //
        final FuelSpendLayout fuelSpendLayout = new FuelSpendLayout();
        // fuelSpendPanel.setContent(fuelSpendLayout.getFuelSpendLayout(fuelSpendPerUnit, fuelSpendPerService, fuelSpendUnitFlag, fuelSpendServiceFlag));
        //
        final AnnualFuelSpendLayout annualFuelSpendLayout = new AnnualFuelSpendLayout();
        annualFuelSpendPanel.setContent(annualFuelSpendLayout.getFuelSpendLayout(annualTotalFuelSpend, serviceTotalFuelSpend, operationalTotalFuelSpend, nonOperationalTotalFuelSpend, serviceFuelSpendPercentage, operationalFuelSpendPercentage, nonOperationalFuelSpendPercentage));
        //
        chart.chartVerticalLayout.removeAllComponents();
        chart.chartVerticalLayout.addComponent(totalTotalFleetFuelSpendPanel);
        chart.chartVerticalLayout.addComponent(dieselPriceRandPerLitrePanel);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.addComponent(oneMonthEfficiencyPanel);
        horizontalLayout.addComponent(threeMonthEfficiencyPanel);
        horizontalLayout.addComponent(twelveMonthEfficiencyPanel);
//        horizontalLayout.addComponent(fuelSpendPanel);
        horizontalLayout.addComponent(annualFuelSpendPanel);
        chart.chartVerticalLayout.addComponent(horizontalLayout);
        // house cleaning
        grandTotalFuelSpend = BigDecimal.ZERO;
        annualTotalFuelSpend = BigDecimal.ZERO;
        startDate = null;
        endDate = null;
        annualMileageSumAllTrucks = new Integer("0");
    }

    public DCharts createTotalFleetFuelSpendChart() {
        TotalFleetFuelSpendBarChart totalFleetFuelSpendChart = new TotalFleetFuelSpendBarChart();
        return totalFleetFuelSpendChart.createChart(fuelSpendMonthlyCostBeanList, grandTotalFuelSpend);
    }

    public DCharts createDieselPriceRandPerLitreChart() {
        DieselPriceRandPerLitreLineChart dieselPriceRandPerLitreChart = new DieselPriceRandPerLitreLineChart();
        return dieselPriceRandPerLitreChart.createChart(fuelSpendMonthlyCostBeanList);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addListeners() {
//        //Register Button Listeners
//        form.save.addClickListener((Button.ClickListener) this);
//        form.edit.addClickListener((Button.ClickListener) this);
//        form.cancel.addClickListener((Button.ClickListener) this);
//        form.update.addClickListener((Button.ClickListener) this);
//        form.delete.addClickListener((Button.ClickListener) this);
        //
        form.startDate.addValueChangeListener((Property.ValueChangeListener) this);
        form.endDate.addValueChangeListener((Property.ValueChangeListener) this);
    }
////    public void clearZeroObjects() {
////        // Clear objects with zeros for  mileage, fuelCost, fuelLitres, .slipNo("0000") in operatingCostTwelveMonthsList and dateRangeOperatingCostList
////        for (OperatingCost operatingCost : operatingCostTwelveMonthsList) {
////            if (operatingCost.getSpeedometer() <= 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0 && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0) {
////                int index = operatingCostTwelveMonthsList.indexOf(operatingCost);
//////                operatingCostTwelveMonthsList.remove(operatingCost);
////                operatingCostTwelveMonthsList.remove(index);
////            }
////        }
////
//////        for (OperatingCost operatingCost : dateRangeOperatingCostList) {
//////            if (operatingCost.getSpeedometer() <= 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0 && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0) {
//////                int index = dateRangeOperatingCostList.indexOf(operatingCost);
////////                dateRangeOperatingCostList.remove(operatingCost);
//////                dateRangeOperatingCostList.remove(index);
//////            }
//////        }
////    }
}
