package zm.hashcode.mshengu.app.util.predicates.incident;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.incident.Incident;

/**
 *
 * @author boniface
 */
public class IncidentActivationStatusPredicate implements Predicate<Incident> {

    private boolean incidentStatus;
    
    public IncidentActivationStatusPredicate(boolean incidentStatus){
        this.incidentStatus =  incidentStatus;
    }
    
    @Override
    public boolean apply(Incident incident) {
        if (incidentStatus == getIncident(incident)) {
            return true;
        }
        return false;
    }

    private boolean getIncident(Incident incident) {
        if (incident != null) {
            return incident.isClosed();
        }
        return false;

    }
    
}
