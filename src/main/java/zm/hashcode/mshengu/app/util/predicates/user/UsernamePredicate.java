/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.predicates.user;

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.ui.position.Position;
import zm.hashcode.mshengu.domain.ui.util.Status;

/**
 *
 * @author boniface
 */
public class UsernamePredicate implements Predicate<Position> {
    private final String STATUS="APPROVED";
    @Override
    public boolean apply(Position position) {
        if (STATUS.equalsIgnoreCase(getStatus(position.getPositionStatus()))) {
            return true;
        }
        return false;
    }

    private String getStatus(Status positionStatus) {
        if(positionStatus!=null) {
            return null;// positionStatus.getStatusValues();
        }
        return null;
    }
}
