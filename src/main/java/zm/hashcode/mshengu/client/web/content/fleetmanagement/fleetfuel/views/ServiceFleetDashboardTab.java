/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
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
import zm.hashcode.mshengu.app.util.panel.PanelEfficiency;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.FleetFuelMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.ServiceFleetDashboardChartUI;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.OneMonthEfficiencyLineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.ThreeMonthEfficiencyLineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.TotalFleetFuelSpendBarChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.TotalMonthlyMileageLineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.forms.ServiceFleetDashboardForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.FuelSpendMonthlyCostBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.MonthlyMileageTotalBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.ServiceFleetOneMonthlyEfficiencyBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.ServiceFleetThreeMonthlyEfficiencyBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.util.EfficiencylLayout;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.util.FleetFuelUtil;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Colin
 */
public class ServiceFleetDashboardTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final ServiceFleetDashboardForm form;
    private final ServiceFleetDashboardChartUI chart;
    private final FleetFuelUtil fleetFuelUtil = new FleetFuelUtil();
    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    public static Date startDate = null;
    public static Date endDate = null;
    public static List<OperatingCost> operatingCostTwelveMonthsList = new ArrayList<>();
    public static List<OperatingCost> dateRangeOperatingCostList = new ArrayList<>();
    public static List<FuelSpendMonthlyCostBean> fuelSpendMonthlyCostBeanList = new ArrayList<>();
    public static List<MonthlyMileageTotalBean> monthlyMileageTotalBeanList = new ArrayList<>();
    //
    public static List<ServiceFleetOneMonthlyEfficiencyBean> serviceFleetOneMonthlyEfficiencyBeanList = new ArrayList<>();
    public static List<ServiceFleetThreeMonthlyEfficiencyBean> serviceFleetThreeMonthlyEfficiencyBeanList = new ArrayList<>();
    public static List<ServiceFleetThreeMonthlyEfficiencyBean> serviceFleetDriverEfficiencyBeanList = new ArrayList<>();
    //
    public static BigDecimal grandTotalFuelSpend = BigDecimal.ZERO;
    public static Integer grandTotalMileage = new Integer("0");
    public static String chartPeriod = null;
    public static Integer chartPeriodCount = null;

    public ServiceFleetDashboardTab(MshenguMain app) {
        main = app;
        form = new ServiceFleetDashboardForm();
        chart = new ServiceFleetDashboardChartUI(main);
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

    private void getDataAndPerformCharts() {
        dateRangeOperatingCostList.clear();
        operatingCostTwelveMonthsList.clear();
        if (endDate.before(fleetFuelUtil.resetMonthToFirstDay(new Date()))) {
            // Determine date range for other calculations on this Tab, extra 3 month for Previous month mileage
            // or get StartMileage for Car
            fleetFuelUtil.determineDateRange(endDate, 25); // Determine date range for other calculations on this Tab
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
                    buildFuelSpendMonthlyCostBeanList(dateRangeOperatingCostList); // used for Bar Chart
                    buildMonthlyMileageTotalBeanList(dateRangeOperatingCostList); // used for Line Chart
                    buildServiceFleetOneMonthEfficiencyBeanList();
                    buildServiceFleetThreeMonthEfficiencyBeanList();

//                    //
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
        Collections.sort(dateRangeOperatingCostList); // ,OperatingCost.AscOrderDateAscOrderTruckIdComparator
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
                    performFleetFuelSpendSubTotal(monthTotal, dateRangeOperatingCostList, operatingCost, randPerLitre, counter);
                }

            } else {
                // Subtotal
                counter++;
                performFleetFuelSpendSubTotal(monthTotal, dateRangeOperatingCostList, operatingCost, randPerLitre, counter);
                //Reset
                monthTotal = operatingCost.getFuelCost();
                transactionDate = fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate());
            }
        }
    }

    private void performFleetFuelSpendSubTotal(BigDecimal monthTotal, List<OperatingCost> dateRangeOperatingCostList, OperatingCost operatingCost, BigDecimal randPerLitre, int counter) {
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

    public void buildMonthlyMileageTotalBeanList(List<OperatingCost> dateRangeOperatingCostList) {
        // SORT THE LIST BY DATE ASC then by TRUCK //
        Collections.sort(dateRangeOperatingCostList, OperatingCost.DescOrderDateAscOrderTruckIdComparator); //
        Date endDatee = fleetFuelUtil.resetMonthToLastDay(endDate);
        Date startDatee = fleetFuelUtil.resetMonthToLastDay(startDate);
        monthlyMileageTotalBeanList.clear();
        List<OperatingCost> truckMonthOperatingCostList = new ArrayList<>();
        Integer monthlyMileageTotal = new Integer("0");
        int counter = 0;
        //
        Calendar calendar = Calendar.getInstance();
        for (calendar.setTime(endDatee); calendar.getTime().after(startDatee) || calendar.getTime().compareTo(startDatee) == 0; calendar.add(Calendar.MONTH, -1)) {
            for (Truck truck : FleetFuelUtil.serviceTrucks) {
                truckMonthOperatingCostList.clear();
                truckMonthOperatingCostList.addAll(getOneMonthOperatingCostForTruck(dateRangeOperatingCostList, calendar.getTime(), truck));

                Integer monthMileageSum = new Integer("0");
                if (!truckMonthOperatingCostList.isEmpty()) {
                    monthMileageSum = fleetFuelUtil.calculateMonthMileageTotal(truckMonthOperatingCostList, truck);
                    monthlyMileageTotal += monthMileageSum;
                }
//                //======= DELETE =========
//                System.out.println("Truck Monthly Mileage Total: Truck= " + truck.getVehicleNumber() + " " + truckMonthOperatingCostList.get(0).getTruckId() + " Month= " + calendar.getTime() + " Mileage Total= " + monthMileageSum);
//                // ======= DELETE =========
            }

//            //======= DELETE =========
//            System.out.println("Monthly Mileage Total: Truck= " + " Month= " + calendar.getTime() + " Mileage Total= " + monthlyMileageTotal);
//            System.out.println("//=============================//");
//            // ======= DELETE =========

            // build MonthlyMileageTotalBean List for month
            performMonthlyMileageSubTotal(monthlyMileageTotal, calendar.getTime(), counter);
            counter++;
            monthlyMileageTotal = new Integer("0");
        }
    }

    private void performMonthlyMileageSubTotal(Integer monthlyMileageTotal, Date mileageTotalMonth, int counter) {
        grandTotalMileage += monthlyMileageTotal;
        monthlyMileageTotalBeanList.add(buildMonthlyMileageTotalBeanBeanObject(monthlyMileageTotal, mileageTotalMonth, counter));
    }

    private MonthlyMileageTotalBean buildMonthlyMileageTotalBeanBeanObject(Integer monthlyMileageTotal, Date mileageTotalMonth, int counter) {
        MonthlyMileageTotalBean monthlyMileageTotalBean = new MonthlyMileageTotalBean();
        monthlyMileageTotalBean.setId(counter + "");
        monthlyMileageTotalBean.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(mileageTotalMonth.toString()));
        monthlyMileageTotalBean.setTransactionMonth(mileageTotalMonth);
        monthlyMileageTotalBean.setMonthlyMileageTotal(monthlyMileageTotal);

        return monthlyMileageTotalBean;
    }

    public List<OperatingCost> getOneMonthOperatingCostForTruck(List<OperatingCost> dateRangeOperatingCostList, Date date, Truck truck) {
        boolean found = false;
        List<OperatingCost> truckMonthOperatingCostList = new ArrayList<>();
        truckMonthOperatingCostList.clear();
        for (OperatingCost operatingCost : dateRangeOperatingCostList) {
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
    //(((((((((((((((((((((((())))))))))))))))))))))))))))))))))))//

    public void buildServiceFleetOneMonthEfficiencyBeanList() {
        // SORT THE LIST BY DATE ASC then by TRUCK //
        Collections.sort(operatingCostTwelveMonthsList, OperatingCost.DescOrderDateAscOrderTruckIdComparator); //
        Date endDatee = fleetFuelUtil.resetMonthToLastDay(endDate);
        Date nuStartDate = fleetFuelUtil.determineStartDate(endDate, 12);
//        Date startDatee = fleetFuelUtil.resetMonthToLastDay(nuStartDate);
        serviceFleetOneMonthlyEfficiencyBeanList.clear();
        int counter = 0;
        //
        Calendar calendar = Calendar.getInstance();
        for (calendar.setTime(endDatee); calendar.getTime().after(nuStartDate) || calendar.getTime().compareTo(nuStartDate) == 0; calendar.add(Calendar.MONTH, -1)) {
            BigDecimal efficiencyValue = getOneMonthServiceFleetEfficiency(calendar.getTime());
            serviceFleetOneMonthlyEfficiencyBeanList.add(buildServiceFleetOneMonthlyEfficiencyBeanObject(calendar.getTime(), efficiencyValue, counter));
            counter++;
        }
    }

    public BigDecimal getOneMonthServiceFleetEfficiency(Date monthDate) {
        boolean found = false;
        BigDecimal mtdActTotal = BigDecimal.ZERO;
        List<OperatingCost> truckMonthOperatingCostList = new ArrayList<>();
        Collections.sort(operatingCostTwelveMonthsList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
//        Date endDatee = fleetFuelUtil.resetMonthToFirstDay(operatingCostTwelveMonthsList.get(0).getTransactionDate());
        Date endDatee = fleetFuelUtil.resetMonthToFirstDay(monthDate);
        int counter = 0; // counter for Number of Truck with operating Cost data for the period specified

        for (Truck truck : FleetFuelUtil.serviceTrucks) { //  Not using allTrucks b/c Ops and Non-Ops Trucks dont have mileage entered
            truckMonthOperatingCostList.clear();
            for (OperatingCost operatingCost : operatingCostTwelveMonthsList) {
                if (!(operatingCost.getSpeedometer() <= 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0
                        && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0)) {
                    if (operatingCost.getTruckId().equals(truck.getId())
                            && endDatee.compareTo(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate())) == 0) {
                        truckMonthOperatingCostList.add(operatingCost);
                        found = true;
                    }
                }
                // if Date changes, then it is no longer End Date as we sorted in Desc Order
                if (found && endDatee.after(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()))) {
                    break;
                }
            }
            // getMTDAct for Truck
            if (truckMonthOperatingCostList.size() > 0) {
                mtdActTotal = mtdActTotal.add(fleetFuelUtil.getMtdAct(truckMonthOperatingCostList, truck));
                counter++; // Counter should increment per truck with Data for specified period
            }
        }
//        System.out.println("MTDAct for all service Trucks for 1M Efficiency= " + mtdActTotal + ", No of Service Trucks with Data for last month in range= " + counter);
        // getMTDAct for ALL Truck
        return fleetFuelUtil.performMtdActAverageCalc(mtdActTotal, counter);
    }

    public ServiceFleetOneMonthlyEfficiencyBean buildServiceFleetOneMonthlyEfficiencyBeanObject(Date date, BigDecimal efficiencyValue, int counter) {
        ServiceFleetOneMonthlyEfficiencyBean serviceFleetOneMonthlyEfficiencyBean = new ServiceFleetOneMonthlyEfficiencyBean();
        serviceFleetOneMonthlyEfficiencyBean.setId(counter + "");
        serviceFleetOneMonthlyEfficiencyBean.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(date.toString()));
        serviceFleetOneMonthlyEfficiencyBean.setMonthlyEfficiencyValue(efficiencyValue);
        serviceFleetOneMonthlyEfficiencyBean.setTransactionMonth(date);

        return serviceFleetOneMonthlyEfficiencyBean;
    }

    public void buildServiceFleetThreeMonthEfficiencyBeanList() {
        Date endDatee = fleetFuelUtil.resetMonthToLastDay(endDate);
        Date nuStartDate = fleetFuelUtil.determineStartDate(endDate, 12);
//        Date startDatee = fleetFuelUtil.resetMonthToLastDay(nuStartDate);
        serviceFleetThreeMonthlyEfficiencyBeanList.clear();
        int counter = 0;
        //
        Calendar calendar = Calendar.getInstance();
        for (calendar.setTime(endDatee); calendar.getTime().after(nuStartDate) || calendar.getTime().compareTo(nuStartDate) == 0; calendar.add(Calendar.MONTH, -1)) {
            //////
            BigDecimal threeMonthsEfficiencyValue = BigDecimal.ZERO;
            BigDecimal allTrucksThreeMonthFuelCost = BigDecimal.ZERO;
            Integer allTrucksThreeMonthsMileageSum = new Integer("0");

            List<OperatingCost> allTrucksThreeMonthsOperatingCostList = getOperatingCostforMonthsList(operatingCostTwelveMonthsList, calendar.getTime(), 3);
            Date threeMonthStartDate = fleetFuelUtil.determineStartDate(calendar.getTime(), 3);
            Date threeMonthstartDatee = fleetFuelUtil.resetMonthToLastDay(threeMonthStartDate);

            for (Truck truck : FleetFuelUtil.serviceTrucks) {
                List<OperatingCost> currentTruckThreeMonthsOperatingCostList = new ArrayList<>();

//                currentTruckOneMonthOperatingCostList.clear();
                Calendar threeMonthCalendar = Calendar.getInstance();
                for (threeMonthCalendar.setTime(calendar.getTime()); threeMonthCalendar.getTime().after(threeMonthstartDatee) || threeMonthCalendar.getTime().compareTo(threeMonthstartDatee) == 0; threeMonthCalendar.add(Calendar.MONTH, -1)) {
                    List<OperatingCost> currentTruckOneMonthOperatingCostList = new ArrayList<>();
                    currentTruckOneMonthOperatingCostList.addAll(getOneMonthlyOperatingCostForTruck(allTrucksThreeMonthsOperatingCostList, threeMonthCalendar.getTime(), truck));

                    if (!currentTruckOneMonthOperatingCostList.isEmpty()) {
                        allTrucksThreeMonthsMileageSum += fleetFuelUtil.calculateMonthMileageTotal(currentTruckOneMonthOperatingCostList, truck);

////                    //======= DELETE =========
////                    for (OperatingCost operatingCost : truckMonthOperatingCostList) {
////                        System.out.println("Truck THREE Month Operating Cost: Truck= " + truck.getVehicleNumber() + " " + operatingCost.getTruckId() + " Month= " + operatingCost.getTransactionDate() + " Mileage= " + operatingCost.getSpeedometer());
////                    }
////                    System.out.println("//=============================//");
////                    //======= DELETE =========
                        currentTruckThreeMonthsOperatingCostList.addAll(currentTruckOneMonthOperatingCostList);
//                        currentTruckOneMonthOperatingCostList.clear();
                    }
                }
                // increment the allTrucksThreeMonthFuelCost for all Trucks
                allTrucksThreeMonthFuelCost.add(fleetFuelUtil.sumOfFuelCostCalculation(currentTruckThreeMonthsOperatingCostList));
//                //========== DELETE ==============
//                System.out.println("SUM OF 3 Month FUEL COST: " + allTrucksThreeMonthFuelCost);
//                //========== DELETE ==============
//                System.out.println("3 Month Efficiency: MILEAGE SUM= " + allTrucksThreeMonthsMileageSum);


            }
            try {
                threeMonthsEfficiencyValue = allTrucksThreeMonthFuelCost.divide(new BigDecimal(allTrucksThreeMonthsMileageSum + ""), 2, RoundingMode.HALF_UP);
                System.out.println("Month is " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(calendar.getTime().toString()) + ": threeMonthsEfficiencyValue is " + threeMonthsEfficiencyValue + " = totalFuelCostAllTrucks (" + allTrucksThreeMonthFuelCost + ") / totalMileageSumAllTrucks (" + allTrucksThreeMonthsMileageSum);
            } catch (ArithmeticException a) {
                System.out.println("Month is " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(calendar.getTime().toString()) + ": totalFuelCostAllTrucks (" + allTrucksThreeMonthFuelCost + ") / totalMileageSumAllTrucks (" + allTrucksThreeMonthsMileageSum + ") | A Divide By Zero exception (ArithmeticException) caught");
//            Notification.show("Error. A Calculation is trying to divide by ZERO. Reason for 0.00 per KM.", Notification.Type.TRAY_NOTIFICATION);
                threeMonthsEfficiencyValue = BigDecimal.ZERO;
            }

            // BUILD A serviceFleetThreeMonthlyEfficiencyBeanList BEAN WITH THE MONTH AND threeMonthsEfficiencyValue VALUE and Counter++
            serviceFleetThreeMonthlyEfficiencyBeanList.add(buildServiceFleetThreeMonthlyEfficiencyBeanObject(counter, calendar.getTime(), threeMonthsEfficiencyValue));
            counter++;
        }


    }
