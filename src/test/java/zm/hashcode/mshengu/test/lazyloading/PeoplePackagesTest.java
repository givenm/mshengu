/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.people.ContactPersonService;
import zm.hashcode.mshengu.services.people.PersonService;
import zm.hashcode.mshengu.services.procurement.RequestService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class PeoplePackagesTest extends AppTest {

    /**
     * people : ContactPersonService, PersonService
     */
    @Autowired
    private ContactPersonService domainClassToTest01;   // Service
    @Autowired
    private PersonService domainClassToTest02;   // Service

//    @Test
    public void testThemAll01() {
        domainClassToTest01 = ctx.getBean(ContactPersonService.class);
        domainClassToTest01.findAll();
    }

//    @Test
    public void testThemAll02() {
        domainClassToTest02 = ctx.getBean(PersonService.class);
        domainClassToTest02.findAll();
    }

}
