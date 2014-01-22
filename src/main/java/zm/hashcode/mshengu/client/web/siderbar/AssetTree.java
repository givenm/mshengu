/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.siderbar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.AssetsMenu;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.UnitMenu;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.FleetMenu;

/**
 *
 * @author boniface
 */
public class AssetTree extends Tree implements ItemClickEvent.ItemClickListener {

    private final MshenguMain main;
    public static final Object TOILETS = "Toilets";
    public static final Object VEHICLES = "Vehicles";
    public static final Object INCIDENTS = "Incidents";
    public static final Object INVENTORY = "Inventory";
    private static final String LANDING_TAB = "LANDING";

    public AssetTree(MshenguMain main) {
        this.main = main;
        addItem(TOILETS);
        addItem(VEHICLES);
        addItem(INVENTORY);

        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (TOILETS.equals(itemId)) {
                manageToiletsView();
            } else if(VEHICLES.equals(itemId)){
                manageVehiclesView();
            } else if(INVENTORY.equals(itemId)){
                manageInventoryView();
            }
            
        }
    }

    private void manageToiletsView() {
        main.content.setSecondComponent(new UnitMenu(main, LANDING_TAB));

    }
    
     private void manageVehiclesView() {
        main.content.setSecondComponent(new FleetMenu(main, LANDING_TAB));

    }
     
      private void manageInventoryView() {
        main.content.setSecondComponent(new AssetsMenu(main, LANDING_TAB));

    }//manageIncidentsView
}
