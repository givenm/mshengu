/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.contactus;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.contactus.views.ActiveContactUSTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.contactus.views.InnactiveContactUSTab;

/**
 *
 * @author Ferox
 */
public class ContactUSMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private ActiveContactUSTab activeContactUSTab;
    private InnactiveContactUSTab innactiveContactUSTab;

    public ContactUSMenu(MshenguMain app, String selectedTab) {
        main = app;

        activeContactUSTab = new ActiveContactUSTab(main);        
        innactiveContactUSTab = new InnactiveContactUSTab(main);


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(activeContactUSTab, "Open Contact Requests", null);
        tab.addTab(innactiveContactUSTab, "Closed Contact Requests", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(activeContactUSTab);
        } else if (selectedTab.equals("CLOSED")) {
            tab.setSelectedTab(innactiveContactUSTab);
        }

        addComponent(tab);
    }
}