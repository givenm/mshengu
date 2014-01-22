/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.suppliers.form;

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
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.models.ServiceProviderProductBean;

/**
 *
 * @author Ferox
 */
public class ServiceProviderProductForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final ServiceProviderProductBean bean;
    public final BeanItem<ServiceProviderProductBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public ServiceProviderProductForm() {
        bean = new ServiceProviderProductBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent
        TextField name = UIComponent.getTextField("Product Name :", "name", ServiceProviderProductBean.class, binder);
        TextField price = UIComponent.getBigDecimalTextField("Price :", "price", ServiceProviderProductBean.class, binder);
        ComboBox productCategoryId = UIComboBox.getServiceProviderProductCategoryComboBox("Product Category :", "productCategoryId", ServiceProviderProductBean.class, binder);

        TextField itemNumber = UIComponent.getTextField("Item Number:", "itemNumber", ServiceProviderProductBean.class, binder);
        TextField unit = UIComponent.getTextField("Unit:", "unit", ServiceProviderProductBean.class, binder);
        TextField volume = UIComponent.getTextField("Volume :", "volume", ServiceProviderProductBean.class, binder);
        
        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();


        grid.addComponent(name, 0, 0);
        grid.addComponent(price, 1, 0);
        grid.addComponent(productCategoryId, 2, 0);
        
        grid.addComponent(itemNumber, 0, 1);
        grid.addComponent(unit, 1, 1);
        grid.addComponent(volume, 2, 1);
        
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
