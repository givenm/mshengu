/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import zm.hashcode.mshengu.app.facade.products.SiteFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.tables.VehicleServiceLogTable;
import zm.hashcode.mshengu.client.web.content.fieldservices.site.ServiceSchedulingMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.site.forms.SiteDetailsForm;
import zm.hashcode.mshengu.domain.products.Site;

/**
 *
 * @author Ferox
 */
public class VehicleServiceLogsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final SiteDetailsForm form;
    private final VehicleServiceLogTable table;
    private String parentId = null;
    private final DateTimeFormatWeeklyHelper dtfwh;
    private final Label lblFrom;
    private final Label lblTo;
    Date date;

    public VehicleServiceLogsTab(MshenguMain app) {
        main = app;
        this.dtfwh = new DateTimeFormatWeeklyHelper();
        date = new Date();
        dtfwh.setDate(date);
        form = new SiteDetailsForm();

        table = new VehicleServiceLogTable(main);
        Label topleft = new Label("");
        topleft.setSizeUndefined();
        topleft.addStyleName("h4");
        Label bottomRight = new Label("");
        bottomRight.setSizeUndefined();
        bottomRight.addStyleName("h4");
        setCaption("Service Performed");
//        addComponent(form);
        Label heading = new Label("Site's Services Performed");
        heading.setSizeUndefined();
        heading.addStyleName("h4");

        lblFrom = new Label("From : " + dtfwh.getMondayDateYYMMDD());
        lblFrom.setSizeUndefined();
        lblFrom.addStyleName("h4");

        lblTo = new Label(" To :" + dtfwh.getSundayDateYYMMDD());
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
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
//            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
//            saveEditedForm(form.binder);
        } else if (source == form.delete) {
//            deleteForm(form.binder);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Site site = SiteFacade.getSiteService().findById(table.getValue().toString());
//            form.binder.setItemDataSource(new BeanItem<>(getBean(site)));
            setReadFormProperties();
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
        main.content.setSecondComponent(new ServiceSchedulingMenu(main, "SERVICE_LOGS"));
    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.save.setVisible(false);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.delete.setVisible(false);
        form.update.setVisible(true);
    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviour
        form.save.setVisible(false);
        form.edit.setVisible(true);
        form.cancel.setVisible(true);
        form.delete.setVisible(true);
        form.update.setVisible(false);

    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        form.cancel.addClickListener((Button.ClickListener) this);
        form.update.addClickListener((Button.ClickListener) this);
        form.delete.addClickListener((Button.ClickListener) this);
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
        setServiceLogRange(startDate, endDate);
        table.loadServiceLogDetails(siteId, startDate, endDate);
    }

    private void setServiceLogRange(Date startDate, Date endDate) {
        lblFrom.setValue("From : " + dtfwh.getYearMonthDay(startDate));
        lblTo.setValue(" To :" + dtfwh.getYearMonthDay(endDate));
    }

}
