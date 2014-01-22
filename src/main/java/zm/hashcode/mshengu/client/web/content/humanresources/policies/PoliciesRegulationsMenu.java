/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.policies;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.Menu;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.policies.views.PoliciesTab;

/**
 *
 * @author Luckbliss
 */
public class PoliciesRegulationsMenu extends Menu {

    private final MshenguMain main;
    private final TabSheet tab;

    public PoliciesRegulationsMenu(MshenguMain app, String selectedTab) {
        main = app;

        final VerticalLayout policiesDetails = new VerticalLayout();
        policiesDetails.setMargin(true);
        policiesDetails.addComponent(new PoliciesTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(policiesDetails, "Policies Details", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(policiesDetails);
        } 
        addComponent(tab);
    }
}
