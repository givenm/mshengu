/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.procurement.AnnualDataFleetMaintenanceCostService;
import zm.hashcode.mshengu.services.procurement.AnnualDataFleetMaintenanceMileageService;
import zm.hashcode.mshengu.services.procurement.MaintenanceSpendBySupplierService;
import zm.hashcode.mshengu.services.procurement.PurchaseOrderNumberService;
import zm.hashcode.mshengu.services.procurement.QuoteNumberService;
import zm.hashcode.mshengu.services.procurement.RequestForQuoteService;
import zm.hashcode.mshengu.services.procurement.RequestPurchaseItemService;
import zm.hashcode.mshengu.services.procurement.RequestService;
import zm.hashcode.mshengu.services.procurement.ResponseToRFQService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class ProcurementPackagesTest extends AppTest {

    /**
     * procurement : AnnualDataFleetMaintenanceCostService,
     * AnnualDataFleetMaintenanceMileageService,
     * MaintenanceSpendBySupplierService PurchaseOrderNumberService,
     * QuoteNumberService, RequestForQuoteService, RequestPurchaseItemService,
     * RequestService, ResponseToRFQService
     */
    @Autowired
    private AnnualDataFleetMaintenanceCostService domainClassToTest01;   // Service
    @Autowired
    private AnnualDataFleetMaintenanceMileageService domainClassToTest02;   // Service
    @Autowired
    private MaintenanceSpendBySupplierService domainClassToTest03;   // Service
    @Autowired
    private PurchaseOrderNumberService domainClassToTest04;   // Service
    @Autowired
    private QuoteNumberService domainClassToTest05;   // Service  
    @Autowired
    private RequestForQuoteService domainClassToTest06;   // Service
    @Autowired
    private RequestPurchaseItemService domainClassToTest07;   // Service
    @Autowired
    private RequestService domainClassToTest08;   // Service
    @Autowired
    private ResponseToRFQService domainClassToTest09;   // Service

//    @Test
    public void testThemAll01() {
        domainClassToTest01 = ctx.getBean(AnnualDataFleetMaintenanceCostService.class);
        domainClassToTest01.findAll();
    }

//    @Test
    public void testThemAll02() {
        domainClassToTest02 = ctx.getBean(AnnualDataFleetMaintenanceMileageService.class);
        domainClassToTest02.findAll();
    }

//    @Test
    public void testThemAll03() {
        domainClassToTest03 = ctx.getBean(MaintenanceSpendBySupplierService.class);
        domainClassToTest03.findAll();
    }

//    @Test
    public void testThemAll04() {
        domainClassToTest04 = ctx.getBean(PurchaseOrderNumberService.class);
        domainClassToTest04.findAll();
    }

//    @Test
    public void testThemAll05() {
        domainClassToTest05 = ctx.getBean(QuoteNumberService.class);
        domainClassToTest05.findAll();
    }

//    @Test
    public void testThemAll06() {
        domainClassToTest06 = ctx.getBean(RequestForQuoteService.class);
        domainClassToTest06.findAll();
    }

//    @Test
    public void testThemAll07() {
        domainClassToTest07 = ctx.getBean(RequestPurchaseItemService.class);
        domainClassToTest07.findAll();
    }

//    @Test
    public void testThemAll08() {
        domainClassToTest08 = ctx.getBean(RequestService.class);
        domainClassToTest08.findAll();
    }

//    @Test
    public void testThemAll09() {
        domainClassToTest09 = ctx.getBean(ResponseToRFQService.class);
        domainClassToTest09.findAll();
    }

}
