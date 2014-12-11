/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.procurement.RequestService;
import zm.hashcode.mshengu.services.serviceprovider.ServiceCategoryService;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderCategoryService;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderProductCategoryService;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderProductService;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class ServiceProvidersPackagesTest extends AppTest {

    @Autowired
    private RequestService requestService;
    /**
     * S-Providers: ServiceCategoryService, ServiceProviderCategoryService,
     * ServiceProviderProductCategoryService, ServiceProviderProductService,
     * ServiceProviderService
     */

    @Autowired
    private ServiceCategoryService domainClassToTest01;   // Service
    @Autowired
    private ServiceProviderCategoryService domainClassToTest02;   // Service
    @Autowired
    private ServiceProviderProductCategoryService domainClassToTest03;   // Service
    @Autowired
    private ServiceProviderProductService domainClassToTest04;   // Service
    @Autowired
    private ServiceProviderService domainClassToTest05;   // Service  

//    @Test
    public void testThemAll01() {
        domainClassToTest01 = ctx.getBean(ServiceCategoryService.class);
        domainClassToTest01.findAll();
    }

//    @Test
    public void testThemAll02() {
        domainClassToTest02 = ctx.getBean(ServiceProviderCategoryService.class);
        domainClassToTest02.findAll();
    }

//    @Test
    public void testThemAll03() {
        domainClassToTest03 = ctx.getBean(ServiceProviderProductCategoryService.class);
        domainClassToTest03.findAll();
    }

//    @Test
    public void testThemAll04() {
        domainClassToTest04 = ctx.getBean(ServiceProviderProductService.class);
        domainClassToTest04.findAll();
    }

//    @Test
    public void testThemAll05() {
        domainClassToTest05 = ctx.getBean(ServiceProviderService.class);
        domainClassToTest05.findAll();
    }

}
