/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.models.DailyInputsBean;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author geek
 */
public class DailyInputsForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private final DailyInputsBean bean;
    public final BeanItem<DailyInputsBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");
    public ComboBox driverId;
    public ComboBox truckId;
    public TextField randPerLitreCalc;
    public TextField fuelCost;
    public TextField fuelLitres;
    public TextField oilLitres;
    public TextField oilCost;
    public DateField transactionDate;
    public TextField speedometer;
    //
    public DateField filterTransactionDate;
    public ComboBox filterTruckId;

    public DailyInputsForm() {
        bean = new DailyInputsBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent
        filterTransactionDate = new DateField("Month Filter");
        filterTruckId = getVehicleComboBox("Truck Filter");


        transactionDate = UIComponent.getDateField("Invoice Date", "transactionDate", DailyInputsBean.class, binder);
        TextField slipNo = UIComponent.getTextField("Invoice Number:", "slipNo", DailyInputsBean.class, binder);
        speedometer = UIComponent.getTextField("Closing Mileage:", "speedometer", DailyInputsBean.class, binder);
        fuelLitres = UIComponent.getTextField("Litres (Fuel):", "fuelLitres", DailyInputsBean.class, binder);
        oilLitres = UIComponent.getTextField("Litres (Oil):", "oilLitres", DailyInputsBean.class, binder);
        fuelCost = UIComponent.getBigDecimalTextField("Cost (Fuel):", "fuelCost", DailyInputsBean.class, binder);
        oilCost = UIComponent.getBigDecimalTextField("Cost (Oil):", "oilCost", DailyInputsBean.class, binder);
        randPerLitreCalc = UIComponent.getBigDecimalTextField("Rand/Litre :", "randPerLitre", DailyInputsBean.class, binder);
        driverId = UIComboBox.getVehicleDriversComboBox("Driver:", "driverId", DailyInputsBean.class, binder);
        truckId = UIComboBox.getVehicleComboBox("Truck:", "truckId", DailyInputsBean.class, binder);

//        NumberFormat.getCurrencyInstance().format(subtotal);
        filterTransactionDate.setWidth(250, Sizeable.Unit.PIXELS);
        filterTransactionDate.setImmediate(true);
        filterTransactionDate.setDateFormat("MMM-yyyy");


        transactionDate.setValue(new Date());
        fuelCost.setValue("0.00");
        speedometer.setValue("0.00");
        fuelLitres.setValue("0.00");
        oilLitres.setValue("0.00");
        oilCost.setValue("0.00");

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        Label filterCaptionLabel = new Label("Filter");
        filterCaptionLabel.setStyleName("captionLabel");

        grid.addComponent(filterCaptionLabel, 0, 0);
        grid.addComponent(filterTransactionDate, 1, 0);
        grid.addComponent(filterTruckId, 2, 0);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 1, 2, 1);

        grid.addComponent(transactionDate, 0, 2);
        grid.addComponent(slipNo, 1, 2);

        grid.addComponent(truckId, 0, 3);
        grid.addComponent(driverId, 1, 3);
        grid.addComponent(speedometer, 2, 3);


        grid.addComponent(fuelCost, 0, 4);
        grid.addComponent(fuelLitres, 1, 4);
        grid.addComponent(randPerLitreCalc, 2, 4);


        grid.addComponent(oilCost, 0, 5);
        grid.addComponent(oilLitres, 1, 5);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 6, 2, 6);
        grid.addComponent(buttons, 0, 7, 2, 7);

        addComponent(grid);

    }

    private ComboBox getVehicleComboBox(String fieldText) {
        ComboBox comboBox = new ComboBox(fieldText);
        List<Truck> truckList = TruckFacade.getTruckService().findAll();

        //  Collection<Location> cities = Collections2.filter(locations, new CityPredicate());
        for (Truck truck : truckList) {
            String truckName = truck.getVehicleNumber() + " - (" + truck.getNumberPlate() + ")";
            comboBox.addItem(truck.getId());
            comboBox.setItemCaption(truck.getId(), truckName);
        }
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(false);
        comboBox.setWidth(250, Sizeable.Unit.PIXELS);
        return comboBox;
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
