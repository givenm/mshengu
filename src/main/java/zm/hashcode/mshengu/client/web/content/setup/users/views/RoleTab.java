/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.users.views;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.ui.util.RoleFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.users.UsersMenu;
import zm.hashcode.mshengu.client.web.content.setup.users.forms.RoleForm;
import zm.hashcode.mshengu.client.web.content.setup.users.models.RoleBean;
import zm.hashcode.mshengu.client.web.content.setup.users.tables.RoleTable;
import zm.hashcode.mshengu.domain.ui.util.Role;


/**
 *
 * @author Ferox
 */
public final class RoleTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final RoleForm form;
    private final RoleTable table;

    public RoleTab(MshenguMain app) {
        main = app;
        form = new RoleForm();
        table = new RoleTable(main);
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
            final Role role = RoleFacade.getRoleService().findById(table.getValue().toString());
            final RoleBean bean = getBean(role);
            form.binder.setItemDataSource(new BeanItem<>(bean));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            RoleFacade.getRoleService().persist(getEntity(binder));
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
            RoleFacade.getRoleService().merge(getEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
       final Role role = getEntity(binder);
        RoleFacade.getRoleService().delete(role);
        getHome();
    }
    private Role getEntity(FieldGroup binder) {
        final RoleBean bean = ((BeanItem<RoleBean>) binder.getItemDataSource()).getBean();
           final Role role = new Role.Builder(bean.getRolename())
                    .description(bean.getDescription())
                    .id(bean.getId())
                    .build();
            return role;
    }

    private void getHome() {
        main.content.setSecondComponent(new UsersMenu(main, "ROLES"));
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
        form.save.addClickListener((ClickListener) this);
        form.edit.addClickListener((ClickListener) this);
        form.cancel.addClickListener((ClickListener) this);
        form.update.addClickListener((ClickListener) this);
        form.delete.addClickListener((ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((ValueChangeListener) this);
    }

    private RoleBean getBean(Role val) {
        final RoleBean bean = new RoleBean();
        final Role role = RoleFacade.getRoleService().findById(val.getId());
        bean.setDescription(role.getDescription());
        bean.setId(role.getId());
        bean.setRolename(role.getRolename());
        return bean;
    }
}
