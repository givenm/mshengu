/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.client.web.content.setup.locations.model.ContactBean;

/**
 *
 * @author boniface
 */
public class ContactListForm extends FormLayout {

    private final  ContactBean bean;
    public final BeanItem<ContactBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public ContactListForm() {
        bean = new ContactBean();
        item = new BeanItem<ContactBean>(bean);
        binder = new FieldGroup(item);

        TextField name = new TextField("Contact List Name");
        name.setNullRepresentation("");


        // Add the bean validator
        name.addValidator(new BeanValidator(ContactBean.class, "name"));
        name.setImmediate(true);


        // Create a field group and use it to bind the fields in the layout

        binder.bind(name, "name");
        addComponent(name);
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);

        // Determines which properties are shown, and in which order:
        edit.setVisible(false);
        update.setVisible(false);
        delete.setVisible(false);
        addComponent(buttons);
    }


}
