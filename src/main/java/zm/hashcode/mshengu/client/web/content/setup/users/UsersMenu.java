/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.users;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.Menu;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.users.views.PersonTab;
import zm.hashcode.mshengu.client.web.content.setup.users.views.ResetTab;
import zm.hashcode.mshengu.client.web.content.setup.users.views.RoleTab;

/**
 *
 * @author boniface
 */
public class UsersMenu extends Menu {

    private final MshenguMain main;
    private final TabSheet tab;

    public UsersMenu(MshenguMain app, String selectedTab) {
//        super(app, selectedTab);
        main = app;
        
//
        final VerticalLayout userTab = new VerticalLayout();
        userTab.setMargin(true);
        userTab.addComponent(new PersonTab(main));


        final VerticalLayout roleTab = new VerticalLayout();
        roleTab.setMargin(true);
        roleTab.addComponent(new RoleTab(main));

        final VerticalLayout resetTab = new VerticalLayout();
        resetTab.setMargin(true);
        resetTab.addComponent(new ResetTab(main));



        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        
        tab.addTab(userTab, "Add System USERS", null);
        tab.addTab(roleTab, "Add System ROLES", null);
        tab.addTab(resetTab, "Reset CREDENTIALS", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(userTab);
        } else if (selectedTab.equals("ROLES")) {
            tab.setSelectedTab(roleTab);
        }else if (selectedTab.equals("CREDENTIALS")) {
            tab.setSelectedTab(resetTab);
        }
        addComponent(tab);
//
//        switch (selectedTab) {
//            case "LANDING":
//                tab.setSelectedTab(userTab);
//                break;
//            case "ROLES":
//                tab.setSelectedTab(roleTab);
//                break;
//            case "RESETS":
//                tab.setSelectedTab(roleTab);
//                break;
//        }
//        addComponent(tab);
//
    }
}
