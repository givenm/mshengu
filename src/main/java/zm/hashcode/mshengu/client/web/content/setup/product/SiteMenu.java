/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.product;

import com.vaadin.data.Property;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.site.forms.CustomerSiteForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.site.views.CustomerSiteDetailsTab;

/**
 *
 * @author Ferox
 */
public class SiteMenu extends VerticalLayout implements Property.ValueChangeListener {

    private MshenguMain main;
    private TabSheet tab;
    private Accordion accordion;
    private CustomerSiteForm selectCustomerSite;
    CustomerSiteDetailsTab customerSiteDetailsTab;
    private String selectedCustomerId;

    public SiteMenu(MshenguMain app, String selectedTab) {
        main = app;

        selectCustomerSite = new CustomerSiteForm();

//        CustomerSiteDetailsAccordion customerSiteDetailsAccordion = new CustomerSiteDetailsAccordion(main);
//        SiteLogsAccordion siteLogsAccordion = new SiteLogsAccordion(main);
//        SiteUnitsAccordion siteUnitsAccordion = new SiteUnitsAccordion(main);

        customerSiteDetailsTab = new CustomerSiteDetailsTab(main);
//        customerSiteDetailsTab.setMargin(true);
//        customerSiteDetailsTab.addComponent(new CustomerSiteDetailsTab(main));







//        tab = new TabSheet();
//        tab.setHeight("100%");
//        tab.setWidth("100%");
//        tab.addTab(siteUnitTab, "Site Details", null);

        accordion = new Accordion();
        accordion.setHeight("100%");
        accordion.setWidth("100%");
        accordion.addTab(customerSiteDetailsTab, "Site Details", null);
        if (selectedCustomerId != null) {
            if (selectedTab.equals("LANDING")) {
                accordion.setSelectedTab(customerSiteDetailsTab);
            }
//            } else if (selectedTab.equals("U_LIFE_CYCLE")) {
//                accordion.setSelectedTab(siteLogsAccordion);
//            } else if (selectedTab.equals("U_SERVICE_LOGS")) {
//                accordion.setSelectedTab(siteUnitsAccordion);
//            }
        } else {

            Notification.show("Please select a customer!", Notification.Type.TRAY_NOTIFICATION);
        }
        addComponent(selectCustomerSite);
//        addComponent(customerSiteDetailsAccordion);
//        addComponent(siteLogsAccordion);
//        addComponent(siteUnitsAccordion);
        addComponent(accordion);

        selectCustomerSite.comboBoxSelectCustomer.addValueChangeListener((Property.ValueChangeListener) this);
        selectCustomerSite.comboBoxSelectSite.addValueChangeListener((Property.ValueChangeListener) this);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == selectCustomerSite.comboBoxSelectCustomer) {
            if (selectCustomerSite.comboBoxSelectCustomer.getValue().toString() != null) {
                setSelectedCustomerId(selectCustomerSite.comboBoxSelectCustomer.getValue().toString());
                selectCustomerSite.comboBoxSelectSite.removeValueChangeListener((Property.ValueChangeListener) this);
                selectCustomerSite.loadCustomerSites(getSelectedCustomerId());
            }
        }
    }

    /**
     * @return the selectedCustomerId
     */
    public String getSelectedCustomerId() {
        return selectedCustomerId;
    }

    /**
     * @param selectedCustomerId the selectedCustomerId to set
     */
    public void setSelectedCustomerId(String selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
        setSelectedCustomerInTabs(getSelectedCustomerId());
    }

    public void setSelectedCustomerInTabs(String selectedCustomerId) {
        this.customerSiteDetailsTab.setParentId(selectedCustomerId);
    }
}