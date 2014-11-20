/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.fleet.AnnualDataFleetFuelService;
import zm.hashcode.mshengu.services.fleet.FuelAndOilPriceService;
import zm.hashcode.mshengu.services.fleet.OperatingCostService;
import zm.hashcode.mshengu.services.fleet.OperationalAllowanceService;
import zm.hashcode.mshengu.services.fleet.ServiceCostService;
import zm.hashcode.mshengu.services.fleet.TruckCategoryService;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class FleetPackagesTest extends AppTest {

    /*
     * Fleet    : AnnualDataFleetFuelService, FuelAndOilPriceService, OperatingCostService, OperationalAllowanceService, ServiceCostService
     *            TruckCategoryService, TruckService
     */
    @Autowired
    private AnnualDataFleetFuelService domainClassToTest01;   // Service
    @Autowired
    private FuelAndOilPriceService domainClassToTest02;   // Service
    @Autowired
    private OperatingCostService domainClassToTest03;   // Service
    @Autowired
    private OperationalAllowanceService domainClassToTest04;   // Service
    @Autowired
    private ServiceCostService domainClassToTest05;   // Service  
    @Autowired
    private TruckCategoryService domainClassToTest06;   // Service
    @Autowired
    private TruckService domainClassToTest07;   // Service

//    @Test
    public void testThemAll01() {
        domainClassToTest01 = ctx.getBean(AnnualDataFleetFuelService.class);
        domainClassToTest01.findAll();
    }

//    @Test
    public void testThemAll02() {
        domainClassToTest02 = ctx.getBean(FuelAndOilPriceService.class);
        domainClassToTest02.findAll();
    }

//    @Test
    public void testThemAll03() {
        domainClassToTest03 = ctx.getBean(OperatingCostService.class);
        domainClassToTest03.findAll();
    }

//    @Test
    public void testThemAll04() {
        domainClassToTest04 = ctx.getBean(OperationalAllowanceService.class);
        domainClassToTest04.findAll();
    }

//    @Test
    public void testThemAll05() {
        domainClassToTest05 = ctx.getBean(ServiceCostService.class);
        domainClassToTest05.findAll();
    }

//    @Test
    public void testThemAll06() {
        domainClassToTest06 = ctx.getBean(TruckCategoryService.class);
        domainClassToTest06.findAll();
    }

//    @Test
    public void testThemAll07() {
        domainClassToTest07 = ctx.getBean(TruckService.class);
        domainClassToTest07.findAll();
    }

}
