/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.view.AssignDriversTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.view.ManageRoutesTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.view.VehicleSchedulingTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.view.WorkSchedulingTab;

/**
 *
 * @author Ferox
 */
public class WorkSchedulingMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private AssignDriversTab assignDriversTab;
    private ManageRoutesTab manageRoutesTab;
    private WorkSchedulingTab workSchedulingTab;
    private VehicleSchedulingTab vehicleSchedulingTab;

    public WorkSchedulingMenu(MshenguMain app, String selectedTab) {
        main = app;

        assignDriversTab = new AssignDriversTab(main);
        manageRoutesTab = new ManageRoutesTab(main);
        workSchedulingTab = new WorkSchedulingTab(main);
        vehicleSchedulingTab = new VehicleSchedulingTab(main);


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        
        tab.addTab(workSchedulingTab, "Scheduled Routes", null);
        tab.addTab(assignDriversTab, "Assign Driver to Vehicle", null);
        tab.addTab(manageRoutesTab, "Manage Routes", null);
        tab.addTab(vehicleSchedulingTab, "Fleet Weekly Work Schedule", null);


            if (selectedTab.equals("DRIVERS")) {
                tab.setSelectedTab(assignDriversTab);
            } else if (selectedTab.equals("ROUTES")) {
                tab.setSelectedTab(manageRoutesTab);
            } else if (selectedTab.equals("LANDING")) {
                tab.setSelectedTab(workSchedulingTab);
            } else if (selectedTab.equals("VEHICLE_SCHEDULING")) {
                tab.setSelectedTab(vehicleSchedulingTab);
            } 
        
        addComponent(tab);
    }

}