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
import java.util.HashSet;
import java.util.Set;
import org.springframework.dao.DuplicateKeyException;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.app.facade.ui.util.RoleFacade;
import zm.hashcode.mshengu.app.security.PasswordEncrypt;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.users.UsersMenu;
import zm.hashcode.mshengu.client.web.content.setup.users.forms.PersonForm;
import zm.hashcode.mshengu.client.web.content.setup.users.models.UserBean;
import zm.hashcode.mshengu.client.web.content.setup.users.tables.PersonTable;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.ui.util.Role;

/**
 *
 * @author Ferox
 */
public final class PersonTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final PersonForm form;
    private final PersonTable table;

    public PersonTab(MshenguMain app) {
        main = app;
        form = new PersonForm();
        table = new PersonTable(main);
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
            final Person user = PersonFacade.getPersonService().findById(table.getValue().toString());
            final UserBean bean = getBean(user);
            form.binder.setItemDataSource(new BeanItem<>(bean));
            setReadFormProperties();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            PersonFacade.getPersonService().persist(getNewEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        } catch(DuplicateKeyException dp){
             Notification.show("Username is already taken!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void saveEditedForm(FieldGroup binder) {
        try {
            binder.commit();
            PersonFacade.getPersonService().merge(getUpdateEntity(binder));
            getHome();
            Notification.show("Record UPDATED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }catch(DuplicateKeyException dp){
             Notification.show("Username is already taken!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        PersonFacade.getPersonService().delete(getUpdateEntity(binder));
        getHome();
    }

    private Person getNewEntity(FieldGroup binder) {
//        String password = PasswordEncrypt.encrypt(new PasswordGenerator().getStaticPassword());
        String password = PasswordEncrypt.encrypt("mshenguDemo");
        final UserBean bean = ((BeanItem<UserBean>) binder.getItemDataSource()).getBean();
        Set<Role> userRoles = new HashSet<>();

        if (bean.getRoleIds() != null) {
            for (String roleId : bean.getRoleIds()) {
                Role role = RoleFacade.getRoleService().findById(roleId);
                if (role != null) {
                    userRoles.add(role);
                }
            }
        }
        final Person person = new Person.Builder(bean.getLastname())
                .firstname(bean.getFirstname())
                .role(userRoles)
                .enable(bean.isEnabled())
                .password(password)
                .username(bean.getUsername())
                .user(true)
                .institutionId(bean.getInstitutionId())
                .build();
        return person;
    }

    private Person getUpdateEntity(FieldGroup binder) {
        final UserBean bean = ((BeanItem<UserBean>) binder.getItemDataSource()).getBean();
        Set<Role> userRoles = new HashSet<>();

         if (bean.getRoleIds()!= null) {
            for (String roleId : bean.getRoleIds()) {
                Role role = RoleFacade.getRoleService().findById(roleId);
                if (role != null) {
                    userRoles.add(role);
                }
            }
        }

        final Person person = new Person.Builder(bean.getLastname())
                .firstname(bean.getFirstname())
                .role(userRoles)
                .enable(bean.isEnabled())
                .username(bean.getUsername())
                .password(bean.getPassword())
                .user(true)
                .institutionId(bean.getInstitutionId())
                .build();
        return person;
    }

    private void getHome() {
        main.content.setSecondComponent(new UsersMenu(main, "LANDING"));
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
        form.rolesList.addValueChangeListener((ValueChangeListener) this);
    }

    public UserBean getBean(Person user) {
        Set<String> userRolesId = new HashSet<>();

        if (user.getRole() != null) {
            for (Role role : user.getRole()) {
                userRolesId.add(role.getId());
            }
        }

        UserBean bean = new UserBean();
        bean.setFirstname(user.getFirstname());
        bean.setLastname(user.getLastname());
        bean.setUsername(user.getUsername());
        bean.setEnabled(user.isEnable());
        bean.setRoleIds(userRolesId);
        bean.setPassword(user.getPassword());
        bean.setInstitutionId(user.getInstitutionId());
        return bean;
    }
}
