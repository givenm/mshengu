/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.status.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.ui.util.StatusFacade; 
import zm.hashcode.mshengu.app.facade.ui.util.StatusTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.OfficeUtilsMenu;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.status.forms.StatusForm;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.status.models.StatusBean;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.status.tables.StatusTable;
import zm.hashcode.mshengu.domain.ui.util.Status;
import zm.hashcode.mshengu.domain.ui.util.StatusType;



/**
 *
 * @author Ferox
 */
public class StatusTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final StatusForm form;
    private final StatusTable table;

    public StatusTab(MshenguMain app) {
        main = app;
        
        form = new StatusForm();
        table = new StatusTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
            saveEditedForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        }
    }

    
    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final Status truckCategory = StatusFacade.getStatusService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(truckCategory)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            StatusFacade.getStatusService().persist(getEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            StatusFacade.getStatusService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }
    private void deleteForm(FieldGroup binder) {
        StatusFacade.getStatusService().delete(getEntity(binder));
        getHome();
    }

    private Status getEntity(FieldGroup binder) {
        //final  Status cust = new Status.Builder(binder.getItemDataSource().getItemProperty("name")).
        final StatusBean statusBean = ((BeanItem<StatusBean>) binder.getItemDataSource()).getBean();
        final StatusType statusType = StatusTypeFacade.getStatusTypeService().findById(statusBean.getStatusType());
        final Status status = new Status.Builder(statusBean.getName())
                .statusType(statusType)
                .id(statusBean.getId())
                .build();
        return status;
    }

    private void getHome() {
        main.content.setSecondComponent(new OfficeUtilsMenu(main, "STATUS"));
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
    }

    private StatusBean getBean(Status status) {
        StatusBean bean = new StatusBean();
        bean.setName(status.getName());
        bean.setStatusType(status.getStatusTypeId());
        bean.setId(status.getId());
        return bean;
    }
}
