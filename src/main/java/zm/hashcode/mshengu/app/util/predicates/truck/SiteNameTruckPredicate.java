/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.predicates.truck;

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.products.Site;

/**
 *
 * @author boniface
 */
public class SiteNameTruckPredicate implements Predicate<Truck> {

//    private final List<Site> sites;
    private final String siteName;

    public SiteNameTruckPredicate(String siteName) {
        this.siteName = siteName;
    }

    @Override
    public boolean apply(Truck truck) {
        if (truck != null) {
            return getRoutes(truck);
        } else {
            return false;
        }

    }

    private boolean getRoutes(Truck truck) {
        boolean siteFound = false;
//        System.out.println("===============================\n START " + truck.getVehicleNumber() + "\n===============================");
        for (Site site : truck.getRoutes()) {
            if (site.getName().equalsIgnoreCase(siteName)) {
//                System.out.println(" THE SITE IS  " + site.getName() + " UNIT HAS A SITE " + truck.getVehicleNumber());
                siteFound = true;
                if (siteFound) {
                    break;
                }
            }
        }

//        System.out.println("===============================\n END " + truck.getVehicleNumber() + "\n===============================");
        return siteFound;
    }
}
