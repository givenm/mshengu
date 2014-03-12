 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
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
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.MonthlyFuelExpenseUI;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.monthlyfuelexpense.NonServiceVehiclesTwelveMonthChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.monthlyfuelexpense.ServiceVehiclesTwelveMonthChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.forms.MonthlyFuelExpenseForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.executivedashboard.FuelSpendMonthlyCostBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.monthlyfuelexpense.MonthlyFuelExpenseBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.monthlyfuelexpense.NonServiceVehiclesTwelveMonthBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.monthlyfuelexpense.ServiceVehiclesTwelveMonthBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.tables.MonthlyFuelExpenseTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.util.FleetFuelUtil;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Colin
 */
public class MonthlyFuelExpenseTab extends VerticalLayout implements Property.ValueChangeListener /*,Button.ClickListener */ {

    private final MshenguMain main;
    private final MonthlyFuelExpenseForm form;
    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private final FleetFuelUtil fleetFuelUtil = new FleetFuelUtil();
    private final MonthlyFuelExpenseUI monthlyFuelExpenseUI;
    public static Date startDate = null;
    public static Date endDate = null;
    //
    public static List<OperatingCost> operatingCostTwentyFiveMonthsList = new ArrayList<>();
    public static List<OperatingCost> dateRangeOperatingCostList = new ArrayList<>();
    private static List<OperatingCost> operatingCostTwelveMonthsList = new ArrayList<>();
    //
    private static List<MonthlyFuelExpenseBean> monthlyFuelExpenseBeanList = new ArrayList<>();
    private static List<NonServiceVehiclesTwelveMonthBean> nonServiceVehiclesTwelveMonthBeanList = new ArrayList<>();
    private static List<ServiceVehiclesTwelveMonthBean> serviceVehiclesTwelveMonthBeanList = new ArrayList<>();
    //
    private static BigDecimal nonOperationalGrandTotal = BigDecimal.ZERO;
    private static BigDecimal operationalGrandTotal = BigDecimal.ZERO;
    private static BigDecimal serviceFleetGrandTotal = BigDecimal.ZERO;
    private static BigDecimal grandTotal = BigDecimal.ZERO;
    //
//    private final NonServiceVehiclesTwelveMonthChart nonServiceVehiclesTwelveMonthChart;
//    private final ServiceVehiclesTwelveMonthChart serviceVehiclesTwelveMonthChart;
//    private final MonthlyFuelExpenseTable monthlyFuelExpenseTable;

