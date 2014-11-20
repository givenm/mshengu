/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.external.ContactUSService;
import zm.hashcode.mshengu.services.external.IncomingRFQService;
import zm.hashcode.mshengu.services.external.MailNotificationsService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class ExternalPackagesTest extends AppTest {

    /**
     * External : ContactUSService, IncomingRFQService, MailNotificationsService
     */
    @Autowired
    private ContactUSService domainClassToTest01;   // Service
    @Autowired
    private IncomingRFQService domainClassToTest02;   // Service
    @Autowired
    private MailNotificationsService domainClassToTest03;   // Service

//    @Test
    public void testThemAll01() {
        domainClassToTest01 = ctx.getBean(ContactUSService.class);
        domainClassToTest01.findAll();
    }

//    @Test
    public void testThemAll02() {
        domainClassToTest02 = ctx.getBean(IncomingRFQService.class);
        domainClassToTest02.findAll();
    }

//    @Test
    public void testThemAll03() {
        domainClassToTest03 = ctx.getBean(MailNotificationsService.class);
        domainClassToTest03.findAll();
    }
}
