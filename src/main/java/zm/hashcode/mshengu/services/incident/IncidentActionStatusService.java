/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.incident;

import java.util.List;
import zm.hashcode.mshengu.domain.incident.IncidentActionStatus;

/**
 *
 * @author Ferox
 */
public interface IncidentActionStatusService {

    public List<IncidentActionStatus> findAll();

    public void persist(IncidentActionStatus incidentActionStatus);

    public void merge(IncidentActionStatus incidentActionStatus);

    public IncidentActionStatus findById(String id);

    public void delete(IncidentActionStatus incidentActionStatus);
}