    public MonthlyFuelExpenseTab(MshenguMain app) {
        main = app;
        form = new MonthlyFuelExpenseForm();
//        nonServiceVehiclesTwelveMonthChart = new NonServiceVehiclesTwelveMonthChart();
//        serviceVehiclesTwelveMonthChart = new ServiceVehiclesTwelveMonthChart();
//        monthlyFuelExpenseTable = new MonthlyFuelExpenseTable(app);
        monthlyFuelExpenseUI = new MonthlyFuelExpenseUI(app);

        addListeners();
        addComponent(form);
        addComponent(monthlyFuelExpenseUI);
        setSizeFull();
    }

//    @Override
//    public void buttonClick(Button.ClickEvent event) {
//        final Button source = event.getButton();
//////        if (source == form.generateButton) {
//////            generateData();
//////        }
//    }
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
        // Determine date range for other calculations on this Tab
        fleetFuelUtil.determineDateRange(endDate, 25); // Determine date range for other calculations on this Tab
        fleetFuelUtil.getTrucks();
        operatingCostTwentyFiveMonthsList.addAll(fleetFuelUtil.getOperatingCostByTruckBetweenTwoDates(FleetFuelUtil.startDate, FleetFuelUtil.endDate));
        Collections.sort(operatingCostTwentyFiveMonthsList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
        // Extract date Range for Charts only
        dateRangeOperatingCostList.addAll(fleetFuelUtil.getOperatingCostsForDateRange(startDate, endDate, operatingCostTwentyFiveMonthsList));
        if (!dateRangeOperatingCostList.isEmpty()) {
            buildMonthlyFuelExpenseBeanList(); // used for TABLE
            Collections.sort(monthlyFuelExpenseBeanList);
            //
            Date nuEndDate = fleetFuelUtil.resetMonthToFirstDay(endDate); // first day of endDate month so startDate will be first day of month
            Date startDatee = fleetFuelUtil.determineStartDate(nuEndDate, 12);
            nuEndDate = fleetFuelUtil.resetMonthToLastDay(endDate); // reset Endate to last day of month
            operatingCostTwelveMonthsList = getOperatingCostsforMonthsRange(nuEndDate, startDatee);

            buildNonServiceVehiclesTwelveMonthBeanList();
            Collections.sort(nonServiceVehiclesTwelveMonthBeanList);
            buildServiceVehiclesTwelveMonthBeanList();
            Collections.sort(serviceVehiclesTwelveMonthBeanList);
            displayCharts();
        } else {
            Notification.show("No Daily input Found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private List<OperatingCost> getOperatingCostsforMonthsRange(Date endDate, Date startDate) {
        List<OperatingCost> monthsOperatingCostList = new ArrayList<>();
        for (OperatingCost operatingCost : operatingCostTwentyFiveMonthsList) {
            // Omit ZERO OBJECTS
            if (!(operatingCost.getSpeedometer() <= 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0 && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0)) {
                if (operatingCost.getTransactionDate().compareTo(startDate) == 0
                        || (operatingCost.getTransactionDate().after(startDate) && operatingCost.getTransactionDate().before(endDate))
                        || operatingCost.getTransactionDate().compareTo(endDate) == 0) {
                    monthsOperatingCostList.add(operatingCost);
                }
            }
            // if Date is before startDate, then looping stops as we sorted operatingCostTwentyFiveMonthsList in Desc Order
            if (operatingCost.getTransactionDate().before(startDate)) {
                break;
            }
        }
        return monthsOperatingCostList;
    }

    private void buildMonthlyFuelExpenseBeanList() {
        List<OperatingCost> serviceTruckOperatingCostList = new ArrayList<>();
        List<OperatingCost> operationalTruckOperatingCostList = new ArrayList<>();
        List<OperatingCost> nonOperationalTruckOperatingCostList = new ArrayList<>();

        for (OperatingCost operatingCost : dateRangeOperatingCostList) {
            if (operatingCost.getTruckId() == null) {
                System.out.println("Operating Cost with Transaction Date= " + operatingCost.getTransactionDate() + ", ID= " + operatingCost.getId() + ", Mileage= " + operatingCost.getSpeedometer() + " and Driver= " + operatingCost.getDriverName() + " does not have a TruckId");
            } else {
                Truck truck = fleetFuelUtil.findTruckFromAllTruckListById(operatingCost.getTruckId());
                if (truck != null) {
                    if (FleetFuelUtil.truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MMV")) {
                        nonOperationalTruckOperatingCostList.add(operatingCost);
                    } else if (FleetFuelUtil.truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MOV")) {
                        operationalTruckOperatingCostList.add(operatingCost);
                    } else if (FleetFuelUtil.truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MSV") || FleetFuelUtil.truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MUV")) {
                        serviceTruckOperatingCostList.add(operatingCost);
                    }
                }
            }
        }
        //
        Date endDatee = fleetFuelUtil.resetMonthToFirstDay(endDate); // For Loop ACCURACY sakes
        Date startDatee = fleetFuelUtil.resetMonthToFirstDay(startDate);
        int counter = 0;
        monthlyFuelExpenseBeanList.clear();
        Calendar calendar = Calendar.getInstance();
        for (calendar.setTime(endDatee); calendar.getTime().after(startDatee) || calendar.getTime().compareTo(startDatee) == 0; calendar.add(Calendar.MONTH, -1)) {
            BigDecimal nonOperationalFuelCostTotal = getFuelCostTotalForMonth(nonOperationalTruckOperatingCostList, calendar.getTime());
            BigDecimal operationalFuelCostTotal = getFuelCostTotalForMonth(operationalTruckOperatingCostList, calendar.getTime());
            BigDecimal serviceFleetFuelCostTotal = getFuelCostTotalForMonth(serviceTruckOperatingCostList, calendar.getTime());
            //
            BigDecimal allTruckTotal = new BigDecimal(nonOperationalFuelCostTotal.toString());
            allTruckTotal = allTruckTotal.add(operationalFuelCostTotal);
            allTruckTotal = allTruckTotal.add(serviceFleetFuelCostTotal);
            allTruckTotal = allTruckTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            //
            grandTotal = grandTotal.add(allTruckTotal);

            buildMonthlyFuelExpenseBeanObject(counter, calendar.getTime(), nonOperationalFuelCostTotal, operationalFuelCostTotal, serviceFleetFuelCostTotal, allTruckTotal);
            counter++;
        }
        grandTotal = grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP);

    }

