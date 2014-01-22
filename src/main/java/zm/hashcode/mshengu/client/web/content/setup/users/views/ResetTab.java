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
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.app.facade.ui.util.RoleFacade;
import zm.hashcode.mshengu.app.security.PasswordEncrypt;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.users.UsersMenu;
import zm.hashcode.mshengu.client.web.content.setup.users.forms.ResetForm;
import zm.hashcode.mshengu.client.web.content.setup.users.models.UserBean;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.ui.util.Role;

/**
 *
 * @author Ferox
 */
public class ResetTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final ResetForm form;

    public ResetTab(MshenguMain app) {
        main = app;
        form = new ResetForm();

        setSizeFull();
        addComponent(form);

        addListeners();
    }

    @Override
    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.reset) {
            resetPassword(form.binder);
        } else if (source == form.cancel) {
            getHome();
        }
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == form.comboBoxUsers) {


            setReadFormProperties();
        }
    }

    private void resetPassword(FieldGroup binder) {
        try {
            binder.commit();
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {

        getHome();
    }

    private void getHome() {
        main.content.setSecondComponent(new UsersMenu(main, "CREDENTIALS"));
    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.reset.setVisible(false);
        form.cancel.setVisible(true);

    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviou
        form.reset.setVisible(true);
        form.cancel.setVisible(true);

    }

    private void addListeners() {
        //Register Button Listeners
        form.reset.addClickListener((ClickListener) this);
        form.cancel.addClickListener((ClickListener) this);

        //Register Table Listerners
        form.comboBoxUsers.addValueChangeListener((ValueChangeListener) this);
    }

    private void passwordUpdate(FieldGroup binder) {
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
        Person person = PersonFacade.getPersonService().findById(bean.getId());
//         Person person = PersonFacade.getPersonService().
        String password = PasswordEncrypt.encrypt("mshenguReset");
        final Person person2 = new Person.Builder(bean.getLastname())
                .firstname(bean.getFirstname())
                .role(userRoles)
                .enable(bean.isEnabled())
                .username(bean.getEmail())
                .password(password)
                .build();
       
        PersonFacade.getPersonService().merge(person2);
    }
}
