/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.customer.ContractService;
import zm.hashcode.mshengu.services.customer.ContractTypeService;
import zm.hashcode.mshengu.services.customer.CustomerService;
import zm.hashcode.mshengu.services.customer.EmployeeDetailService;
import zm.hashcode.mshengu.services.customer.InvoiceService;
import zm.hashcode.mshengu.services.customer.InvoiceTypeService;
import zm.hashcode.mshengu.services.customer.OrderService;
import zm.hashcode.mshengu.services.customer.OrderTypeService;
import zm.hashcode.mshengu.services.customer.ServiceRequestService;
import zm.hashcode.mshengu.services.customer.ServiceRequestTypeService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class CustomerPackagesTest extends AppTest {

    /**
     * Customer : ContractService,ContractTypeService, CustomerService,
     * EmployeeDetailService, InvoiceService, InvoiceTypeService OrderService,
     * OrderTypeService, ServiceRequestService, ServiceRequestTypeService
     */
    @Autowired
    private ContractService domainClassToTest01;   // Service
    @Autowired
    private ContractTypeService domainClassToTest02;   // Service
    @Autowired
    private CustomerService domainClassToTest03;   // Service
    @Autowired
    private EmployeeDetailService domainClassToTest04;   // Service
    @Autowired
    private InvoiceService domainClassToTest05;   // Service  
    @Autowired
    private InvoiceTypeService domainClassToTest06;   // Service
    @Autowired
    private OrderService domainClassToTest07;   // Service
    @Autowired
    private OrderTypeService domainClassToTest08;   // Service
    @Autowired
    private ServiceRequestService domainClassToTest09;   // Service
    @Autowired
    private ServiceRequestTypeService domainClassToTest10;   // Service    

//    @Test
    public void testThemAll01() {
        domainClassToTest01 = ctx.getBean(ContractService.class);
        domainClassToTest01.findAll();
    }

//    @Test
    public void testThemAll02() {
        domainClassToTest02 = ctx.getBean(ContractTypeService.class);
        domainClassToTest02.findAll();
    }

//    @Test
    public void testThemAll03() {
        domainClassToTest03 = ctx.getBean(CustomerService.class);
        domainClassToTest03.findAll();
    }

//    @Test
    public void testThemAll04() {
        domainClassToTest04 = ctx.getBean(EmployeeDetailService.class);
        domainClassToTest04.findAll();
    }

//    @Test
    public void testThemAll05() {
        domainClassToTest05 = ctx.getBean(InvoiceService.class);
        domainClassToTest05.findAll();
    }

//    @Test
    public void testThemAll06() {
        domainClassToTest06 = ctx.getBean(InvoiceTypeService.class);
        domainClassToTest06.findAll();
    }

//    @Test
    public void testThemAll07() {
        domainClassToTest07 = ctx.getBean(OrderService.class);
        domainClassToTest07.findAll();
    }

//    @Test
    public void testThemAll08() {
        domainClassToTest08 = ctx.getBean(OrderTypeService.class);
        domainClassToTest08.findAll();
    }

//    @Test
    public void testThemAll09() {
        domainClassToTest09 = ctx.getBean(ServiceRequestService.class);
        domainClassToTest09.findAll();
    }

//    @Test
    public void testThemAll10() {
        domainClassToTest10 = ctx.getBean(ServiceRequestTypeService.class);
        domainClassToTest10.findAll();
    }
}
