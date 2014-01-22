/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.mobileapi;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.services.fieldservices.ServiceSiteUnits;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author boniface
 */
public class MobileFieldServiceTest extends AppTest {

    @Autowired
    private ServiceSiteUnits serviceSiteUnits;

//    @Test
    public void getTruckUnitsTests() {

        serviceSiteUnits = ctx.getBean(ServiceSiteUnits.class);

        System.out.println(" THE START TIME IS ");
        long startTime = System.currentTimeMillis();
        System.out.println(" THE START TIME IS " + startTime);
        List<SiteUnit> units = serviceSiteUnits.getUnitsFromSite("526a6d10e4b09f304093d6bf");


        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println(" DIFFERENCE " + estimatedTime);

        System.out.println(" THE SIZE of the Truck is " + units.size());
    }

    
}
