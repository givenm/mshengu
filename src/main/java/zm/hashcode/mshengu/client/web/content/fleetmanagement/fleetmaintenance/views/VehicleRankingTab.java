/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.dussan.vaadin.dcharts.DCharts;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.chemicals.DashboardChemicalsMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.FleetMaintenanceMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.VehicleRankingChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceMileage;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendByVehicle;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendKmTraveled;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendMonthly;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.panel.PanelStyled;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables.MonthSpendTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables.RandPerKmTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables.RatingTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables.TotalMaintenanceCostTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables.TotalMileageTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables.VehicleNumberTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils.FleetMaintenanceUtil;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils.MaintenanceSpendByKmTravelledChart;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;

/**
 *
 * @author ColinWa
 */
public class VehicleRankingTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final VehicleNumberTable vehicleNumberTable;
    private final TotalMaintenanceCostTable totalMaintenanceCostTable;
    private final TotalMileageTable totalMileageTable;
    private final RandPerKmTable randPerKmTable;
    private final RatingTable ratingTable;
    private final MonthSpendTable monthSpendTable;
//    private final VehicleRankingForm form;
    public final VehicleRankingChart chart;
    private FleetMaintenanceUtil fleetMaintenanceUtil = new FleetMaintenanceUtil();
    private static List<AnnualDataFleetMaintenanceCost> maintenanceCostList = null;
    private static List<AnnualDataFleetMaintenanceMileage> maintenanceMileageList = null;
    private static List<TotalMaintenanceSpendMonthly> spendMonthlyChartDataList = null;
    private static List<TotalMaintenanceSpendByVehicle> spendByVehicleChartDataList = null; // Total Spend Per Vehicle - 3rd Panel 2nd Table
    private static List<TotalMaintenanceMileage> spendMaintenanceMileageList = null; // 4th Panel 3rd Table
    private static List<TotalMaintenanceSpendKmTraveled> spendByKmTravelledChartDataList = null; // Graph 1st Panel// Also provides value for "2nd Panel 1st table" & "5th Panel 4th Table"
    public static BigDecimal grandTotalMaintenanceSpend = BigDecimal.ZERO;
    public static BigDecimal grandTotalMaintenanceMileage = BigDecimal.ZERO;
    public static String chartPeriod = null;

    public VehicleRankingTab(MshenguMain app, FleetMaintenanceMenu fleetMaintenanceMenu) {
        main = app;
        vehicleNumberTable = new VehicleNumberTable(main);
        totalMaintenanceCostTable = new TotalMaintenanceCostTable(main);
        totalMileageTable = new TotalMileageTable(main);
        randPerKmTable = new RandPerKmTable(main);
        ratingTable = new RatingTable(main);
        monthSpendTable = new MonthSpendTable(main);
//        form = new VehicleRankingForm();
        chart = new VehicleRankingChart(main);

//        addComponent(form);
        addComponent(chart);
        addListeners();
        setSizeFull();


        fleetMaintenanceUtil.determineDateRange(new Date(), 12);
        maintenanceCostList = getMaintenanceCostList();
        maintenanceMileageList = getMaintenanceMileageList();

        if (!maintenanceCostList.isEmpty()) {
            performSpendMonthlyChart(maintenanceCostList, FleetMaintenanceUtil.startDate, 12);
            performSpendByVehicleChart(maintenanceCostList, FleetMaintenanceUtil.startDate, 12);
            getSpendMaintenanceMileageList(maintenanceMileageList, FleetMaintenanceUtil.startDate, 12);
            performSpendKmTravelledChart();
            displayCharts();
        } else {
            Notification.show("No Maintenance Cost found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
        }

    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
    }

    private void getHome() {
        main.content.setSecondComponent(new DashboardChemicalsMenu(main, "VEHILCE_RANKING"));
    }

    private void addListeners() {
//        //Register Button Listeners
//        form.save.addClickListener((Button.ClickListener) this);
//        form.edit.addClickListener((Button.ClickListener) this);
//        form.cancel.addClickListener((Button.ClickListener) this);
//        form.update.addClickListener((Button.ClickListener) this);
//        form.delete.addClickListener((Button.ClickListener) this);
        //
//        form.startDate.addValueChangeListener((Property.ValueChangeListener) this);
//        form.endDate.addValueChangeListener((Property.ValueChangeListener) this);
//        form.optionGroup.addValueChangeListener((Property.ValueChangeListener) this);
        //Register Table Listerners
//        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

//    public void doSomething() {
//        fleetMaintenanceUtil.determineDateRange(new Date(), 12);
//        maintenanceCostList = getMaintenanceCostList();
//        maintenanceMileageList = getMaintenanceMileageList();
//
//        if (!maintenanceCostList.isEmpty()) {
//            performSpendMonthlyChart(maintenanceCostList, FleetMaintenanceUtil.startDate, 12);
//            performSpendByVehicleChart(maintenanceCostList, FleetMaintenanceUtil.startDate, 12);
//            getSpendMaintenanceMileageList(maintenanceMileageList, FleetMaintenanceUtil.startDate, 12);
//            performSpendKmTravelledChart();
//            displayCharts();
//        } else {
//            Notification.show("No Maintenance Cost found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
//        }
//    }
    private List<AnnualDataFleetMaintenanceCost> getMaintenanceCostList() {
        return fleetMaintenanceUtil.findMaintenanceCostBetweenTwoDates(FleetMaintenanceUtil.startDate, FleetMaintenanceUtil.endDate);
    }

    private List<AnnualDataFleetMaintenanceMileage> getMaintenanceMileageList() {
        return fleetMaintenanceUtil.findMaintenanceMileageBetweenTwoDates(FleetMaintenanceUtil.startDate, FleetMaintenanceUtil.endDate);
    }

    private void performSpendMonthlyChart(List<AnnualDataFleetMaintenanceCost> maintenanceCostList, Date date, Integer period) {
        spendMonthlyChartDataList = fleetMaintenanceUtil.getMaintenanceSpendMonthlyChartData(maintenanceCostList, date, period);
        grandTotalMaintenanceSpend = FleetMaintenanceUtil.grandTotalMaintenanceSpend;

//        // TESTING
//        System.out.println("\n Total Fleet Maintenance Spend Data");
//        for (TotalMaintenanceSpendMonthly totalMaintenanceSpendMonthly : spendMonthlyChartDataList) {
//            System.out.println(totalMaintenanceSpendMonthly.getMonthYear() + " = " + totalMaintenanceSpendMonthly.getTotal());
////             System.out.println("Month: " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(annualDataFleetMaintenanceCost.getTransactionMonth() + "") + ", Total: " + annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
//        }
    }

    private void performSpendByVehicleChart(List<AnnualDataFleetMaintenanceCost> maintenanceCostList, Date date, Integer period) {
        spendByVehicleChartDataList = fleetMaintenanceUtil.getMaintenanceSpendByVehicleChartData(maintenanceCostList, date, period);

//        // TESTING
//        System.out.println("\n Total Maintenance Spend Per Vehicle Data");
//        for (TotalMaintenanceSpendByVehicle totalMaintenanceSpendByVehicle : spendByVehicleChartDataList) {
//            System.out.println(totalMaintenanceSpendByVehicle.getNumberPlate() + " = " + totalMaintenanceSpendByVehicle.getTotal());
//        }
    }

    private void getSpendMaintenanceMileageList(List<AnnualDataFleetMaintenanceMileage> maintenanceMileageList, Date date, Integer period) {
        spendMaintenanceMileageList = fleetMaintenanceUtil.getMaintenanceMileageList(maintenanceMileageList, date, period);

        // Calculate Mileage Total
        System.out.println("\n Total Maintenance Mileage for all Vehicles");
        for (TotalMaintenanceMileage totalMaintenanceMileage : spendMaintenanceMileageList) {
            grandTotalMaintenanceMileage = grandTotalMaintenanceMileage.add(new BigDecimal(totalMaintenanceMileage.getTruckMileagetotal()));
        }
        System.out.println(" = " + grandTotalMaintenanceMileage);

//        // TESTING
//        System.out.println("\n Total Maintenance Mileage Total by Vehicle Data");
//        for (TotalMaintenanceMileage totalMaintenanceMileage : spendMaintenanceMileageList) {
//            System.out.println(totalMaintenanceMileage.getNumberPlate() + " = " + totalMaintenanceMileage.getTruckMileagetotal());
//        }
//
//        // next calculate the chart data using data in spendMaintenanceMileageList and spendByVehicleChartDataList
//        // Do this by Dividing SpendByVehicle (spendByVehicleChartDataList) over vehicle Mileage Total (spendMaintenanceMileageList)
//        // See performSpendKmTravelledChart() Method below
    }

    private void performSpendKmTravelledChart() {
        spendByKmTravelledChartDataList = fleetMaintenanceUtil.getMaintenanceMileageChartData(spendMaintenanceMileageList, spendByVehicleChartDataList);
        // TESTING
        System.out.println("\n Maintenance SPend KM Travelled (R/Km) Chart");
        for (TotalMaintenanceSpendKmTraveled totalMaintenanceMileage : spendByKmTravelledChartDataList) {
            System.out.println(totalMaintenanceMileage.getNumberPlate() + " = " + totalMaintenanceMileage.getRandPerKilometre());
        }
    }

    private void displayCharts() {
        chartPeriod = spendMonthlyChartDataList.get(0).getMonthYear() + " - " + spendMonthlyChartDataList.get(spendMonthlyChartDataList.size() - 1).getMonthYear();
        chart.removeAllComponents(); // fleetMaintenanceMenu.vehicleRankingTab.chart.chartRootVerticalLayout.removeAllComponents();
//        fleetMaintenanceMenu.vehicleRankingTab.chart.chartRootVerticalLayout.addComponent(kmTravelledVehicleMenuPanel); // fleetMaintenanceMenu.vehicleRankingTab.chart.chartRootVerticalLayout.addComponent(kmTravelledVehicleMenuPanel);

        DCharts dKmTravelledVehicleMenuChart = displayMaintenanceSpendByKmTravelledChart("vehicleMenu");
        // use sort order in spendByKmTravelledChartDataList which is sorted by Total
        //  extract data from 1)spendByVehicleChartDataList 2) spendMaintenanceMileageList 3) spendByKmTravelledChartDataList
        // 4) Perform flagging based on spendByKmTravelledChartDataList

        // DYNAMIC HEIGHT depending on Number of Service Trucks, to match height of Comparison Tables
        int numberOfTrucks = spendByKmTravelledChartDataList.size();
        int height = 32 * numberOfTrucks;
        // each chart siries is 32px // decided upon calculation. in case more trucks are added in the future, chart height should grow to allign
        // with other tables in this UI
        dKmTravelledVehicleMenuChart.setWidth("520px");
        dKmTravelledVehicleMenuChart.setHeight(height + "px");

        // Create a Panels
        PanelStyled kmTravelledVehicleChartPanel = new PanelStyled("Maintenance Spend Km Travelled(R/Km): " + chartPeriod); //
        PanelStyled vehicleNumberPanel = new PanelStyled("-");
        vehicleNumberPanel.setStyleName("white");
        PanelStyled totalMaintenanceCostPanel = new PanelStyled("Maintenance(R)");
        PanelStyled totalMileagePanel = new PanelStyled("Mileage (Km)");
        PanelStyled randsPerKmPanel = new PanelStyled("R/Km");
        PanelStyled ratingPanel = new PanelStyled(" Rating ");
        PanelStyled monthSpendPanel = new PanelStyled("Month Spend");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Create a vehicleNumberTable table AND pass spendByKmTravelledChartDataList

        // NB we have to flip the list in DESC by Rand per Km b/c the chart was flipped in DESC
        Collections.sort(spendByKmTravelledChartDataList, TotalMaintenanceSpendKmTraveled.DescendingOrderComparator);

        vehicleNumberTable.populateVehicleNumberTable(spendByKmTravelledChartDataList);
        vehicleNumberTable.performTableCellStyling();
        vehicleNumberTable.setPageLength(vehicleNumberTable.size()); // Adjust the table height a bit

        // Create a totalMaintenanceCostTable table AND pass spendByVehicleChartDataList
        totalMaintenanceCostTable.populateTotalMaintenanceCostTable(spendByKmTravelledChartDataList, spendByVehicleChartDataList);
        totalMaintenanceCostTable.performTableCellStyling();
        totalMaintenanceCostTable.setPageLength(totalMaintenanceCostTable.size()); // Adjust the table height a bit

        // Create a totalMileageTable table AND pass spendMaintenanceMileageList
        totalMileageTable.populateTotalMileageTable(spendByKmTravelledChartDataList, spendMaintenanceMileageList);
        totalMileageTable.performTableCellStyling();
        totalMileageTable.setPageLength(totalMileageTable.size()); // Adjust the table height a bit

// Create a RandPerKmTable table AND pass TotalMaintenanceSpendKmTraveled spendByKmTravelledChartDataList
        randPerKmTable.populateTotalMaintenanceCostTable(spendByKmTravelledChartDataList);
        randPerKmTable.performTableCellStyling();
        randPerKmTable.setPageLength(randPerKmTable.size()); // Adjust the table height a bit

// Create a RatingTable table AND pass TotalMaintenanceSpendKmTraveled spendByKmTravelledChartDataList
        ratingTable.populateRatingTable(spendByKmTravelledChartDataList);
        ratingTable.performTableCellStyling();
        ratingTable.setPageLength(ratingTable.size()); // Adjust the table height a bit

//// Create a MonthSpendTable table AND pass Maintenance Cost for the Month before current Month(The last month of THE lIST)
//        monthSpendTable.populateMonthSpendTable(spendByKmTravelledChartDataList);
//        monthSpendTable .performTableCellStyling();
//        monthSpendTable.setPageLength(monthSpendTable.size()); // Adjust the table height a bit

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ADD Charts and (Layout &) Tables to Panels

        kmTravelledVehicleChartPanel.setContent(dKmTravelledVehicleMenuChart); // prev. kmTravelledVehicleMenuPanelLayout
//        // Adjust the panels default layout (a VerticalLayout)
//        VerticalLayout layout = (VerticalLayout) kmTravelledVehicleChartPanel.getContent();
//        layout.setMargin(true); // we don't want a margin
//        layout.setSpacing(true); // and no spacing between components

        // Add vehicleNumberTable to Layout then to the Panel
        VerticalLayout vehicleNumberLayout = new VerticalLayout();
        vehicleNumberLayout.addComponent(vehicleNumberTable);
        vehicleNumberPanel.setContent(vehicleNumberLayout);
        vehicleNumberTable.setSizeFull();

        // Add totalMaintenanceCostTable to Layout then to the Panel
        VerticalLayout maintenanceCostLayout = new VerticalLayout();
        maintenanceCostLayout.addComponent(totalMaintenanceCostTable);
        totalMaintenanceCostPanel.setContent(maintenanceCostLayout);
//        totalMaintenanceCostTable.setWidth(totalMaintenanceCostPanel.getWidth() + "px");
//        totalMaintenanceCostPanel.setWidth(totalMaintenanceCostTable.getWidth() + "px");

        // Add totalMileageTable to Layout then to the Panel
        VerticalLayout totalMileageLayout = new VerticalLayout();
        totalMileageLayout.addComponent(totalMileageTable);
        totalMileagePanel.setContent(totalMileageLayout);
//        totalMileagePanel.setWidth(totalMileageTable.getWidth() + "px");

        // Add randPerKmTable to Layout then to the Panel
        VerticalLayout randPerKmLayout = new VerticalLayout();
        randPerKmLayout.addComponent(randPerKmTable);
        randsPerKmPanel.setContent(randPerKmLayout);
        randsPerKmPanel.setWidth(randPerKmTable.getWidth() + "px");

        // Add ratingTable to Layout then to the Panel
        VerticalLayout ratingLayout = new VerticalLayout();
        ratingLayout.addComponent(ratingTable);
        ratingPanel.setContent(ratingLayout);
        ratingPanel.setWidth(ratingTable.getWidth() + "px");

        // Add monthSpendTable to Layout then to the Panel
        VerticalLayout monthSpendLayout = new VerticalLayout();
//        monthSpendLayout.addComponent(monthSpendTable);
        monthSpendPanel.setContent(monthSpendLayout);
//        monthSpendPanel.setWidth(monthSpendTable.getWidth() + "px");

        // ADD Panels to VehicleRankingChart.java
        chart.defineAndAddLayouts(kmTravelledVehicleChartPanel, vehicleNumberPanel, totalMaintenanceCostPanel, totalMileagePanel, randsPerKmPanel, ratingPanel, monthSpendPanel);

    }

    public DCharts displayMaintenanceSpendByKmTravelledChart(String chartType) {
        MaintenanceSpendByKmTravelledChart maintenanceSpendByKmTravelledChart = new MaintenanceSpendByKmTravelledChart();
        return maintenanceSpendByKmTravelledChart.createChart(spendByKmTravelledChartDataList, chartPeriod, chartType);
    }
}
