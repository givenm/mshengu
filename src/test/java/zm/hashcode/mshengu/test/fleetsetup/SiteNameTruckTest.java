/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup;

import org.testng.annotations.Test;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author Tiwana Siyabonga
 */
public class SiteNameTruckTest extends AppTest {

    private TruckService truckService;

    //@Test
    public void setUpTitles() {

            truckService = ctx.getBean(TruckService.class);
            truckService.findBySiteName("Du Noon - Doornbach");


    }
}
