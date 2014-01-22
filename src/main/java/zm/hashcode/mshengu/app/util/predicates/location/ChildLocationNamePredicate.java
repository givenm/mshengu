package zm.hashcode.mshengu.app.util.predicates.location;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.ui.location.Location;

/**
 *
 * @author boniface
 */
public class ChildLocationNamePredicate implements Predicate<Location> {

    private String parentId;
    
    public ChildLocationNamePredicate(String parentId){
        this.parentId =  parentId;
    }
    
    @Override
    public boolean apply(Location location) {
        if (parentId.equalsIgnoreCase(getLocationName(location))) {
            return true;
        }
        return false;
    }

    private String getLocationName(Location location) {
        if (location != null) {
            return getLocationParent(location.getParent());
        }
        return null;

    }

    private String getLocationParent(Location location) {
        if (location != null) {
            return location.getName();
        }
        return null;
    }
}
