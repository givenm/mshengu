/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup.annualdata.fleetmaintenance.maintenancecost;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;
import zm.hashcode.mshengu.services.procurement.AnnualDataFleetMaintenanceCostService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author ColinWa
 */
public class Clean_up_Annual_Data_Fleet_Maintenance_Cost extends AppTest {

    @Autowired
    private AnnualDataFleetMaintenanceCostService annualDataFleetMaintenanceCostService;

//    @Test
    public void testSheet() {
        annualDataFleetMaintenanceCostService = ctx.getBean(AnnualDataFleetMaintenanceCostService.class);
        List<AnnualDataFleetMaintenanceCost> annualDataFleetMaintenanceCostServiceList = annualDataFleetMaintenanceCostService.findAll();
        for (AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost : annualDataFleetMaintenanceCostServiceList) {
            System.out.println("DELETING - TruckId: " + annualDataFleetMaintenanceCost.getTruckId() + ". Date: " + annualDataFleetMaintenanceCost.getTransactionMonth() + ". TruckId: " + annualDataFleetMaintenanceCost.getTruckId() + ". Monthly Maintenance Cost: " + annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost() + ". Driver Person ID: " + annualDataFleetMaintenanceCost.getDriverPersonId());
            annualDataFleetMaintenanceCostService.delete(annualDataFleetMaintenanceCost);
        }
    }

//    @Test(dependsOnMethods = {"testSheet"})
    public void readDatabase() {
        List<AnnualDataFleetMaintenanceCost> annualDataFleetMaintenanceCostServiceList = annualDataFleetMaintenanceCostService.findAll();
        if (annualDataFleetMaintenanceCostServiceList.isEmpty()) {
            System.out.println("\nNO ANNUAL DATA FOR FLEET Maintenance Maintenance Cost FOUND\n");
        } else {
            for (AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost : annualDataFleetMaintenanceCostServiceList) {
                System.out.println("DELETING - TruckId: " + annualDataFleetMaintenanceCost.getTruckId() + ". Date: " + annualDataFleetMaintenanceCost.getTransactionMonth() + ". TruckId: " + annualDataFleetMaintenanceCost.getTruckId() + ". Monthly Maintenance Cost: " + annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost() + ". Driver Person ID: " + annualDataFleetMaintenanceCost.getDriverPersonId());
            }
        }
    }
}
