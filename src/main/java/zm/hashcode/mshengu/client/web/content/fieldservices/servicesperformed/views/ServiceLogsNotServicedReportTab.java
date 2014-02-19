
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views;

import com.vaadin.data.Property;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import zm.hashcode.mshengu.app.facade.products.SiteFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.ServicePerformedMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.tables.NotServicedServiceLogsTable;
import zm.hashcode.mshengu.domain.products.Site;

/**
 *
 * @author Ferox
 */
public class ServiceLogsNotServicedReportTab extends VerticalLayout implements
        Property.ValueChangeListener {

    private final MshenguMain main;
//    private final SiteDetailsForm form;
    private final NotServicedServiceLogsTable table;
    private String parentId = null;
    private final DateTimeFormatWeeklyHelper dtfwh;
    Date date;
    Date startDate;
    Date endDate;

    public ServiceLogsNotServicedReportTab(MshenguMain app) {
        main = app;
        this.dtfwh = new DateTimeFormatWeeklyHelper();
        date = new Date();
        dtfwh.setDate(date);
        startDate = dtfwh.getMondayDateFull();
        endDate = dtfwh.getSundayDateFull();

        table = new NotServicedServiceLogsTable(main, startDate, endDate);

        setCaption("Missed Services - Weekly");
        setSizeFull();
//        addComponent(form);
        Label topleft = new Label("");
        topleft.setSizeUndefined();
        topleft.addStyleName("h4");
        Label bottomRight = new Label("");
        bottomRight.setSizeUndefined();
        bottomRight.addStyleName("h4");

        Label heading = new Label("Missed Services - Weekly");
        heading.setSizeUndefined();
        heading.addStyleName("h4");

           Label lblFrom = new Label("From : " + dtfwh.getMondayDateYYMMDD());
        lblFrom.setSizeUndefined();
        lblFrom.addStyleName("h4");
        
        Label lblTo = new Label(" To :" + dtfwh.getSundayDateYYMMDD());
        lblTo.setSizeUndefined();
        lblTo.addStyleName("h4");
        GridLayout grid = new GridLayout(6, 3);

        grid.setSizeFull();

        
        grid.addComponent(topleft, 0, 0);
        grid.addComponent(heading, 2, 0, 3, 0);        
        grid.addComponent(lblFrom, 1, 1, 2, 1);
        grid.addComponent(lblTo, 3, 1, 4, 1);
        grid.addComponent(bottomRight, 5, 2);
        addComponent(grid);
        addComponent(table);
        addListeners();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Site site = SiteFacade.getSiteService().findById(table.getValue().toString());
//            form.binder.setItemDataSource(new BeanItem<>(getBean(site)));
//            setReadFormProperties();
//        } else if (property == selectedCustomer.comboBoxSelectCustomer) {
//            String custId = getCustomerId();
//            if (selectedCustomer.comboBoxSelectCustomer.getValue().toString() != null) {
//
//                Notification.show("Customer  Selected!", Notification.Type.TRAY_NOTIFICATION);
//                table.removeValueChangeListener((Property.ValueChangeListener) this);
//                Customer customer  = CustomerFacade.getCustomerService().findById(selectedCustomer.comboBoxSelectCustomer.getValue().toString());
//                table.loadCustomerSites(customer.getSites());
//                table.addValueChangeListener((Property.ValueChangeListener) this);
//            }
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new ServicePerformedMenu(main, "NOT_SERVICED"));
    }

    private void addListeners() {
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
//        selectedCustomer.comboBoxSelectCustomer.addValueChangeListener((Property.ValueChangeListener) this);
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void loadServiceLogDetails(String siteId, Date startDate, Date endDate) {
        table.loadServiceLogDetails(siteId, startDate, endDate);
//        table
    }
}
