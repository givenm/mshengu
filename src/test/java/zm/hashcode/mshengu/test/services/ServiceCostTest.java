/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import zm.hashcode.mshengu.domain.fleet.ServiceCost;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.fleet.TruckCategory;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceCategory;
import zm.hashcode.mshengu.services.fleet.ServiceCostService;
import zm.hashcode.mshengu.services.fleet.TruckCategoryService;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.services.serviceprovider.ServiceCategoryService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author Ferox
 */
public class ServiceCostTest extends AppTest {

    @Autowired
    private ServiceCostService serviceCostService;
    @Autowired
    private ServiceCategoryService serviceCategoryService;
    @Autowired
    private TruckService truckService;
    @Autowired
    private TruckCategoryService truckCategoryService;
    private Set<String> serviceCostIdLis = new HashSet<>();
    private Set<String> trucksIdList = new HashSet<>();
    private String truckCategoryId = null;
    private String serviceCategoryId = null;
    private String serviceProviderId = null;
    private String serviceProviderCategoryId = null;
    private List<ServiceCost> serviceCosts500 = new ArrayList<>();
    private List<ServiceCost> serviceCosts600 = new ArrayList<>();
    private List<ServiceCost> serviceCosts700 = new ArrayList<>();
    private List<ServiceCost> serviceCosts800 = new ArrayList<>();

//    @Test
    public void createTruckCategory() {
        truckCategoryService = ctx.getBean(TruckCategoryService.class);
        TruckCategory truckCategory = new TruckCategory.Builder("Oerational Vehicle")
                .build();
        truckCategoryService.persist(truckCategory);
        truckCategoryId = truckCategory.getId();

        Assert.notNull(truckCategoryId);
    }

//    @Test(dependsOnMethods = {"createTruckCategory"})
    public void createServiceCategory() {
        serviceCategoryService = ctx.getBean(ServiceCategoryService.class);
        ServiceCategory serviceCategory = new ServiceCategory.Builder("SUPPLIER")
                .build();
        serviceCategoryService.persist(serviceCategory);
        serviceCategoryId = serviceCategory.getId();

        Assert.notNull(serviceCategoryId);
    }

//    @Test(dependsOnMethods = {"createServiceCategory"})
    public void craeteTruck() {
        truckCategoryService = ctx.getBean(TruckCategoryService.class);
        truckService = ctx.getBean(TruckService.class);

        TruckCategory truckCategory = truckCategoryService.findById(truckCategoryId);



        Truck truck500 = new Truck.Builder("AG3N7500")
                .brand("TOYOTA")
                .category(truckCategory)
                .dateOfExpire(new Date())
                .description("Hatchback")
                .engineNo("098765")
                .fuelSpec(50000)
                .isActive(true)
                .manufacturingSpec(5000.00)
                .model("Hilux")
                .registerYear(2000)
                .operatingSpec(10000.000)
                .vehicleNumber("MSV-5000")
                .id("MSV-5000")
                .build();
        truckService.persist(truck500);
        trucksIdList.add(truck500.getId());

        Truck truck600 = new Truck.Builder("AG3N7600")
                .brand("Nissan")
                .category(truckCategory)
                .dateOfExpire(new Date())
                .description("Hatchback")
                .engineNo("098765")
                .fuelSpec(50000)
                .isActive(true)
                .manufacturingSpec(5000.00)
                .model("Navara")
                .registerYear(2000)
                .operatingSpec(10000.000)
                .vehicleNumber("MSV-6000")
                .id("MSV-6000")
                .build();

        truckService.persist(truck600);
        trucksIdList.add(truck600.getId());

        Truck truck700 = new Truck.Builder("AG3N7700")
                .brand("Suzuki")
                .category(truckCategory)
                .dateOfExpire(new Date())
                .description("Hatchback")
                .engineNo("098765")
                .fuelSpec(50000)
                .isActive(true)
                .manufacturingSpec(5000.00)
                .model("Jimmy")
                .registerYear(2000)
                .operatingSpec(10000.000)
                .vehicleNumber("MSV-7000")
                .id("MSV-7000")
                .build();
        truckService.persist(truck700);
        trucksIdList.add(truck700.getId());


        Truck truck800 = new Truck.Builder("AG3N7800")
                .brand("Dahiatsu")
                .category(truckCategory)
                .dateOfExpire(new Date())
                .description("Hatchback")
                .engineNo("098765")
                .fuelSpec(50000)
                .isActive(true)
                .manufacturingSpec(5000.00)
                .model("Terios")
                .registerYear(2000)
                .operatingSpec(10000.000)
                .vehicleNumber("MSV-800")
                .id("MSV-8000")
                .build();

        truckService.persist(truck800);
        trucksIdList.add(truck800.getId());

    }

//    @Test(dependsOnMethods = {"craeteTruck"})
    public void craeteServiceCost() {
        serviceCostService = ctx.getBean(ServiceCostService.class);
        truckService = ctx.getBean(TruckService.class);


        for (int i = 1; i <= 10; i++) {
            ServiceCost serviceCost = new ServiceCost.Builder(new Date())
                    .comment("Comment" + i)
                    .slipNo("7500-" + i)
                    .serviceTotalCost(new BigDecimal(100))
                    .build();

            serviceCostService.persist(serviceCost);
            serviceCosts500.add(serviceCost);
        }

        for (int i = 1; i <= 10; i++) {
            ServiceCost serviceCost = new ServiceCost.Builder(new Date())
                    .comment("Comment" + i)
                    .slipNo("7600-" + i)
                    .serviceTotalCost(new BigDecimal(100))
                    .build();

            serviceCostService.persist(serviceCost);
            serviceCosts600.add(serviceCost);

        }

        for (int i = 1; i <= 10; i++) {
            ServiceCost serviceCost = new ServiceCost.Builder(new Date())
                    .comment("Comment" + i)
                    .slipNo("7700-" + i)
                    .serviceTotalCost(new BigDecimal(100))
                    .build();

            serviceCostService.persist(serviceCost);
            serviceCosts700.add(serviceCost);
        }

        for (int i = 1; i <= 10; i++) {
            ServiceCost serviceCost = new ServiceCost.Builder(new Date())
                    .comment("Comment" + i)
                    .slipNo("7800-" + i)
                    .serviceTotalCost(new BigDecimal(100))
                    .build();

            serviceCostService.persist(serviceCost);
            serviceCosts800.add(serviceCost);
        }




    }

//    @Test(dependsOnMethods = {"craeteServiceCost"})
    public void updateTrucks() {
        truckCategoryService = ctx.getBean(TruckCategoryService.class);
        truckService = ctx.getBean(TruckService.class);

        TruckCategory truckCategory = truckCategoryService.findById(truckCategoryId);



        Truck truck500 = new Truck.Builder("AG3N7500")
                .brand("TOYOTA")
                .category(truckCategory)
                .dateOfExpire(new Date())
                .description("Hatchback")
                .engineNo("098765")
                .fuelSpec(50000)
                .isActive(true)
                .manufacturingSpec(5000.00)
                .model("Hilux")
                .registerYear(2000)
                .operatingSpec(10000.000)
                .vehicleNumber("MSV-5000")
                .serviceCosts(serviceCosts500)
                .id("MSV-5000")
                .build();
        truckService.merge(truck500);
        trucksIdList.add(truck500.getId());

        Truck truck600 = new Truck.Builder("AG3N7600")
                .brand("Nissan")
                .category(truckCategory)
                .dateOfExpire(new Date())
                .description("Hatchback")
                .engineNo("098765")
                .fuelSpec(50000)
                .isActive(true)
                .manufacturingSpec(5000.00)
                .model("Navara")
                .registerYear(2000)
                .operatingSpec(10000.000)
                .vehicleNumber("MSV-6000")
                .serviceCosts(serviceCosts600)
                .id("MSV-6000")
                .build();

        truckService.merge(truck600);
        trucksIdList.add(truck600.getId());

        Truck truck700 = new Truck.Builder("AG3N7700")
                .brand("Suzuki")
                .category(truckCategory)
                .dateOfExpire(new Date())
                .description("Hatchback")
                .engineNo("098765")
                .fuelSpec(50000)
                .isActive(true)
                .manufacturingSpec(5000.00)
                .model("Jimmy")
                .registerYear(2000)
                .operatingSpec(10000.000)
                .vehicleNumber("MSV-7000")
                .serviceCosts(serviceCosts700)
                .id("MSV-7000")
                .build();
        truckService.merge(truck700);
        trucksIdList.add(truck700.getId());


        Truck truck800 = new Truck.Builder("AG3N7800")
                .brand("Dahiatsu")
                .category(truckCategory)
                .dateOfExpire(new Date())
                .description("Hatchback")
                .engineNo("098765")
                .fuelSpec(50000)
                .isActive(true)
                .manufacturingSpec(5000.00)
                .model("Terios")
                .registerYear(2000)
                .operatingSpec(10000.000)
                .vehicleNumber("MSV-800")
                .serviceCosts(serviceCosts800)
                .id("MSV-8000")
                .build();

        truckService.merge(truck800);
        trucksIdList.add(truck800.getId());

    }

//    @Test //(dependsOnMethods = {"updateTrucks"})
    public void findServiceCostTruck() {

        serviceCostService = ctx.getBean(ServiceCostService.class);
        truckService = ctx.getBean(TruckService.class);


        List<ServiceCost> serviceCostsList = serviceCostService.findAll();

        for (ServiceCost serviceCost : serviceCostsList) {
            Truck truck = serviceCostService.findServiceCostTruck(serviceCost.getId());
            if (truck != null) {
                System.out.println("Truck ID-" + truck.getId());
                System.out.println("Service Cost ID-" + serviceCost.getSlipNo());
            }
        }
    }
}
