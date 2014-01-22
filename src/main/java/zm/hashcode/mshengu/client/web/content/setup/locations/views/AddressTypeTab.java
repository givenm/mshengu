/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations.views;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.ui.location.AddressTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.locations.LocationMenu;
import zm.hashcode.mshengu.client.web.content.setup.locations.forms.AddressTypeForm;
import zm.hashcode.mshengu.client.web.content.setup.locations.model.AddressTypeBean;
import zm.hashcode.mshengu.client.web.content.setup.locations.tables.AddressTypeTable;
import zm.hashcode.mshengu.domain.ui.location.AddressType;

/**
 *
 * @author boniface
 */
public class AddressTypeTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final AddressTypeForm form;
    private final AddressTypeTable table;

    public AddressTypeTab(MshenguMain app) {
        main = app;
        form = new AddressTypeForm();
        table = new AddressTypeTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(ClickEvent event) {
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
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final AddressType locationType = AddressTypeFacade.getAddressTypeService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(locationType));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            AddressTypeFacade.getAddressTypeService().persist(getEntity(binder));
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
            AddressTypeFacade.getAddressTypeService().merge(getUpdatedEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        AddressTypeFacade.getAddressTypeService().delete(getEntity(binder));
        getHome();
    }

    private AddressType getEntity(FieldGroup binder) {
        final AddressTypeBean bean = ((BeanItem<AddressTypeBean>) binder.getItemDataSource()).getBean();
        final AddressType addressType = new AddressType.Builder(bean.getAddressTypeName())
                .build();

        return addressType;
    }

    private AddressType getUpdatedEntity(FieldGroup binder) {
        final AddressTypeBean bean = ((BeanItem<AddressTypeBean>) binder.getItemDataSource()).getBean();
        final AddressType addressType = new AddressType.Builder(bean.getAddressTypeName())
                .id(bean.getId())
                .build();

        return addressType;
    }

    private void getHome() {
        main.content.setSecondComponent(new LocationMenu(main, "ADDRESSTYPE"));
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
        //Buttons Behaviou
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
}
