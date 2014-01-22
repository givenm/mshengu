/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.password.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import zm.hashcode.mshengu.client.web.content.password.model.PasswordBean;

/**
 *
 * @author boniface
 */
public class PasswordForm extends FormLayout {

    private final PasswordBean bean;
    public final BeanItem<PasswordBean> item;
    public final FieldGroup binder;
    public final Button changePasswordButton = new Button("Change Password");
    public final Button cancelButton = new Button("Cancel");

    public PasswordForm() {
        bean = new PasswordBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        final HorizontalLayout buttons = getButtons();
        changePasswordButton.setStyleName("default");
        cancelButton.setStyleName("default");

        final PasswordField oldpassword = getTextField("Old Password", "oldpassword");
        final PasswordField newPassword = getTextField("New Password", "newPassword");
        final PasswordField repeatPassword = getTextField("Repeat Password", "repeatPassword");

        final GridLayout grid = new GridLayout(3, 10);
        grid.setSizeFull();

        grid.addComponent(oldpassword, 0, 0);
        grid.addComponent(repeatPassword, 1, 0);
        grid.addComponent(newPassword, 2, 0);

        grid.addComponent(buttons, 0, 3, 2, 3);

        addComponent(grid);
    }

    private PasswordField getTextField(String label, String field) {
        PasswordField textField = new PasswordField(label);
        textField.setWidth(250, Sizeable.Unit.PIXELS);
        textField.setNullRepresentation("");
        textField.addValidator(new BeanValidator(PasswordBean.class, field));
        textField.setImmediate(true);
        binder.bind(textField, field);
        return textField;
    }

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponent(changePasswordButton);
        buttons.addComponent(cancelButton);
        return buttons;
    }
}
