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
import zm.hashcode.mshengu.app.facade.ui.location.ContactFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.locations.LocationMenu;
import zm.hashcode.mshengu.client.web.content.setup.locations.forms.ContactListForm;
import zm.hashcode.mshengu.client.web.content.setup.locations.model.ContactBean;
import zm.hashcode.mshengu.client.web.content.setup.locations.tables.ContactListTable;
import zm.hashcode.mshengu.domain.ui.location.Contact;

/**
 *
 * @author boniface
 */
public class ContactListTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final ContactListForm form;
    private final ContactListTable table;

    public ContactListTab(MshenguMain app) {
        main = app;
        form = new ContactListForm();
        table = new ContactListTable(main);
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
            final Contact contact = ContactFacade.getContactService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<>(contact));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            ContactFacade.getContactService().persist(getEntity(binder));
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
            ContactFacade.getContactService().merge(getUpdatedEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        ContactFacade.getContactService().delete(getUpdatedEntity(binder));
        getHome();
    }

    private Contact getEntity(FieldGroup binder) {
        final ContactBean bean = ((BeanItem<ContactBean>) binder.getItemDataSource()).getBean();
        final Contact contact = new Contact.Builder(bean.getName())
                .build();

        return contact;
    }

    private Contact getUpdatedEntity(FieldGroup binder) {
        final ContactBean bean = ((BeanItem<ContactBean>) binder.getItemDataSource()).getBean();
        final Contact contact = new Contact.Builder(bean.getName())
                .id(bean.getId())
                .build();

        return contact;
    }

    private void getHome() {
        main.content.setSecondComponent(new LocationMenu(main, "CONTACTLIST"));
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
