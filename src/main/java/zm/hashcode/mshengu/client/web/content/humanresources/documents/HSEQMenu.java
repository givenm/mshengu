/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.documents;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.Menu;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.humanresources.documents.views.DocumentListTab;
import zm.hashcode.mshengu.client.web.content.humanresources.documents.views.HSEQTab;

/**
 *
 * @author Luckbliss
 */
public class HSEQMenu extends Menu {

    private final MshenguMain main;
    private final TabSheet tab;

    public HSEQMenu(MshenguMain app, String selectedTab) {
        main = app;

        final VerticalLayout documentdetails = new VerticalLayout();
        documentdetails.setMargin(true);
        documentdetails.addComponent(new HSEQTab(main));

        final VerticalLayout documentList = new VerticalLayout();
        documentList.setMargin(true);
        documentList.addComponent(new DocumentListTab(main));
        
        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(documentdetails, "Document Details", null);
        tab.addTab(documentList, "Document List", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(documentdetails);
        } else if (selectedTab.equals("DOCUMENT_LIST")) {
            tab.setSelectedTab(documentList);
        }
        addComponent(tab);
    }
}
