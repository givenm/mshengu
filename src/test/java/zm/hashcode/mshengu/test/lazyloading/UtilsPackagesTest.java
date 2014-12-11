/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.ui.util.AccidentTypeService;
import zm.hashcode.mshengu.services.ui.util.CostCentreCategoryTypeService;
import zm.hashcode.mshengu.services.ui.util.CostCentreTypeService;
import zm.hashcode.mshengu.services.ui.util.CountryService;
import zm.hashcode.mshengu.services.ui.util.CurrencyService;
import zm.hashcode.mshengu.services.ui.util.DisciplineActionTypeService;
import zm.hashcode.mshengu.services.ui.util.ItemCategoryTypeService;
import zm.hashcode.mshengu.services.ui.util.JobPositionService;
import zm.hashcode.mshengu.services.ui.util.PaymentMethodService;
import zm.hashcode.mshengu.services.ui.util.RoleService;
import zm.hashcode.mshengu.services.ui.util.SequenceService;
import zm.hashcode.mshengu.services.ui.util.SequenceTypeService;
import zm.hashcode.mshengu.services.ui.util.StatusService;
import zm.hashcode.mshengu.services.ui.util.StatusTypeService;
import zm.hashcode.mshengu.services.ui.util.TerminateService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class UtilsPackagesTest extends AppTest {

    /**
     * Utils : AccidentTypeService, CostCentreCategoryTypeService,
     * CostCentreTypeService, CountryService, CurrencyService,
     * DisciplineActionTypeService ItemCategoryTypeService, JobPositionService,
     * PaymentMethodService, RoleService, SequenceService, SequenceTypeService
     * StatusService, StatusTypeService, TerminateService
     */
    @Autowired
    private AccidentTypeService domainClassToTest01;   // Service
    @Autowired
    private CostCentreCategoryTypeService domainClassToTest02;   // Service
    @Autowired
    private CostCentreTypeService domainClassToTest03;   // Service
    @Autowired
    private CountryService domainClassToTest04;   // Service
    @Autowired
    private CurrencyService domainClassToTest05;   // Service  
    @Autowired
    private DisciplineActionTypeService domainClassToTest06;   // Service
    @Autowired
    private ItemCategoryTypeService domainClassToTest07;   // Service
    @Autowired
    private JobPositionService domainClassToTest08;   // Service
    @Autowired
    private PaymentMethodService domainClassToTest09;   // Service
    @Autowired
    private RoleService domainClassToTest10;   // Service    
    @Autowired
    private SequenceService domainClassToTest11;   // Service
    @Autowired
    private SequenceTypeService domainClassToTest12;   // Service
    @Autowired
    private StatusService domainClassToTest13;   // Service
    @Autowired
    private StatusTypeService domainClassToTest14;   // Service
    @Autowired
    private TerminateService domainClassToTest15;   // Service 

//    @Test
    public void testThemAll01() {
        domainClassToTest01 = ctx.getBean(AccidentTypeService.class);
        domainClassToTest01.findAll();
    }

//    @Test
    public void testThemAll02() {
        domainClassToTest02 = ctx.getBean(CostCentreCategoryTypeService.class);
        domainClassToTest02.findAll();
    }

//    @Test
    public void testThemAll03() {
        domainClassToTest03 = ctx.getBean(CostCentreTypeService.class);
        domainClassToTest03.findAll();
    }

//    @Test
    public void testThemAll04() {
        domainClassToTest04 = ctx.getBean(CountryService.class);
        domainClassToTest04.findAll();
    }

//    @Test
    public void testThemAll05() {
        domainClassToTest05 = ctx.getBean(CurrencyService.class);
        domainClassToTest05.findAll();
    }

//    @Test
    public void testThemAll06() {
        domainClassToTest06 = ctx.getBean(DisciplineActionTypeService.class);
        domainClassToTest06.findAll();
    }

//    @Test
    public void testThemAll07() {
        domainClassToTest07 = ctx.getBean(ItemCategoryTypeService.class);
        domainClassToTest07.findAll();
    }

//    @Test
    public void testThemAll08() {
        domainClassToTest08 = ctx.getBean(JobPositionService.class);
        domainClassToTest08.findAll();
    }

//    @Test
    public void testThemAll09() {
        domainClassToTest09 = ctx.getBean(PaymentMethodService.class);
        domainClassToTest09.findAll();
    }

//    @Test
    public void testThemAll10() {
        domainClassToTest10 = ctx.getBean(RoleService.class);
        domainClassToTest10.findAll();
    }

//    @Test
    public void testThemAll11() {
        domainClassToTest11 = ctx.getBean(SequenceService.class);
        domainClassToTest11.findAll();
    }

//    @Test
    public void testThemAll12() {
        domainClassToTest12 = ctx.getBean(SequenceTypeService.class);
        domainClassToTest12.findAll();
    }

//    @Test
    public void testThemAll13() {
        domainClassToTest13 = ctx.getBean(StatusService.class);
        domainClassToTest13.findAll();
    }

//    @Test
    public void testThemAll14() {
        domainClassToTest14 = ctx.getBean(StatusTypeService.class);
        domainClassToTest14.findAll();
    }

//    @Test
    public void testThemAll15() {
        domainClassToTest15 = ctx.getBean(TerminateService.class);
        domainClassToTest15.findAll();
    }

}
