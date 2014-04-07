/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.DailyDieselTrackerMenu;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.forms.VehicleFuelUsageForm;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.tables.VehicleFuelUsageTable;

/**
 *
 * @author geek
 */
public class VehicleFuelUsageTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    public final VehicleFuelUsageForm form;
    private final VehicleFuelUsageTable table;

    public VehicleFuelUsageTab(MshenguMain app) {
        main = app;
        form = new VehicleFuelUsageForm();
        table = new VehicleFuelUsageTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
//        if (source == form.save) {
//            saveForm(form.binder);
//        } else if (source == form.edit) {
//            setEditFormProperties();
//        } else if (source == form.cancel) {
//            getHome();
//        } else if (source == form.update) {
//            saveEditedForm(form.binder);
//        } else if (source == form.delete) {
//            deleteForm(form.binder);
//        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
        } else if (property == form.transactionDate) {
            try {
                table.removeAllItems();
                if (form.truckId.getValue().equals("all")) {
                    table.loadVehiclceFuelUsageData(form.transactionDate.getValue());
                    form.mtdActAverage.setReadOnly(false);
                    form.mtdActAverage.setValue(VehicleFuelUsageTable.mtdActAverageCalc.toString());
                    form.mtdActAverage.setReadOnly(true);
                } else {
                    table.loadVehiclceFuelUsageData(form.transactionDate.getValue(), form.truckId.getValue().toString());
                    form.mtdActAverage.setReadOnly(false);
                    form.mtdActAverage.setValue("");
                    form.mtdActAverage.setReadOnly(true);
                }
            } catch (java.lang.NullPointerException ex) {
                Notification.show("Error. Enter a Valid Date for Filter Transaction Date.", Notification.Type.ERROR_MESSAGE);
            }
        } else if (property == form.truckId) {
            if (form.transactionDate.getValue() != null) {
                table.removeAllItems();
                if (form.truckId.getValue().equals("all")) {
                    table.loadVehiclceFuelUsageData(form.transactionDate.getValue());
                    form.mtdActAverage.setReadOnly(false);
                    form.mtdActAverage.setValue(VehicleFuelUsageTable.mtdActAverageCalc.toString());
                    form.mtdActAverage.setReadOnly(true);
                } else {
                    table.loadVehiclceFuelUsageData(form.transactionDate.getValue(), form.truckId.getValue().toString());
                    form.mtdActAverage.setReadOnly(false);
                    form.mtdActAverage.setValue("");
                    form.mtdActAverage.setReadOnly(true);
                }
            }
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new DailyDieselTrackerMenu(main, "LANDING"));
    }

    private void addListeners() {
        //Register Listeners
        form.transactionDate.addValueChangeListener((Property.ValueChangeListener) this);
        form.truckId.addValueChangeListener((Property.ValueChangeListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }
}
