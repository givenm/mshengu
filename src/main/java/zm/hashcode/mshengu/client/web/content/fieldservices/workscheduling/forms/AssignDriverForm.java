/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.app.util.validation.UIValidatorHelper;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.models.AssignDriversBean;

/**
 *
 * @author Ferox
 */
public class AssignDriverForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final AssignDriversBean bean;
    public final BeanItem<AssignDriversBean> item;
    public final FieldGroup binder;
    public ComboBox truckId ;
    // Define Buttons
    public Button save = new Button("Assign Driver");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");
    public Label errorMessage;

    public AssignDriverForm() {
        bean = new AssignDriversBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);


        
        truckId = UIComboBox.getVehicleComboBox("Select Vehicle:", "truckId", AssignDriversBean.class, binder);
        truckId = UIValidatorHelper.setRequiredComboBox(truckId, "Select Vehicle");
        ComboBox driverId = UIComboBox.getDriversComboBox("Driver:", "driverId", AssignDriversBean.class, binder);
        driverId = UIValidatorHelper.setRequiredComboBox(driverId, "Driver");
        errorMessage = UIComponent.getErrorLabel();

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(errorMessage, 1, 0, 2, 0);
        
        grid.addComponent(truckId , 0, 1);
        grid.addComponent(driverId, 2, 1);
        
       



        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 7, 2, 7);
        grid.addComponent(buttons, 0, 8, 2, 8);

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
