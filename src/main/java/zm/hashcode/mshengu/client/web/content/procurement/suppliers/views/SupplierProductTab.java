/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.suppliers.views;

import com.vaadin.data.Property;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.form.SelectServiceProviderForm;

/**
 *
 * @author Luckbliss
 */
public class SupplierProductTab extends VerticalLayout implements
        Property.ValueChangeListener {

    private MshenguMain main;
    private TabSheet tab;
    private SelectServiceProviderForm selectServiceProviderForm;
    ServiceProviderProductDatabaseTab suppilerProductDatabaseTab;
    ServiceProviderProductTab serviceProviderProductTab;

    public SupplierProductTab(MshenguMain app, String selectedTab) {
        main = app;

        selectServiceProviderForm = new SelectServiceProviderForm();

        suppilerProductDatabaseTab = new ServiceProviderProductDatabaseTab(main);
        suppilerProductDatabaseTab.setMargin(true);

        serviceProviderProductTab = new ServiceProviderProductTab(main);
        serviceProviderProductTab.setMargin(true);

        VerticalLayout serviceProviderProductCategoryTab = new VerticalLayout();
        serviceProviderProductCategoryTab.setMargin(true);
        serviceProviderProductCategoryTab.addComponent(new ServiceProviderProductCategoryTab(main));



        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(suppilerProductDatabaseTab, "Supplier Product Database", null);
        tab.addTab(serviceProviderProductTab, "Add Product", null);
        tab.addTab(serviceProviderProductCategoryTab, "Add Product Category", null);
        switch (selectedTab) {
            case "LANDING":
                tab.setSelectedTab(suppilerProductDatabaseTab);
                break;
            case "PRODUCT":
                tab.setSelectedTab(serviceProviderProductTab);
                break;
            case "PRODUCT_CATEGORY":
                tab.setSelectedTab(serviceProviderProductCategoryTab);
                break;
        }

        addComponent(selectServiceProviderForm);
        addComponent(tab);

        selectServiceProviderForm.supplierComboBox.addValueChangeListener((Property.ValueChangeListener) this);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == selectServiceProviderForm.supplierComboBox) {
            serviceProviderProductTab.setSupplierId(selectServiceProviderForm.supplierComboBox.getValue().toString());
            suppilerProductDatabaseTab.setSupplierId(selectServiceProviderForm.supplierComboBox.getValue().toString());
        }
    }
}

