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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.dussan.vaadin.dcharts.DCharts;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.FleetFuelMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.ServiceFleetDashboardChartUI;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.TotalFleetFuelSpendBarChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.TotalMonthlyMileageLineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.forms.ServiceFleetDashboardForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.FuelSpendMonthlyCostBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.MonthlyMileageTotalBean;
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
            fleetFuelUtil.determineDateRange(endDate, 12); // Determine date range for other calculations on this Tab
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
//                    // Clear objects with zeros for  mileage, fuelCost, fuelLitres, speedometer, .slipNo("0000") in operatingCostTwelveMonthsList and dateRangeOperatingCostList
////                    clearZeroObjects();
//                    oneMonthEfficiency = getOneMonthlyFleetEfficiency(operatingCostTwelveMonthsList); // dateRangeOperatingCostList, endDate
//                    oneMonthEfficiencyFlag = flagImage.determineFuelUsageFlag(oneMonthEfficiency);
//                    Collections.sort(operatingCostTwelveMonthsList, OperatingCost.DescOrderDateAscOrderTruckIdComparator);
//                    threeMonthEfficiency = getThreeMonthlyFleetEfficiency(operatingCostTwelveMonthsList, endDate);
//                    threeMonthEfficiencyFlag = flagImage.determineFuelUsageFlag(threeMonthEfficiency);
//                    twelveMonthEfficiency = getTwelveMonthlyFleetEfficiency(operatingCostTwelveMonthsList, endDate);
//                    twelveMonthEfficiencyFlag = flagImage.determineFuelUsageFlag(twelveMonthEfficiency);
//                    // buildFuelSpendData(operatingCostTwelveMonthsList); // PENDING
//                    buildAnnualFuelSpendData(operatingCostTwelveMonthsList);
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
        Date startDatee = fleetFuelUtil.resetMonthToFirstDay(startDate);
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
                //======= DELETE =========
                System.out.println("Truck Monthly Mileage Total: Truck= " + truck.getVehicleNumber() + " " + truckMonthOperatingCostList.get(0).getTruckId() + " Month= " + calendar.getTime() + " Mileage Total= " + monthMileageSum);
                // ======= DELETE =========
            }

            //======= DELETE =========
            System.out.println("Monthly Mileage Total: Truck= " + " Month= " + calendar.getTime() + " Mileage Total= " + monthlyMileageTotal);
            System.out.println("//=============================//");
            // ======= DELETE =========

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

    public List<OperatingCost> getOneMonthOperatingCostForTruck(List<OperatingCost> operatingCostList, Date date, Truck truck) {
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
            if (found && fleetFuelUtil.resetMonthToFirstDay(date).before(fleetFuelUtil.resetMonthToFirstDay(operatingCost.getTransactionDate()))) {
                break;
            }
        }

        return truckMonthOperatingCostList;
    }

    public void displayCharts() {
        chartPeriod = fuelSpendMonthlyCostBeanList.get(0).getMonth() + " - " + fuelSpendMonthlyCostBeanList.get(fuelSpendMonthlyCostBeanList.size() - 1).getMonth();
        chartPeriodCount = fuelSpendMonthlyCostBeanList.size();

        // Assuming it is sorted by Date in Asc Order
        DCharts dTotalFleetFuelSpendChart = createTotalFleetFuelSpendChart();
        DCharts dTotalMonthlyMileageChart = createTotalMonthlyMileageChart();

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

////        PanelEfficiency oneMonthEfficiencyPanel = new PanelEfficiency("Total Fleet 1M Efficiency");
////        PanelEfficiency threeMonthEfficiencyPanel = new PanelEfficiency("Total Fleet 3M Efficiency");
////        PanelEfficiency twelveMonthEfficiencyPanel = new PanelEfficiency("Total Fleet 12M Efficiency");
////        PanelEfficiency fuelSpendPanel = new PanelEfficiency("Fuel Spend");
////        PanelEfficiency annualFuelSpendPanel = new PanelEfficiency("Annual Fuel Spend");
////        //
////        EfficiencylLayout efficiencylLayout = new EfficiencylLayout();
////        oneMonthEfficiencyPanel.setContent(efficiencylLayout.getEfficiencyLayout(oneMonthEfficiency, oneMonthEfficiencyFlag));
////        efficiencylLayout = new EfficiencylLayout();
////        threeMonthEfficiencyPanel.setContent(efficiencylLayout.getEfficiencyLayout(threeMonthEfficiency, threeMonthEfficiencyFlag));
////        efficiencylLayout = new EfficiencylLayout();
////        twelveMonthEfficiencyPanel.setContent(efficiencylLayout.getEfficiencyLayout(twelveMonthEfficiency, twelveMonthEfficiencyFlag));
////        //
////        final FuelSpendLayout fuelSpendLayout = new FuelSpendLayout();
////        // fuelSpendPanel.setContent(fuelSpendLayout.getFuelSpendLayout(fuelSpendPerUnit, fuelSpendPerService, fuelSpendUnitFlag, fuelSpendServiceFlag));
////        //
////        final AnnualFuelSpendLayout annualFuelSpendLayout = new AnnualFuelSpendLayout();
////        annualFuelSpendPanel.setContent(annualFuelSpendLayout.getFuelSpendLayout(annualTotalFuelSpend, serviceTotalFuelSpend, operationalTotalFuelSpend, nonOperationalTotalFuelSpend, serviceFuelSpendPercentage, operationalFuelSpendPercentage, nonOperationalFuelSpendPercentage));
        //
        chart.chartVerticalLayout.removeAllComponents();
        chart.chartVerticalLayout.addComponent(totalTotalFleetFuelSpendPanel);
        chart.chartVerticalLayout.addComponent(dieselPriceRandPerLitrePanel);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
////        horizontalLayout.addComponent(oneMonthEfficiencyPanel);
////        horizontalLayout.addComponent(threeMonthEfficiencyPanel);
////        horizontalLayout.addComponent(twelveMonthEfficiencyPanel);

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
}
