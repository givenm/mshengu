package zm.hashcode.mshengu.app.util.predicates.incomingrfq;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.external.IncomingRFQ;

/**
 *
 * @author boniface
 */
public class IncomingRFQActivationStatusPredicate implements Predicate<IncomingRFQ> {

    private boolean incomingRFQStatus;
    
    public IncomingRFQActivationStatusPredicate(boolean incomingRFQStatus){
        this.incomingRFQStatus =  incomingRFQStatus;
    }
    
    @Override
    public boolean apply(IncomingRFQ incomingRFQ) {
        if (incomingRFQStatus == getIncomingRFQ(incomingRFQ)) {
            return true;
        }
        return false;
    }

    private boolean getIncomingRFQ(IncomingRFQ incomingRFQ) {
        if (incomingRFQ != null) {
            return incomingRFQ.isClosed();
        }
        return false;

    }
    
}
