/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.views;

import com.vaadin.data.Property;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import org.dussan.vaadin.dcharts.DCharts;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.chemicals.DashboardChemicalsMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.FleetMaintenanceMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.DashBoardChartUI;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.forms.DashBoardForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceMileage;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendByVehicle;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendKmTraveled;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendMonthly;
import zm.hashcode.mshengu.app.util.FlagImage;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils.FleetMaintenanceUtil;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.actualcharts.MaintenanceSpendByKmTravelledChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.actualcharts.TotalMaintenanceSpendMonthlyChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.actualcharts.TotalMaintenanceSpendPerVehicleChart;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;

/**
 *
 * @author ColinWa
 */
public class DashBoardTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final DashBoardForm form;
    private final DashBoardChartUI chart;
    private FleetMaintenanceUtil fleetMaintenanceUtil = new FleetMaintenanceUtil();
    public static List<AnnualDataFleetMaintenanceCost> maintenanceCostList = null;
    public static List<AnnualDataFleetMaintenanceMileage> maintenanceMileageList = null;
    public static List<TotalMaintenanceSpendMonthly> spendMonthlyChartDataList = null;
    public static List<TotalMaintenanceSpendByVehicle> spendByVehicleChartDataList = null;
    public static List<TotalMaintenanceMileage> spendMaintenanceMileageList = null;
    public static List<TotalMaintenanceSpendKmTraveled> spendByKmTravelledChartDataList = null;
    public static Date startDate = null;
    public static Date endDate = null;
    public static BigDecimal grandTotalMaintenanceSpend = BigDecimal.ZERO;
    public static BigDecimal grandTotalMaintenanceMileage = BigDecimal.ZERO;
    public static String chartPeriod = null;
    public static Integer chartPeriodCount = null;
    public static Image maintenanceSpendPerKmRatingFlag; // USE the randsPerKilometreCalc above
    public FleetMaintenanceMenu fleetMaintenanceMenu = null;
    private final FlagImage flagImage = new FlagImage();

    public DashBoardTab(MshenguMain app, FleetMaintenanceMenu fleetMaintenanceMenu) {
        main = app;
        this.fleetMaintenanceMenu = fleetMaintenanceMenu;
        form = new DashBoardForm();
        chart = new DashBoardChartUI(main);

        addComponent(form);
        addComponent(chart);
        addListeners();
        setSizeFull();

    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == form.startDate) {
            startDate = fleetMaintenanceUtil.resetMonthToFirstDay(form.startDate.getValue()); // reset the choosen start date to 1st day
            if (endDate != null) {
                getDataAndPerformCharts();
            }
        } else if (property == form.endDate) {
            endDate = fleetMaintenanceUtil.resetMonthToLastDay(form.endDate.getValue());
            if (startDate != null) {
                getDataAndPerformCharts();
            }
        } else if (property == form.optionGroup) {
            if (form.optionGroup.getValue() == "3-Monthly") {
                fleetMaintenanceUtil.determineDateRange(new Date(), 3);
                maintenanceCostList = getMaintenanceCostList();
                maintenanceMileageList = getMaintenanceMileageList();
                if (!maintenanceCostList.isEmpty()) {
                    performSpendMonthlyChart(maintenanceCostList, FleetMaintenanceUtil.getStartDate(), 3);
                    performSpendByVehicleChart(maintenanceCostList, FleetMaintenanceUtil.getStartDate(), 3);
                    getSpendMaintenanceMileageList(maintenanceMileageList, FleetMaintenanceUtil.getStartDate(), 3);
                    performSpendKmTravelledChart();
                    displayCharts();
                } else {
                    Notification.show("No Maintenance Cost found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
                }
            } else if (form.optionGroup.getValue() == "6-Monthly") {
                fleetMaintenanceUtil.determineDateRange(new Date(), 6);
                maintenanceCostList = getMaintenanceCostList();
                maintenanceMileageList = getMaintenanceMileageList();
                if (!maintenanceCostList.isEmpty()) {
                    performSpendMonthlyChart(maintenanceCostList, FleetMaintenanceUtil.getStartDate(), 6);
                    performSpendByVehicleChart(maintenanceCostList, FleetMaintenanceUtil.getStartDate(), 6);
                    getSpendMaintenanceMileageList(maintenanceMileageList, FleetMaintenanceUtil.getStartDate(), 6);
                    performSpendKmTravelledChart();
                    displayCharts();
                } else {
                    Notification.show("No Maintenance Cost found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
                }
            } else if (form.optionGroup.getValue() == "12-Monthly") {
                fleetMaintenanceUtil.determineDateRange(new Date(), 12);
                maintenanceCostList = getMaintenanceCostList();

                maintenanceMileageList = getMaintenanceMileageList();
                if (!maintenanceCostList.isEmpty()) {
                    performSpendMonthlyChart(maintenanceCostList, FleetMaintenanceUtil.getStartDate(), 12);
                    performSpendByVehicleChart(maintenanceCostList, FleetMaintenanceUtil.getStartDate(), 12);
                    getSpendMaintenanceMileageList(maintenanceMileageList, FleetMaintenanceUtil.getStartDate(), 12);
                    performSpendKmTravelledChart();
                    displayCharts();
                } else {
                    Notification.show("No Maintenance Cost found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
                }
            }
        }
    }

    private void getDataAndPerformCharts() {
        int monthCount = fleetMaintenanceUtil.countMonthsInRange(startDate, endDate);

        if (monthCount > 0) {
            maintenanceCostList = fleetMaintenanceUtil.findMaintenanceCostBetweenTwoDates(startDate, endDate);
            maintenanceMileageList = fleetMaintenanceUtil.findMaintenanceMileageBetweenTwoDates(startDate, endDate);

            if (!maintenanceCostList.isEmpty()) {
//                List<TotalMaintenanceSpendMonthly> spendMonthlyChartData = fleetMaintenanceUtil.getMaintenanceSpendMonthlyChartData(maintenanceCostList, startDate, monthCount);
                performSpendMonthlyChart(maintenanceCostList, startDate, monthCount);
                performSpendByVehicleChart(maintenanceCostList, startDate, monthCount);
                getSpendMaintenanceMileageList(maintenanceMileageList, startDate, monthCount);
                performSpendKmTravelledChart();
                displayCharts();
            } else {
                Notification.show("No Maintenance Cost found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
            }
        } else {
            Notification.show("Please Specify Date Range in Approprate Order.", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private List<AnnualDataFleetMaintenanceCost> getMaintenanceCostList() {
        return fleetMaintenanceUtil.findMaintenanceCostBetweenTwoDates(FleetMaintenanceUtil.getStartDate(), FleetMaintenanceUtil.getEndDate());
    }

    private List<AnnualDataFleetMaintenanceMileage> getMaintenanceMileageList() {
        return fleetMaintenanceUtil.findMaintenanceMileageBetweenTwoDates(FleetMaintenanceUtil.getStartDate(), FleetMaintenanceUtil.getEndDate());
    }

    public void performSpendMonthlyChart(List<AnnualDataFleetMaintenanceCost> maintenanceCostList, Date date, Integer period) {
        spendMonthlyChartDataList = fleetMaintenanceUtil.getMaintenanceSpendMonthlyChartData(maintenanceCostList, date, period);
        grandTotalMaintenanceSpend = FleetMaintenanceUtil.grandTotalMaintenanceSpend;

//        // TESTING
//        System.out.println("\n Total Fleet Maintenance Spend Data");
//        for (TotalMaintenanceSpendMonthly totalMaintenanceSpendMonthly : spendMonthlyChartDataList) {
//            System.out.println(totalMaintenanceSpendMonthly.getMonthYear() + " = " + totalMaintenanceSpendMonthly.getTotal());
////             System.out.println("Month: " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(annualDataFleetMaintenanceCost.getTransactionMonth() + "") + ", Total: " + annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
//        }
    }

    public void performSpendByVehicleChart(List<AnnualDataFleetMaintenanceCost> maintenanceCostList, Date date, Integer period) {
        spendByVehicleChartDataList = fleetMaintenanceUtil.getMaintenanceSpendByVehicleChartData(maintenanceCostList, date, period);

//        // TESTING
//        System.out.println("\n Total Maintenance Spend Per Vehicle Data");
//        for (TotalMaintenanceSpendByVehicle totalMaintenanceSpendByVehicle : spendByVehicleChartDataList) {
//            System.out.println(totalMaintenanceSpendByVehicle.getNumberPlate() + " = " + totalMaintenanceSpendByVehicle.getTotal());
//        }
    }

    public void getSpendMaintenanceMileageList(List<AnnualDataFleetMaintenanceMileage> maintenanceMileageList, Date date, Integer period) {
        spendMaintenanceMileageList = fleetMaintenanceUtil.getMaintenanceMileageList(maintenanceMileageList, date, period);

        // Calculate Mileage Total
//        System.out.println("\n Total Maintenance Mileage for all Vehicles");
        for (TotalMaintenanceMileage totalMaintenanceMileage : spendMaintenanceMileageList) {
            grandTotalMaintenanceMileage = grandTotalMaintenanceMileage.add(new BigDecimal(totalMaintenanceMileage.getTruckMileagetotal()));
        }
//        System.out.println(" = " + grandTotalMaintenanceMileage);

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

    public void performSpendKmTravelledChart() {
        spendByKmTravelledChartDataList = fleetMaintenanceUtil.getMaintenanceMileageChartData(spendMaintenanceMileageList, spendByVehicleChartDataList);
        // TESTING
//        System.out.println("\n Maintenance SPend KM Travelled (R/Km) Chart");
//        for (TotalMaintenanceSpendKmTraveled totalMaintenanceMileage : spendByKmTravelledChartDataList) {
//            System.out.println(totalMaintenanceMileage.getNumberPlate() + " = " + totalMaintenanceMileage.getRandPerKilometre());
//        }
    }

    private void getHome() {
        main.content.setSecondComponent(new DashboardChemicalsMenu(main, "LANDING"));
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
        form.optionGroup.addValueChangeListener((Property.ValueChangeListener) this);
        //Register Table Listerners
//        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    public void displayCharts() {
        chartPeriodCount = spendMonthlyChartDataList.size();
        chartPeriod = spendMonthlyChartDataList.get(0).getMonthYear() + " - " + spendMonthlyChartDataList.get(spendMonthlyChartDataList.size() - 1).getMonthYear();

        DCharts dTotalMaintenanceCostMOnthlySpendChart = createTotalMaintenanceCostMOnthlySpendChart();
        DCharts dTotalMaintenanceSpendPerVehicleChart = createTotalMaintenanceSpendPerVehicleChart();
        DCharts dMaintenanceSpendByKmTravelledChart = createMaintenanceSpendByKmTravelledChart("dashboard");
        dMaintenanceSpendByKmTravelledChart.setWidth("600px");
        dMaintenanceSpendByKmTravelledChart.setHeight("300px");

        // Create a Panel
        Panel totalFleetMaintenanceSpendPanel = new Panel("Total Fleet Maintenance Spend: " + spendMonthlyChartDataList.get(0).getMonthYear() + " - " + spendMonthlyChartDataList.get(spendMonthlyChartDataList.size() - 1).getMonthYear()); //
        totalFleetMaintenanceSpendPanel.setWidth("100%");
        totalFleetMaintenanceSpendPanel.setHeight("100%");
        totalFleetMaintenanceSpendPanel.setStyleName("bubble");
        totalFleetMaintenanceSpendPanel.setSizeUndefined(); // Shrink to fit content

        // Create a Panel
        Panel totalMaintenanceSpendPerVehiclePanel = new Panel("Total Fleet Maintenance Spend: " + spendMonthlyChartDataList.get(0).getMonthYear() + " - " + spendMonthlyChartDataList.get(spendMonthlyChartDataList.size() - 1).getMonthYear()); //
        totalMaintenanceSpendPerVehiclePanel.setWidth("100%");
        totalMaintenanceSpendPerVehiclePanel.setHeight("100%");
        totalMaintenanceSpendPerVehiclePanel.setStyleName("bubble");
        totalMaintenanceSpendPerVehiclePanel.setSizeUndefined(); // Shrink to fit content

        // Create a Panel
        Panel maintenanceSpendByKmTravelledPanel = new Panel("Maintenance Spend Km Travelled(R/Km): " + spendMonthlyChartDataList.get(0).getMonthYear() + " - " + spendMonthlyChartDataList.get(spendMonthlyChartDataList.size() - 1).getMonthYear()); //
        maintenanceSpendByKmTravelledPanel.setWidth("100%");
        maintenanceSpendByKmTravelledPanel.setHeight("100%");
        maintenanceSpendByKmTravelledPanel.setStyleName("bubble");
        maintenanceSpendByKmTravelledPanel.setSizeUndefined(); // Shrink to fit content

        // ========
        HorizontalLayout totalFleetMaintenanceSpendLayout = new HorizontalLayout();
        totalFleetMaintenanceSpendLayout.setMargin(true); //
        totalFleetMaintenanceSpendLayout.setSizeUndefined(); // Shrink to fit content

        totalFleetMaintenanceSpendLayout.addComponent(dTotalMaintenanceCostMOnthlySpendChart);
        // Add item to the Panel
        totalFleetMaintenanceSpendPanel.setContent(totalFleetMaintenanceSpendLayout);

        // ==========
        HorizontalLayout totalMaintenanceSpendPerVehiclePanelLayout = new HorizontalLayout();
        totalMaintenanceSpendPerVehiclePanelLayout.setMargin(true); //
        totalMaintenanceSpendPerVehiclePanelLayout.setSizeUndefined(); // Shrink to fit content

        totalMaintenanceSpendPerVehiclePanelLayout.addComponent(dTotalMaintenanceSpendPerVehicleChart);
        // Add item to the Panel
        totalMaintenanceSpendPerVehiclePanel.setContent(totalMaintenanceSpendPerVehiclePanelLayout);

        // ==========
//        Label totalFleetCaption = new Label ("<i>This</i> is an <b>HTML</b> formatted label", Label.CONTENT_XHTML);
        Label totalFleetCaption = new Label("<b style=\"color:brown; font-size:25px;\">Total FLeet</b>", Label.CONTENT_XHTML);
        totalFleetCaption.setWidth(360, Sizeable.Unit.PIXELS);
        totalFleetCaption.setHeight(25, Sizeable.Unit.PIXELS);

        Label totalFleetLabel = new Label("Total spend in Maintenance for the last " + chartPeriodCount + " months for every Km travelled");
        totalFleetLabel.setWidth(360, Sizeable.Unit.PIXELS);

        BigDecimal totalMaintenanceSpendPerKm = grandTotalMaintenanceSpend.divide(grandTotalMaintenanceMileage, 2, RoundingMode.HALF_UP);
        Label totalPerKmLabel = new Label("<b style=\"color:red; font-size:25px;\">R " + totalMaintenanceSpendPerKm + "</b>", Label.CONTENT_XHTML);
        totalPerKmLabel.setWidth(100, Sizeable.Unit.PIXELS);
        totalPerKmLabel.setHeight(25, Sizeable.Unit.PIXELS);


        Label perKmLabel = new Label("<b style=\"color:red;\">per Km</b>", Label.CONTENT_XHTML);
        perKmLabel.setWidth(50, Sizeable.Unit.PIXELS);

        maintenanceSpendPerKmRatingFlag = flagImage.determineImageFlag(totalMaintenanceSpendPerKm);
        maintenanceSpendPerKmRatingFlag.setHeight(maintenanceSpendPerKmRatingFlag.getHeight(), Sizeable.Unit.PIXELS);
        maintenanceSpendPerKmRatingFlag.setWidth(maintenanceSpendPerKmRatingFlag.getWidth(), Sizeable.Unit.PIXELS);

        HorizontalLayout topHorizonLayout = new HorizontalLayout();
        topHorizonLayout.addComponent(totalFleetCaption);
        Label spacerLabel = new Label("");
        spacerLabel.setWidth(50, Sizeable.Unit.PIXELS);
        topHorizonLayout.addComponent(spacerLabel);
        topHorizonLayout.addComponent(totalPerKmLabel);
//        Label spacerLabel0 = new Label("");
//        spacerLabel0.setWidth(10, Sizeable.Unit.PIXELS);
//        topHorizonLayout.addComponent(spacerLabel0);
        topHorizonLayout.addComponent(maintenanceSpendPerKmRatingFlag);
//        topHorizonLayout.setComponentAlignment(maintenanceSpendPerKmRatingFlag, Alignment.TOP_RIGHT);

        HorizontalLayout bottomHorizonLayout = new HorizontalLayout();
        bottomHorizonLayout.addComponent(totalFleetLabel);
        Label spacerLabel1 = new Label("");
        spacerLabel1.setWidth(50, Sizeable.Unit.PIXELS);
        bottomHorizonLayout.addComponent(spacerLabel1);
        bottomHorizonLayout.addComponent(perKmLabel);

        VerticalLayout totalFleetVerticalLayout = new VerticalLayout();
        totalFleetVerticalLayout.addComponent(topHorizonLayout);
        totalFleetVerticalLayout.addComponent(bottomHorizonLayout);

        VerticalLayout maintenanceSpendByKmTravelledPanelLayout = new VerticalLayout();
        maintenanceSpendByKmTravelledPanelLayout.setMargin(true); //
        maintenanceSpendByKmTravelledPanelLayout.setSizeUndefined(); // Shrink to fit content

        maintenanceSpendByKmTravelledPanelLayout.addComponent(dMaintenanceSpendByKmTravelledChart);  // add chart
        maintenanceSpendByKmTravelledPanelLayout.addComponent(totalFleetVerticalLayout); // add Total fleet sPEND per every Km travelled

        // Add item to the Panel
        maintenanceSpendByKmTravelledPanel.setContent(maintenanceSpendByKmTravelledPanelLayout);


        chart.chartVerticalLayout.removeAllComponents();
        chart.chartVerticalLayout.addComponent(totalFleetMaintenanceSpendPanel);
        chart.chartVerticalLayout.addComponent(totalMaintenanceSpendPerVehiclePanel);
        chart.chartVerticalLayout.addComponent(maintenanceSpendByKmTravelledPanel);

//        chart.chartRootVerticalLayout.setComponentAlignment(totalFleetMaintenanceSpendPanel, Alignment.MIDDLE_CENTER);
//        chart.chartRootVerticalLayout.setComponentAlignment(totalMaintenanceSpendPerVehiclePanel, Alignment.MIDDLE_CENTER);
//        chart.chartRootVerticalLayout.setComponentAlignment(maintenanceSpendByKmTravelledPanel, Alignment.MIDDLE_CENTER);



////        // chart for Vehicle Menu chart for Vehicle Menu Tab ????????????????????????????????????????????????????????????????????????????
////
////        DCharts dKmTravelledVehicleMenuChart = createMaintenanceSpendByKmTravelledChart("vehicleMenu");
////        // use sort order in spendByKmTravelledChartDataList which is sorted by Total
////        //  extract data from 1)spendByVehicleChartDataList 2) spendMaintenanceMileageList 3) spendByKmTravelledChartDataList
////        // 4) Perform flagging based on spendByKmTravelledChartDataList
////
////
////        dKmTravelledVehicleMenuChart.setWidth("600px");
////        dKmTravelledVehicleMenuChart.setHeight("600px");
////
////        // Create a Panel
////        Panel kmTravelledVehicleMenuPanel = new Panel("Maintenance Spend Km Travelled(R/Km): " + spendMonthlyChartDataList.get(0).getMonthYear() + " - " + spendMonthlyChartDataList.get(spendMonthlyChartDataList.size() - 1).getMonthYear()); //
////        kmTravelledVehicleMenuPanel.setWidth("100%");
////        kmTravelledVehicleMenuPanel.setHeight("100%");
////        kmTravelledVehicleMenuPanel.setStyleName("panelorange");
////        kmTravelledVehicleMenuPanel.setSizeUndefined(); // Shrink to fit content
////
////        VerticalLayout kmTravelledVehicleMenuPanelLayout = new VerticalLayout();
////        kmTravelledVehicleMenuPanelLayout.setMargin(true); //
////        kmTravelledVehicleMenuPanelLayout.setSizeUndefined(); // Shrink to fit content
////
////        kmTravelledVehicleMenuPanelLayout.addComponent(dKmTravelledVehicleMenuChart);  // add chart
//////        kmTravelledVehicleMenuPanelLayout.addComponent(totalFleetVerticalLayout); // add Total fleet sPEND per every Km travelled
////
////        // Add item to the Panel
////        kmTravelledVehicleMenuPanel.setContent(kmTravelledVehicleMenuPanelLayout);
////
////        //
////        fleetMaintenanceMenu.vehicleRankingTab.chart.removeAllComponents(); // fleetMaintenanceMenu.vehicleRankingTab.chart.chartRootVerticalLayout.removeAllComponents();
//////        fleetMaintenanceMenu.vehicleRankingTab.chart.chartRootVerticalLayout.addComponent(kmTravelledVehicleMenuPanel); // fleetMaintenanceMenu.vehicleRankingTab.chart.chartRootVerticalLayout.addComponent(kmTravelledVehicleMenuPanel);
////
////        // Create a table with 1 column
////        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////        Table vehicleNumberTable = new Table();
////        vehicleNumberTable.addContainerProperty("", String.class, null);
////// Insert some data
//////        List<Object> vehicleNumberList = new ArrayList<>();
////        Integer i = 0;
////        for (TotalMaintenanceSpendKmTraveled totalMaintenanceSpendKmTraveled : spendByKmTravelledChartDataList) {
//////            vehicleNumberList.add(totalMaintenanceSpendKmTraveled.getVehicleNumber());
////            vehicleNumberTable.addItem(new Object[]{totalMaintenanceSpendKmTraveled.getVehicleNumber()}, i);
////            i++;
////        }
////        // Adjust the table height a bit
////        vehicleNumberTable.setPageLength(vehicleNumberTable.size());
////
////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // house cleaning
        grandTotalMaintenanceMileage = BigDecimal.ZERO;
        grandTotalMaintenanceSpend = BigDecimal.ZERO;
    }

    public DCharts createTotalMaintenanceCostMOnthlySpendChart() {
        TotalMaintenanceSpendMonthlyChart TotalMaintenanceSpendMonthlyChart = new TotalMaintenanceSpendMonthlyChart();
        return TotalMaintenanceSpendMonthlyChart.createChart(spendMonthlyChartDataList, grandTotalMaintenanceSpend);
    }

    public DCharts createTotalMaintenanceSpendPerVehicleChart() {
        TotalMaintenanceSpendPerVehicleChart totalMaintenanceSpendPerVehicleChart = new TotalMaintenanceSpendPerVehicleChart();
        return totalMaintenanceSpendPerVehicleChart.createChart(spendByVehicleChartDataList, chartPeriod);
    }

    public DCharts createMaintenanceSpendByKmTravelledChart(String chartType) {
        MaintenanceSpendByKmTravelledChart maintenanceSpendByKmTravelledChart = new MaintenanceSpendByKmTravelledChart();
        return maintenanceSpendByKmTravelledChart.createChart(spendByKmTravelledChartDataList, chartPeriod, chartType);
    }
}
