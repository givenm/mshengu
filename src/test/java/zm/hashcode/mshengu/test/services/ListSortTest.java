/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;
import zm.hashcode.mshengu.services.products.MobileAppService;
import zm.hashcode.mshengu.services.products.SiteUnitService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author boniface
 */
public class ListSortTest extends AppTest {

    @Autowired
    private MobileAppService mobileAppService;
    private SiteUnitService siteUnitService;
    private String unitId = "MTU-000054";//MTU-000001"; //MTU-000279

//    @Test
    public void siteUnitDeploymenTest() {
        siteUnitService = ctx.getBean(SiteUnitService.class);
        SiteUnit siteUnit = siteUnitService.findByUnitId(unitId);

        System.out.println("Unordered List");
        int index = 0;
        for (UnitLocationLifeCycle unitLocationLifeCycle : siteUnit.getUnitLocationLifeCycle()) {
            index++;
            System.out.println(index + "* Date of Action is : " + unitLocationLifeCycle.getDateofAction());
        }

        List<UnitLocationLifeCycle> sortedUnitLocationLifeCycleList = new ArrayList<>();
        sortedUnitLocationLifeCycleList.addAll(siteUnit.getUnitLocationLifeCycle());
        Collections.sort(sortedUnitLocationLifeCycleList);

        System.out.println("================================================================");
        System.out.println("Ordered List");
        index = 0;
        for (UnitLocationLifeCycle unitLocationLifeCycle : sortedUnitLocationLifeCycleList) {
            index++;
            System.out.println(index + "* Date of Action is : " + unitLocationLifeCycle.getDateofAction());
        }

        
        
        
        UnitLocationLifeCycle latestUnitLocationLifeCycle = siteUnitService.getUnitCurrentLocation(siteUnit.getId());
        UnitLocationLifeCycle latestUnitLocationLifeCycleSorted = sortedUnitLocationLifeCycleList.get(0);
            System.out.println("latest UnitLocationLifeCicle id " + latestUnitLocationLifeCycle.getId());
            System.out.println("latest UnitLocationLifeCicle Site Name " + latestUnitLocationLifeCycle.getSiteName());
            System.out.println("latest UnitLocationLifeCicle date of action " + latestUnitLocationLifeCycle.getDateofAction());
            System.out.println("latest UnitLocationLifeCicle id (sorted) " + latestUnitLocationLifeCycleSorted.getId());
            System.out.println("latest UnitLocationLifeCicle Site Name (sorted) " + latestUnitLocationLifeCycleSorted.getSiteName());
            System.out.println("latest UnitLocationLifeCicle date of action (sorted) " + latestUnitLocationLifeCycleSorted.getDateofAction());
        if (latestUnitLocationLifeCycle.getId().equalsIgnoreCase(latestUnitLocationLifeCycleSorted.getId())) {
            System.out.println("List was sorted correctly");
        }else{
            System.out.println("List was NOT sorted correctly");
        }

    }
}
