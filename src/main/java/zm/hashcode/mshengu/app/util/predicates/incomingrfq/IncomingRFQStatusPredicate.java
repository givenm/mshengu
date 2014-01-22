package zm.hashcode.mshengu.app.util.predicates.incomingrfq;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.external.IncomingRFQ;
import zm.hashcode.mshengu.domain.incident.UserAction;

/**
 *
 * @author boniface
 */
public class IncomingRFQStatusPredicate implements Predicate<IncomingRFQ> {

    private String incomingRFQStatus;
    
    public IncomingRFQStatusPredicate(String incomingRFQStatus){
        this.incomingRFQStatus =  incomingRFQStatus;
    }
    
    @Override
    public boolean apply(IncomingRFQ incomingRFQ) {
        if (incomingRFQStatus.equalsIgnoreCase(getIncomingRFQ(incomingRFQ))) {
            return true;
        }
        return false;
    }

    private String getIncomingRFQ(IncomingRFQ incomingRFQ) {
        if (incomingRFQ != null) {
                  return getUserActionStatus(incomingRFQ.getLastUserAction());
        }
        return null;

    }
    
        private String getUserActionStatus(UserAction  userAction) {
        if (userAction != null) {
            return userAction.getUserActionStatusName();
        }
        return null;

    }
    

    
}
