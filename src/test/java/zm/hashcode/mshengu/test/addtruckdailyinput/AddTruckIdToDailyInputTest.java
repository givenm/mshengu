/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.addtruckdailyinput;

import com.vaadin.ui.Notification;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.app.facade.fleet.OperatingCostFacade;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.services.fleet.OperatingCostService;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author Colin
 */
public class AddTruckIdToDailyInputTest extends AppTest {

    @Autowired
    private OperatingCostService operatingCostService;
    @Autowired
    private TruckService truckService; //

    @Test
    public void testAddTruckIdToDailyInput() {
        operatingCostService = ctx.getBean(OperatingCostService.class);
        truckService = ctx.getBean(TruckService.class);

        List<Truck> trucks = truckService.findAll();
        for (Truck truck : trucks) {
            List<OperatingCost> truckOperatingCosts = truck.getOperatingCosts();
            if (!truckOperatingCosts.isEmpty()) {
                for (OperatingCost operatingCost : truckOperatingCosts) {
                    updateEntity(operatingCost, truck);
                }
            }
        }
    }

    @Test(dependsOnMethods = {"testAddTruckIdToDailyInput"})
    public void testDb() {
        operatingCostService = ctx.getBean(OperatingCostService.class);
        // Next Test all OperatingCosts for TruckId
        List<OperatingCost> operatingCostList = operatingCostService.findAll();
        for (OperatingCost operatingCost : operatingCostList) {
            if (operatingCost.getTruckId() == null) {
                System.out.println("This Operating Cost has a null for TruckId: getId()= " + operatingCost.getId() + " | TransactionDate= " + operatingCost.getTransactionDate() + " | Driver= " + operatingCost.getDriverName());
            }
        }
    }

    private void updateEntity(OperatingCost operatingCost, Truck truck) {
        final OperatingCost unUpdatedOperatingCosts = new OperatingCost.Builder(operatingCost.getTransactionDate())
                .fuelCost(operatingCost.getFuelCost())
                .fuelLitres(operatingCost.getFuelLitres())
                .oilCost(operatingCost.getOilCost())
                .oilLitres(operatingCost.getOilLitres())
                .speedometer(operatingCost.getSpeedometer())
                .slipNo(operatingCost.getSlipNo())
                .driver(operatingCost.getDriver())
                .randPerLitre(operatingCost.getRandPerLitre())
                .truckId(truck.getId())
                .id(operatingCost.getId())
                .build();
        try {
            operatingCostService.merge(unUpdatedOperatingCosts);
        } catch (Exception e) {
            Notification.show("Could not Update OperatingCost with TruckId: \n" + e, Notification.Type.TRAY_NOTIFICATION);
        }
    }
}
