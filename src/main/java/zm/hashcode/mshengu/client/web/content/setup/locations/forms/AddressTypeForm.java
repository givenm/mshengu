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
import zm.hashcode.mshengu.client.web.content.setup.locations.model.AddressTypeBean;
import zm.hashcode.mshengu.domain.ui.location.AddressType;

/**
 *
 * @author boniface
 */
public class AddressTypeForm extends FormLayout {

    private final AddressTypeBean bean;
    public final BeanItem<AddressTypeBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public AddressTypeForm() {
        bean = new AddressTypeBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);

        TextField addressTypeName = new TextField("Address Type Name: ");
        addressTypeName.setNullRepresentation("");


        // Add the bean validator
        addressTypeName.addValidator(new BeanValidator(AddressTypeBean.class, "addressTypeName"));
        addressTypeName.setImmediate(true);


        // Create a field group and use it to bind the fields in the layout

        binder.bind(addressTypeName, "addressTypeName");

        addComponent(addressTypeName);
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
