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
public class WorkSchedulingMenu extends VerticalLayout implements TabSheet.SelectedTabChangeListener {

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

        switch (selectedTab) {
            case "DRIVERS":
                tab.setSelectedTab(assignDriversTab);

                break;
            case "ROUTES":
                tab.setSelectedTab(manageRoutesTab);

                break;
            case "LANDING":
                tab.setSelectedTab(workSchedulingTab);
                break;
            case "VEHICLE_SCHEDULING":
                tab.setSelectedTab(vehicleSchedulingTab);
                break;
        }

        addComponent(tab);
        tab.addSelectedTabChangeListener(this);
    }

    @Override
    public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
        TabSheet tabSheet = event.getTabSheet();
        if (tabSheet.getSelectedTab() == assignDriversTab) {
            if (assignDriversTab.getTable() == null) {
                assignDriversTab.createAndLoadComponents();
            }
        } else if (tabSheet.getSelectedTab() == manageRoutesTab) {
            if (manageRoutesTab.getTable() == null) {
                manageRoutesTab.createAndLoadComponents();
            }
        } else if (tabSheet.getSelectedTab() == workSchedulingTab) {
        } else if (tabSheet.getSelectedTab() == vehicleSchedulingTab) {
            if (vehicleSchedulingTab.getTable() == null) {
                vehicleSchedulingTab.createAndLoadComponents();
            }
        }
    }

}
