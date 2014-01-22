/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.customer.customer;

import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.customer.customer.views.CustomerContractsTab;
import zm.hashcode.mshengu.client.web.content.customer.customer.views.CustomerDetailsTab;

/**
 *
 * @author Ferox
 */
public class CustomerMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
            private CustomerContractsTab customerContractsTab;

    public CustomerMenu(MshenguMain app, String selectedTab) {
        main = app;

        Label heading = new Label("CREATE A NEW CUSTOMER");
        heading.setSizeUndefined();
        heading.addStyleName("h4");
        VerticalLayout customerDetailsTab = new VerticalLayout();
        customerDetailsTab.setMargin(true);
        customerDetailsTab.addComponent(new CustomerDetailsTab(main));

         customerContractsTab= new CustomerContractsTab(main);
//        customerContractsTab.setMargin(true);
//        customerContractsTab.addComponent(new CustomerContractsTab(main));







        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(customerDetailsTab, "Customer Details", null);
        tab.addTab(customerContractsTab, "Hiring Terms", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(customerDetailsTab);
        } else if (selectedTab.equals("CONTRACTS")) {
            tab.setSelectedTab(customerContractsTab);
        }
        addComponent(heading);
        addComponent(tab);
        setMargin(true);
       
    }
    
    public CustomerMenu(MshenguMain app, String selectedTab, String selectedCustomerId){
        CustomerMenu customerMenu = new CustomerMenu(app, selectedTab);
        customerMenu.getCustomerContractsTab().setSelectedCustomer(selectedCustomerId);
    }

    /**
     * @return the customerContractsTab
     */
    public CustomerContractsTab getCustomerContractsTab() {
        return customerContractsTab;
    }


}