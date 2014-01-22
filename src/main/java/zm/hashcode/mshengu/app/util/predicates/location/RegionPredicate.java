package zm.hashcode.mshengu.app.util.predicates.location;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.ui.location.Location;
import zm.hashcode.mshengu.domain.ui.location.LocationType;

/**
 *
 * @author boniface
 */
public class RegionPredicate implements Predicate<Location> {

    @Override
    public boolean apply(Location location) {
        if ("REGION".equalsIgnoreCase(getLocationName(location))) {
            return true;
        }
        return false;
    }

    private String getLocationName(Location location) {
        if (location != null) {
            return getLocationType(location.getLocationType());
        }
        return null;

    }

    private String getLocationType(LocationType locationType) {
        if (locationType != null) {
            return locationType.getName();
        }
        return null;
    }
}
