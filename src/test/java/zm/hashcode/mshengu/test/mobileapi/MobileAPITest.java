/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.mobileapi;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.client.rest.api.resources.UnitDeliveryResource;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;
import zm.hashcode.mshengu.services.products.MobileAppService;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author boniface
 */
public class MobileAPITest extends AppTest {

    @Autowired
    private MobileAppService mobileAppService;
    @Autowired
    private SiteService siteService;
    private String latitude = "33.9317";
    private String longitude = "18.5128";
    private String siteId = "RR Section";
    private String unitId = "MTU-000004"; //MTU-000279

//    @Test
    public void siteUnitDeploymenTest() {
        mobileAppService = ctx.getBean(MobileAppService.class);
        UnitDeliveryResource unitDelivery = new UnitDeliveryResource();
        unitDelivery.setLatitude(latitude);
        unitDelivery.setLongitude(longitude);
        unitDelivery.setSiteId(siteId);
        unitDelivery.setUnitId(unitId);

        mobileAppService.deployUnitToSite(unitDelivery);

    }

//    @Test
    public void siteUnitDeploymenlocationTest() {
        String siteName = "Du Noon - Doornbach";
        siteService = ctx.getBean(SiteService.class);

        List<SiteUnit> siteUnitsList = siteService.findAllSiteUnit(siteName);

        System.out.println(siteName + " has " + siteUnitsList.size() + " toilets Units");
        for (SiteUnit unit : siteUnitsList) {
            try {
                if (unit.getUnitLocationLifeCycle().size() > 0) {
                    System.out.println("Unit ID - " + unit.getUnitId());
                    UnitLocationLifeCycle location = unit.getLatestDeployment();
                    if (location != null) {
                        System.out.println("Last Location Name - " + location.getSiteName());
                    } else {
                        System.out.println("Last Location not Found");

                    }
//                    unitId = unit.getUnitId();
//                    latitude = location.getLatitude();
//                    longidude = location.getLongitude();
//                    GoogleMapMarker marker = new GoogleMapMarker(unit.getUnitId(), new LatLon(Double.parseDouble(location.getLatitude()), Double.parseDouble(location.getLongitude())), false);
//                    updateGoogleMap.addMarker(marker);
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Catch NumberFormatException");
                System.out.println("Unit ID " + unitId);
                System.out.println("latitude " + latitude);
                System.out.println("longidude " + longitude);
                System.out.println("===========================");

            }


        }

    }
}
