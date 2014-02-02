/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.views;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.AnnualDataTablesUI;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.forms.AnnualDataForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.MonthlyMileageData;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.MonthlySpendData;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables.AnnualMaintenanceCostHeadingTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables.AnnualMaintenanceCostTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables.AnnualMileageTable;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils.FleetMaintenanceUtil;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;

/**
 *
 * @author ColinWa
 */
public class MaintenanceAnnualDataTab extends VerticalLayout implements Button.ClickListener, Table.ColumnResizeListener {

    private final MshenguMain main;
    private final AnnualDataForm form;
    private final AnnualDataTablesUI annualDataTablesUI;
    private final AnnualMaintenanceCostHeadingTable annualMaintenanceCostHeadingTable;
    private final AnnualMaintenanceCostTable annualMaintenanceCostTable;
    private final AnnualMileageTable annualMileageTable;
    //
    private DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private FleetMaintenanceUtil fleetMaintenanceUtil = new FleetMaintenanceUtil();
    private static List<AnnualDataFleetMaintenanceCost> annualMaintenanceCostList = null;
    private static List<AnnualDataFleetMaintenanceMileage> annualMileageList = null;
    private static List<MonthlySpendData> maintenanceCostDataList = null;
    private static List<MonthlyMileageData> mileageList = null;
    public static BigDecimal grandTotalMaintenanceCost = BigDecimal.ZERO;
    public static BigDecimal grandTotalMaintenanceMileage = BigDecimal.ZERO;
    private static int annualDataMonthCount = 12;

    public MaintenanceAnnualDataTab(MshenguMain app) {
        main = app;
        form = new AnnualDataForm();
        annualDataTablesUI = new AnnualDataTablesUI();
        annualMaintenanceCostHeadingTable = new AnnualMaintenanceCostHeadingTable(main);
        annualMaintenanceCostTable = new AnnualMaintenanceCostTable(main);
        annualMileageTable = new AnnualMileageTable(main);

        addListeners();
        addComponent(form);
        addComponent(annualDataTablesUI);

        setSizeFull();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.generateButton) {
            generateData();
        }
    }

    private void addListeners() {
//        //Register Button Listeners
        form.generateButton.addClickListener((Button.ClickListener) this);
        annualMaintenanceCostHeadingTable.addColumnResizeListener(this);
    }

    public void generateData() {
//        Date endDate = new Date();
        int year = Integer.parseInt(dateTimeFormatHelper.getYearNumber(new Date()));

//        Date endDate = dateTimeFormatHelper.getDate(1, 1, year); // Start of Year after Current Year i.e. 1st January ???? counter begins from month before
        fleetMaintenanceUtil.determineDateRange(new Date(), annualDataMonthCount);
//        fleetMaintenanceUtil.determineDateRange(getDate(1, 10, 2013), annualDataMonthCount); Testing the for months with DATA
        annualMaintenanceCostList = getMaintenanceCostList();
        annualMileageList = getMaintenanceMileageList();

        if (!annualMaintenanceCostList.isEmpty()) {
            grabMaintenanceAnnualData(annualMaintenanceCostList); // , FleetMaintenanceUtil.startDate, 12
            // Sort AnnualDataFleetMaintenanceCost List in ASC Order by Date ASC Order by Vehicle Number .
            Collections.sort(maintenanceCostDataList, MonthlySpendData.AscOrderDateAscOrderVehicleNumberComparator);
            createMaintenanceCostAndHeadingTable();
        } else {
            Notification.show("No Maintenance Cost Data found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
        }

        if (!annualMileageList.isEmpty()) {
            grabMileageAnnualData(annualMileageList); // , FleetMaintenanceUtil.startDate, 12
            // Sort AnnualDataFleetMaintenanceCost List in ASC Order by Vehicle Number ASC Order by Date.
            Collections.sort(mileageList, MonthlyMileageData.AscOrderDateAscOrderVehicleNumberComparator);
            createMileageAndHeadingTable();
        } else {
            Notification.show("No Mileage Data found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
        }
        if (!annualMaintenanceCostList.isEmpty() || !annualMileageList.isEmpty()) {
            showTables();
        }
    }

    private List<AnnualDataFleetMaintenanceCost> getMaintenanceCostList() {
        return fleetMaintenanceUtil.findMaintenanceCostBetweenTwoDates(FleetMaintenanceUtil.getStartDate(), FleetMaintenanceUtil.getEndDate());
    }

//    public Date getDate(int day, int month, int year) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, year);
//        calendar.set(Calendar.MONTH, month);
//        calendar.set(Calendar.DAY_OF_MONTH, day);
//// Set time fields to zero
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        return calendar.getTime();
//    }
    public void grabMaintenanceAnnualData(List<AnnualDataFleetMaintenanceCost> maintenanceCostList) {
        maintenanceCostDataList = fleetMaintenanceUtil.buildAnnualDataMaintenanceCost(maintenanceCostList);
    }

    private List<AnnualDataFleetMaintenanceMileage> getMaintenanceMileageList() {
        return fleetMaintenanceUtil.findMaintenanceMileageBetweenTwoDates(FleetMaintenanceUtil.getStartDate(), FleetMaintenanceUtil.getEndDate());
    }

    public void grabMileageAnnualData(List<AnnualDataFleetMaintenanceMileage> maintenanceMileageList) {
        mileageList = fleetMaintenanceUtil.buildAnnualDataMileage(maintenanceMileageList);
    }

    public void createMaintenanceCostAndHeadingTable() {
        annualMaintenanceCostHeadingTable.populateAnnualMaintenanceCostHeadingTable();
        // Size the table Height to the number of Rows u want or to autofit the rows in it
        annualMaintenanceCostHeadingTable.setPageLength(annualMaintenanceCostHeadingTable.size()); // Adjust the table height a bit

////         // Add totalMileageTable to Layout then to the Panel
////        VerticalLayout costHeadingTableLayout = new VerticalLayout();
////        costHeadingTableLayout.addComponent(annualMaintenanceCostHeadingTable);
////        totalMileagePanel.setContent(costHeadingTableLayout);
//////        totalMileagePanel.setWidth(totalMileageTable.getWidth() + "px");

        annualMaintenanceCostTable.populateAnnualMaintenanceCostTable(maintenanceCostDataList, annualDataMonthCount, fleetMaintenanceUtil.getStartDate());
        // Size the table Height to the number of Rows u want or to autofit the rows in it
        annualMaintenanceCostTable.setPageLength(annualMaintenanceCostTable.size()); // Adjust the table height a bit
    }

    public void createMileageAndHeadingTable() {
        // No Table with Headings before Table with Annual Mileage
        annualMileageTable.populateAnnualMileageTable(mileageList, annualDataMonthCount, fleetMaintenanceUtil.getStartDate());
        // Size the table Height to the number of Rows u want or to autofit the rows in it
        annualMileageTable.setPageLength(annualMileageTable.size()); // Adjust the table height a bit

    }

    public void showTables() {
        // SHow Tables
        annualDataTablesUI.addTables(annualMaintenanceCostHeadingTable, annualMaintenanceCostTable, annualMileageTable);
    }

    @Override
    public void columnResize(Table.ColumnResizeEvent event) {
        // Get the new width of the resized column
        int width = event.getCurrentWidth();

        // Get the property ID of the resized column
        String column = (String) event.getPropertyId();

        System.out.println("Column " + column + " width is: " + width);
    }
}
