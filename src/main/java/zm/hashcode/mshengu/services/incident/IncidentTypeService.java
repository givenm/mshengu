/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.incident;

import java.util.List;
import zm.hashcode.mshengu.domain.incident.IncidentType;

/**
 *
 * @author Ferox
 */
public interface IncidentTypeService {

    public List<IncidentType> findAll();

    public void persist(IncidentType incidentType);

    public void merge(IncidentType incidentType);

    public IncidentType findById(String id);

    public void delete(IncidentType incidentType);
    public IncidentType findByName(String name);
}
