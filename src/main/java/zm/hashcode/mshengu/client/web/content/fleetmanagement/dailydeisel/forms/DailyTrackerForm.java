/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import zm.hashcode.mshengu.app.util.UIComboBoxHelper;
import zm.hashcode.mshengu.app.util.UIComponentHelper;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.models.DailyTrackerBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util.TrackerUtil;

/**
 *
 * @author geek
 */
public class DailyTrackerForm extends FormLayout {

    private UIComponentHelper UIComponent = new UIComponentHelper();
    private UIComboBoxHelper UIComboBox = new UIComboBoxHelper();
    private TrackerUtil trackerUtil = new TrackerUtil();
    //
    // Use a specific locale for formatting decimal numbers
    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));
    //
    private final DailyTrackerBean bean;
    public final BeanItem<DailyTrackerBean> item;
    public final FieldGroup binder;
    public GridLayout grid;
    public DateField transactionDate;
    public ComboBox driverId;
    public ComboBox truckId;
    public TextField operatingSpec;
    public TextField manufacturerSpec;
    public TextField targetSpec;
    public TextField operationalAllowance;
    public TextField MTD;
    public Label monthlyFlag;

    public DailyTrackerForm() {
        bean = new DailyTrackerBean();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);

//        // UIComponent
        transactionDate = UIComponent.getDateField("Select Month & Year", "transactionDate", DailyTrackerBean.class, binder);
        monthlyFlag = new Label("<i>Monthly flag</i>", ContentMode.HTML);
        targetSpec = UIComponent.getBigDecimalTextField("Target Spec :", "targetSpec", DailyTrackerBean.class, binder);
        manufacturerSpec = UIComponent.getTextField("Manufacturer Fuel Spec (Ltr/Km):", "manufacturerSpec", DailyTrackerBean.class, binder);
        operatingSpec = UIComponent.getTextField("Operating Spec (R/Km) :", "operatingSpec", DailyTrackerBean.class, binder);
        driverId = UIComboBox.getVehicleDriversComboBox("Driver:", "driverId", DailyTrackerBean.class, binder);
        truckId = UIComboBox.getVehicleComboBox("Truck:", "truckId", DailyTrackerBean.class, binder);
        operationalAllowance = UIComponent.getBigDecimalTextField("Operational Allowance :", "operationalAllowance", DailyTrackerBean.class, binder);
        MTD = UIComponent.getBigDecimalTextField("MTD :", "mtd", DailyTrackerBean.class, binder);

        transactionDate.setWidth(250, Sizeable.Unit.PIXELS);
        transactionDate.setImmediate(true);
        transactionDate.setDateFormat("MMM-yyyy");
//                transactionDate.setValue(new Date());
//        transactionDate.setEnabled(false);

        this.operationalAllowance.setValue(df.format(Double.parseDouble(trackerUtil.getOperationalAllowance().toString())));
        operationalAllowance.setReadOnly(true);
        targetSpec.setReadOnly(true);
        manufacturerSpec.setReadOnly(true);
        operatingSpec.setReadOnly(true);
        MTD.setReadOnly(true);

        grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(transactionDate, 0, 0);
        grid.addComponent(truckId, 1, 0);
        grid.addComponent(driverId, 2, 0);

        grid.addComponent(operatingSpec, 0, 1);
        grid.addComponent(manufacturerSpec, 1, 1);
        grid.addComponent(operationalAllowance, 2, 1); //


        grid.addComponent(targetSpec, 0, 2);
        grid.addComponent(MTD, 1, 2);
        grid.addComponent(monthlyFlag, 2, 2);


        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 3, 2, 3);
//        grid.addComponent(buttons, 0, 6, 2, 6);

        addComponent(grid);

    }
//    private HorizontalLayout getButtons() {
//        HorizontalLayout buttons = new HorizontalLayout();
//        save.setSizeFull();
//        edit.setSizeFull();
//        cancel.setSizeFull();
//        update.setSizeFull();
//        delete.setSizeFull();
//
//    save.setStyleName("default");
//        edit.setStyleName("default");
//        cancel.setStyleName("default");
//        update.setStyleName("default");
//        delete.setStyleName("default");
//        buttons.addComponent(save);
//        buttons.addComponent(edit);
//        buttons.addComponent(cancel);
//        buttons.addComponent(update);
//        buttons.addComponent(delete);
//        return buttons;
//    }
}
