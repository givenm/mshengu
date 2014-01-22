/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.forms.TruckDetailsForm;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.views.TruckDetailsTab;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author Ferox
 */
public class TruckTable extends Table {

    private final MshenguMain main;
    private final TruckDetailsTab tab;
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();

    public TruckTable(final MshenguMain main, final TruckDetailsTab tab) {
        this.main = main;
        this.tab = tab;
        setSizeFull();

        addContainerProperty("Vehicle Number", String.class, null);
        addContainerProperty("Number Plate", String.class, null);
        addContainerProperty("Driver", String.class, null);
        addContainerProperty("Vehicle Category", String.class, null);
        addContainerProperty("Expiry Date", String.class, null);
        addContainerProperty("View", Button.class, null);
//        addContainerProperty("In Service?", Boolean.class, null);
        //addContainerProperty("Contact Number", String.class, null);

        // Add Data Columns
        loadTable();
    }

    public final void loadTable() {
        List<Truck> truckList = TruckFacade.getTruckService().findAll();
        for (Truck truck : truckList) {
            Button showDetails = new Button("Details");
            showDetails.setData(truck.getId());
            showDetails.setStyleName(Reindeer.BUTTON_LINK);
            showDetails.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    Truck truck = TruckFacade.getTruckService().findById((String) event.getButton().getData());
                    TruckDetailsForm form = new TruckDetailsForm(main, truck);
                    tab.removeAllComponents();
                    tab.addComponent(form);
                }
            });
            addItem(new Object[]{
                truck.getVehicleNumber(),
                truck.getNumberPlate(),
                truck.getDriverName(),
                truck.getCategoryName(),
                formatHelper.getYearMonthDay(truck.getDateOfExpire()),
                showDetails,}, truck.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }
}
