/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.client.rest.api.resources.UnitServiceResource;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;
import zm.hashcode.mshengu.domain.products.UnitType;
import zm.hashcode.mshengu.services.products.SiteUnitService;
import zm.hashcode.mshengu.services.products.UnitLocationLifeCycleService;
import zm.hashcode.mshengu.services.products.UnitTypeService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author Luckbliss
 */
public class CompareCoordinatesTest extends AppTest{
    
    private UnitLocationLifeCycleService cycleService;
    private UnitTypeService typeService;
    private SiteUnitService service;
    
//    @Test
    public void compare(){        
        cycleService = ctx.getBean(UnitLocationLifeCycleService.class);
        UnitLocationLifeCycle cycle = new UnitLocationLifeCycle.Builder(new Date()).latitude("43.9322").longitude("19.6403").build();
        cycleService.persist(cycle);   
                     
        List<UnitLocationLifeCycle> list = new ArrayList<>();
        list.add(cycle);
        typeService = ctx.getBean(UnitTypeService.class);
        UnitType type = new UnitType.Builder("Type1").build();
        typeService.persist(type);
        
        service = ctx.getBean(SiteUnitService.class);  
        SiteUnit siteUnit = new SiteUnit.Builder(type).unitLocationLifeCycle(list).build();
        service.persist(siteUnit);
        SiteUnitService siteUnitService = ctx.getBean(SiteUnitService.class);
        UnitServiceResource resource = new UnitServiceResource();
        resource.setUnitId(siteUnit.getId());
        resource.setLatitude("43.9385");
        resource.setLongitude("19.6409");
        String compare = siteUnitService.checkCoordinates(resource);
        System.out.println("\n\n" + compare + "\n\n");        
    }
}