//(((((((((((((((((((((((())))))))))))))))))))))))))))))))))))//

    private ServiceFleetThreeMonthlyEfficiencyBean buildServiceFleetThreeMonthlyEfficiencyBeanObject(int counter, Date month, BigDecimal threeMonthsEfficiencyValue) {
        ServiceFleetThreeMonthlyEfficiencyBean serviceFleetThreeMonthlyEfficiencyBean = new ServiceFleetThreeMonthlyEfficiencyBean();
        serviceFleetThreeMonthlyEfficiencyBean.setId(counter + "");
        serviceFleetThreeMonthlyEfficiencyBean.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(month.toString()));
        serviceFleetThreeMonthlyEfficiencyBean.setMonthlyEfficiencyValue(threeMonthsEfficiencyValue);
        serviceFleetThreeMonthlyEfficiencyBean.setTransactionMonth(month);
        return serviceFleetThreeMonthlyEfficiencyBean;
    }

    public void displayCharts() {
        chartPeriod = fuelSpendMonthlyCostBeanList.get(0).getMonth() + " - " + fuelSpendMonthlyCostBeanList.get(fuelSpendMonthlyCostBeanList.size() - 1).getMonth();
        chartPeriodCount = fuelSpendMonthlyCostBeanList.size();

        // Assuming it is sorted by Date in Asc Order
        DCharts dTotalFleetFuelSpendChart = createTotalFleetFuelSpendChart();
        DCharts dTotalMonthlyMileageChart = createTotalMonthlyMileageChart();
        DCharts dOneMonthEfficiencyChart = createOneMonthEfficiencyChart();
        DCharts dThreeMonthEfficiencyChart = createThreeMonthEfficiencyChart();
//DCharts dDriverEfficiencyChart = createDriverEfficiencyChart();

        // Create a Panel
        Panel totalTotalFleetFuelSpendPanel = new Panel("Service Vehicles - Monthly Fuel Spend: " + chartPeriod); //
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
        Panel dieselPriceRandPerLitrePanel = new Panel("Service Vehicles - Monthly Mileage: " + chartPeriod); //
        dieselPriceRandPerLitrePanel.setWidth("100%");
        dieselPriceRandPerLitrePanel.setHeight("100%");
        dieselPriceRandPerLitrePanel.setStyleName("bubble");
        dieselPriceRandPerLitrePanel.setSizeUndefined(); // Shrink to fit content

        HorizontalLayout dieselPriceRandPerLitreLayout = new HorizontalLayout();
        dieselPriceRandPerLitreLayout.setMargin(true); //
        dieselPriceRandPerLitreLayout.setSizeUndefined(); // Shrink to fit content
        dieselPriceRandPerLitreLayout.addComponent(dTotalMonthlyMileageChart);
        // Add item to the Panel
        dieselPriceRandPerLitrePanel.setContent(dieselPriceRandPerLitreLayout);
        // ========

        PanelEfficiency oneMonthEfficiencyPanel = new PanelEfficiency("1 Monthly Fuel Efficiency (R/Km)");
        PanelEfficiency threeMonthEfficiencyPanel = new PanelEfficiency("3 Monthly Fuel Efficiency (R/Km)");
//        PanelEfficiency driverMonthEfficiencyPanel = new PanelEfficiency("Driver Efficiency (R/Km Usage) - " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(endDate.toString()));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        HorizontalLayout oneMonthEfficiencyLayout = new HorizontalLayout();
//        oneMonthEfficiencyLayout.setMargin(true); //
//        oneMonthEfficiencyLayout.setSizeUndefined(); // Shrink to fit content
//        oneMonthEfficiencyLayout.addComponent(dOneMonthEfficiencyChart);// add chart


//HorizontalLayout threeMonthEfficiencyLayout = new HorizontalLayout();
//        threeMonthEfficiencyLayout.setMargin(true); //
//        threeMonthEfficiencyLayout.setSizeUndefined(); // Shrink to fit content
//        threeMonthEfficiencyLayout.addComponent();// add chart

//
//HorizontalLayout driverMonthEfficiencyLayout = new HorizontalLayout();
//        driverMonthEfficiencyLayout.setMargin(true); //
//        driverMonthEfficiencyLayout.setSizeUndefined(); // Shrink to fit content
//        driverMonthEfficiencyLayout.addComponent(dDriverMonthEfficiencyChart);// add chart
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        oneMonthEfficiencyPanel.setContent(dOneMonthEfficiencyChart); // oneMonthEfficiencyLayout
        threeMonthEfficiencyPanel.setContent(dThreeMonthEfficiencyChart);//threeMonthEfficiencyLayout
//        driverMonthEfficiencyPanel.setContent(dDriverEfficiencyChart); // driverMonthEfficiencyLayout

        chart.chartVerticalLayout.removeAllComponents();
        chart.chartVerticalLayout.addComponent(totalTotalFleetFuelSpendPanel);
        chart.chartVerticalLayout.addComponent(dieselPriceRandPerLitrePanel);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.addComponent(oneMonthEfficiencyPanel);
        horizontalLayout.addComponent(threeMonthEfficiencyPanel);
//        horizontalLayout.addComponent(driverEfficiencyPanel);

        chart.chartVerticalLayout.addComponent(horizontalLayout);
        // house cleaning
        grandTotalFuelSpend = BigDecimal.ZERO;
        grandTotalMileage = new Integer("0");
        startDate = null;
        endDate = null;
////        annualMileageSumAllTrucks = new Integer("0");
    }

    public DCharts createTotalFleetFuelSpendChart() {
        TotalFleetFuelSpendBarChart totalFleetFuelSpendChart = new TotalFleetFuelSpendBarChart();
        return totalFleetFuelSpendChart.createChart(fuelSpendMonthlyCostBeanList, grandTotalFuelSpend);
    }

    public DCharts createTotalMonthlyMileageChart() {
        TotalMonthlyMileageLineChart totalMonthlyMileageLineChart = new TotalMonthlyMileageLineChart();
        return totalMonthlyMileageLineChart.createChart(monthlyMileageTotalBeanList, grandTotalMileage);
    }

    public DCharts createOneMonthEfficiencyChart() {
        OneMonthEfficiencyLineChart oneMonthEfficiencyLineChart = new OneMonthEfficiencyLineChart();
        return oneMonthEfficiencyLineChart.createChart(serviceFleetOneMonthlyEfficiencyBeanList);
    }

    public DCharts createThreeMonthEfficiencyChart() {
        ThreeMonthEfficiencyLineChart threeMonthEfficiencyLineChart = new ThreeMonthEfficiencyLineChart();
        return threeMonthEfficiencyLineChart.createChart(serviceFleetThreeMonthlyEfficiencyBeanList);
    }
//     public DCharts createDriverEfficiencyChart(){
//        DriverEfficiencyLineChart driverMonthEfficiencyLineChart = new DriverMonthEfficiencyLineChart();
//        return driverMonthEfficiencyLineChart.createChart(serviceFleetDriverEfficiencyBeanList);
//    }

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
}
