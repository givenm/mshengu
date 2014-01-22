/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.views.DailyInputsTab;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.views.DailyTrackerTab;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.views.VehicleFuelUsageTab;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.views.OperationalAllowanceTab;

/**
 *
 * @author geek
 */
public class DailyDieselTrackerMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private DailyInputsTab dailyInputsTab;
//    private FuelAndOilPriceTab fuelAndOilPriceTab;
    private VehicleFuelUsageTab vehicleFuelUsage;
    private DailyTrackerTab dailyTrackerTab;
    private OperationalAllowanceTab operationalAllowanceTab;

    public DailyDieselTrackerMenu(MshenguMain app, String selectedTab) {
        main = app;

        dailyInputsTab = new DailyInputsTab(main);
//        fuelAndOilPriceTab = new FuelAndOilPriceTab(main);
        vehicleFuelUsage = new VehicleFuelUsageTab(main);
        dailyTrackerTab = new DailyTrackerTab(main);
        operationalAllowanceTab = new OperationalAllowanceTab(main);

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

//        tab.addTab(fuelAndOilPriceTab, "Fuel Price", null);
        tab.addTab(vehicleFuelUsage, "Vehicle Fuel Usage", null); //   Previously "Fleet Menu"
        tab.addTab(dailyTrackerTab, "Daily Tracker", null);
        tab.addTab(dailyInputsTab, "Daily Inputs", null);
        tab.addTab(operationalAllowanceTab, "Operational Allowance", null);

        switch (selectedTab) {

            case "LANDING":
                tab.setSelectedTab(vehicleFuelUsage);
                break;
            case "DAILY_INPUTS":
                tab.setSelectedTab(dailyInputsTab);
                break;
            case "DAILY_TRACKER":
                tab.setSelectedTab(dailyTrackerTab);
                break;
            case "OPERATIONAL_ALLOW":
                tab.setSelectedTab(operationalAllowanceTab);
                break;
        }

        addComponent(tab);
    }

    /**
     * @return the dailyTrackerTab
     */
    public DailyTrackerTab getDailyTrackerTab() {
        return dailyTrackerTab;
    }

    /**
     * @return the vehicleFuelUsage
     */
    public VehicleFuelUsageTab getVehicleFuelUsage() {
        return vehicleFuelUsage;
    }
}
