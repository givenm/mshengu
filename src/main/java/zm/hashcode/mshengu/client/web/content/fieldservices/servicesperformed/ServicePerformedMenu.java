/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.forms.CustomerSiteFiledServicesForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views.ServiceLogsNotServicedReportTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views.ServiceLogsPendingReportTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views.SiteSiteServiceLogTab;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views.SiteSiteUnitTab;

/**
 *
 * @author Ferox
 */
public class ServicePerformedMenu extends VerticalLayout implements
        Button.ClickListener,  Property.ValueChangeListener {

    private MshenguMain main;
    private TabSheet tab;
//    private TabSheet tab;
    private CustomerSiteFiledServicesForm selectCustomerSite;
    ServiceLogsPendingReportTab serviceLogsPendingReportTab;
    SiteSiteServiceLogTab siteServiceLogTab;
    ServiceLogsNotServicedReportTab serviceLogsNotServicedReportTab;
    SiteSiteUnitTab siteSiteUnitTab;
    private String selectedCustomerId;
    private String selectedSiteId;
    private Date startDate;
    private Date endDate;

    public ServicePerformedMenu(MshenguMain app, String selectedTab) {
        main = app;

        selectCustomerSite = new CustomerSiteFiledServicesForm();
        serviceLogsPendingReportTab = new ServiceLogsPendingReportTab(main);
        serviceLogsNotServicedReportTab  = new ServiceLogsNotServicedReportTab(main);
        siteSiteUnitTab = new SiteSiteUnitTab(main, "LANDING");
        siteServiceLogTab = new SiteSiteServiceLogTab(main);

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(serviceLogsPendingReportTab, "Site Service Pending");
        tab.addTab(serviceLogsNotServicedReportTab, "Site Services Missed");
        tab.addTab(siteServiceLogTab, "Site Service Logs");
        tab.addTab(siteSiteUnitTab, "Site Toilets List");
        if (selectedCustomerId != null) {
            if (selectedTab.equals("LANDING")) {
                tab.setSelectedTab(serviceLogsPendingReportTab);
            }else if (selectedTab.equals("NOT_SERVICED")) {
                tab.setSelectedTab(serviceLogsNotServicedReportTab);
            }  else if (selectedTab.equals("SERVICE_LOGS")) {
                tab.setSelectedTab(siteServiceLogTab);
            } else if (selectedTab.equals("SITE_UNITS")) {
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
        selectCustomerSite.btnLoadLogs.addClickListener((Button.ClickListener) this);
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
        /*else if (property == selectCustomerSite.startDate) {
            if (selectCustomerSite.startDate.getValue() != null) {
                setStartDate(selectCustomerSite.startDate.getValue());
                if (selectCustomerSite.endDate == null) {
                    setEndDate(new Date());
                }
                if (selectedCustomerId != null) {
                    loadLogTables();
                }
//                setSelectedSiteInTabs(getSelectedCustomerId());
//                selectCustomerSite.loadCustomerSites(getSelectedCustomerId());

            }
        } else if (property == selectCustomerSite.endDate) {
            if (selectCustomerSite.comboBoxSelectSite.getValue() != null) {
                setEndDate(selectCustomerSite.endDate.getValue());
                if ((selectedCustomerId != null) && (startDate != null)) {
                    loadLogTables();
                } else {

                    Notification.show("Please select Site and Start Date!", Notification.Type.TRAY_NOTIFICATION);
                }

            }
        }*/
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

    private void loadLogTables() {
//        serviceLogsPendingReportTab.loadServiceLogDetails(selectedSiteId, getStartDate(), getStartDate());
        siteServiceLogTab.loadServiceLogDetails(selectedSiteId, getStartDate(), getStartDate());
//siteServiceLogTab.   

    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
      @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == selectCustomerSite.btnLoadLogs) {
            loadServiceLogs();
        }
//        } else if (source == form.edit) {
//            setEditFormProperties();
//        } else if (source == form.cancel) {
//            getHome();
//        } else if (source == form.update) {
//            saveEditedForm(form.binder);
//        } else if (source == form.delete) {
//            deleteForm(form.binder);
//        }
    }
    
    private void loadServiceLogs(){
          if (getSelectedSiteId() != null) {//              
                setStartDate(selectCustomerSite.startDate.getValue());
                setEndDate(selectCustomerSite.endDate.getValue());
                
                if ((startDate == null) || (endDate == null)) {
                    Notification.show("Please select  Start date and End Date!", Notification.Type.TRAY_NOTIFICATION);
                    
                } else if (startDate.after(endDate)) {
                    Notification.show("End date cannot be posterior to start date!", Notification.Type.TRAY_NOTIFICATION);
                    
                }  else {
                    siteServiceLogTab.loadServiceLogDetails(selectedSiteId, getStartDate(), getEndDate());
                }

            }else{                
                    Notification.show("Please select a Site!", Notification.Type.TRAY_NOTIFICATION);
            }
    }

}
