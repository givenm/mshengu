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
import zm.hashcode.mshengu.app.util.flagImages.FlagImage;
import zm.hashcode.mshengu.app.util.panel.PanelEfficiency;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.FleetFuelMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.ServiceFleetDashboardChartUI;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.servicefleetdashboard.DriverEfficiencyChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.servicefleetdashboard.OneMonthEfficiencyLineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.servicefleetdashboard.ThreeMonthEfficiencyLineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.executivedashboard.TotalFleetFuelSpendBarChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.servicefleetdashboard.TotalMonthlyMileageLineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.forms.ServiceFleetDashboardForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.servicefleetdashboard.DriverEfficiencyBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.executivedashboard.FuelSpendMonthlyCostBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.servicefleetdashboard.MonthlyMileageTotalBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.servicefleetdashboard.ServiceFleetOneMonthlyEfficiencyBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.servicefleetdashboard.ServiceFleetThreeMonthlyEfficiencyBean;
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
    private final FlagImage flagImage = new FlagImage();
    public static Date startDate = null;
    public static Date endDate = null;
    public static List<OperatingCost> operatingCostTwentyFiveMonthsList = new ArrayList<>();
    public static List<OperatingCost> dateRangeOperatingCostList = new ArrayList<>();
    public static List<FuelSpendMonthlyCostBean> fuelSpendMonthlyCostBeanList = new ArrayList<>();
    public static List<MonthlyMileageTotalBean> monthlyMileageTotalBeanList = new ArrayList<>();
    //
    public static List<ServiceFleetOneMonthlyEfficiencyBean> serviceFleetOneMonthlyEfficiencyBeanList = new ArrayList<>();
    public static List<ServiceFleetThreeMonthlyEfficiencyBean> serviceFleetThreeMonthlyEfficiencyBeanList = new ArrayList<>();
    public static List<DriverEfficiencyBean> driverEfficiencyBeanList = new ArrayList<>();
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
                if (endDate.before(fleetFuelUtil.resetMonthToFirstDay(new Date()))) {
                    int monthCount = fleetFuelUtil.countMonthsInRange(startDate, endDate);
                    if (monthCount > 0 && monthCount <= 12) {
                        getDataAndPerformCharts();
                    } else {
                        Notification.show("Please Specify Date Range in Approprate Order and at most 12 months.", Notification.Type.TRAY_NOTIFICATION);
                    }
                } else {
                    Notification.show("Error. Select any month before Current Month as End Date.", Notification.Type.TRAY_NOTIFICATION);
                }
            }
        } else if (property == form.endDate) {
            endDate = fleetFuelUtil.resetMonthToLastDay(form.endDate.getValue());
            try {
                startDate = fleetFuelUtil.resetMonthToFirstDay(form.startDate.getValue());
            } catch (java.lang.NullPointerException ex) {
            }
            if (startDate != null) {
                if (endDate.before(fleetFuelUtil.resetMonthToFirstDay(new Date()))) {
                    int monthCount = fleetFuelUtil.countMonthsInRange(startDate, endDate);
                    if (monthCount > 0 && monthCount <= 12) {
                        getDataAndPerformCharts();
                    } else {
                        Notification.show("Please Specify Date Range in Approprate Order and at most 12 months.", Notification.Type.TRAY_NOTIFICATION);
                    }
                } else {
                    Notification.show("Error. Select any month before Current Month as End Date.", Notification.Type.TRAY_NOTIFICATION);
                }
            }
        }
    }

    private void getDataAndPerformCharts() {
        dateRangeOperatingCostList.clear();
        operatingCostTwentyFiveMonthsList.clear();
        // Determine date range for other calculations on this Tab, extra 3 month for Previous month mileage
        // or get StartMileage for Car
        fleetFuelUtil.determineDateRange(endDate, 25); // Determine date range for other calculations on this Tab
        fleetFuelUtil.getTrucks();
        operatingCostTwentyFiveMonthsList.addAll(fleetFuelUtil.getOperatingCostByTruckBetweenTwoDates(FleetFuelUtil.startDate, FleetFuelUtil.endDate));
        Collections.sort(operatingCostTwentyFiveMonthsList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
        // Extract date Range for Charts only
        dateRangeOperatingCostList.addAll(fleetFuelUtil.getOperatingCostsForDateRange(startDate, endDate, operatingCostTwentyFiveMonthsList));
        if (!dateRangeOperatingCostList.isEmpty()) {
            buildFuelSpendMonthlyCostBeanList(); // used for Bar Chart
            buildMonthlyMileageTotalBeanList(); // used for Line Chart
            Collections.sort(monthlyMileageTotalBeanList);
            buildServiceFleetOneMonthEfficiencyBeanList();
            Collections.sort(serviceFleetOneMonthlyEfficiencyBeanList);
            buildServiceFleetThreeMonthEfficiencyBeanList();
            Collections.sort(serviceFleetThreeMonthlyEfficiencyBeanList);
            buildDriverEfficiencyBeanList();
            Collections.sort(driverEfficiencyBeanList, DriverEfficiencyBean.DescendingEfficiencyComparator);
            //
            displayCharts();
        } else {
            Notification.show("No Daily input Found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    public void buildFuelSpendMonthlyCostBeanList() {

        BigDecimal randPerLitre = BigDecimal.ZERO;
//        DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        // SORT THE LIST BY DATE ASC
        Collections.sort(dateRangeOperatingCostList); // ,OperatingCost.AscOrderDateAscOrderTruckIdComparator
        // LOOP AND SUBTOTAL using operatingCostTwentyFiveMonthsList BY dATE and add to fuelSpendMonthlyCostBeanList
        fuelSpendMonthlyCostBeanList.clear();
        BigDecimal monthTotal = BigDecimal.ZERO;
        Date transactionDate = fleetFuelUtil.resetMonthToFirstDay(dateRangeOperatingCostList.get(0).getTransactionDate());
        int counter = 0;
        List<OperatingCost> serviceTruckOperatingCostList = new ArrayList<>();
        for (OperatingCost operatingCost : dateRangeOperatingCostList) {
            Truck truck = fleetFuelUtil.findTruckFromAllTruckListById(operatingCost.getTruckId());
            if (truck != null) {
                if (FleetFuelUtil.truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MSV") || FleetFuelUtil.truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MUV")) {
                    serviceTruckOperatingCostList.add(operatingCost);
                }
            }
        }
        for (OperatingCost operatingCost : serviceTruckOperatingCostList) {
            if (fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()).equals(transactionDate)) {
                monthTotal = monthTotal.add(operatingCost.getFuelCost());
                for (Truck truckk : FleetFuelUtil.serviceTrucks) {
                    if (truckk.getId().equals(operatingCost.getTruckId())) {
                        if (FleetFuelUtil.truncate(truckk.getVehicleNumber(), 3).equalsIgnoreCase("MSV") && operatingCost.getRandPerLitre().compareTo(BigDecimal.ZERO) > 0) {
                            randPerLitre = operatingCost.getRandPerLitre();
                        }
                        break;
                    }
                }
                // Test for Last item in List and SubTotal
                if (serviceTruckOperatingCostList.indexOf(operatingCost) == serviceTruckOperatingCostList.size() - 1) {
                    counter++;
                    performFleetFuelSpendSubTotal(monthTotal, serviceTruckOperatingCostList, operatingCost, randPerLitre, counter);
                }

            } else {
                // Subtotal
                counter++;
                performFleetFuelSpendSubTotal(monthTotal, serviceTruckOperatingCostList, operatingCost, randPerLitre, counter);
                //Reset
                monthTotal = operatingCost.getFuelCost();
                transactionDate = fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate());
            }
        }
    }

    private void performFleetFuelSpendSubTotal(BigDecimal monthTotal, List<OperatingCost> serviceTruckOperatingCostList, OperatingCost operatingCost, BigDecimal randPerLitre, int counter) {
        // Subtotal
        monthTotal = monthTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        grandTotalFuelSpend = grandTotalFuelSpend.add(monthTotal);
        // Build FuelSpendMonthlyCostBean and add to ArrayList
        int currentIndex = serviceTruckOperatingCostList.indexOf(operatingCost);
        fuelSpendMonthlyCostBeanList.add(buildFuelSpendMonthlyCostBeanObject(counter, monthTotal, serviceTruckOperatingCostList.get(currentIndex - 1), randPerLitre));
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

    public void buildMonthlyMileageTotalBeanList() {
        // SORT THE LIST BY DATE DESC then by TRUCK ASC //
        Collections.sort(dateRangeOperatingCostList, OperatingCost.DescOrderDateAscOrderTruckIdComparator); //
        Date endDatee = fleetFuelUtil.resetMonthToFirstDay(endDate);
        Date startDatee = fleetFuelUtil.resetMonthToFirstDay(startDate);
        monthlyMileageTotalBeanList.clear();
        List<OperatingCost> truckMonthOperatingCostList = new ArrayList<>();
        Integer monthlyMileageTotal = new Integer("0");
        int counter = 0;
        //
        Calendar calendar = Calendar.getInstance();
        for (calendar.setTime(endDatee); calendar.getTime().after(startDatee) || calendar.getTime().compareTo(startDatee) == 0; calendar.add(Calendar.MONTH, -1)) {
            for (Truck truck : FleetFuelUtil.serviceTrucks) { // this TAB for Service/Utility Trucks Only // msvTrucks, serviceTrucks
                truckMonthOperatingCostList.clear();
                truckMonthOperatingCostList.addAll(getOneMonthOperatingCostForTruck(dateRangeOperatingCostList, calendar.getTime(), truck));

//                Integer monthMileageSum = new Integer("0");
                if (!truckMonthOperatingCostList.isEmpty()) {
//                    monthMileageSum = fleetFuelUtil.calculateMonthMileageTotal(truckMonthOperatingCostList, truck);
                    monthlyMileageTotal += fleetFuelUtil.calculateMonthMileageTotal(truckMonthOperatingCostList, truck);
                }
//                //======= DELETE =========
//                System.out.println("Truck Monthly Mileage Total: Truck= " + truck.getVehicleNumber() + " " + truckMonthOperatingCostList.get(0).getTruckId() + " Month= " + calendar.getTime() + " Mileage Total= " + fleetFuelUtil.calculateMonthMileageTotal(truckMonthOperatingCostList, truck));
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
        List<OperatingCost> truckMonthOperatingCostList = new ArrayList<>();
//        truckMonthOperatingCostList.clear();
        for (OperatingCost operatingCost : dateRangeOperatingCostList) {
            if (operatingCost.getTruckId().equals(truck.getId())
                    && date.compareTo(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate())) == 0) {
                truckMonthOperatingCostList.add(operatingCost);
            }
            // if Date changes, then it is no longer End Date as we sorted in Desc Order
            if (date.after(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()))) {
                break;
            }
        }

        return truckMonthOperatingCostList;
    }

    public void buildServiceFleetOneMonthEfficiencyBeanList() {
//        // SORT THE LIST BY DATE DESC then by TRUCK ASC //
//        Collections.sort(operatingCostTwentyFiveMonthsList, OperatingCost.DescOrderDateAscOrderTruckIdComparator); //
        Date endDatee = fleetFuelUtil.resetMonthToFirstDay(endDate);
        Date startDatee = fleetFuelUtil.determineStartDate(endDatee, 12);
        serviceFleetOneMonthlyEfficiencyBeanList.clear();
        int counter = 0;
        //
        Calendar calendar = Calendar.getInstance();
        for (calendar.setTime(endDatee); calendar.getTime().after(startDatee) || calendar.getTime().compareTo(startDatee) == 0; calendar.add(Calendar.MONTH, -1)) {
            BigDecimal efficiencyValue = getOneMonthServiceFleetEfficiency(calendar.getTime());
            serviceFleetOneMonthlyEfficiencyBeanList.add(buildServiceFleetOneMonthlyEfficiencyBeanObject(calendar.getTime(), efficiencyValue, counter));
            counter++;
        }
    }

    private BigDecimal getOneMonthServiceFleetEfficiency(Date monthDate) { // monthDate has been reset to 1st of month from calling method
        boolean found = false;
        BigDecimal mtdActTotal = BigDecimal.ZERO;
        List<OperatingCost> truckMonthOperatingCostList = new ArrayList<>();
//        Collections.sort(operatingCostTwentyFiveMonthsList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
        int counter = 0; // counter for Number of Truck with operating Cost data for the period specified

        for (Truck truck : FleetFuelUtil.serviceTrucks) { //  This Tab is for Service/Utility Trucks ONLY
            truckMonthOperatingCostList.clear();
            for (OperatingCost operatingCost : operatingCostTwentyFiveMonthsList) {
                if (!(operatingCost.getSpeedometer() <= 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0
                        && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0)) {
                    if (operatingCost.getTruckId().equals(truck.getId())
                            && monthDate.compareTo(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate())) == 0) {
                        truckMonthOperatingCostList.add(operatingCost);
                        found = true;
                    }
                }
                // if Date changes, then it is no longer End Date as we sorted in Desc Order
                if (found && monthDate.after(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()))) {
                    break;
                }
            }
            // getMTDAct for Truck
            if (truckMonthOperatingCostList.size() > 0) {
                mtdActTotal = mtdActTotal.add(fleetFuelUtil.getMtdAct(truckMonthOperatingCostList, truck));
//                System.out.println("Month= " + truckMonthOperatingCostList.get(0).getTransactionDate() + ", Truck= " + truck.getVehicleNumber() + ", MTDAct= " + fleetFuelUtil.getMtdAct(truckMonthOperatingCostList, truck));
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
        Date endDatee = fleetFuelUtil.resetMonthToFirstDay(endDate);
        Date startDatee = fleetFuelUtil.determineStartDate(endDatee, 12);
        serviceFleetThreeMonthlyEfficiencyBeanList.clear();
        int counter = 0;
        Calendar calendar = Calendar.getInstance();
        for (calendar.setTime(endDatee); calendar.getTime().after(startDatee) || calendar.getTime().compareTo(startDatee) == 0; calendar.add(Calendar.MONTH, -1)) {
            BigDecimal efficiencyValue = getThreeMonthServiceFleetEfficiency(calendar.getTime());
            serviceFleetThreeMonthlyEfficiencyBeanList.add(buildServiceFleetThreeMonthlyEfficiencyBeanObject(counter, calendar.getTime(), efficiencyValue));
            counter++;
        }
    }

    private BigDecimal getThreeMonthServiceFleetEfficiency(Date monthDate) { // monthDate has been reset to 1st of month from calling method
        Date threeMonthStartDate = fleetFuelUtil.determineStartDate(monthDate, 3);
        BigDecimal allTruckFuelCostTotal = BigDecimal.ZERO;
        Integer allTruckMileageSum = new Integer("0");
        for (Truck truck : FleetFuelUtil.serviceTrucks) { //  This Tab is for Service/Utility Trucks ONLY
            List<OperatingCost> truckThreeMonthsOperatingCostList = getOperatingCostForTruckforMonthsList(truck, operatingCostTwentyFiveMonthsList, fleetFuelUtil.resetMonthToLastDay(monthDate), threeMonthStartDate);
            if (!truckThreeMonthsOperatingCostList.isEmpty()) {
                Date currentTransactionDate = fleetFuelUtil.resetMonthToFirstDay(truckThreeMonthsOperatingCostList.get(0).getTransactionDate());
                //
                List<OperatingCost> truckCurrentMonthsOperatingCostList = new ArrayList<>();
                for (OperatingCost operatingCost : truckThreeMonthsOperatingCostList) {
                    if (fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()).equals(currentTransactionDate)) {
                        truckCurrentMonthsOperatingCostList.add(operatingCost);
                        // Test for Last item in List and SubTotal
                        if (truckThreeMonthsOperatingCostList.indexOf(operatingCost) == truckThreeMonthsOperatingCostList.size() - 1) {
                            allTruckMileageSum += fleetFuelUtil.calculateMonthMileageTotal(truckCurrentMonthsOperatingCostList, truck);
                        }
                    } else {
                        // Subtotal
                        allTruckMileageSum += fleetFuelUtil.calculateMonthMileageTotal(truckCurrentMonthsOperatingCostList, truck);
                        //Reset
                        truckCurrentMonthsOperatingCostList.clear();
                        truckCurrentMonthsOperatingCostList.add(operatingCost);
                        currentTransactionDate = fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate());
                    }
                }
                // SUM the FuelCostS for truck for these 3 months and increment the totalFuelCostAllTrucks value
                allTruckFuelCostTotal = allTruckFuelCostTotal.add(fleetFuelUtil.sumOfFuelCostCalculation(truckThreeMonthsOperatingCostList));
            }
        }
        try {
            System.out.println(monthDate + " - " + threeMonthStartDate + ": 3M Fuel Efficiency: FUEL COST SUM= " + allTruckFuelCostTotal + " / 3M Fuel Efficiency: MILEAGE SUM= " + allTruckMileageSum + " ANS - " + (allTruckFuelCostTotal.divide(new BigDecimal(allTruckMileageSum + ""), 2, BigDecimal.ROUND_HALF_UP)));
            return allTruckFuelCostTotal.divide(new BigDecimal(allTruckMileageSum + ""), 2, BigDecimal.ROUND_HALF_UP);
        } catch (ArithmeticException a) {
            System.out.println("All Truck Fuel Cost Total (" + allTruckFuelCostTotal + ") / All Truck Mileage Sum (" + allTruckMileageSum + ") | A Divide By Zero exception (ArithmeticException) caught");
//            Notification.show("Error. A Calculation is trying to divide by ZERO. Reason for 0.00 per KM.", Notification.Type.TRAY_NOTIFICATION);
            return BigDecimal.ZERO;
        }
    }

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
        DCharts dDriverEfficiencyChart = createDriverEfficiencyChart();

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
        PanelEfficiency driverEfficiencyPanel = new PanelEfficiency("Driver Efficiency (R/Km Usage) - " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(endDate.toString()));

        oneMonthEfficiencyPanel.setContent(dOneMonthEfficiencyChart); // oneMonthEfficiencyLayout
        threeMonthEfficiencyPanel.setContent(dThreeMonthEfficiencyChart);//threeMonthEfficiencyLayout
        driverEfficiencyPanel.setContent(dDriverEfficiencyChart); // driverMonthEfficiencyLayout

        chart.chartVerticalLayout.removeAllComponents();
        chart.chartVerticalLayout.addComponent(totalTotalFleetFuelSpendPanel);
        chart.chartVerticalLayout.addComponent(dieselPriceRandPerLitrePanel);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.addComponent(oneMonthEfficiencyPanel);
        horizontalLayout.addComponent(threeMonthEfficiencyPanel);
        chart.chartVerticalLayout.addComponent(horizontalLayout);
        chart.chartVerticalLayout.addComponent(driverEfficiencyPanel);
        // house cleaning
        grandTotalFuelSpend = BigDecimal.ZERO;
        grandTotalMileage = new Integer("0");
        startDate = null;
        endDate = null;
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

    public DCharts createDriverEfficiencyChart() {
        DriverEfficiencyChart driverEfficiencyChart = new DriverEfficiencyChart();
        return driverEfficiencyChart.createChart(driverEfficiencyBeanList);
    }

    private void addListeners() {
        form.startDate.addValueChangeListener((Property.ValueChangeListener) this);
        form.endDate.addValueChangeListener((Property.ValueChangeListener) this);
    }

    public List<OperatingCost> getOperatingCostForTruckforMonthsList(Truck truck, List<OperatingCost> operatingCostTwentyFiveMonthsList, Date endDate, Date startDate) {
        List<OperatingCost> monthsOperatingCostList = new ArrayList<>();
        Date nuStartDate = fleetFuelUtil.resetMonthToFirstDay(startDate);
        Date nuEndDate = fleetFuelUtil.resetMonthToLastDay(endDate);
        for (OperatingCost operatingCost : operatingCostTwentyFiveMonthsList) {
            // Omit ZERO OBJECTS
            if (!(operatingCost.getSpeedometer() == 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0 && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0 && operatingCost.getSlipNo().equals("0000") && operatingCost.getRandPerLitre().compareTo(BigDecimal.ZERO) == 0)) {
                if (truck.getId().equals(operatingCost.getTruckId())
                        && (operatingCost.getTransactionDate().compareTo(nuStartDate) == 0
                        || (operatingCost.getTransactionDate().after(nuStartDate)
                        && operatingCost.getTransactionDate().before(nuEndDate))
                        || operatingCost.getTransactionDate().compareTo(nuEndDate) == 0)) {
                    monthsOperatingCostList.add(operatingCost);
                }
            }
            // if Date is before startDate, then looping stops as we sorted operatingCostTwentyFiveMonthsList in Desc Order
            if (operatingCost.getTransactionDate().before(nuStartDate)) {
                break;
            }
        }

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

    private void buildDriverEfficiencyBeanList() { // This method is for End Month ONLY
        // endDate has been reset to 1st of month from calling method
        Date newEndDate = fleetFuelUtil.resetMonthToFirstDay(endDate);
        driverEfficiencyBeanList.clear();
        List<OperatingCost> truckMonthOperatingCostList = new ArrayList<>();
//        Collections.sort(operatingCostTwentyFiveMonthsList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
        int counter = 0; // counter for Number of Truck with operating Cost data for the period specified

        for (Truck truck : FleetFuelUtil.msvTrucks) { //  This chart is for Service Trucks ONLY
            truckMonthOperatingCostList.clear();
            for (OperatingCost operatingCost : operatingCostTwentyFiveMonthsList) {
                // Omit ZERO OBJECTS
                if (!(operatingCost.getSpeedometer() <= 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0
                        && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0)) {
                    if (operatingCost.getTruckId().equals(truck.getId())
                            && newEndDate.compareTo(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate())) == 0) {
                        truckMonthOperatingCostList.add(operatingCost);
                    }
                }
                // if Date changes, then it is no longer End Date as we sorted in Desc Order
                if (newEndDate.after(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()))) {
                    break;
                }
            }
            // getMTDAct for Truck
            if (truckMonthOperatingCostList.size() > 0) {
                BigDecimal mtdAct = fleetFuelUtil.getMtdAct(truckMonthOperatingCostList, truck);
//                System.out.println("Month= " + truckMonthOperatingCostList.get(0).getTransactionDate() + ", Truck= " + truck.getVehicleNumber() + ", MTDAct= " + fleetFuelUtil.getMtdAct(truckMonthOperatingCostList, truck));
                buildDriverEfficiencyBean(truck, mtdAct, newEndDate, counter);
                counter++; // Counter should increment per truck with Data for specified period
            } else {
                // build Zero object for Truck
                buildDriverEfficiencyBean(truck, BigDecimal.ZERO, newEndDate, counter);
                counter++;
            }
        }
    }

    private void buildDriverEfficiencyBean(Truck truck, BigDecimal mtdAct, Date date, int counter) {
        DriverEfficiencyBean driverEfficiencyBean = new DriverEfficiencyBean();
        driverEfficiencyBean.setDriverName(truck.getDriverName());
        driverEfficiencyBean.setId(counter + "");
        driverEfficiencyBean.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(date.toString()));
        driverEfficiencyBean.setMonthlyEfficiencyValue(mtdAct);
        driverEfficiencyBean.setTransactionMonth(date);
        driverEfficiencyBean.setMonthlyEfficiencyColor(flagImage.determineFlagColor(mtdAct));
        driverEfficiencyBeanList.add(driverEfficiencyBean);
    }
}
