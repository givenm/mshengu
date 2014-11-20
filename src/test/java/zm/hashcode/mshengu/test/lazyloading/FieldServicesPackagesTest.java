/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.fieldservices.LogSiteEventService;
import zm.hashcode.mshengu.services.fieldservices.ServiceSiteUnits;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class FieldServicesPackagesTest extends AppTest {

    /**
     * F-Services: LogSiteEventService, ServiceSiteUnits
     */
    @Autowired
    private LogSiteEventService domainClassToTest01;   // Service
    @Autowired
    private ServiceSiteUnits domainClassToTest02;   // Service

//    @Test
    public void testThemAll01() {
        domainClassToTest01 = ctx.getBean(LogSiteEventService.class);
        domainClassToTest01.findAll();
    }

//    @Test
    public void testThemAll02() {
        domainClassToTest02 = ctx.getBean(ServiceSiteUnits.class);
        domainClassToTest02.getSiteForUnit("MTU-0000011");
    }
}
