package zm.hashcode.mshengu.app.util.predicates.incident;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.incident.Incident;
import zm.hashcode.mshengu.domain.incident.UserAction;

/**
 *
 * @author boniface
 */
public class IncidentStatusPredicate implements Predicate<Incident> {

    private String incidentStatus;
    
    public IncidentStatusPredicate(String incidentStatus){
        this.incidentStatus =  incidentStatus;
    }
    
    @Override
    public boolean apply(Incident incident) {
        if (incidentStatus.equalsIgnoreCase(getIncident(incident))) {
            return true;
        }
        return false;
    }

    private String getIncident(Incident incident) {
        if (incident != null) {
                return getUserActionStatus(incident.getLastUserAction());
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
