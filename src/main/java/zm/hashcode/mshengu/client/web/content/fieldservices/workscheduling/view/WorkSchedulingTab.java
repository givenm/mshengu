/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.view;

import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;
import org.springframework.util.StringUtils;
import org.vaadin.haijian.PdfExporter;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.WorkSchedulingMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.forms.WorkSchedulingForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.tables.WorkSchedulingTable;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Ferox
 */
public class WorkSchedulingTab extends VerticalLayout implements Property.ValueChangeListener {

    private final MshenguMain main;
    private final WorkSchedulingForm form;
//    private final VehicleInfoForm vehicleInfoForm;
    private final WorkSchedulingTable table;
    private final PdfExporter export;

    public WorkSchedulingTab(MshenguMain app) {
        main = app;
        form = new WorkSchedulingForm();
        table = new WorkSchedulingTable(main);
//        vehicleInfoForm = new VehicleInfoForm();
        setSizeFull();
//        addComponent(vehicleInfoForm);

        export = new PdfExporter(table);
        export.setCaption("Export PDF");
        addComponent(form);
        addComponent(export);
        addComponent(table);

//        form.binder.setReadOnly(true);

        form.truckId.addValueChangeListener((Property.ValueChangeListener) this);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == form.truckId) {
            final Truck truck = TruckFacade.getTruckService().findById(form.truckId.getValue().toString());
            if (!StringUtils.isEmpty(truck.getDriver())) {
                form.setDriversName(truck.getDriver());
            }
            if (!StringUtils.isEmpty(truck.getRoutes())) {
                table.loadVehicleRoutes(truck.getRoutes());
            }
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new WorkSchedulingMenu(main, "LANDING"));
    }
}
