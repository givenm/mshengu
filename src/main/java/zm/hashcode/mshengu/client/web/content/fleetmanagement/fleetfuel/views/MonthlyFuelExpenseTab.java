 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.FleetFuelMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.MonthlyFuelExpenseUI;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.monthlyfuelexpense.NonServiceVehiclesTwelveMonthChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.monthlyfuelexpense.ServiceVehiclesTwelveMonthChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.forms.MonthlyFuelExpenseForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.executivedashboard.FuelSpendMonthlyCostBean;
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
    private final NonServiceVehiclesTwelveMonthChart nonServiceVehiclesTwelveMonthChart;
    private final ServiceVehiclesTwelveMonthChart serviceVehiclesTwelveMonthChart;
    private final MonthlyFuelExpenseTable monthlyFuelExpenseTable;
    private final MonthlyFuelExpenseUI monthlyFuelExpenseUI;
    public static Date startDate = null;
    public static Date endDate = null;
    public static List<OperatingCost> operatingCostTwentyFiveMonthsList = new ArrayList<>();
    public static List<OperatingCost> dateRangeOperatingCostList = new ArrayList<>();
//    public static List<FuelSpendMonthlyCostBean> fuelSpendMonthlyCostBeanList = new ArrayList<>();
    private final FleetFuelUtil fleetFuelUtil = new FleetFuelUtil();

    public MonthlyFuelExpenseTab(MshenguMain app) {
        main = app;
        form = new MonthlyFuelExpenseForm();
        nonServiceVehiclesTwelveMonthChart = new NonServiceVehiclesTwelveMonthChart();
        serviceVehiclesTwelveMonthChart = new ServiceVehiclesTwelveMonthChart();
        monthlyFuelExpenseTable = new MonthlyFuelExpenseTable(app);
        monthlyFuelExpenseUI = new MonthlyFuelExpenseUI(app);

//        addListeners();
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
        // Determine date range for other calculations on this Tab, extra 3 month for Previous month mileage
        // or get StartMileage for Car
        fleetFuelUtil.determineDateRange(endDate, 25); // Determine date range for other calculations on this Tab
        fleetFuelUtil.getTrucks();
        operatingCostTwentyFiveMonthsList.addAll(fleetFuelUtil.getOperatingCostByTruckBetweenTwoDates(FleetFuelUtil.startDate, FleetFuelUtil.endDate));
        Collections.sort(operatingCostTwentyFiveMonthsList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
        // Extract date Range for Charts only
        dateRangeOperatingCostList.addAll(fleetFuelUtil.getOperatingCostsForSpecDates(startDate, endDate, operatingCostTwentyFiveMonthsList));
        if (!dateRangeOperatingCostList.isEmpty()) {
//////            buildFuelSpendMonthlyCostBeanList(); // used for Bar Chart
//////            buildMonthlyMileageTotalBeanList(); // used for Line Chart
//////            buildServiceFleetOneMonthEfficiencyBeanList();
//////            buildServiceFleetThreeMonthEfficiencyBeanList();
//////            buildDriverEfficiencyBeanList();
//////            Collections.sort(driverEfficiencyBeanList, DriverEfficiencyBean.DescendingEfficiencyComparator);
//////            //
//////            displayCharts();
        } else {
            Notification.show("No Daily input Found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    public void getTwelveMonthlyFleetEfficiency(Date endDate) {
        BigDecimal totalFuelCostAllTrucks = BigDecimal.ZERO;
        Date nuEndDate = fleetFuelUtil.resetMonthToFirstDay(endDate); // first day of endDate month so startDate will be first day of month
        Date startDatee = fleetFuelUtil.determineStartDate(nuEndDate, 12);
        nuEndDate = fleetFuelUtil.resetMonthToLastDay(endDate); // reset Endate to last day of month
//////        List<OperatingCost> operatingCostTwelveMonthList = getOperatingCostforMonthsList(operatingCostTwentyFiveMonthsList, nuEndDate, startDatee);
        List<OperatingCost> selectedTwelveMonthsOperatingCostList = new ArrayList<>();
//        if (!isTwelveMonthOutOfRange) {
        List<OperatingCost> truckCurrentMonthOperatingCostList = new ArrayList<>();
//////        Collections.sort(operatingCostTwelveMonthList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
        nuEndDate = fleetFuelUtil.resetMonthToFirstDay(endDate); // For Loop ACCURACY sakes
        for (Truck truck : FleetFuelUtil.allTrucks) {
            truckCurrentMonthOperatingCostList.clear();
//            Integer truck12MonthMileageSum = new Integer("0");
            Calendar calendar = Calendar.getInstance();
            for (calendar.setTime(nuEndDate); calendar.getTime().after(startDatee) || calendar.getTime().compareTo(startDatee) == 0; calendar.add(Calendar.MONTH, -1)) {
////                truckCurrentMonthOperatingCostList.addAll(getOneMonthlyOperatingCostForTruck(operatingCostTwelveMonthList, calendar.getTime(), truck));

                if (!truckCurrentMonthOperatingCostList.isEmpty()) {
//                    Integer mileageSUM = fleetFuelUtil.calculateMonthMileageTotal(truckCurrentMonthOperatingCostList, truck);
//                    truck12MonthMileageSum += mileageSUM;
////                    annualMileageSumAllTrucks += fleetFuelUtil.calculateMonthMileageTotal(truckCurrentMonthOperatingCostList, truck);

                    selectedTwelveMonthsOperatingCostList.addAll(truckCurrentMonthOperatingCostList);
                    truckCurrentMonthOperatingCostList.clear();
                }
            }

            // SUM the FuelCostS for truck for these 3 months and increment the totalFuelCostAllTrucks value
            BigDecimal truckTwelveMonthFuelTotal = fleetFuelUtil.sumOfFuelCostCalculation(selectedTwelveMonthsOperatingCostList);
            totalFuelCostAllTrucks = totalFuelCostAllTrucks.add(truckTwelveMonthFuelTotal);
            selectedTwelveMonthsOperatingCostList.clear();


//////            // Use this twelve Month list and get the EFFICIENCY  // annualTotalFuelSpend
//////            annualTotalFuelSpend = totalFuelCostAllTrucks; // fleetFuelUtil.sumOfFuelCostCalculation(selectedTwelveMonthsOperatingCostList);
//////
//////            try {
//////                return annualTotalFuelSpend.divide(new BigDecimal(annualMileageSumAllTrucks + ""), 2, RoundingMode.HALF_UP);
//////            } catch (ArithmeticException a) {
//////                System.out.println("annualTotalFuelSpend (" + annualTotalFuelSpend + ") / annualMileageSumAllTrucks (" + annualMileageSumAllTrucks + ") | A Divide By Zero exception (ArithmeticException) caught");
////////            Notification.show("Error. A Calculation is trying to divide by ZERO. Reason for 0.00 per KM.", Notification.Type.TRAY_NOTIFICATION);
//////                return BigDecimal.ZERO;
//////            }

        }
    }
//    private void addListeners() {
//        //Register Button Listeners
//        form.generateButton.addClickListener((Button.ClickListener) this);
//    }
//}
}
