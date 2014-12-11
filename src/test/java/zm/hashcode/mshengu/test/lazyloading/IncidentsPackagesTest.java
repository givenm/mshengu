/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.lazyloading;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.incident.IncidentActionStatusService;
import zm.hashcode.mshengu.services.incident.IncidentService;
import zm.hashcode.mshengu.services.incident.IncidentTypeService;
import zm.hashcode.mshengu.services.incident.UserActionService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author Luckbliss
 */
public class IncidentsPackagesTest extends AppTest {

    /**
     * Incident : IncidentActionStatusService, IncidentService,
     * IncidentTypeService, UserActionService
     */
    @Autowired
    private IncidentActionStatusService domainClassToTest01;   // Service
    @Autowired
    private IncidentService domainClassToTest02;   // Service
    @Autowired
    private IncidentTypeService domainClassToTest03;   // Service
    @Autowired
    private UserActionService domainClassToTest04;   // Service

//    @Test
    public void testThemAll01() {
        domainClassToTest01 = ctx.getBean(IncidentActionStatusService.class);
        domainClassToTest01.findAll();
    }

//    @Test
    public void testThemAll02() {
        domainClassToTest02 = ctx.getBean(IncidentService.class);
        domainClassToTest02.findAll();
    }

//    @Test
    public void testThemAll03() {
        domainClassToTest03 = ctx.getBean(IncidentTypeService.class);
        domainClassToTest03.findAll();
    }

//    @Test
    public void testThemAll04() {
        domainClassToTest04 = ctx.getBean(UserActionService.class);
        domainClassToTest04.findAll();
    }
}
