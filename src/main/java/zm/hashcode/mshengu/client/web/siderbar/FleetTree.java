/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.siderbar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.FleetMaintenanceMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.DailyDieselTrackerMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.FleetFuelMenu;

/**
 *
 * @author boniface
 */
public class FleetTree extends Tree implements ItemClickEvent.ItemClickListener {

    private final MshenguMain main;
    public static final Object DAILY_DIESEL_TRACKER = "Daily Diesel Tracker";
    public static final Object FLEET_FUEL = "Fleet Fuel";
    public static final Object FLEET_MAINTENANCE = "Fleet Maintenance";
    private static final String LANDING_TAB = "LANDING";

    public FleetTree(MshenguMain main) {
        this.main = main;

//        addItem(FLEET_FUEL);
        addItem(FLEET_MAINTENANCE);
        addItem(DAILY_DIESEL_TRACKER);
        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (DAILY_DIESEL_TRACKER.equals(itemId)) {
                dailyDieselView();
            }
            if (FLEET_FUEL.equals(itemId)) {
                fleetFuelView();
            }
            if (FLEET_MAINTENANCE.equals(itemId)) {
                fleetMaintenanceView();
            }
        }
    }

    private void dailyDieselView() {
        main.content.setSecondComponent(new DailyDieselTrackerMenu(main, LANDING_TAB));

    }

    private void fleetFuelView() {
        main.content.setSecondComponent(new FleetFuelMenu(main, LANDING_TAB));

    }

    private void fleetMaintenanceView() {
        main.content.setSecondComponent(new FleetMaintenanceMenu(main, LANDING_TAB));

    }
}
