/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup.annualdata.fleetmaintenance.maintenancecostbysupplier;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.procurement.MaintenanceSpendBySupplier;
import zm.hashcode.mshengu.services.procurement.MaintenanceSpendBySupplierService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author Colin
 */
public class CleanUp_SpendBySuppliers extends AppTest {

    @Autowired
    private MaintenanceSpendBySupplierService maintenanceSpendBySupplierService;

    /* (dependsOnMethods = {"testSheet"}) */
//    @Test
    
    public void cleanDatabase() {
        try {
            maintenanceSpendBySupplierService = ctx.getBean(MaintenanceSpendBySupplierService.class);
            List<MaintenanceSpendBySupplier> maintenanceSpendBySupplierList = maintenanceSpendBySupplierService.findAll();
            for (MaintenanceSpendBySupplier maintenanceSpendBySupplier : maintenanceSpendBySupplierList) {
                System.out.println("Deleting Object - TruckId: " + maintenanceSpendBySupplier.getTruckId() + ". Date: " + maintenanceSpendBySupplier.getTransactionDate() + ". Maintenance Cost: " + maintenanceSpendBySupplier.getMaintenanceCost() + ". Supplier ID: " + maintenanceSpendBySupplier.getSupplierId() + " | TruckId" + maintenanceSpendBySupplier.getTruckId());
                maintenanceSpendBySupplierService.delete(maintenanceSpendBySupplier);
            }

            List<MaintenanceSpendBySupplier> maintenanceSpendBySupplierList2 = maintenanceSpendBySupplierService.findAll();
            if (maintenanceSpendBySupplierList2.isEmpty()) {
                System.out.println("\n\nNo MaintenanceSpendBySupplier FOUND in DB\n\n");
            } else {
                for (MaintenanceSpendBySupplier maintenanceSpendBySupplier : maintenanceSpendBySupplierList2) {
                    System.out.println("READING DATABASE - TruckId: " + maintenanceSpendBySupplier.getTruckId() + ". Date: " + maintenanceSpendBySupplier.getTransactionDate() + ". Maintenance Cost: " + maintenanceSpendBySupplier.getMaintenanceCost() + ". Supplier ID: " + maintenanceSpendBySupplier.getSupplierId());
                }
            }
        } catch (java.lang.NullPointerException ex) {
            System.out.println("Nothing Found when READING DATABASE");
        }
    }
}
