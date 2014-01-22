/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed;

import com.vaadin.data.Property;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.forms.CustomerSiteFiledServicesForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views.SiteSiteServiceExceptionReportTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views.SiteSiteServiceLogTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views.SiteSiteUnitTab;

/**
 *
 * @author Ferox
 */
public class ServicePerformedMenu extends VerticalLayout implements Property.ValueChangeListener {

    private MshenguMain main;
    private TabSheet tab;
//    private TabSheet tab;
    private CustomerSiteFiledServicesForm selectCustomerSite;
    SiteSiteServiceExceptionReportTab serviceExceptionReportTab;
    SiteSiteServiceLogTab siteServiceLogTab;
    SiteSiteUnitTab siteSiteUnitTab;
    private String selectedCustomerId;
    private String selectedSiteId;

    public ServicePerformedMenu(MshenguMain app, String selectedTab) {
        main = app;

        
        selectCustomerSite = new CustomerSiteFiledServicesForm();
        serviceExceptionReportTab =  new SiteSiteServiceExceptionReportTab(main);
        siteSiteUnitTab = new SiteSiteUnitTab(main, "LANDING");
        siteServiceLogTab = new SiteSiteServiceLogTab(main);
        
        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(siteServiceLogTab, "Site Service Logs");
        tab.addTab(siteSiteUnitTab, "Site Toilets List");
        if (selectedCustomerId != null) {
            if (selectedTab.equals("LANDING")) {
                tab.setSelectedTab(serviceExceptionReportTab);
            }else if (selectedTab.equals("SERVICE_LOGS")) {
                tab.setSelectedTab(siteServiceLogTab);
            }else if (selectedTab.equals("SITE_UNITS")) {
                tab.setSelectedTab(siteSiteUnitTab);
            }
        } else {

            Notification.show("Please select a customer!", Notification.Type.TRAY_NOTIFICATION);
        }
        addComponent(selectCustomerSite);
        addComponent(tab);

        selectCustomerSite.comboBoxSelectContractType.addValueChangeListener((Property.ValueChangeListener) this);
        selectCustomerSite.comboBoxSelectCustomer.addValueChangeListener((Property.ValueChangeListener) this);
        selectCustomerSite.comboBoxSelectSite.addValueChangeListener((Property.ValueChangeListener) this);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
          if (property == selectCustomerSite.comboBoxSelectContractType) {
            if (selectCustomerSite.comboBoxSelectContractType.getValue().toString() != null) {
                String id = selectCustomerSite.comboBoxSelectContractType.getValue().toString();
                String caption = selectCustomerSite.comboBoxSelectContractType.getItemCaption(id);
                selectCustomerSite.comboBoxSelectCustomer.removeValueChangeListener((Property.ValueChangeListener) this);
                selectCustomerSite.loadCustomersByContract(caption);
                selectCustomerSite.comboBoxSelectCustomer.addValueChangeListener((Property.ValueChangeListener) this);
            }
        } else if (property == selectCustomerSite.comboBoxSelectCustomer) {
            if (selectCustomerSite.comboBoxSelectCustomer.getValue().toString() != null) {
                setSelectedCustomerId(selectCustomerSite.comboBoxSelectCustomer.getValue().toString());
                selectCustomerSite.comboBoxSelectSite.removeValueChangeListener((Property.ValueChangeListener) this);
                selectCustomerSite.loadCustomerSites(getSelectedCustomerId());
                selectCustomerSite.comboBoxSelectSite.addValueChangeListener((Property.ValueChangeListener) this);
             
            }
        } else if (property == selectCustomerSite.comboBoxSelectSite) {
            if (selectCustomerSite.comboBoxSelectSite.getValue().toString() != null) {
                setSelectedSiteId(selectCustomerSite.comboBoxSelectSite.getValue().toString());
//                setSelectedSiteInTabs(getSelectedCustomerId());
//                selectCustomerSite.loadCustomerSites(getSelectedCustomerId());

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
//        this.customerSiteDetailsTab.setParentId(selectedCustomerId);
//        this.contactPersonDetailsTab.setParentId(selectedCustomerId);
    }

    public void addListener() {
        selectCustomerSite.comboBoxSelectSite.addValueChangeListener((Property.ValueChangeListener) this);
    }

    /**
     * @return the selectedSiteId
     */
    public String getSelectedSiteId() {
        return selectedSiteId;
    }

    /**
     * @param selectedSiteId the selectedSiteId to set
     */
    public void setSelectedSiteId(String selectedSiteId) {
        this.selectedSiteId = selectedSiteId;
        setSelectedSiteInTabs(selectedSiteId);
    }

    public void setSelectedSiteInTabs(String selectedSiteId) {
        this.siteSiteUnitTab.loadSiteUnits(selectedSiteId);
    }
}