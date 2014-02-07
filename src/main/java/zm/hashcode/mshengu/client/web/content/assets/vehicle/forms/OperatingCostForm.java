/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.models.OperatingCostBean;

/**
 *
 * @author Ferox
 */
public class OperatingCostForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final OperatingCostBean bean;
    public final BeanItem<OperatingCostBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public OperatingCostForm() {
        bean = new OperatingCostBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent

        DateField transactionDate = UIComponent.getDateField("Transaction Date", "transactionDate", OperatingCostBean.class, binder);
        TextField slipNo = UIComponent.getTextField("Slip Number:", "slipNo", OperatingCostBean.class, binder);
        TextField speedometer = UIComponent.getTextField("Speedometer:", "speedometer", OperatingCostBean.class, binder);
        TextField fuelLitres = UIComponent.getTextField("Fuel Litres:", "fuelLitres", OperatingCostBean.class, binder);
        TextField oilLitres = UIComponent.getTextField("Oil Litres:", "oilLitres", OperatingCostBean.class, binder);
        TextField fuelCost = UIComponent.getBigDecimalTextField("Fuel Cost :", "fuelCost", OperatingCostBean.class, binder);
        TextField oilCost = UIComponent.getBigDecimalTextField("Oil Cost :", "oilCost", OperatingCostBean.class, binder);
        TextField otherCosts = UIComponent.getBigDecimalTextField("Other Costs :", "otherCosts", OperatingCostBean.class, binder);
        ComboBox driverId = UIComboBox.getVehicleDriversComboBox("Driver:", "driverId", OperatingCostBean.class, binder);

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(slipNo, 0, 0);

        grid.addComponent(transactionDate, 1, 0);
        grid.addComponent(driverId, 2, 0);

        grid.addComponent(speedometer, 0, 1);
        grid.addComponent(fuelLitres, 1, 1);
        grid.addComponent(fuelCost, 2, 1);

        grid.addComponent(oilLitres, 0, 2);
        grid.addComponent(oilCost, 1, 2);
        grid.addComponent(otherCosts, 2, 2);

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
