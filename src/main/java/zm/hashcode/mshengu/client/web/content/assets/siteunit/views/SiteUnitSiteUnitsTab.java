/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.views;

import com.vaadin.data.Property;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.forms.CustomerSiteUnitForm;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.forms.SelectSiteUnitSiteUnitForm;
import zm.hashcode.mshengu.domain.products.SiteUnit;

/**
 *
 * @author Ferox
 */
public class SiteUnitSiteUnitsTab extends VerticalLayout implements
        Property.ValueChangeListener {

    private MshenguMain main;
    private TabSheet tab;
//    private TabSheet tab;
    private String selectedSiteUnitId;
    private SelectSiteUnitSiteUnitForm selectSiteUnitForm;
//    private final CustomerSiteUnitForm customerSiteUnitForm;
    SiteUnitUnitDetailsTab siteUnitDetailsTab;
    SiteUniUnitLifeCycleTab siteUnitLifeCycleTab;
    SiteUnitUnitServiceLogTab siteUnitServiceLogTab;

    public SiteUnitSiteUnitsTab(MshenguMain app, String selectedTab) {
        main = app;

        selectSiteUnitForm = new SelectSiteUnitSiteUnitForm();
        siteUnitDetailsTab = new SiteUnitUnitDetailsTab(main);
        siteUnitLifeCycleTab = new SiteUniUnitLifeCycleTab(main);
        siteUnitServiceLogTab = new SiteUnitUnitServiceLogTab(main);
//        customerSiteUnitForm = new CustomerSiteUnitForm();

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(siteUnitDetailsTab, "Toilets Details", null);
        tab.addTab(siteUnitLifeCycleTab, "Toilets Deployment History", null);
        tab.addTab(siteUnitServiceLogTab, "Toilets Service History", null);


        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(siteUnitDetailsTab);
        } else if (selectedTab.equals("LIFE_CYCLE")) {
            tab.setSelectedTab(siteUnitLifeCycleTab);
        } else if (selectedTab.equals("SERVICE_LOGS")) {
            tab.setSelectedTab(siteUnitServiceLogTab);
        }

        addComponent(selectSiteUnitForm);
        addComponent(tab);

        selectSiteUnitForm.comboBoxSelectUnit.addValueChangeListener((Property.ValueChangeListener) this);
//        customerSiteUnitForm.comboBoxSelectCustomer.addValueChangeListener((Property.ValueChangeListener) this);
//        customerSiteUnitForm.comboBoxSelectSite.addValueChangeListener((Property.ValueChangeListener) this);
//        customerSiteUnitForm.comboBoxSelectUnit.addValueChangeListener((Property.ValueChangeListener) this);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == selectSiteUnitForm.comboBoxSelectUnit) {
            if (!StringUtils.isEmpty(selectSiteUnitForm.comboBoxSelectUnit.getValue().toString())) {
                setSelectedSiteUnitId(selectSiteUnitForm.comboBoxSelectUnit.getValue().toString());
            }
        } 
//            else if (property == customerSiteUnitForm.comboBoxSelectCustomer) {
//            if (!StringUtils.isEmpty(customerSiteUnitForm.comboBoxSelectCustomer.getValue().toString())) {
//                loadCustomerSites(customerSiteUnitForm.comboBoxSelectCustomer.getValue().toString());
//            }
//        } else if (property == customerSiteUnitForm.comboBoxSelectSite) {
//            if (!StringUtils.isEmpty(customerSiteUnitForm.comboBoxSelectSite.getValue().toString())) {
//                loadSiteUnits(customerSiteUnitForm.comboBoxSelectSite.getValue().toString());
//            }
//        } else if (property == customerSiteUnitForm.comboBoxSelectUnit) {
//            if (!StringUtils.isEmpty(customerSiteUnitForm.comboBoxSelectUnit.getValue().toString())) {
//                setSelectedSiteUnitId(customerSiteUnitForm.comboBoxSelectUnit.getValue().toString());
//            }
//        }
    }

    /**
     * @return the selectedCustomerId
     */
    public String getSelectedCustomerId() {
        return getSelectedSiteUnitId();
    }

    /**
     * @param selectedCustomerId the selectedCustomerId to set
     */
    public void setSelectedCustomerId(String selectedCustomerId) {
        this.setSelectedSiteUnitId(selectedCustomerId);
    }

    /**
     * @return the selectedSiteUnitId
     */
    public String getSelectedSiteUnitId() {
        return selectedSiteUnitId;
    }

    /**
     * @param selectedSiteUnitId the selectedSiteUnitId to set
     */
    public void setSelectedSiteUnitId(String selectedSiteUnitId) {
        this.selectedSiteUnitId = selectedSiteUnitId;
        setSelectedSiteUnitInInnerTabs(getSelectedSiteUnitId());
    }

    public void setSelectedSiteUnitInInnerTabs(String selectedSiteUnitId) {
        siteUnitDetailsTab.loadSiteUnitDetails(selectedSiteUnitId);
        siteUnitLifeCycleTab.loadUnitLifeCycle(selectedSiteUnitId);
        siteUnitServiceLogTab.loadServiceLogs(selectedSiteUnitId);
    }

//    private void loadCustomerSites(String customerId) {
//        Customer customer = CustomerFacade.getCustomerService().findById(customerId);
//        if (!StringUtils.isEmpty(customer)) {
//            if (!StringUtils.isEmpty(customer.getSites())) {
//                customerSiteUnitForm.comboBoxSelectSite.removeValueChangeListener((Property.ValueChangeListener) this);
//                customerSiteUnitForm.loadCustomerSites(customer.getSites());
//                customerSiteUnitForm.comboBoxSelectSite.addValueChangeListener((Property.ValueChangeListener) this);
//            }
//        }
//    }
//
//    private void loadSiteUnits(String siteId) {
//        SiteServiceContractLifeCycle serviceContractLifeCycle = SiteFacade.getSiteService().getSitetCurrentContract(siteId);
//
//        if (!StringUtils.isEmpty(serviceContractLifeCycle)) {
//            if (!StringUtils.isEmpty(serviceContractLifeCycle.getSiteUnit())) {
//                customerSiteUnitForm.comboBoxSelectUnit.removeValueChangeListener((Property.ValueChangeListener) this);
//                customerSiteUnitForm.loadSiteUnits(serviceContractLifeCycle.getSiteUnit());
//                customerSiteUnitForm.comboBoxSelectUnit.addValueChangeListener((Property.ValueChangeListener) this);
//            }
//        }
//    }
//    
    public void loadToiletUnits(List<SiteUnit> siteUnitsList) {        
        selectSiteUnitForm.comboBoxSelectUnit.removeValueChangeListener((Property.ValueChangeListener) this);
        selectSiteUnitForm.loadSiteUnits(siteUnitsList);
        selectSiteUnitForm.comboBoxSelectUnit.addValueChangeListener((Property.ValueChangeListener) this);
    }
}