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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import zm.hashcode.mshengu.app.util.flagImages.FlagImage;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils.FleetMaintenanceUtil;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.actualcharts.MaintenanceSpendByKmTravelledChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.actualcharts.MaintenanceSpendBySupplierBarChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.actualcharts.MaintenanceSpendBySupplierPieChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.actualcharts.TotalMaintenanceSpendMonthlyChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.actualcharts.TotalMaintenanceSpendPerVehicleChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendBySupplier;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils.MaintenanceSpendBySupplierUtil;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;
import zm.hashcode.mshengu.domain.procurement.MaintenanceSpendBySupplier;

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
    private MaintenanceSpendBySupplierUtil maintenanceSpendBySupplierUtil = new MaintenanceSpendBySupplierUtil();
    public static List<AnnualDataFleetMaintenanceCost> maintenanceCostList = null;
    public static List<AnnualDataFleetMaintenanceMileage> maintenanceMileageList = null;
    public static List<MaintenanceSpendBySupplier> maintenanceSpendBySupplierList = null;
    public static List<TotalMaintenanceSpendMonthly> spendMonthlyChartDataList = null;
    public static List<TotalMaintenanceSpendByVehicle> spendByVehicleChartDataList = null;
    public static List<TotalMaintenanceMileage> spendMaintenanceMileageList = null;
    public static List<TotalMaintenanceSpendKmTraveled> spendByKmTravelledChartDataList = null;
    public static List<TotalMaintenanceSpendBySupplier> spendBySupplierChartDataList = null;
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
            try {
                endDate = fleetMaintenanceUtil.resetMonthToLastDay(form.endDate.getValue());
            } catch (java.lang.NullPointerException ex) {
            }
            if (endDate != null) {
                fleetMaintenanceUtil.findServiceTrucks();
                getDataAndPerformCharts();
            }
        } else if (property == form.endDate) {
            endDate = fleetMaintenanceUtil.resetMonthToLastDay(form.endDate.getValue());
            try {
                startDate = fleetMaintenanceUtil.resetMonthToLastDay(form.startDate.getValue());
            } catch (java.lang.NullPointerException ex) {
            }
            if (startDate != null) {
                fleetMaintenanceUtil.findServiceTrucks();
                getDataAndPerformCharts();
            }
        } else if (property == form.optionGroup) {
            if (form.optionGroup.getValue() == "3-Monthly") {
                fleetMaintenanceUtil.findServiceTrucks();
                fleetMaintenanceUtil.determineDateRange(new Date(), 3);
                maintenanceSpendBySupplierUtil.determineDateRange(new Date(), 3);
                maintenanceCostList = getMaintenanceCostList();
                maintenanceMileageList = getMaintenanceMileageList();
                maintenanceSpendBySupplierList = getMaintenanceSpendBySupplier();
                if (!maintenanceCostList.isEmpty()) {
                    getSpendMonthlyList(maintenanceCostList, FleetMaintenanceUtil.getStartDate(), 3);
                    getSpendByVehicleList(maintenanceCostList, FleetMaintenanceUtil.getStartDate(), 3);
                    getSpendMaintenanceMileageList(maintenanceMileageList, FleetMaintenanceUtil.getStartDate(), 3);
                    getSpendBySupplierList(maintenanceSpendBySupplierList /* , maintenanceSpendBySupplierUtil.getStartDate(), 3 */); // 2 SpendBySupplier CHARTS
                    getSpendKmTravelledList();
                    displayCharts();
                } else {
                    Notification.show("No Maintenance Cost found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
                }
            } else if (form.optionGroup.getValue() == "6-Monthly") {
                fleetMaintenanceUtil.findServiceTrucks();
                fleetMaintenanceUtil.determineDateRange(new Date(), 6);
                maintenanceSpendBySupplierUtil.determineDateRange(new Date(), 6);
                maintenanceCostList = getMaintenanceCostList();
                maintenanceMileageList = getMaintenanceMileageList();
                maintenanceSpendBySupplierList = getMaintenanceSpendBySupplier();
                if (!maintenanceCostList.isEmpty()) {
                    getSpendMonthlyList(maintenanceCostList, FleetMaintenanceUtil.getStartDate(), 6);
                    getSpendByVehicleList(maintenanceCostList, FleetMaintenanceUtil.getStartDate(), 6);
                    getSpendMaintenanceMileageList(maintenanceMileageList, FleetMaintenanceUtil.getStartDate(), 6);
                    getSpendBySupplierList(maintenanceSpendBySupplierList /* , maintenanceSpendBySupplierUtil.getStartDate(), 6 */); // 2 SpendBySupplier CHARTS
                    getSpendKmTravelledList();
                    displayCharts();
                } else {
                    Notification.show("No Maintenance Cost found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
                }
            } else if (form.optionGroup.getValue() == "12-Monthly") {
                fleetMaintenanceUtil.findServiceTrucks();
                fleetMaintenanceUtil.determineDateRange(new Date(), 12);
                maintenanceSpendBySupplierUtil.determineDateRange(new Date(), 12);
                maintenanceCostList = getMaintenanceCostList();
                maintenanceMileageList = getMaintenanceMileageList();
                maintenanceSpendBySupplierList = getMaintenanceSpendBySupplier();
                if (!maintenanceCostList.isEmpty()) {
                    getSpendMonthlyList(maintenanceCostList, FleetMaintenanceUtil.getStartDate(), 12);
                    getSpendByVehicleList(maintenanceCostList, FleetMaintenanceUtil.getStartDate(), 12);
                    getSpendMaintenanceMileageList(maintenanceMileageList, FleetMaintenanceUtil.getStartDate(), 12);
                    getSpendBySupplierList(maintenanceSpendBySupplierList /* , maintenanceSpendBySupplierUtil.getStartDate(), 12 */); // 2 SpendBySupplier CHARTS
                    getSpendKmTravelledList();
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
            maintenanceMileageList = fleetMaintenanceUtil.findMileagesBetweenTwoDates(startDate, endDate);
            maintenanceSpendBySupplierList = maintenanceSpendBySupplierUtil.findMaintenanceSpendBySupplierBetweenTwoDates(startDate, endDate);

            if (!maintenanceCostList.isEmpty()) {
//                List<TotalMaintenanceSpendMonthly> spendMonthlyChartData = fleetMaintenanceUtil.getMaintenanceSpendMonthlyChartData(maintenanceCostList, startDate, monthCount);
                getSpendMonthlyList(maintenanceCostList, startDate, monthCount);
                getSpendByVehicleList(maintenanceCostList, startDate, monthCount);
                getSpendMaintenanceMileageList(maintenanceMileageList, startDate, monthCount);
                getSpendBySupplierList(maintenanceSpendBySupplierList /*, startDate, monthCount */); // 2 SpendBySupplier CHARTS
                getSpendKmTravelledList();
                displayCharts();
            } else {
                Notification.show("No Maintenance Cost found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
            }
        } else {
            Notification.show("Please Specify Date Range in Approprate Order.", Notification.Type.TRAY_NOTIFICATION);
        }
//        endDate = null;
//        startDate = null;
    }

    private List<AnnualDataFleetMaintenanceCost> getMaintenanceCostList() {
        return fleetMaintenanceUtil.findMaintenanceCostBetweenTwoDates(FleetMaintenanceUtil.getStartDate(), FleetMaintenanceUtil.getEndDate());
    }

    private List<AnnualDataFleetMaintenanceMileage> getMaintenanceMileageList() {
        return fleetMaintenanceUtil.findMileagesBetweenTwoDates(FleetMaintenanceUtil.getStartDate(), FleetMaintenanceUtil.getEndDate());
    }

    private List<MaintenanceSpendBySupplier> getMaintenanceSpendBySupplier() {
        return maintenanceSpendBySupplierUtil.findMaintenanceSpendBySupplierBetweenTwoDates(maintenanceSpendBySupplierUtil.getStartDate(), maintenanceSpendBySupplierUtil.getEndDate());
    }

    public void getSpendMonthlyList(List<AnnualDataFleetMaintenanceCost> maintenanceCostList, Date date, Integer period) {
        spendMonthlyChartDataList = fleetMaintenanceUtil.getMaintenanceSpendMonthlyChartData(maintenanceCostList, date, period);
        grandTotalMaintenanceSpend = FleetMaintenanceUtil.grandTotalMaintenanceSpend;

//        // TESTING
//        System.out.println("\n Total Fleet Maintenance Spend Data");
//        for (TotalMaintenanceSpendMonthly totalMaintenanceSpendMonthly : spendMonthlyChartDataList) {
//            System.out.println(totalMaintenanceSpendMonthly.getMonthYear() + " = " + totalMaintenanceSpendMonthly.getTotal());
////             System.out.println("Month: " + dateTimeFormatHelper.getMonthYearMonthAsMediumString(annualDataFleetMaintenanceCost.getTransactionMonth() + "") + ", Total: " + annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
//        }
    }

    public void getSpendByVehicleList(List<AnnualDataFleetMaintenanceCost> maintenanceCostList, Date date, Integer period) {
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
//        // See getSpendKmTravelledList() Method below
    }

    public void getSpendBySupplierList(List<MaintenanceSpendBySupplier> maintenanceSpendBySupplierList /* , Date startDate, Integer period */) {
        spendBySupplierChartDataList = maintenanceSpendBySupplierUtil.buildTotalMaintenanceSpendBySupplier(maintenanceSpendBySupplierList);
    }

    public void getSpendKmTravelledList() {
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
        // Sort SpendBySupplier in Asc by Total Amount
        Collections.sort(spendBySupplierChartDataList, TotalMaintenanceSpendBySupplier.AscOrderAmountSpendComparator);

        DCharts dTotalMaintenanceCostMOnthlySpendChart = createTotalMaintenanceCostMOnthlySpendChart();
        DCharts dTotalMaintenanceSpendPerVehicleChart = createTotalMaintenanceSpendPerVehicleChart();
        DCharts dTotalSpendBySupplierBarChart = createTotalSpendBySupplierBarChart();
        DCharts dTotalSpendBySupplierPieChart = createTotalSpendBySupplierPieChart();
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
        Panel totalMaintenanceSpendPerVehiclePanel = new Panel("Total Fleet Maintenance Spend By Vehicle: " + spendMonthlyChartDataList.get(0).getMonthYear() + " - " + spendMonthlyChartDataList.get(spendMonthlyChartDataList.size() - 1).getMonthYear()); //
        totalMaintenanceSpendPerVehiclePanel.setWidth("100%");
        totalMaintenanceSpendPerVehiclePanel.setHeight("100%");
        totalMaintenanceSpendPerVehiclePanel.setStyleName("bubble");
        totalMaintenanceSpendPerVehiclePanel.setSizeUndefined(); // Shrink to fit content

        // Create a Panel
        Panel totalSpendBySupplierBarChartPanel = new Panel("Total Fleet Maintenance Spend by Supplier: " + spendMonthlyChartDataList.get(0).getMonthYear() + " - " + spendMonthlyChartDataList.get(spendMonthlyChartDataList.size() - 1).getMonthYear()); //
        totalSpendBySupplierBarChartPanel.setWidth("100%");
        totalSpendBySupplierBarChartPanel.setHeight("100%");
        totalSpendBySupplierBarChartPanel.setStyleName("bubble");
        totalSpendBySupplierBarChartPanel.setSizeUndefined(); // Shrink to fit content

//        // Create a Panel
//        Panel totalSpendBySupplierPieChartPanel = new Panel("Total Fleet Maintenance Spend by Supplier: " + spendMonthlyChartDataList.get(0).getMonthYear() + " - " + spendMonthlyChartDataList.get(spendMonthlyChartDataList.size() - 1).getMonthYear()); //
//        totalSpendBySupplierPieChartPanel.setWidth("100%");
//        totalSpendBySupplierPieChartPanel.setHeight("100%");
//        totalSpendBySupplierPieChartPanel.setStyleName("bubble");
//        totalSpendBySupplierPieChartPanel.setSizeUndefined(); // Shrink to fit content

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


        // =============================================================================================================================
        HorizontalLayout totalSpendBySupplierBarLayout = new HorizontalLayout();
        totalSpendBySupplierBarLayout.setMargin(true); //
        totalSpendBySupplierBarLayout.setSizeUndefined(); // Shrink to fit content
        totalSpendBySupplierBarLayout.addComponent(dTotalSpendBySupplierBarChart);
        totalSpendBySupplierBarLayout.addComponent(dTotalSpendBySupplierPieChart);
        // Add item to the Panel
        totalSpendBySupplierBarChartPanel.setContent(totalSpendBySupplierBarLayout);

        // ========
//        HorizontalLayout totalSpendBySupplierPieLayout = new HorizontalLayout();
//        totalSpendBySupplierPieLayout.setMargin(true); //
//        totalSpendBySupplierPieLayout.setSizeUndefined(); // Shrink to fit content
//        totalSpendBySupplierPieLayout.addComponent(dTotalSpendBySupplierPieChart);
//        // Add item to the Panel
//        totalSpendBySupplierPieChartPanel.setContent(totalSpendBySupplierPieLayout);

        // =============================================================================================================================
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

        BigDecimal totalMaintenanceSpendPerKm = grandTotalMaintenanceSpend.divide(grandTotalMaintenanceMileage, 2, BigDecimal.ROUND_HALF_UP);
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

        //=======================================================================================================================

        chart.chartVerticalLayout.removeAllComponents();
        chart.chartVerticalLayout.addComponent(totalFleetMaintenanceSpendPanel);
        chart.chartVerticalLayout.addComponent(totalMaintenanceSpendPerVehiclePanel);
        chart.chartVerticalLayout.addComponent(totalSpendBySupplierBarChartPanel);
//        chart.chartVerticalLayout.addComponent(totalSpendBySupplierPieChartPanel);
        chart.chartVerticalLayout.addComponent(maintenanceSpendByKmTravelledPanel);

        // house cleaning
        grandTotalMaintenanceMileage = BigDecimal.ZERO;
        grandTotalMaintenanceSpend = BigDecimal.ZERO;
        endDate = startDate = null;
    }

    public DCharts createTotalMaintenanceCostMOnthlySpendChart() {
        TotalMaintenanceSpendMonthlyChart TotalMaintenanceSpendMonthlyChart = new TotalMaintenanceSpendMonthlyChart();
        final Locale locale = new Locale("za", "ZA");
        // Format a decimal value for a specific locale
        final DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));
        return TotalMaintenanceSpendMonthlyChart.createChart(spendMonthlyChartDataList, grandTotalMaintenanceSpend);
    }

    public DCharts createTotalMaintenanceSpendPerVehicleChart() {
        TotalMaintenanceSpendPerVehicleChart totalMaintenanceSpendPerVehicleChart = new TotalMaintenanceSpendPerVehicleChart();
        return totalMaintenanceSpendPerVehicleChart.createChart(spendByVehicleChartDataList, chartPeriod);
    }

    public DCharts createTotalSpendBySupplierBarChart() { // ;dTotalSpendBySupplierBarChart =
        MaintenanceSpendBySupplierBarChart maintenanceSpendBySupplierBarChart = new MaintenanceSpendBySupplierBarChart();
        return maintenanceSpendBySupplierBarChart.createChart(spendBySupplierChartDataList, chartPeriod /* , chartPeriod */);
    }

    public DCharts createTotalSpendBySupplierPieChart() { //;dTotalSpendBySupplierPieChart =
        MaintenanceSpendBySupplierPieChart maintenanceSpendBySupplierPieChart = new MaintenanceSpendBySupplierPieChart();
        return maintenanceSpendBySupplierPieChart.createChart(spendBySupplierChartDataList, chartPeriod /* , chartType */);
    }

    public DCharts createMaintenanceSpendByKmTravelledChart(String chartType) {
        MaintenanceSpendByKmTravelledChart maintenanceSpendByKmTravelledChart = new MaintenanceSpendByKmTravelledChart();
        return maintenanceSpendByKmTravelledChart.createChart(spendByKmTravelledChartDataList, chartPeriod, chartType);
    }
}
