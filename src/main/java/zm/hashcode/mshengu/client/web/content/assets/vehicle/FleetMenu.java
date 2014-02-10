/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.views.TruckCategoryTab;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.views.TruckDetailsTab;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.views.VehicleQRCodesTab;

/**
 *
 * @author Ferox
 */
public class FleetMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;

    public FleetMenu(MshenguMain app, String selectedTab) {
        main = app;

        VerticalLayout truckDetailsTab = new VerticalLayout();
        truckDetailsTab.setMargin(true);
        truckDetailsTab.addComponent(new TruckDetailsTab(main));

        VerticalLayout truckrCategoryTab = new VerticalLayout();
        truckrCategoryTab.setMargin(true);
        truckrCategoryTab.addComponent(new TruckCategoryTab(main));


        VerticalLayout vehicleIDTab = new VerticalLayout();
        vehicleIDTab.setMargin(true);
        vehicleIDTab.addComponent(new VehicleQRCodesTab(main));
//
//        VerticalLayout serviceCostsTab = new VerticalLayout();
//        serviceCostsTab.setMargin(true);
//        serviceCostsTab.addComponent(new ServiceCostsTab(main));


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(truckDetailsTab, "Vehicle Details", null);
        tab.addTab(truckrCategoryTab, "Vehicle Categories", null);
        tab.addTab(vehicleIDTab, "Vehicle ID", null);
//        tab.addTab(serviceCostsTab, "Servicing Cost", null);
//

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(truckDetailsTab);

        } else if (selectedTab.equals("CATEGORY")) {
            tab.setSelectedTab(truckrCategoryTab);
        } else  if (selectedTab.equals("VEHICLE_ID")) {
            tab.setSelectedTab(vehicleIDTab);
        }
//        else if (selectedTab.equals("SERVICE_COST")) {
//            tab.setSelectedTab(serviceCostsTab);
//        }
        addComponent(tab);
    }
}
