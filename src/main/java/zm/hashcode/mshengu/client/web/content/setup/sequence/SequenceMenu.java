/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.sequence;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.sequence.views.SequenceTab;
import zm.hashcode.mshengu.client.web.content.setup.sequence.views.SequenceTypeTab;

/**
 *
 * @author Ferox
 */
public class SequenceMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;

    public SequenceMenu(MshenguMain app, String selectedTab) {
        main = app;

        VerticalLayout sequenceTab = new VerticalLayout();
        sequenceTab.setMargin(true);
        sequenceTab.addComponent(new SequenceTab(main));

        VerticalLayout sequenceTypeTab = new VerticalLayout();
        sequenceTypeTab.setMargin(true);
        sequenceTypeTab.addComponent(new SequenceTypeTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(sequenceTab, "Sequences", null);
        tab.addTab(sequenceTab, "Sequences Type", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(sequenceTab);
        } else if (selectedTab.equals("SEQUENCE_TYPE")) {
            tab.setSelectedTab(sequenceTypeTab);
        }
        
        addComponent(tab);
    }
}