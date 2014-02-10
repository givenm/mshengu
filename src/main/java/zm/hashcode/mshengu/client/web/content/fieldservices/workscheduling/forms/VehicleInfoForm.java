/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.models.VehicleInfoBean;

/**
 *
 * @author Ferox
 */
public class VehicleInfoForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final VehicleInfoBean bean;
    public final BeanItem<VehicleInfoBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Assign Driver");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public VehicleInfoForm() {
        bean = new VehicleInfoBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);


        
        TextField numberPlate = UIComponent.getTextField("Number Plate:", "numberPlate", VehicleInfoBean.class, binder);
        TextField vehicleNumber = UIComponent.getTextField("Vehicle Number:", "vehicleNumber", VehicleInfoBean.class, binder);
        TextField engineNo = UIComponent.getTextField("Engine Number:", "engineNo", VehicleInfoBean.class, binder);
        ComboBox categoryId = UIComboBox.getTruckCategoryComboBox("Truck Category:", "categoryId", VehicleInfoBean.class, binder);
        TextField brand = UIComponent.getTextField("Truck Brand:", "brand", VehicleInfoBean.class, binder);
        TextField model = UIComponent.getTextField("Truck model:", "model", VehicleInfoBean.class, binder);

        CheckBox isActive = UIComponent.getCheckBox("Active", "isActive", VehicleInfoBean.class, binder);

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        
        
        grid.addComponent(brand , 0, 1);
        grid.addComponent(model, 1, 1);
        grid.addComponent(numberPlate, 2, 1);

        
        grid.addComponent(vehicleNumber , 0, 2);
        grid.addComponent(categoryId,  1, 2);
        grid.addComponent(engineNo, 2, 2);
        grid.addComponent(isActive , 3, 2);


        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 6, 2, 6);
//        grid.addComponent(buttons, 0, 7, 2, 7);

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
//        buttons.addComponent(edit);
//        buttons.addComponent(cancel);
//        buttons.addComponent(update);
//        buttons.addComponent(delete);
        return buttons;
    }
}
