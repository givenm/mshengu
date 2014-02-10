/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.site.forms.SelectSiteSiteUnitForm;

/**
 *
 * @author Ferox
 */
public class SiteSiteUnitTab extends VerticalLayout implements Property.ValueChangeListener {

    private MshenguMain main;
    private TabSheet tab;
//    private TabSheet tab;
    private String selectedSiteUnitId;
    private SelectSiteSiteUnitForm selectSiteUnitForm;
    SiteUnitDetailsTab siteUnitDetailsTab;
    SiteUnitLifeCycleTab siteUnitLifeCycleTab;
    SiteUnitServiceLogTab siteUnitServiceLogTab;

    public SiteSiteUnitTab(MshenguMain app, String selectedTab) {
        main = app;

        selectSiteUnitForm = new SelectSiteSiteUnitForm();
        siteUnitDetailsTab = new SiteUnitDetailsTab(main);
        siteUnitLifeCycleTab = new SiteUnitLifeCycleTab(main);
        siteUnitServiceLogTab = new SiteUnitServiceLogTab(main);

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(siteUnitDetailsTab, "Toilet Details", null);
        tab.addTab(siteUnitLifeCycleTab, "Toilet Deployment History", null);
        tab.addTab(siteUnitServiceLogTab, "Toilet Service History", null);

        if (!StringUtils.isEmpty(getSelectedSiteUnitId())) {
            if (selectedTab.equals("LANDING")) {
                tab.setSelectedTab(siteUnitDetailsTab);
            } else if (selectedTab.equals("LIFE_CYCLE")) {
                tab.setSelectedTab(siteUnitLifeCycleTab);
            } else if (selectedTab.equals("SERVICE_LOGS")) {
                tab.setSelectedTab(siteUnitServiceLogTab);
            }
        } else {

            Notification.show("Please select a Unit!", Notification.Type.TRAY_NOTIFICATION);
        }
        addComponent(selectSiteUnitForm);
        addComponent(tab);

        selectSiteUnitForm.comboBoxSelectUnit.addValueChangeListener((Property.ValueChangeListener) this);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == selectSiteUnitForm.comboBoxSelectUnit) {
            if (selectSiteUnitForm.comboBoxSelectUnit.getValue().toString() != null) {
                setSelectedSiteUnitId(selectSiteUnitForm.comboBoxSelectUnit.getValue().toString());
            }
        }
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

    public void loadSiteUnits(String siteId) {
        selectSiteUnitForm.comboBoxSelectUnit.removeValueChangeListener((Property.ValueChangeListener) this);
        selectSiteUnitForm.loadSiteUnits(siteId);
        selectSiteUnitForm.comboBoxSelectUnit.addValueChangeListener((Property.ValueChangeListener) this);
        tab.setSelectedTab(siteUnitDetailsTab);
    }
}