/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.services.fieldservices.ServiceSiteUnits;
import zm.hashcode.mshengu.services.fieldservices.ServiceUnit;
import zm.hashcode.mshengu.services.products.SiteUnitService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author boniface
 */
public class ServiceUniTest extends AppTest {

    @Autowired
    private ServiceUnit serviceUnit;
    @Autowired
    private SiteUnitService siteUnitService;
    @Autowired
    private ServiceSiteUnits serviceSiteUnits;

//    @Test
    public void tesAddService() {
        siteUnitService = ctx.getBean(SiteUnitService.class);
        serviceUnit = ctx.getBean(ServiceUnit.class);

        SiteUnit unit = siteUnitService.findById("528af3ceb4c569a97484f91f");
        Map<String, Boolean> tasks = new HashMap<>();
        tasks.put("pumpOut", Boolean.TRUE);
        tasks.put("washBucket", Boolean.TRUE);
        tasks.put("suctionOut", Boolean.TRUE);
        tasks.put("scrubFloor", Boolean.FALSE);
        tasks.put("rechargeBacket", Boolean.TRUE);
        tasks.put("cleanPerimeter", Boolean.TRUE);
        UnitServiceResource resource = new UnitServiceResource();
        resource.setIncident("Nothing Wrong");
        resource.setLatitude("13123232132");
        resource.setLongitude("42353522525");
        resource.setServices(tasks);
        resource.setStatusMessage("WITHIN");
        resource.setTruckId("1221");
        resource.setUnitId(unit.getUnitId());
        resource.setUnitPosition("121");
        serviceUnit.serviceunit(resource, unit, resource.getStatusMessage());
    }

//    @Test
    public void testUnitRange() {
        siteUnitService = ctx.getBean(SiteUnitService.class);
        serviceUnit = ctx.getBean(ServiceUnit.class);
        serviceSiteUnits = ctx.getBean(ServiceSiteUnits.class);

        SiteUnit start = siteUnitService.findByUnitId("MTU-000376");

        SiteUnit finish = siteUnitService.findByUnitId("MTU-000380");

        System.out.println(" THE FIRST IS " + start.getUnitId());

        System.out.println(" THE FINISH IS " + finish.getUnitId());

        List<SiteUnit> unites = serviceSiteUnits.getSiteUnitsRange(start, finish);

        for (SiteUnit siteUnit : unites) {
            System.out.println(" THE UNIT IS " + siteUnit.getUnitId());

        }

        Map<String, Boolean> tasks = new HashMap<>();
        tasks.put("pumpOut", Boolean.TRUE);
        tasks.put("washBucket", Boolean.TRUE);
        tasks.put("suctionOut", Boolean.TRUE);
        tasks.put("scrubFloor", Boolean.FALSE);
        tasks.put("rechargeBacket", Boolean.TRUE);
        tasks.put("cleanPerimeter", Boolean.TRUE);


        UnitServiceResource resource = new UnitServiceResource();
        resource.setIncident("Nothing Wrong");
        resource.setLatitude("13123232132");
        resource.setLongitude("42353522525");
        resource.setServices(tasks);
        resource.setStatusMessage("WITHIN");
        resource.setTruckId("1221");
        resource.setUnitPosition("121");

        serviceSiteUnits.serviceBulkUnits(unites, resource, resource.getStatusMessage());


    }
}
