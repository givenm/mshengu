/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.incidents;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.views.ActiveIncidentsTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.views.IncidentActionStatusTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.views.IncidentTypeTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.incidents.views.InnactiveIncidentsTab;

/**
 *
 * @author Ferox
 */
public class IncidentMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private String selectedIncidentId;

    public IncidentMenu(MshenguMain app, String selectedTab) {
        main = app;


        VerticalLayout activeIncidentsTab = new VerticalLayout();
        activeIncidentsTab.setMargin(true);
        activeIncidentsTab.addComponent(new ActiveIncidentsTab(main));

        VerticalLayout inActiveIncidentsTab = new VerticalLayout();
        inActiveIncidentsTab.setMargin(true);
        inActiveIncidentsTab.addComponent(new InnactiveIncidentsTab(main));

        VerticalLayout incidentActionStatusTab = new VerticalLayout();
        incidentActionStatusTab.setMargin(true);
        incidentActionStatusTab.addComponent(new IncidentActionStatusTab(main));

        VerticalLayout incidentTypeTab = new VerticalLayout();
        incidentTypeTab.setMargin(true);
        incidentTypeTab.addComponent(new IncidentTypeTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(activeIncidentsTab, "Open Incidents", null);
        tab.addTab(inActiveIncidentsTab, "Closed Incidents", null);
        tab.addTab(incidentTypeTab, "Incident Type", null);
//        tab.addTab(incidentActionStatusTab, "Incident Action Status", null);
        //
        //        backtoMainPage.setStyleName("default");
        switch (selectedTab) {
            case "LANDING":
                tab.setSelectedTab(activeIncidentsTab);
                break;
            case "CLOSED":
                tab.setSelectedTab(inActiveIncidentsTab);
                break;
            case "TYPE":
                tab.setSelectedTab(incidentTypeTab);
                break;
            case "ACTION_STATUS":
                tab.setSelectedTab(incidentActionStatusTab);
                break;
        }
        addComponent(tab);
    }
}