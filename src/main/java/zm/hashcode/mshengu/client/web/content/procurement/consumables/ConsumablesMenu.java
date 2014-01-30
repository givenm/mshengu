/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.consumables;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.consumables.views.ChemicalsTab;
import zm.hashcode.mshengu.client.web.content.procurement.consumables.views.UniformsTab;

/**
 *
 * @author Ferox
 */
public class ConsumablesMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private ChemicalsTab chemicalsTab;
    private UniformsTab uniformsTab;

    public ConsumablesMenu(MshenguMain app, String selectedTab) {
        main = app;

        chemicalsTab = new ChemicalsTab(main);
        uniformsTab = new UniformsTab(main);


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(chemicalsTab, "Chemicals", null);
        tab.addTab(uniformsTab, "Uniforms", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(chemicalsTab);
        } else if (selectedTab.equals("UNIFORMS")) {
            tab.setSelectedTab(uniformsTab);
        }

        addComponent(tab);
    }
}