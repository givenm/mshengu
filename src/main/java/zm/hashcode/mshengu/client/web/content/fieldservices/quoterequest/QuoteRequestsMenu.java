/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.quoterequest;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.quoterequest.views.QuoteRequestsTab;

/**
 *
 * @author Luckbliss
 */
public class QuoteRequestsMenu extends VerticalLayout{
    private MshenguMain main;
    private TabSheet tab;
    private QuoteRequestsTab quoteRequestsTab;

    public QuoteRequestsMenu(MshenguMain app, String selectedTab) {
        main = app;

        quoteRequestsTab = new QuoteRequestsTab(main);        


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(quoteRequestsTab, "Quote Requested", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(quoteRequestsTab);
        } 

        addComponent(tab);
    }
}
