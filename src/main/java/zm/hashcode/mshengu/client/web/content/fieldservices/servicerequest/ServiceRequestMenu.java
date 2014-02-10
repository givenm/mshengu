/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.views.ActiveServiceRequestTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.views.InnactiveServiceRequestTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.views.ServiceRequestTypeTab;

/**
 *
 * @author Ferox
 */
public class ServiceRequestMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private ActiveServiceRequestTab activeServiceRequestTab;
    private InnactiveServiceRequestTab innactiveServiceRequestTab;
    private ServiceRequestTypeTab serviceRequestTypeTab;

    public ServiceRequestMenu(MshenguMain app, String selectedTab) {
        main = app;

        activeServiceRequestTab = new ActiveServiceRequestTab(main);        
        innactiveServiceRequestTab = new InnactiveServiceRequestTab(main);
        serviceRequestTypeTab = new ServiceRequestTypeTab(main);


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(activeServiceRequestTab, "Customer Service Requests", null);
        tab.addTab(innactiveServiceRequestTab, "Closed Service Requests", null);
        tab.addTab(serviceRequestTypeTab, "Service Requests Type", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(activeServiceRequestTab);
        } else if (selectedTab.equals("CLOSED")) {
            tab.setSelectedTab(innactiveServiceRequestTab);
        } else if (selectedTab.equals("REQUEST_TYPE")) {
            tab.setSelectedTab(serviceRequestTypeTab);
        }

        addComponent(tab);
    }
}