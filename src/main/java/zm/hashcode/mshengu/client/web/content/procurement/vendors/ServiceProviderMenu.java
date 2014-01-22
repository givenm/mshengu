/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.vendors;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.views.SupplierProductTab;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.views.ServiceCategoryTab;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.views.ServiceProviderCategoryTab;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.views.ServiceProviderDatabaseTab;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.views.ServiceProviderDetailsTab;

/**
 *
 * @author Ferox
 */
public class ServiceProviderMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;

    public ServiceProviderMenu(MshenguMain app, String selectedTab) {

        main = app;

        VerticalLayout serviceProviderDatabaseTab = new VerticalLayout();
        serviceProviderDatabaseTab.setMargin(true);
        serviceProviderDatabaseTab.addComponent(new ServiceProviderDatabaseTab(main));

        VerticalLayout serviceProviderDetailsTab = new VerticalLayout();
        serviceProviderDetailsTab.setMargin(true);
        serviceProviderDetailsTab.addComponent(new ServiceProviderDetailsTab(main));


        VerticalLayout serviceCategoryTab = new VerticalLayout();
        serviceCategoryTab.setMargin(true);
        serviceCategoryTab.addComponent(new ServiceCategoryTab(main));
        
                VerticalLayout serviceProviderCategoryTab = new VerticalLayout();
        serviceProviderCategoryTab.setMargin(true);
        serviceProviderCategoryTab.addComponent(new ServiceProviderCategoryTab(main));

        VerticalLayout supplierProductTab = new VerticalLayout();
        supplierProductTab.setMargin(true);
        supplierProductTab.addComponent(new SupplierProductTab(main, selectedTab));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(serviceProviderDatabaseTab, "Vendors Database", null);
        tab.addTab(serviceProviderDetailsTab, "Add Vendors", null);
        tab.addTab(serviceProviderCategoryTab, "Add Service Provider Category", null);
        tab.addTab(serviceCategoryTab, "Add Service Category", null);
        tab.addTab(supplierProductTab, "Set Supplier Product", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(serviceProviderDatabaseTab);
        } else if (selectedTab.equals("VENDOR")) {
            tab.setSelectedTab(serviceProviderDetailsTab);
        } else if (selectedTab.equals("SERVICE_CATEGORY")) {
            tab.setSelectedTab(serviceCategoryTab);
        } else if (selectedTab.equals("PROVIDER_CATEGORY")) {
            tab.setSelectedTab(supplierProductTab);
        }

        addComponent(tab);
    }
}
