/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.forms;

import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author geek
 */
public class VehicleFuelUsageForm extends FormLayout {

    public DateField transactionDate;
    public ComboBox truckId;
    public TextField mtdActAverage;

    public VehicleFuelUsageForm() {
        transactionDate = new DateField("Select Month & Year");
        transactionDate.setWidth(250, Sizeable.Unit.PIXELS);
        transactionDate.setImmediate(true);
        transactionDate.setDateFormat("MMM-yyyy");

        truckId = this.getVehicleComboBox();
        mtdActAverage = new TextField("MTD Fleet Rating");

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();

        grid.addComponent(transactionDate, 0, 0);
        grid.addComponent(truckId, 1, 0);
        grid.addComponent(mtdActAverage, 2, 0);
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 1, 2, 1);

        truckId.setValue("all");
        addComponent(grid);

    }

    private ComboBox getVehicleComboBox() {
        truckId = new ComboBox("Select a Truck");
        List<Truck> truckList = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
        truckId.addItem("all");
        truckId.setItemCaption("all", "All");
        for (Truck truck : truckList) {
            String truckName = truck.getVehicleNumber() + " - (" + truck.getNumberPlate() + ")";
            truckId.addItem(truck.getId());
            truckId.setItemCaption(truck.getId(), truckName);
        }
        truckId.setImmediate(true);
        truckId.setNullSelectionAllowed(false);
        truckId.setWidth(250, Sizeable.Unit.PIXELS);

        return truckId;
    }
}
