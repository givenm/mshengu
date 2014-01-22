/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.tables;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceCostFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.fleet.ServiceCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Ferox
 */
public class ServiceCostTable extends Table {

    private final MshenguMain main;
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();

    public ServiceCostTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Invoice Number", String.class, null);
        addContainerProperty("Invoice Date", String.class, null);
        addContainerProperty("Truck", String.class, null);
        addContainerProperty("Amount", BigDecimal.class, null);
        addContainerProperty("Service Category", String.class, null);
        addContainerProperty("Supplier", String.class, null);
        addContainerProperty("Service Comment", String.class, null);


        // Add Data Columns
        List<ServiceCost> serviceCostList = ServiceCostFacade.getServiceCostService().findAll();
        List<Truck> trucks = TruckFacade.getTruckService().findAll();
        Truck currentTruck = null;
        for (ServiceCost serviceCost : serviceCostList) {
            for (Truck truck : trucks) {
                List<ServiceCost> serviceCostListz = truck.getServiceCosts();
                for (ServiceCost serviceCostt : serviceCostListz) {
                    if (serviceCostt.getId().equals(serviceCost.getId())) {
                        currentTruck = truck;
                        break;
                    }
                }
            }

            for (Truck truck : trucks) {
                List<ServiceCost> serviceCostListz = truck.getServiceCosts();
                for (ServiceCost serviceCostt : serviceCostListz) {
                    addItem(new Object[]{
                                serviceCost.getSlipNo(),
                                formatHelper.getYearMonthDay(serviceCost.getTransactionDate()),
                                currentTruck == null ? "" : currentTruck.getBrand() + " " + currentTruck.getModel() + " " + currentTruck.getNumberPlate(),
                                serviceCost.getServiceTotalCost(),
                                serviceCost.getServiceCategoryName(),
                                serviceCost.getServiceProviderName(),
                                serviceCost.getComment(),}, serviceCost.getId());
                }
            }

        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}
