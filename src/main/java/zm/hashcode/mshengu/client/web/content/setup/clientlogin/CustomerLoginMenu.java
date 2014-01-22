/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.clientlogin;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.clientlogin.views.UnitLifeCycleTab2;
import zm.hashcode.mshengu.client.web.content.setup.clientlogin.views.UnitServiceLogTab2;

/**
 *
 * @author Ferox
 */
public class CustomerLoginMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;

    public CustomerLoginMenu(MshenguMain app, String selectedTab) {
        main = app;

//        VerticalLayout unitDetailsTab = new VerticalLayout();
//        unitDetailsTab.setMargin(true);
//        unitDetailsTab.addComponent(new UnitDetailsTab(main));
    
        VerticalLayout unitLifeCycleTab = new VerticalLayout();
        unitLifeCycleTab.setMargin(true);
        unitLifeCycleTab.addComponent(new UnitLifeCycleTab2(main));
        
        VerticalLayout unitServiceLogTab = new VerticalLayout();
        unitServiceLogTab.setMargin(true);
        unitServiceLogTab.addComponent(new UnitServiceLogTab2(main));

        
//        VerticalLayout unitTypeTab = new VerticalLayout();
//        unitTypeTab.setMargin(true);
//        unitTypeTab.addComponent(new UnitTypeTab(main));




        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
//        tab.addTab(unitDetailsTab, "Unit Details", null);
        tab.addTab(unitLifeCycleTab, "Unit Life Cycle", null);
        tab.addTab(unitServiceLogTab, "Unit Service Logs", null);
//        tab.addTab(unitTypeTab, "Unit Type", null);
        
        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(unitLifeCycleTab);
        } else if (selectedTab.equals("TYPE")) {
//            tab.setSelectedTab(unitTypeTab);
        } else if (selectedTab.equals("LIFE_CYCLE")) {
            tab.setSelectedTab(unitLifeCycleTab);
        } else if (selectedTab.equals("SERVICE_LOGS")) {
            tab.setSelectedTab(unitServiceLogTab);
        }
        addComponent(tab);
    }
}