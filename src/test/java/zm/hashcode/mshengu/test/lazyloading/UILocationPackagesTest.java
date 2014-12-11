/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.ui.location.AddressService;
import zm.hashcode.mshengu.services.ui.location.AddressTypeService;
import zm.hashcode.mshengu.services.ui.location.ContactService;
import zm.hashcode.mshengu.services.ui.location.LocationService;
import zm.hashcode.mshengu.services.ui.location.LocationTypeService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class UILocationPackagesTest extends AppTest {

    /**
     * UI-Location: AddressService, AddressTypeService, ContactService,
     * LocationService, LocationTypeService
     */

    @Autowired
    private AddressService domainClassToTest01;   // Service
    @Autowired
    private AddressTypeService domainClassToTest02;   // Service
    @Autowired
    private ContactService domainClassToTest03;   // Service
    @Autowired
    private LocationService domainClassToTest04;   // Service
    @Autowired
    private LocationTypeService domainClassToTest05;   // Service  

    @Test
    public void testThemAll01() {
        domainClassToTest01 = ctx.getBean(AddressService.class);
        domainClassToTest01.findAll();
    }

//    @Test
    public void testThemAll02() {
        domainClassToTest02 = ctx.getBean(AddressTypeService.class);
        domainClassToTest02.findAll();
    }

//    @Test
    public void testThemAll03() {
        domainClassToTest03 = ctx.getBean(ContactService.class);
        domainClassToTest03.findAll();
    }

//    @Test
    public void testThemAll04() {
        domainClassToTest04 = ctx.getBean(LocationService.class);
        domainClassToTest04.findAll();
    }

//    @Test
    public void testThemAll05() {
        domainClassToTest05 = ctx.getBean(LocationTypeService.class);
        domainClassToTest05.findAll();
    }

}
