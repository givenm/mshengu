/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup;

import zm.hashcode.mshengu.client.web.content.setup.sequence.views.SequenceTab;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;

/**
 *
 * @author Ferox
 */
public class SetUpMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;

    public SetUpMenu(MshenguMain app, String selectedTab) {
        main = app;

         VerticalLayout sequenceTab = new VerticalLayout();
        sequenceTab.setMargin(true);
        sequenceTab.addComponent(new SequenceTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(sequenceTab, "Sequences", null); 
    
        
        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(sequenceTab);
        } 
        addComponent(tab);
    }
}