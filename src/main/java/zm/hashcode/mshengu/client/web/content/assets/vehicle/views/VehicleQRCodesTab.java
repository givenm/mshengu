/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.views;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.haijian.PdfExporter;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.vehicle.tables.VehcicleQRCodesTable;
import zm.hashcode.mshengu.domain.fleet.Truck;

/*
 * @author Ferox
 */
public class VehicleQRCodesTab extends VerticalLayout {

    public final VehcicleQRCodesTable table;
    public final HorizontalLayout headerBar = new HorizontalLayout();
    private final TextField truckSearchBox = new TextField("Search For Site Unit");
    public final VerticalLayout contentPanel = new VerticalLayout();
    private final MshenguMain main;
    private List<Truck> trucksArrayList;// = new ArrayList<>();

    public VehicleQRCodesTab(MshenguMain app) {
        main = app;
        contentPanel.setSizeFull();
        table = new VehcicleQRCodesTable(main, this);
        headerBar.setSizeFull();
        headerBar.addComponent(truckSearchBox);
        headerBar.setComponentAlignment(truckSearchBox, Alignment.MIDDLE_LEFT);

        truckSearchBox.setWidth("400px");
        addComponent(headerBar);
        addComponent(new Label("<HR/>", ContentMode.HTML));
        contentPanel.removeAllComponents();
        PdfExporter export = new PdfExporter(table);
        export.setCaption("Export PDF");
        export.setStyleName("default");

        contentPanel.addComponent(table);
        contentPanel.addComponent(export);
        addComponent(contentPanel);



        truckSearchBox.addShortcutListener(new ShortcutListener("Search Vehicle Number", ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                if (target == truckSearchBox) {
                    if (!truckSearchBox.getValue().isEmpty()) {
//                        List<Truck> units = TruckFacade.getTruckService().findAll();
                        trucksArrayList = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
                        List<Truck> list = new ArrayList<>();
                        for (Truck truck : trucksArrayList) {
                            if (truck.getVehicleNumber().toLowerCase().contains(truckSearchBox.getValue().toLowerCase())) {
                                list.add(truck);
                            }
                        }
                        table.removeAllItems();
                        table.loadTrucksID(list);
                    } else {
                        trucksArrayList = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
                        table.removeAllItems();
                        if (trucksArrayList != null) {
                            table.loadTrucksID(trucksArrayList);
                        }
                    }
                }
            }
        });
    }

    public void loadTrucksID(List<Truck> trucksList) {
//        table.removeValueChangeListener((Property.ValueChangeListener) this);
        table.loadTrucksID(trucksList);
//        table.addValueChangeListener((Property.ValueChangeListener) this);
        trucksArrayList = new ArrayList<>();
        trucksArrayList.addAll(trucksList);

    }
}
