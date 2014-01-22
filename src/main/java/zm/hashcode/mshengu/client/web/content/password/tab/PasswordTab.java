/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.password.tab;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.app.security.GetUserCredentials;
import zm.hashcode.mshengu.app.security.PasswordEncrypt;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.password.PasswordMenu;
import zm.hashcode.mshengu.client.web.content.password.forms.PasswordForm;
import zm.hashcode.mshengu.client.web.content.password.model.PasswordBean;
import zm.hashcode.mshengu.client.web.content.password.util.PasswordCheckUtil;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author boniface
 */
public class PasswordTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private final PasswordForm form;

    public PasswordTab(MshenguMain app) {
        main = app;
        form = new PasswordForm();
        setSizeFull();
        addComponent(form);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (form.cancelButton == source) {
            getHome();
        } else if (form.changePasswordButton == source) {
            saveForm(form.binder);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
    }

    private void saveForm(FieldGroup binder) {
        final PasswordBean bean = ((BeanItem<PasswordBean>) form.binder.getItemDataSource()).getBean();
        try {
            binder.commit();
            if (new PasswordCheckUtil().checkOldPassowrd(bean.getOldpassword())) {
                if (PasswordCheckUtil.comparePasswords(bean.getNewPassword(), bean.getRepeatPassword())) {
                    changePassword(bean.getNewPassword());
                    Notification.show("Password Changed!", Notification.Type.WARNING_MESSAGE);
                } else {
                    Notification.show("New Password Do not Match!", Notification.Type.WARNING_MESSAGE);
                }
            } else {
                Notification.show("Old Passoword Wrong!", Notification.Type.WARNING_MESSAGE);
            }
            getHome();

        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new PasswordMenu(main, "LANDING"));
    }

    private void addListeners() {
        //Register Button Listeners
        form.cancelButton.addClickListener((Button.ClickListener) this);
        form.changePasswordButton.addClickListener((Button.ClickListener) this);
    }

    private void changePassword(String newPassword) {
        Person person = new GetUserCredentials().getLoggedInPerson();
        String password = PasswordEncrypt.encrypt(newPassword);
        Person updatedPerson = new Person.Builder(person.getLastname())
                .person(person)
                .password(password)
                .build();
        PersonFacade.getPersonService().merge(updatedPerson);
    }
}
