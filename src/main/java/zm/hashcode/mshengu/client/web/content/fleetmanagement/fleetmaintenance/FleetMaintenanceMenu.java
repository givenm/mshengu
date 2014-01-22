/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.views.ServiceCostsTab;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.views.DashBoardTab;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.views.MaintenanceAnnualDataTab;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.views.VehicleRankingTab;

/**
 *
 * @author ColinWa
 */
public class FleetMaintenanceMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private DashBoardTab dashBoardTab;
    public VehicleRankingTab vehicleRankingTab;
    private MaintenanceAnnualDataTab MaintenanceAnnualDataTab;

    public FleetMaintenanceMenu(MshenguMain app, String selectedTab) {
        main = app;

        dashBoardTab = new DashBoardTab(main, this);
        vehicleRankingTab = new VehicleRankingTab(main, this);
        MaintenanceAnnualDataTab = new MaintenanceAnnualDataTab(main);

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(dashBoardTab, "Dash Board", null);
        tab.addTab(vehicleRankingTab, "Vehicle Ranking", null);
        tab.addTab(MaintenanceAnnualDataTab, "Maintenance Annual Data", null);

        switch (selectedTab) {
            case "LANDING":
                tab.setSelectedTab(dashBoardTab);
                break;
            case "VEHILCE_RANKING":
                tab.setSelectedTab(vehicleRankingTab);
                break;
            case "MAINTAIN_ANNUAL_DATA":
                tab.setSelectedTab(MaintenanceAnnualDataTab);
                break;

        }

        addComponent(tab);
    }
}
