/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup.annualdata.fleetmaintenance.mileage;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;
import zm.hashcode.mshengu.services.procurement.AnnualDataFleetMaintenanceMileageService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author ColinWa
 */
public class Clean_Up_Annual_Data_Fleet_Maintenance_Mileage extends AppTest {

    @Autowired
    private AnnualDataFleetMaintenanceMileageService annualDataFleetMaintenanceMileageService;

    @Test
    public void testSheet() {
        annualDataFleetMaintenanceMileageService = ctx.getBean(AnnualDataFleetMaintenanceMileageService.class);
        List<AnnualDataFleetMaintenanceMileage> annualDataFleetMaintenanceMileageList = annualDataFleetMaintenanceMileageService.findAll();
        for (AnnualDataFleetMaintenanceMileage annualDataFleetMaintenanceMileage : annualDataFleetMaintenanceMileageList) {
            System.out.println("DELETING - TruckId: " + annualDataFleetMaintenanceMileage.getTruckId() + ". Date: " + annualDataFleetMaintenanceMileage.getTransactionMonth() + ". TruckId: " + annualDataFleetMaintenanceMileage.getTruckId() + ". Closing Mileage: " + annualDataFleetMaintenanceMileage.getMonthlyMileage() + ". Driver Person ID: " + annualDataFleetMaintenanceMileage.getDriverPersonId());
            annualDataFleetMaintenanceMileageService.delete(annualDataFleetMaintenanceMileage);
        }
    }

    @Test(dependsOnMethods = {"testSheet"})
    public void readDatabase() {
        List<AnnualDataFleetMaintenanceMileage> annualDataFleetMaintenanceMileageList = annualDataFleetMaintenanceMileageService.findAll();
        if (annualDataFleetMaintenanceMileageList.isEmpty()) {
            System.out.println("\nNO ANNUAL DATA FOR FLEET Maintenance Mileage FOUND\n");
        } else {
            for (AnnualDataFleetMaintenanceMileage annualDataFleetMaintenanceMileage : annualDataFleetMaintenanceMileageList) {
                System.out.println("READING DATABASE - TruckId: " + annualDataFleetMaintenanceMileage.getTruckId() + ". Date: " + annualDataFleetMaintenanceMileage.getTransactionMonth() + ". TruckId: " + annualDataFleetMaintenanceMileage.getTruckId() + ". Closing Mileage: " + annualDataFleetMaintenanceMileage.getMonthlyMileage() + ". Driver Person ID: " + annualDataFleetMaintenanceMileage.getDriverPersonId());
            }
        }
    }
}
