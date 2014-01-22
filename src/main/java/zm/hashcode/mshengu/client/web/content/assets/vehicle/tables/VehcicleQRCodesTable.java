/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.tables;

import com.vaadin.ui.Table;
import java.util.List;
import org.vaadin.haijian.PdfExporter;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.views.UnitsQRCodesTab;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.views.VehicleQRCodesTab;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author boniface
 */
public class VehcicleQRCodesTable extends Table {

    private MshenguMain main;
    private VehicleQRCodesTab tab;

    public VehcicleQRCodesTable(final MshenguMain main, final VehicleQRCodesTab tab) {
        tab.removeAllComponents();
        this.main = main;
        this.tab = tab;
        setSizeFull();
        addContainerProperty("Vehicle Number", String.class, null);
        addContainerProperty("Truck ID", String.class, null);
        setNullSelectionAllowed(false);

        // Allow selecting items from the table.

        tab.addComponent(this);
        PdfExporter export = new PdfExporter(this);

        tab.addComponent(export);
        setImmediate(true);
        setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);


        List<Truck> trucksArrayList = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
        loadTrucksID(trucksArrayList);

//        set

    }

    public final void loadTrucksID(List<Truck> truckList) {
        int a = 0;
        for (Truck truck : truckList) {
            addItem(new Object[]{
                        truck.getVehicleNumber(),
                        truck.getId(),}, truck.getVehicleNumber());
        }
    }
}
