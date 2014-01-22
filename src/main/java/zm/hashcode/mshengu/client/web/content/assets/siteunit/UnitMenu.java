/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit;

import com.vaadin.data.Property;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import zm.hashcode.mshengu.app.facade.products.SiteFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.forms.CustomerSiteAssetForm;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.views.SiteUnitBulkTagUnitTab;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.views.SiteUnitManageSiteUnitTab;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.views.SiteUnitQRCodeGenerationTab;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.views.SiteUnitSiteUnitsTab;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.views.SiteUnitUnitTypeTab;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.views.UnitsLocationMapTab;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.views.UnitsQRCodesTab;
import zm.hashcode.mshengu.domain.products.SiteUnit;

/**
 *
 * @author Ferox
 */
public class UnitMenu extends VerticalLayout implements Property.ValueChangeListener {

    private MshenguMain main;
    private TabSheet tab;
    private CustomerSiteAssetForm selectCustomerSite;
    private String selectedCustomerId;
    private String selectedSiteId;
    private String selectedSiteName;
    private SiteUnitManageSiteUnitTab siteUnitManageSiteUnitTab;
    private SiteUnitSiteUnitsTab siteUnitSiteUnitsTab;
    private UnitsQRCodesTab unitsMapsTab;
    private SiteUnitQRCodeGenerationTab generateQRCodeTab;
    private SiteUnitUnitTypeTab unitTypeTab;
    private SiteUnitBulkTagUnitTab siteUnitBulkTagUnitTab;
    private UnitsLocationMapTab unitsLocationMapTab;

    public UnitMenu(MshenguMain app, String selectedTab) {
        main = app;
        selectCustomerSite = new CustomerSiteAssetForm();
        siteUnitManageSiteUnitTab = new SiteUnitManageSiteUnitTab(main);
        siteUnitSiteUnitsTab = new SiteUnitSiteUnitsTab(main, "LANDING");
        unitsMapsTab = new UnitsQRCodesTab(main);
        generateQRCodeTab = new SiteUnitQRCodeGenerationTab(main);
        unitTypeTab = new SiteUnitUnitTypeTab(main);
        siteUnitBulkTagUnitTab = new SiteUnitBulkTagUnitTab(main);
        unitsLocationMapTab = new UnitsLocationMapTab(main);

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");

        tab.addTab(siteUnitManageSiteUnitTab, "Tag Toilets", null);
        tab.addTab(siteUnitSiteUnitsTab, "Toilet History", null);
        tab.addTab(unitTypeTab, "Toilet Type", null);
        tab.addTab(unitsMapsTab, "Toilet QR Codes", null);
//        tab.addTab(generateQRCodeTab, "Toilet QR Codes (TEST)", null); //unitsQRCodeTestTab
        tab.addTab(siteUnitBulkTagUnitTab, "Bulk Tagging", null); //siteUnitBulkTagUnitTab //siteUnitBulkTagUnitTab
        tab.addTab(unitsLocationMapTab, "Units Location Map", null);

        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(siteUnitManageSiteUnitTab);
        } else if (selectedTab.equals("HISTORY")) {
            tab.setSelectedTab(siteUnitSiteUnitsTab);
        } else if (selectedTab.equals("TYPE")) {
            tab.setSelectedTab(unitTypeTab);
        } else if (selectedTab.equals("MAP")) {
            tab.setSelectedTab(unitsMapsTab);
        } else if (selectedTab.equals("GENERATE")) {
            tab.setSelectedTab(generateQRCodeTab);
        } else if (selectedTab.equals("BULK")) {
            tab.setSelectedTab(siteUnitBulkTagUnitTab);
        } else if (selectedTab.equals("LOCATIONMAP")) {
            tab.setSelectedTab(siteUnitBulkTagUnitTab);
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
                String id = selectCustomerSite.comboBoxSelectSite.getValue().toString();
                String caption = selectCustomerSite.comboBoxSelectSite.getItemCaption(id);
                setSelectedSiteId(id);
                setSelectedSiteName(caption);
//                
                Notification.show("Site " + caption + " Was Selected!", Notification.Type.TRAY_NOTIFICATION);

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
//        setSelectedCustomerInTabs(getSelectedCustomerId());
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
    }

    public void setSelectedSiteInTabs(String selectedSiteName) {
        List<SiteUnit> siteUnitsList = SiteFacade.getSiteService().findAllSiteUnit(selectedSiteName);
        siteUnitManageSiteUnitTab.loadToiletUnits(siteUnitsList);
        siteUnitSiteUnitsTab.loadToiletUnits(siteUnitsList);
        unitsMapsTab.loadToiletUnits(siteUnitsList);
        unitsLocationMapTab.showUnitsLocations(siteUnitsList);
//    private SiteUnitUnitTypeTab unitTypeTab;
//    private SiteUnitBulkTagUnitTab siteUnitBulkTagUnitTab;
//    private UnitsLocationMapTab unitsLocationMapTab;
//        this.siteSiteUnitTab.loadSiteUnits(selectedSiteId);
    }

    /**
     * @return the selectedSiteName
     */
    public String getSelectedSiteName() {
        return selectedSiteName;
    }

    /**
     * @param selectedSiteName the selectedSiteName to set
     */
    public void setSelectedSiteName(String selectedSiteName) {
        this.selectedSiteName = selectedSiteName;
        setSelectedSiteInTabs(selectedSiteName);
    }
}
