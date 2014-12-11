/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.procurement.RequestService;
import zm.hashcode.mshengu.services.products.MobileAPIAppService;
import zm.hashcode.mshengu.services.products.MobileAppService;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.services.products.SiteServiceContractLifeCycleService;
import zm.hashcode.mshengu.services.products.SiteServiceLogService;
import zm.hashcode.mshengu.services.products.SiteUnitOperationalStatusService;
import zm.hashcode.mshengu.services.products.SiteUnitService;
import zm.hashcode.mshengu.services.products.UnitCleaningActivitiesService;
import zm.hashcode.mshengu.services.products.UnitLocationLifeCycleService;
import zm.hashcode.mshengu.services.products.UnitServiceLogService;
import zm.hashcode.mshengu.services.products.UnitTypeService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class ProducPackagesTest extends AppTest {

    @Autowired
    private RequestService requestService;
    /**
     * Chemical : ChemicalCostService, Customer :
     * ContractService,ContractTypeService, CustomerService,
     * EmployeeDetailService, InvoiceService, InvoiceTypeService OrderService,
     * OrderTypeService, ServiceRequestService, ServiceRequestTypeService
     * Documents: FileStorageService External : ContactUSService,
     * IncomingRFQService, MailNotificationsService F-Services:
     * LogSiteEventService, ServiceSiteUnits Fleet : AnnualDataFleetFuelService,
     * FuelAndOilPriceService, OperatingCostService,
     * OperationalAllowanceService, ServiceCostService TruckCategoryService,
     * TruckService Incident : IncidentActionStatusService, IncidentService,
     * IncidentTypeService, UserActionService KPI : KPAService, KPIItemService,
     * KPIValuesService, LoadKPIFiveService, LoadKPIFourService,
     * LoadKPIOneService, LoadKPIThreeService, LoadKPITwoService people :
     * ContactPersonService, PersonService procurement :
     * AnnualDataFleetMaintenanceCostService,
     * AnnualDataFleetMaintenanceMileageService,
     * MaintenanceSpendBySupplierService PurchaseOrderNumberService,
     * QuoteNumberService, RequestForQuoteService, RequestPurchaseItemService,
     * RequestService, ResponseToRFQService
     *
     * Products : MobileAPIAppService, MobileAppService, SiteService,
     * SiteServiceContractLifeCycleService, SiteServiceLogService,
     * SiteUnitOperationalStatusService, SiteUnitService,
     * UnitCleaningActivitiesService, UnitLocationLifeCycleService
     * UnitServiceLogService, UnitTypeService
     */

    @Autowired
    private MobileAPIAppService domainClassToTest01;   // Service
    @Autowired
    private MobileAppService domainClassToTest02;   // Service
    @Autowired
    private SiteService domainClassToTest03;   // Service
    @Autowired
    private SiteServiceContractLifeCycleService domainClassToTest04;   // Service
    @Autowired
    private SiteServiceLogService domainClassToTest05;   // Service  
    @Autowired
    private SiteUnitOperationalStatusService domainClassToTest06;   // Service
    @Autowired
    private SiteUnitService domainClassToTest07;   // Service
    @Autowired
    private UnitCleaningActivitiesService domainClassToTest08;   // Service
    @Autowired
    private UnitLocationLifeCycleService domainClassToTest09;   // Service
    @Autowired
    private UnitServiceLogService domainClassToTest10;   // Service    
    @Autowired
    private UnitTypeService domainClassToTest11;   // Service

//    @Test    public void testThemAll01(){domainClassToTest01 = ctx.getBean( MobileAPIAppService.class);     domainClassToTest01.;  }    
//    @Test    public void testThemAll02(){domainClassToTest02 = ctx.getBean( MobileAppService.class); domainClassToTest02.();}
//    @Test
    public void testThemAll03() {
        domainClassToTest03 = ctx.getBean(SiteService.class);
        domainClassToTest03.findAll();
    }

//    @Test
    public void testThemAll04() {
        domainClassToTest04 = ctx.getBean(SiteServiceContractLifeCycleService.class);
        domainClassToTest04.findAll();
    }

//    @Test
    public void testThemAll05() {
        domainClassToTest05 = ctx.getBean(SiteServiceLogService.class);
        domainClassToTest05.findAll();
    }

//    @Test
    public void testThemAll06() {
        domainClassToTest06 = ctx.getBean(SiteUnitOperationalStatusService.class);
        domainClassToTest06.findAll();
    }

//    @Test
    public void testThemAll07() {
        domainClassToTest07 = ctx.getBean(SiteUnitService.class);
        domainClassToTest07.findAll();
    }

//    @Test
    public void testThemAll08() {
        domainClassToTest08 = ctx.getBean(UnitCleaningActivitiesService.class);
        domainClassToTest08.findAll();
    }

//    @Test
    public void testThemAll09() {
        domainClassToTest09 = ctx.getBean(UnitLocationLifeCycleService.class);
        domainClassToTest09.findAll();
    }

//    @Test
    public void testThemAll10() {
        domainClassToTest10 = ctx.getBean(UnitServiceLogService.class);
        domainClassToTest10.findAll();
    }

//    @Test
    public void testThemAll11() {
        domainClassToTest11 = ctx.getBean(UnitTypeService.class);
        domainClassToTest11.findAll();
    }

}
