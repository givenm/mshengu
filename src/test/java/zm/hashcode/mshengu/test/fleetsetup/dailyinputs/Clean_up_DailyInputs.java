/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup.dailyinputs;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.services.fleet.OperatingCostService;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.services.people.PersonService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author geek
 */
public class Clean_up_DailyInputs extends AppTest {

    @Autowired
    private OperatingCostService operatingCostService;
    @Autowired
    private TruckService truckService; //

//    @Test
    public void testSheet() {

        operatingCostService = ctx.getBean(OperatingCostService.class);
        truckService = ctx.getBean(TruckService.class);

        // Clear All operatingCosts for each Truck
        List<Truck> trucks = truckService.findAll();
        List<OperatingCost> updatedOperatingCosts = new ArrayList<>();
        for (Truck truck : trucks) {
            Truck updatedTruck = new Truck.Builder(truck.getNumberPlate())
                    .operatingCosts(updatedOperatingCosts)
                    .serviceCosts(truck.getServiceCosts())
                    .trackerGPS(truck.getTrackerGPS())
                    .brand(truck.getBrand())
                    .category(truck.getCategory())
                    .radioSerialNumber(truck.getRadioSerialNumber())
                    .dateOfExpire(truck.getDateOfExpire())
                    .description(truck.getDescription())
                    .driver(truck.getDriver())
                    .engineNo(truck.getEngineNo())
                    .isActive(truck.isIsActive())
                    .vehicleNumber(truck.getVehicleNumber())
                    .model(truck.getModel())
                    .vehicleCost(truck.getVehicleCost())
                    .registerYear(truck.getRegisterYear())
                    .routes(truck.getRoutes())
                    .tare(truck.getTare())
                    .vinNo(truck.getVinNo())
                    .manufacturingSpec(truck.getManufacturingSpec())
                    .operatingSpec(truck.getOperatingSpec())
                    .operationalAllowance(truck.getOperationalAllowance())
                    .startMileage(truck.getStartMileage())
                    .fuelSpec(truck.getFuelSpec())
                    .id(truck.getId())
                    .build();
            truckService.merge(updatedTruck);
        }

        // Next Clear all OperatingCosts
        List<OperatingCost> operatingCostList = operatingCostService.findAll();
        for (OperatingCost operatingCost : operatingCostList) {
            operatingCostService.delete(operatingCost);
        }
    }

//    @Test(dependsOnMethods = {"testSheet"})
    public void readDatabase() {

        // cHECK IF operatingCosts exist for each Truck AFTER CLEAN-UP
        List<Truck> trucks = truckService.findAll();
        if (!trucks.isEmpty()) {
            for (Truck truckk : trucks) {
                List<OperatingCost> truckOperatingCosts = truckk.getOperatingCosts();
                if (!truckOperatingCosts.isEmpty()) {
                    for (OperatingCost oprCost : truckOperatingCosts) {
                        System.out.println("OperatingCost FOUND AFTER CLEAN-UP, TruckId: " + truckk.getVehicleNumber() + "-" + truckk.getNumberPlate() + ", Operating Cost ID: " + oprCost.getId());
                    }
                } else {
                    System.out.println("\nNO Operating CostS FOUND AFTER CLEAN-UP for Truck: " + truckk.getVehicleNumber() + "-" + truckk.getNumberPlate());
                }
            }
        }

// cHECK IF operatingCosts exist AFTER CLEAN-UP
        List<OperatingCost> operatingCostList = operatingCostService.findAll();
        if (operatingCostList.isEmpty()) {
            System.out.println("\nNO Operating CostS FOUND AFTER CLEAN-UP\n");
        } else {
            for (OperatingCost operatingCost : operatingCostList) {
                System.out.println("OperatingCost FOUND AFTER CLEAN-UP, OperatingCoST Id: " + operatingCost.getId() + ". Date: " + operatingCost.getTransactionDate() + ". Amount: " + operatingCost.getFuelCost() + ". Closing Mileage: " + operatingCost.getSpeedometer());
            }
        }
    }
}
