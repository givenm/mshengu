/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.humanresources.staff.models.StaffContactPersonBean;

/**
 * I
 *
 * @author Ferox
 */
public class StaffContactPersonForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final StaffContactPersonBean bean;
    public final BeanItem<StaffContactPersonBean> item;
    public final FieldGroup binder;
    public ComboBox staffId;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public StaffContactPersonForm() {
        bean = new StaffContactPersonBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent
        staffId = UIComboBox.getStaffComboBox("Select Staff :", "parentId", StaffContactPersonBean.class, binder);
        TextField firstName = UIComponent.getTextField("Name :", "firstName", StaffContactPersonBean.class, binder);
        TextField lastName = UIComponent.getTextField("Surname :", "lastName", StaffContactPersonBean.class, binder);
        TextField mainNumber = UIComponent.getTextField("Cell Number :", "mainNumber", StaffContactPersonBean.class, binder);
        TextField otherNumber = UIComponent.getTextField("Telephone Number :", "otherNumber", StaffContactPersonBean.class, binder);
        TextField emailAddress = UIComponent.getTextField("Email :", "emailAddress", StaffContactPersonBean.class, binder);
        TextArea address = UIComponent.getTextArea("Adress :", "address", StaffContactPersonBean.class, binder);
        address.addValidator(new BeanValidator(StaffContactPersonBean.class, "address"));
        TextField position = UIComponent.getTextField("Relationship:", "position", StaffContactPersonBean.class, binder);

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(staffId, 0, 1);
        
        grid.addComponent(firstName, 1, 1);
        grid.addComponent(lastName, 2, 1);

        grid.addComponent(mainNumber, 0, 2);
        grid.addComponent(otherNumber, 1, 2);
        
        grid.addComponent(emailAddress, 0, 3);
        grid.addComponent(position, 1, 3);
        grid.addComponent(address, 2, 2,2, 3);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 2, 4);
        grid.addComponent(buttons, 0, 5, 2, 5);

        addComponent(grid);

    }

    private HorizontalLayout getButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        save.setSizeFull();
        edit.setSizeFull();
        cancel.setSizeFull();
        update.setSizeFull();
        delete.setSizeFull();

        buttons.addComponent(save);
        buttons.addComponent(edit);
        buttons.addComponent(cancel);
        buttons.addComponent(update);
        buttons.addComponent(delete);
        return buttons;
    }
}
