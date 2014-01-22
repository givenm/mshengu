/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import java.math.BigDecimal;
import java.util.Date;
import zm.hashcode.mshengu.app.facade.fleet.FuelAndOilPriceFacade;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.models.FuelAndOilPriceBean;
import zm.hashcode.mshengu.domain.fleet.FuelAndOilPrice;

/**
 *
 * @author geek
 */
public class FuelAndOilPriceForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private final FuelAndOilPriceBean bean;
    public final BeanItem<FuelAndOilPriceBean> item;
    public final FieldGroup binder;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public FuelAndOilPriceForm() {
        bean = new FuelAndOilPriceBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        HorizontalLayout buttons = getButtons();
        buttons.setSizeFull();
        // Determines which properties are shown
        update.setVisible(false);
        delete.setVisible(false);

        // UIComponent
        DateField entryDate = UIComponent.getDateField("Entry Date", "entryDate", FuelAndOilPriceBean.class, binder);
        TextField fuelPrice = UIComponent.getBigDecimalTextField("Fuel Price:", "fuelPrice", FuelAndOilPriceBean.class, binder);
        DateField fuelEffectDate = UIComponent.getDateField("Fuel Effect Date", "fuelEffectDate", FuelAndOilPriceBean.class, binder);
        TextField engineOilPrice = UIComponent.getBigDecimalTextField("Engine Oil Price:", "engineOilPrice", FuelAndOilPriceBean.class, binder);
        DateField engineOilEffectDate = UIComponent.getDateField("Engine Oil Effect Date", "engineOilEffectDate", FuelAndOilPriceBean.class, binder);

        entryDate.setValue(new Date());
        entryDate.setEnabled(false);

        BigDecimal currentFuelPrice = FuelAndOilPriceFacade.getFuelAndOilPriceService().getCurrentFuelPrice();
        BigDecimal currentEngineOilPrice = FuelAndOilPriceFacade.getFuelAndOilPriceService().getCurrentEngineOilPrice();
        FuelAndOilPrice currentFuelAndOilPrice = FuelAndOilPriceFacade.getFuelAndOilPriceService().getCurrentFuelAndOilPriceEntity();

        fuelPrice.setValue(currentFuelPrice.toString());
        fuelEffectDate.setValue(currentFuelAndOilPrice.getFuelEffectDate());
        engineOilPrice.setValue(currentEngineOilPrice.toString());
        engineOilEffectDate.setValue(currentFuelAndOilPrice.getEngineOilEffectDate());


        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        Label headingLabel = new Label("<h3 style=\" color:red;\">NB: Default values are Current prices. Change only when and where necessary. Don't change other values</h3>");
        headingLabel.setContentMode(ContentMode.HTML);

        grid.addComponent(headingLabel, 0, 0, 3, 0);

        grid.addComponent(entryDate, 0, 1);

        grid.addComponent(fuelPrice, 0, 2);
        grid.addComponent(fuelEffectDate, 1, 2);

        grid.addComponent(engineOilPrice, 0, 3);
        grid.addComponent(engineOilEffectDate, 1, 3);

        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 4, 1, 4);
        grid.addComponent(buttons, 0, 5, 1, 5);

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
