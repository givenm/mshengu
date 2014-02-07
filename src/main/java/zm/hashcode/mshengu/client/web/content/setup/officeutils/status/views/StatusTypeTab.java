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
import zm.hashcode.mshengu.app.facade.ui.util.StatusTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.OfficeUtilsMenu;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.status.forms.StatusTypeForm;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.status.models.StatusTypeBean;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.status.tables.StatusTypeTable;
import zm.hashcode.mshengu.domain.ui.util.StatusType;

/**
 *
 * @author Ferox
 */
public class StatusTypeTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final StatusTypeForm form;
    private final StatusTypeTable table;

    public StatusTypeTab(MshenguMain app) {
        main = app;
        form = new StatusTypeForm();
        table = new StatusTypeTable(main);
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
            final StatusType truckCategory = StatusTypeFacade.getStatusTypeService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(getBean(truckCategory)));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            StatusTypeFacade.getStatusTypeService().persist(getEntity(binder));
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
            StatusTypeFacade.getStatusTypeService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        StatusTypeFacade.getStatusTypeService().delete(getEntity(binder));
        getHome();
    }

    private StatusType getEntity(FieldGroup binder) {
        //final  StatusType cust = new StatusType.Builder(binder.getItemDataSource().getItemProperty("name")).
        
        final StatusTypeBean statusTypeBean = ((BeanItem<StatusTypeBean>) binder.getItemDataSource()).getBean();


        final StatusType statusType = new StatusType.Builder(statusTypeBean.getName())
                .description(statusTypeBean.getDescription())
                .id(statusTypeBean.getId())
                .build();

        return statusType;


    }

    private void getHome() {
        main.content.setSecondComponent(new OfficeUtilsMenu(main, "STATUS_TYPE"));
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

    private StatusTypeBean getBean(StatusType statusType) {
        StatusTypeBean bean = new StatusTypeBean();
        bean.setName(statusType.getName());
        bean.setDescription(statusType.getDescription());
        bean.setId(statusType.getId());
        return bean;
    }


}
