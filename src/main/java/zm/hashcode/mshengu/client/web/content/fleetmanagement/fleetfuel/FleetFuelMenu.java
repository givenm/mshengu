/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.views.ServiceFleetDashboardTab;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.views.FuelAnnualDataTab;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.views.ExecutiveDashboardTab;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.views.MonthlySpendTab;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.views.VehicleFuelUsageTab;

/**
 *
 * @author Ferox
 */
public class FleetFuelMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private ExecutiveDashboardTab executiveDashboardTab;
    private ServiceFleetDashboardTab fleetFuelDashboardTab;
    private VehicleFuelUsageTab vehicleFuelUsageTab;
    private FuelAnnualDataTab fuelAnnualDataTab;
    private MonthlySpendTab monthlySpendTab;

    public FleetFuelMenu(MshenguMain app, String selectedTab) {
        main = app;

        executiveDashboardTab = new ExecutiveDashboardTab(main);
        fleetFuelDashboardTab = new ServiceFleetDashboardTab(main);
        vehicleFuelUsageTab = new VehicleFuelUsageTab(main);
        fuelAnnualDataTab = new FuelAnnualDataTab(main);
        monthlySpendTab = new MonthlySpendTab(main);

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(executiveDashboardTab, "Executive Dashboard", null);
        tab.addTab(fleetFuelDashboardTab, "Service Fleet Dashboard", null);
//        tab.addTab(vehicleFuelUsageTab, "Fleet Fuel", null); // Previously "Fleet Menu" , "Vehicle Fuel Usage"
//        tab.addTab(fuelAnnualDataTab, "Fuel Annual Data", null);
//        tab.addTab(monthlySpendTab, "Monthly Spend", null);
        switch (selectedTab) {
            case "LANDING":
                tab.setSelectedTab(executiveDashboardTab);
                break;
            case "FLEET_DASHBOARD":
                tab.setSelectedTab(fleetFuelDashboardTab);
                break;
            case "VEHICLE_FUEL_USAGE":
                tab.setSelectedTab(vehicleFuelUsageTab);
                break;
            case "ANNUAL":
                tab.setSelectedTab(fuelAnnualDataTab);
                break;
            case "MONTHLY":
                tab.setSelectedTab(monthlySpendTab);
                break;
        }

        addComponent(tab);
    }
}
