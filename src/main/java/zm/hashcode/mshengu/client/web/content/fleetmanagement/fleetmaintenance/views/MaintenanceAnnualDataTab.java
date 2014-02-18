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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
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
import zm.hashcode.mshengu.domain.fleet.Truck;
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
    private static List<Truck> serviceTrucks = null;
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
        serviceTrucks = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
//        Date endDate = new Date();
        int year = Integer.parseInt(dateTimeFormatHelper.getYearNumber(new Date()));

//        Date endDate = dateTimeFormatHelper.getDate(1, 1, year); // Start of Year after Current Year i.e. 1st January ???? counter begins from month before
        fleetMaintenanceUtil.determineDateRange(new Date(), annualDataMonthCount);
//        fleetMaintenanceUtil.determineDateRange(getDate(1, 10, 2013), annualDataMonthCount); Testing the for months with DATA
        annualMaintenanceCostList = getMaintenanceCostList();
        annualMileageList = getMaintenanceMileageList();

        if (!annualMaintenanceCostList.isEmpty()) {
////            System.out.println("buildAnnualDataMaintenanceCost in MaintenanceAnnualDataTab.java STARTS: " + new Date());
            maintenanceCostDataList = buildAnnualDataMaintenanceCost(annualMaintenanceCostList, serviceTrucks);
////            System.out.println("buildAnnualDataMaintenanceCost in MaintenanceAnnualDataTab.java ENDS: " + new Date());
            // Sort AnnualDataFleetMaintenanceCost List in ASC Order by Date ASC Order by Vehicle Number .
            Collections.sort(maintenanceCostDataList, MonthlySpendData.AscOrderDateAscOrderVehicleNumberComparator);
            createMaintenanceCostAndHeadingTable();
        } else {
            Notification.show("No Maintenance Cost Data found for Specified Date Range!", Notification.Type.TRAY_NOTIFICATION);
        }

        if (!annualMileageList.isEmpty()) {
////            System.out.println("buildAnnualDataMileage in MaintenanceAnnualDataTab.java STARTS: " + new Date());
            mileageList = buildAnnualDataMileage(annualMileageList, serviceTrucks);
////            System.out.println("buildAnnualDataMileage in MaintenanceAnnualDataTab.java ENDS: " + new Date());
            // Sort AnnualDataFleetMaintenanceCost List in ASC Order by Vehicle Number ASC Order by Date.
            Collections.sort(mileageList, MonthlyMileageData.AscOrderDateAscOrderVehicleNumberComparator);
            createMileageTable();
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

    private List<AnnualDataFleetMaintenanceMileage> getMaintenanceMileageList() {
        return fleetMaintenanceUtil.findMaintenanceMileageBetweenTwoDates(FleetMaintenanceUtil.getStartDate(), FleetMaintenanceUtil.getEndDate());
    }

    public void createMaintenanceCostAndHeadingTable() {
        annualMaintenanceCostHeadingTable.populateAnnualMaintenanceCostHeadingTable();
        // Size the table Height to the number of Rows u want or to autofit the rows in it
        annualMaintenanceCostHeadingTable.setPageLength(annualMaintenanceCostHeadingTable.size()); // Adjust the table height a bit
        annualMaintenanceCostTable.populateAnnualMaintenanceCostTable(maintenanceCostDataList, annualDataMonthCount, FleetMaintenanceUtil.getStartDate(), serviceTrucks);
        // Size the table Height to the number of Rows u want or to autofit the rows in it
        annualMaintenanceCostTable.setPageLength(annualMaintenanceCostTable.size()); // Adjust the table height a bit
    }

    public void createMileageTable() {
        // No Table with Headings before Table with Annual Mileage
        annualMileageTable.populateAnnualMileageTable(mileageList, annualDataMonthCount, FleetMaintenanceUtil.getStartDate(), serviceTrucks);
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

    public List<MonthlySpendData> buildAnnualDataMaintenanceCost(List<AnnualDataFleetMaintenanceCost> annualDataFleetMaintenanceCostList, List<Truck> serviceTrucks) {
        String month = null;
        int iD = 1;
        List<MonthlySpendData> monthlySpendDataList = new ArrayList<>();
        Collections.sort(annualDataFleetMaintenanceCostList, AnnualDataFleetMaintenanceCost.AscOrderTruckAscOrderDateComparator);
        for (AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost : annualDataFleetMaintenanceCostList) {
            month = dateTimeFormatHelper.getMonthYearMonthAsMediumString(annualDataFleetMaintenanceCost.getTransactionMonth().toString());
            monthlySpendDataList.add(createTotalMonthlySpendList(annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost(), month, annualDataFleetMaintenanceCost.getTransactionMonth(), annualDataFleetMaintenanceCost.getTruckId(), iD, serviceTrucks));
            iD++;
        }
        return monthlySpendDataList;
    }

    private MonthlySpendData createTotalMonthlySpendList(BigDecimal totalMonthlySpend, String month, Date date, String truckId, int id, List<Truck> serviceTrucks) {
        Truck truck = null;
        for (Truck truckk : serviceTrucks) {
            if (truckk.getId().equals(truckId)) {
                truck = truckk;
                break;
            }
        }
        MonthlySpendData monthlySpendData = new MonthlySpendData();
        monthlySpendData.setVehicleNumber(truck.getVehicleNumber());
        monthlySpendData.setNumberPlate(truck.getNumberPlate());
        monthlySpendData.setTransactionDate(month);
        monthlySpendData.setTransactDate(date);
        monthlySpendData.setTruckMonthlySpendTotal(totalMonthlySpend);
        monthlySpendData.setId(id + "");

        return monthlySpendData;
    }

    public List<MonthlyMileageData> buildAnnualDataMileage(List<AnnualDataFleetMaintenanceMileage> annualDataFleetMaintenanceMileageList, List<Truck> serviceTrucks) {
        String month = null;
        int iD = 1;
        final List<MonthlyMileageData> monthlyMileageDataList = new ArrayList<>();
        Collections.sort(annualDataFleetMaintenanceMileageList, AnnualDataFleetMaintenanceMileage.AscOrderTruckAscOrderDateComparator);
        for (AnnualDataFleetMaintenanceMileage annualDataFleetMaintenanceMileage : annualDataFleetMaintenanceMileageList) {
            month = dateTimeFormatHelper.getMonthYearMonthAsMediumString(annualDataFleetMaintenanceMileage.getTransactionMonth().toString());
            monthlyMileageDataList.add(createMonthlyMileageList(annualDataFleetMaintenanceMileage.getMonthlyMileage(), month, annualDataFleetMaintenanceMileage.getTransactionMonth(), annualDataFleetMaintenanceMileage.getTruckId(), iD, serviceTrucks));
            iD++;
        }
        return monthlyMileageDataList;
    }

    private MonthlyMileageData createMonthlyMileageList(Integer monthlyMileageTotal, String month, Date date, String truckId, int id, List<Truck> serviceTrucks) {
        Truck truck = null;
        for (Truck truckk : serviceTrucks) {
            if (truckk.getId().equals(truckId)) {
                truck = truckk;
                break;
            }
        }
        MonthlyMileageData monthlyMileageData = new MonthlyMileageData();
        monthlyMileageData.setVehicleNumber(truck.getVehicleNumber());
        monthlyMileageData.setNumberPlate(truck.getNumberPlate());
        monthlyMileageData.setTransactionDate(month);
        monthlyMileageData.setTransactDate(date);
        monthlyMileageData.setTruckMonthlyMileageTotal(monthlyMileageTotal);
        monthlyMileageData.setId(id + "");

        return monthlyMileageData;
    }
}
