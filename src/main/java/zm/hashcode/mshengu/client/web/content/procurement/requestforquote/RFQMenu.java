/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.views.RFQListTab;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.views.RFQTab;

/**
 *
 * @author Luckbliss
 */
public class RFQMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private RFQTab rFQTab;
    private RFQListTab listTab;

    public RFQMenu(MshenguMain app, String selectedTab) {
        main = app;
        rFQTab = new RFQTab(main);
        listTab = new RFQListTab(main);

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(rFQTab, "Request For Quote Form (RFQ)", null);
        tab.addTab(listTab, "List of RFQs", null);


        switch (selectedTab) {
            case "LANDING":
                tab.setSelectedTab(rFQTab);
                break;
            case "LIST_RFQ":
                tab.setSelectedTab(listTab);
                break;
        }

        addComponent(tab);
    }
}
