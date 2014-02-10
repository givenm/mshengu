/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.inventory.views.UnitMaintenanceTab;
import zm.hashcode.mshengu.client.web.content.assets.inventory.views.UnitPartsTab;
import zm.hashcode.mshengu.client.web.content.assets.inventory.views.UnitRepairsTab;
import zm.hashcode.mshengu.client.web.content.assets.inventory.views.UnitToolsTab;

/**
 *
 * @author Ferox
 */
public class AssetsMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private UnitRepairsTab repairsTab;
    private UnitMaintenanceTab maintenanceTab;
   private UnitToolsTab unitToolsTab;
    private UnitPartsTab unitPartsTab;
    
    
    public AssetsMenu(MshenguMain app, String selectedTab) {
        main = app;

        repairsTab = new UnitRepairsTab(main);
        maintenanceTab = new UnitMaintenanceTab(main);
        unitToolsTab = new UnitToolsTab(main);
        unitPartsTab = new UnitPartsTab(main);


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(repairsTab, "Repairs", null);
        tab.addTab(maintenanceTab, "Maintenance", null);
        tab.addTab(unitToolsTab, "Tools", null);
        tab.addTab(unitPartsTab, "Parts", null);


            if (selectedTab.equals("LANDING")) {
                tab.setSelectedTab(repairsTab);
            } else if (selectedTab.equals("MAINTENANCE")) {
                tab.setSelectedTab(maintenanceTab);
            } else if (selectedTab.equals("TOOLS")) {
                tab.setSelectedTab(unitToolsTab);
            } else if (selectedTab.equals("PARTS")) {
                tab.setSelectedTab(unitPartsTab);
            } 
        
        addComponent(tab);
    }

}