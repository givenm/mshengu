package zm.hashcode.mshengu.app.util.predicates.status;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.ui.util.Status;
import zm.hashcode.mshengu.domain.ui.util.StatusType;

/**
 *
 * @author boniface
 */
public class StatusPredicate implements Predicate<Status> {

    private String statusName;
    
    public StatusPredicate(String statusName){
        this.statusName =  statusName;
    }
    
    @Override
    public boolean apply(Status status) {
        if (statusName.equalsIgnoreCase(getStatusName(status))) {
            return true;
        }
        return false;
    }

    private String getStatusName(Status status) {
        if (status != null) {
            return getStatusType(status.getStatusType());
        }
        return null;

    }

    private String getStatusType(StatusType statusType) {
        if (statusType != null) {
            return statusType.getName();
        }
        return null;
    }
}