    private BigDecimal getFuelCostTotalForMonth(List<OperatingCost> truckOperatingCostList, Date date) {
        if (truckOperatingCostList.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            List<OperatingCost> truckMonthOperatingCostList = new ArrayList<>();
            for (OperatingCost operatingCost : truckOperatingCostList) {
                // Omit ZERO OBJECTS
                if (!(operatingCost.getSpeedometer() <= 0 && operatingCost.getFuelCost().compareTo(BigDecimal.ZERO) == 0
                        && operatingCost.getFuelLitres().compareTo(Double.parseDouble("0.0")) == 0)) {
                    if (date.compareTo(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate())) == 0) {
                        truckMonthOperatingCostList.add(operatingCost);
                    }
                }
                // if Date changes, then it is no longer End Date as we sorted in Desc Order
                if (date.after(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()))) {
                    break;
                }
            }
            //
            if (truckMonthOperatingCostList.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return fleetFuelUtil.sumOfFuelCostCalculation(truckMonthOperatingCostList);
            }
        }
    }

    private void buildMonthlyFuelExpenseBeanObject(int counter, Date date, BigDecimal nonOperationalFuelCostTotal, BigDecimal operationalFuelCostTotal, BigDecimal serviceFleetFuelCostTotal, BigDecimal allTruckTotal) {
        MonthlyFuelExpenseBean monthlyFuelExpenseBean = new MonthlyFuelExpenseBean();
        monthlyFuelExpenseBean.setAllTrucksFuelTotal(allTruckTotal);
        monthlyFuelExpenseBean.setId(counter + "");
        monthlyFuelExpenseBean.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(date.toString()));
        monthlyFuelExpenseBean.setNonOperationalTrucksFuelTotal(nonOperationalFuelCostTotal);
        monthlyFuelExpenseBean.setOperationalTrucksFuelTotal(operationalFuelCostTotal);
        monthlyFuelExpenseBean.setServiceTrucksFuelTotal(serviceFleetFuelCostTotal);
        monthlyFuelExpenseBean.setTransactionMonth(date);
        monthlyFuelExpenseBeanList.add(monthlyFuelExpenseBean);
    }

    private void buildNonServiceVehiclesTwelveMonthBeanList() {
        nonServiceVehiclesTwelveMonthBeanList.clear();
        List<OperatingCost> operationalTruckOperatingCostList = new ArrayList<>();
        List<OperatingCost> nonOperationalTruckOperatingCostList = new ArrayList<>();

        for (OperatingCost operatingCost : operatingCostTwelveMonthsList) {
            if (operatingCost.getTruckId() == null) {
                System.out.println("Operating Cost with Transaction Date= " + operatingCost.getTransactionDate() + ", ID= " + operatingCost.getId() + ", Mileage= " + operatingCost.getSpeedometer() + " and Driver= " + operatingCost.getDriverName() + " does not have a TruckId");
            } else {
                Truck truck = fleetFuelUtil.findTruckFromAllTruckListById(operatingCost.getTruckId());
                if (truck != null) {
                    if (FleetFuelUtil.truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MMV")) {
                        nonOperationalTruckOperatingCostList.add(operatingCost);
                    } else if (FleetFuelUtil.truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MOV")) {
                        operationalTruckOperatingCostList.add(operatingCost);
                    }
                }
            }
        }
        //
        Date nuEndDate = fleetFuelUtil.resetMonthToFirstDay(endDate); // first day of endDate month so startDate will be first day of month
        Date startDatee = fleetFuelUtil.determineStartDate(nuEndDate, 12);
//        nuEndDate = fleetFuelUtil.resetMonthToLastDay(endDate); // reset Endate to last day of month
        int counter = 0;

        Calendar calendar = Calendar.getInstance();
        for (calendar.setTime(nuEndDate); calendar.getTime().after(startDatee) || calendar.getTime().compareTo(startDatee) == 0; calendar.add(Calendar.MONTH, -1)) {
            BigDecimal nonOperationalFuelCostTotal = getFuelCostTotalForMonth(nonOperationalTruckOperatingCostList, calendar.getTime());
            BigDecimal operationalFuelCostTotal = getFuelCostTotalForMonth(operationalTruckOperatingCostList, calendar.getTime());

            buildNonServiceVehiclesTwelveMonthBeanObject(counter, calendar.getTime(), nonOperationalFuelCostTotal, operationalFuelCostTotal);
            counter++;
        }
    }

    private void buildNonServiceVehiclesTwelveMonthBeanObject(int counter, Date date, BigDecimal nonOperationalFuelCostTotal, BigDecimal operationalFuelCostTotal) {
        NonServiceVehiclesTwelveMonthBean nonServiceVehiclesTwelveMonthBean = new NonServiceVehiclesTwelveMonthBean();
        nonServiceVehiclesTwelveMonthBean.setId(counter + "");
        nonServiceVehiclesTwelveMonthBean.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(date.toString()));
        nonServiceVehiclesTwelveMonthBean.setNonOperationalTotalAmount(nonOperationalFuelCostTotal);
        nonServiceVehiclesTwelveMonthBean.setOperationalTotalAmount(operationalFuelCostTotal);
        nonServiceVehiclesTwelveMonthBean.setTransactionMonth(date);
        nonServiceVehiclesTwelveMonthBeanList.add(nonServiceVehiclesTwelveMonthBean);
    }

    private void buildServiceVehiclesTwelveMonthBeanList() {
        nonServiceVehiclesTwelveMonthBeanList.clear();
        List<OperatingCost> serviceTrucksOperatingCostList = new ArrayList<>();

        for (OperatingCost operatingCost : operatingCostTwelveMonthsList) {
            if (operatingCost.getTruckId() == null) {
                System.out.println("Operating Cost with Transaction Date= " + operatingCost.getTransactionDate() + ", ID= " + operatingCost.getId() + ", Mileage= " + operatingCost.getSpeedometer() + " and Driver= " + operatingCost.getDriverName() + " does not have a TruckId");
            } else {
                Truck truck = fleetFuelUtil.findTruckFromAllTruckListById(operatingCost.getTruckId());
                if (truck != null) {
                    if (FleetFuelUtil.truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MSV") || FleetFuelUtil.truncate(truck.getVehicleNumber(), 3).equalsIgnoreCase("MUV")) {
                        serviceTrucksOperatingCostList.add(operatingCost);
                    }
                }
            }
        }
        //
        Date nuEndDate = fleetFuelUtil.resetMonthToFirstDay(endDate); // first day of endDate month so startDate will be first day of month
        Date startDatee = fleetFuelUtil.determineStartDate(nuEndDate, 12);
//        nuEndDate = fleetFuelUtil.resetMonthToLastDay(endDate); // reset Endate to last day of month
        int counter = 0;

        Calendar calendar = Calendar.getInstance();
        for (calendar.setTime(nuEndDate); calendar.getTime().after(startDatee) || calendar.getTime().compareTo(startDatee) == 0; calendar.add(Calendar.MONTH, -1)) {
            BigDecimal serviceFuelCostTotal = getFuelCostTotalForMonth(serviceTrucksOperatingCostList, calendar.getTime());

            buildServiceVehiclesTwelveMonthBeanObject(counter, calendar.getTime(), serviceFuelCostTotal);
            counter++;
        }
    }

    private void buildServiceVehiclesTwelveMonthBeanObject(int counter, Date date, BigDecimal serviceFuelCostTotal) {
        ServiceVehiclesTwelveMonthBean serviceVehiclesTwelveMonthBean = new ServiceVehiclesTwelveMonthBean();
        serviceVehiclesTwelveMonthBean.setId(counter + "");
        serviceVehiclesTwelveMonthBean.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(date.toString()));
        serviceVehiclesTwelveMonthBean.setTotalAmount(serviceFuelCostTotal);
        serviceVehiclesTwelveMonthBean.setTransactionMonth(date);
        serviceVehiclesTwelveMonthBeanList.add(serviceVehiclesTwelveMonthBean);

    }

    private void displayCharts() {
        String tablePeriod = dateTimeFormatHelper.getMonthYearMonthAsMediumString(startDate.toString()) + " - " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(endDate.toString());
        String chartPeriod = serviceVehiclesTwelveMonthBeanList.get(0).getMonth() + " - " + serviceVehiclesTwelveMonthBeanList.get(serviceVehiclesTwelveMonthBeanList.size() - 1).getMonth();

        // Assuming it is sorted by Date in Asc Order
        Table dMonthlyFuelExpenseTable = createMonthlyFuelExpenseTable();
        dMonthlyFuelExpenseTable.setPageLength(dMonthlyFuelExpenseTable.size());
        DCharts dServiceTrucksTwelveMonthFuelSpendChart = createServiceTrucksTwelveMonthFuelSpendChart();
        DCharts dNonServiceTrucksTwelveMonthFuelSpendChart = createNonServiceTrucksTwelveMonthFuelSpendChart();

        // TO DO
//        Create Panel and add Table and 2 Charts to Panel
        PanelEfficiency monthFuelExpensePanel = new PanelEfficiency("Monthly Fuel Expense: " + tablePeriod);
        PanelEfficiency serviceTrucksTwelveMonthFuelSpendPanel = new PanelEfficiency("Service Vehicles 12M Fuel Spend: " + chartPeriod);
        PanelEfficiency nonServiceTrucksTwelveMonthFuelSpendPanel = new PanelEfficiency("Non-Service Vehicles 12M Fuel Spend: " + chartPeriod);

        monthFuelExpensePanel.setContent(dMonthlyFuelExpenseTable);
        serviceTrucksTwelveMonthFuelSpendPanel.setContent(dServiceTrucksTwelveMonthFuelSpendChart);
        nonServiceTrucksTwelveMonthFuelSpendPanel.setContent(dNonServiceTrucksTwelveMonthFuelSpendChart);

        monthlyFuelExpenseUI.tableVerticalLayout.removeAllComponents();
        monthlyFuelExpenseUI.chartVerticalLayout.removeAllComponents();
        // Add Panels to UI
        monthlyFuelExpenseUI.tableVerticalLayout.addComponent(monthFuelExpensePanel);
        monthlyFuelExpenseUI.chartVerticalLayout.addComponent(serviceTrucksTwelveMonthFuelSpendPanel);
        monthlyFuelExpenseUI.chartVerticalLayout.addComponent(nonServiceTrucksTwelveMonthFuelSpendPanel);

        // House Keeping
        startDate = null;
        endDate = null;

        monthlyFuelExpenseBeanList.clear();
        nonServiceVehiclesTwelveMonthBeanList.clear();
        serviceVehiclesTwelveMonthBeanList.clear();
        grandTotal = BigDecimal.ZERO;
        nonOperationalGrandTotal = BigDecimal.ZERO;
        operationalGrandTotal = BigDecimal.ZERO;
        serviceFleetGrandTotal = BigDecimal.ZERO;
    }

    private Table createMonthlyFuelExpenseTable() {
        MonthlyFuelExpenseTable fuelExpenseTable = new MonthlyFuelExpenseTable(main);
        return fuelExpenseTable.createTable(monthlyFuelExpenseBeanList);
    }

    private DCharts createServiceTrucksTwelveMonthFuelSpendChart() {
        ServiceVehiclesTwelveMonthChart serviceVehiclesTwelveMonthChart = new ServiceVehiclesTwelveMonthChart();
        return serviceVehiclesTwelveMonthChart.createChart(serviceVehiclesTwelveMonthBeanList);
    }

    private DCharts createNonServiceTrucksTwelveMonthFuelSpendChart() {
        NonServiceVehiclesTwelveMonthChart nonServiceVehiclesTwelveMonthChart = new NonServiceVehiclesTwelveMonthChart();
        return nonServiceVehiclesTwelveMonthChart.createChart(nonServiceVehiclesTwelveMonthBeanList);
    }

    private void addListeners() {
        form.startDate.addValueChangeListener((Property.ValueChangeListener) this);
        form.endDate.addValueChangeListener((Property.ValueChangeListener) this);
    }
}
