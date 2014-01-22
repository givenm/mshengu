/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.site.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.fieldservices.site.models.SiteContactPersonBean;

/**
 * I
 *
 * @author Ferox
 */
public class SiteContactPersonForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private final SiteContactPersonBean bean;
    public final BeanItem<SiteContactPersonBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public SiteContactPersonForm() {
        bean = new SiteContactPersonBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent
        TextField firstName = UIComponent.getTextField("Contact Person Name :", "firstName", SiteContactPersonBean.class, binder);
        TextField lastName = UIComponent.getTextField("Contact Person Surname :", "lastName", SiteContactPersonBean.class, binder);
        TextField mainNumber = UIComponent.getTextField("Office Number :", "mainNumber", SiteContactPersonBean.class, binder);
        TextField otherNumber = UIComponent.getTextField("Mobile Number :", "otherNumber", SiteContactPersonBean.class, binder);
        TextField emailAddress = UIComponent.getTextField("Contact Person Email :", "emailAddress", SiteContactPersonBean.class, binder);
        TextArea address = UIComponent.getTextArea("Office Adress :", "address", SiteContactPersonBean.class, binder);
        address.addValidator(new BeanValidator(SiteContactPersonBean.class,  "address"));
        TextField position = UIComponent.getTextField("Contact Person Position :", "position", SiteContactPersonBean.class, binder);


        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(firstName, 0, 0);
        grid.addComponent(lastName, 1, 0);
        grid.addComponent(position, 2, 0);
        
        grid.addComponent(mainNumber, 0, 1);
        grid.addComponent(otherNumber, 1, 1);
        grid.addComponent(emailAddress, 2, 1);
        
        grid.addComponent(address, 0, 3);
        //grid.addComponent(position, 2, 2);

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
