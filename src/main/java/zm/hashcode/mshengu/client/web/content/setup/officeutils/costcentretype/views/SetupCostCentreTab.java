/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.views;

import com.vaadin.data.Property;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;

/**
 *
 * @author Luckbliss
 */
public class SetupCostCentreTab extends VerticalLayout implements
        Property.ValueChangeListener {

    private MshenguMain main;
    private TabSheet tab;
    private CostCentreNameTab costCentreNameTab;
    private CostCentreCategoryTab costCentreCategoryTab;
    private CostCentreItemTab costCentreItemTab;

    public SetupCostCentreTab(MshenguMain app, String selectedTab) {
        main = app;

        costCentreNameTab = new CostCentreNameTab(main);
        costCentreNameTab.setMargin(true);

        costCentreCategoryTab = new CostCentreCategoryTab(main);
        costCentreCategoryTab.setMargin(true);
        
        costCentreItemTab = new CostCentreItemTab(main);
        costCentreItemTab.setMargin(true);

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(costCentreNameTab, "Cost Centre Name", null);
        tab.addTab(costCentreCategoryTab, "Cost Centre Category", null);
        tab.addTab(costCentreItemTab, "Item Category", null);
        switch (selectedTab) {
            case "LANDING":
                tab.setSelectedTab(costCentreNameTab);
                break;
            case "CATEGORY":
                tab.setSelectedTab(costCentreCategoryTab);
                break;
            case "ITEM_CATEGORY":
                tab.setSelectedTab(costCentreItemTab);
                break;
        }
        addComponent(tab);

    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
//        if (property == selectServiceProviderForm.supplierComboBox) {
//        }
    }
}