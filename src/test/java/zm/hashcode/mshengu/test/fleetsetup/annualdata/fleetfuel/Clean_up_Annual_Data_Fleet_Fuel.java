/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup.annualdata.fleetfuel;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.fleet.AnnualDataFleetFuel;
import zm.hashcode.mshengu.services.fleet.AnnualDataFleetFuelService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author ColinWa
 */
public class Clean_up_Annual_Data_Fleet_Fuel extends AppTest {

    @Autowired
    private AnnualDataFleetFuelService annualDataService;

    @Test
    public void testSheet() {
        annualDataService = ctx.getBean(AnnualDataFleetFuelService.class);
        List<AnnualDataFleetFuel> annualDataFleetFuelList = annualDataService.findAll();
        for (AnnualDataFleetFuel annualDataFuel : annualDataFleetFuelList) {
            System.out.println("DELETING - TruckId: " + annualDataFuel.getTruckId() + ". Date: " + annualDataFuel.getTransactionDate() + ". Amount: " + annualDataFuel.getMonthlyFuelCost() + ". Closing Mileage: " + annualDataFuel.getClosingMileage() + ". Driver Person ID: " + annualDataFuel.getDriverPersonId());

            annualDataService.delete(annualDataFuel);
        }
    }

    @Test(dependsOnMethods = {"testSheet"})
    public void readDatabase() {
        List<AnnualDataFleetFuel> annualDataFleetFuelList = annualDataService.findAll();
        if (annualDataFleetFuelList.isEmpty()) {
            System.out.println("\nNO ANNUAL DATA FOR FLEET FUEL FOUND\n");
        } else {
            for (AnnualDataFleetFuel annualDataFuel : annualDataFleetFuelList) {
                System.out.println("READING DATABASE - TruckId: " + annualDataFuel.getTruckId() + ". Date: " + annualDataFuel.getTransactionDate() + ". Amount: " + annualDataFuel.getMonthlyFuelCost() + ". Closing Mileage: " + annualDataFuel.getClosingMileage() + ". Driver Person ID: " + annualDataFuel.getDriverPersonId());
            }
        }
    }
}
