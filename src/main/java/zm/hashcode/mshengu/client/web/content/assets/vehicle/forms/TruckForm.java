/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.models.TruckBean;

/**
 *
 * @author Ferox
 */
public class TruckForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final TruckBean bean;
    public final BeanItem<TruckBean> item;
    public final FieldGroup binder;
    public ComboBox categoryId;
    private TextField vehicleNumber;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public TruckForm() {
        bean = new TruckBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);


        TextField numberPlate = UIComponent.getTextField("*Number Plate:", "numberPlate", TruckBean.class, binder);
        vehicleNumber = UIComponent.getTextField("*Vehicle Number :", "vehicleNumber", TruckBean.class, binder);
        TextField startMileage = UIComponent.getTextField("*Start Mileage:", "startMileage", TruckBean.class, binder);
        TextField vinNo = UIComponent.getTextField("Vin Number:", "vinNo", TruckBean.class, binder);
        TextField engineNo = UIComponent.getTextField("Engine Number:", "engineNo", TruckBean.class, binder);
        categoryId = UIComboBox.getTruckCategoryComboBox("*Vehicle Category:", "categoryId", TruckBean.class, binder);
        TextField brand = UIComponent.getTextField("*Vehicle Brand:", "brand", TruckBean.class, binder);
        TextField model = UIComponent.getTextField("*Vehicle Model:", "model", TruckBean.class, binder);
        TextField tare = UIComponent.getTextField("Tare:", "tare", TruckBean.class, binder);
        TextField trackerGPS = UIComponent.getTextField("Tracker GPS Unit Bar Code:", "trackerGPS", TruckBean.class, binder);

        TextField radioSerialNumber = UIComponent.getTextField("Two - Way Radio Serial Number:", "radioSerialNumber", TruckBean.class, binder);
        TextField vehicleCost = UIComponent.getBigDecimalTextField("Cost of Vehicle:", "vehicleCost", TruckBean.class, binder);
//        TextArea description = UIComponent.getTextArea("Description:", "description", TruckBean.class, binder, 350);
        TextField registerYear = UIComponent.getTextField("*Registration Year", "registerYear", TruckBean.class, binder);
        DateField dateOfExpire = UIComponent.getDateField("*Date of Expiry", "dateOfExpire", TruckBean.class, binder);

        CheckBox isActive = UIComponent.getCheckBox("Active", "isActive", TruckBean.class, binder);
        ComboBox driverId = UIComboBox.getVehicleDriversComboBox("Driver:", "driverId", TruckBean.class, binder);

//        TextField operatingSpec = UIComponent.getTextField("Operating Spec :", "operatingSpec", TruckBean.class, binder);
        TextField manufacturingSpec = UIComponent.getTextField("*Manufacturer Fuel Spec (Ltrs/100km)", "manufacturingSpec", TruckBean.class, binder);
//        TextField operationalAllowance = UIComponent.getBigDecimalTextField("Operational Allowance :", "operationalAllowance", TruckBean.class, binder);
//        TextField fuelSpec = UIComponent.getTextField("Fuel Spec", "fuelSpec", TruckBean.class, binder);

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(brand, 0, 0);
        grid.addComponent(model, 1, 0);
        grid.addComponent(numberPlate, 2, 0);
        
        grid.addComponent(categoryId, 0, 1);
        grid.addComponent(vehicleNumber, 1, 1);
        grid.addComponent(registerYear, 2, 1);
                        
        grid.addComponent(driverId, 0, 2);
        grid.addComponent(dateOfExpire, 1, 2);
        grid.addComponent(isActive, 2, 2);        

        grid.addComponent(engineNo, 0,3);
        grid.addComponent(vinNo, 1, 3);
        grid.addComponent(tare, 2, 3);

        grid.addComponent(vehicleCost, 0, 4);
        grid.addComponent(startMileage, 1, 4);
        grid.addComponent(manufacturingSpec, 2, 4);        
        
        grid.addComponent(trackerGPS, 0, 5);
        grid.addComponent(radioSerialNumber, 1, 5);
//        grid.addComponent(operatingSpec, 1, 5);
//        grid.addComponent(operationalAllowance, 2, 5);
//        grid.addComponent(fuelSpec, 3, 5);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 6, 2, 6);
        grid.addComponent(buttons, 0, 7, 2, 7);

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

    public void setNextVehicleNumber(String nextVehicleNumber) {
        if (nextVehicleNumber != null) {
            vehicleNumber.setValue(nextVehicleNumber);
        }
    }
}
