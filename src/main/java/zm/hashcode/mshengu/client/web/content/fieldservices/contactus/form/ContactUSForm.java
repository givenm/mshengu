/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.contactus.form;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.fieldservices.contactus.models.ContactUSBean;

/**
 *
 * @author Ferox
 */
public class ContactUSForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final ContactUSBean bean;
    public final BeanItem<ContactUSBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public ContactUSForm() {
        bean = new ContactUSBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent
//          private String id;
        TextField refNumber = UIComponent.getTextField("Reference Number:", "refNumber", ContactUSBean.class, binder);
        DateField dateOfAction = UIComponent.getDateField("Reported On:", "dateOfAction", ContactUSBean.class, binder);
        TextField contactPersonFirstname = UIComponent.getTextField("Contact Person Firstname:", "contactPersonFirstname", ContactUSBean.class, binder);
        TextField contactPersonLastname = UIComponent.getTextField("Contact Person Lastname:", "contactPersonLastname", ContactUSBean.class, binder);
        TextField company = UIComponent.getTextField("Company", "company", ContactUSBean.class, binder);
        TextField email = UIComponent.getTextField("Email:", "email", ContactUSBean.class, binder);
        TextField phone = UIComponent.getTextField("Phone:", "phone", ContactUSBean.class, binder);
        TextField faxNumber = UIComponent.getTextField("Fax:", "faxNumber", ContactUSBean.class, binder);
        TextField hearAboutUs = UIComponent.getTextField("Heard About Us:", "hearAboutUs", ContactUSBean.class, binder);
        TextArea message = UIComponent.getTextArea("Message:", "message", ContactUSBean.class, binder);
        message.addValidator(new BeanValidator(ContactUSBean.class, "message"));

        CheckBox closed = UIComponent.getCheckBox("Closed:", "closed", ContactUSBean.class, binder);

//        ComboBox status = UIComboBox.getContactUSStatusComboBox("Status :", "status", ContactUSBean.class, binder);
        ComboBox mailNotifications = UIComboBox.getMailNotificationComboBox("Notification Name:", "mailNotifications", ContactUSBean.class, binder);
//        mailNotifications

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(refNumber, 0, 0);
        grid.addComponent(contactPersonFirstname, 1, 0);
        grid.addComponent(contactPersonLastname, 2, 0);
        grid.addComponent(company, 0, 1);
        grid.addComponent(email, 0, 2);
        grid.addComponent(phone, 1, 1);
        grid.addComponent(faxNumber, 2, 1);
        grid.addComponent(mailNotifications, 0, 3);
        grid.addComponent(hearAboutUs, 1, 2);
        grid.addComponent(closed, 2, 2);
        grid.addComponent(message, 0, 4);
//        grid.addComponent(closed, 1, 3);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 5, 2, 5);
        grid.addComponent(buttons, 0, 6, 2, 6);

        addComponent(grid);

    }

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        save.setSizeFull();
        edit.setSizeFull();
        cancel.setSizeFull();
        update.setSizeFull();
        delete.setSizeFull();

        save.setStyleName("default");
        edit.setStyleName("default");
        cancel.setStyleName("default");
        update.setStyleName("default");
        delete.setStyleName("default");

        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);
        return buttons;
    }
}