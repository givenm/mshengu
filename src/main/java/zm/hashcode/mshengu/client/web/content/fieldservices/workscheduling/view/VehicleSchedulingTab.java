/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.view;

import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;
import org.springframework.util.StringUtils;
import org.vaadin.haijian.ExcelExporter;
import org.vaadin.haijian.PdfExporter;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.WorkSchedulingMenu;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.forms.VehicleSheduleForm;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.models.VehicleScheduleBean;
import zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.tables.VehicleSchedulingTable;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Ferox
 */
public class VehicleSchedulingTab extends VerticalLayout implements Property.ValueChangeListener {

    private final MshenguMain main;
    private final VehicleSheduleForm form;
//    private final VehicleInfoForm vehicleInfoForm;
    private final VehicleSchedulingTable table;
    private final ExcelExporter export;

    public VehicleSchedulingTab(MshenguMain app) {
        main = app;
        form = new VehicleSheduleForm();
        table = new VehicleSchedulingTable(main);
//        vehicleInfoForm = new VehicleInfoForm();
        setSizeFull();
//        addComponent(vehicleInfoForm);
        export = new ExcelExporter(table);
        export.setCaption("Export PDF");
        addComponent(form);
        addComponent(export);
        addComponent(table);

//        form.binder.setReadOnly(true);

        form.vehicleNumber.addValueChangeListener((Property.ValueChangeListener) this);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == form.vehicleNumber) {
            if (form.vehicleNumber.getValue() != null) {
                System.out.println("Vehicle ID" + form.vehicleNumber.getValue().toString());

                final Truck truck = TruckFacade.getTruckService().findById(form.vehicleNumber.getValue().toString());
//                form.binder.setItemDataSource(new BeanItem<>(getBean(truck)));
                if (!StringUtils.isEmpty(truck.getRoutes())) {

                    form.setTruckDetails(truck.getNumberPlate(), truck.getDriverName());
                    table.loadVehicleRoutes(truck.getRoutes());
                    form.setContractTotals(table.getContractSites(), table.getContractFrequency(), table.getContractUnits(), table.getContractServices());
                    form.setPrivateTotals(table.getPrivateSites(), table.getPrivateFrequency(), table.getPrivateUnits(), table.getPrivateServices());
                    form.setOtherTotals(table.getOtherSites(), table.getOtherFrequency(), table.getOtherUnits(), table.getOtherServices());
                    form.setGlobalTotals(table.getTotalSites(), table.getTotalFrequency(), table.getTotalUnits(), table.getTotalServices());
                } else {
                    table.removeAllItems();
                    form.setContractTotals(0, 0, 0, 0);
                    form.setPrivateTotals(0, 0, 0, 0);
                    form.setOtherTotals(0, 0, 0, 0);
                    form.setGlobalTotals(0, 0, 0, 0);
                    form.setTruckDetails("", "");
                }
            } else {
                System.out.println("Vehicle ID = NULL " + form.vehicleNumber.getValue());
            }
        }

    }

    private void getHome() {
        main.content.setSecondComponent(new WorkSchedulingMenu(main, "VEHICLE_SCHEDULING"));
    }

    private VehicleScheduleBean getBean(Truck truck) {
        VehicleScheduleBean bean = new VehicleScheduleBean();

        bean.setNumberPlate(truck.getNumberPlate());
        bean.setDriverName(truck.getDriverName());
        return bean;
    }
}
