/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.incident;

import java.util.List;
import zm.hashcode.mshengu.domain.incident.Incident;
import zm.hashcode.mshengu.domain.incident.UserAction;

/**
 *
 * @author Ferox
 */
public interface IncidentService {

    public List<Incident> findAll();

    public void persist(Incident incident);

    public void merge(Incident incident);

    public Incident findById(String id);

    public void delete(Incident incident);
    
    public UserAction getLatestIncidentAction(String id);
    
    
    public List<Incident> findAllClosed();
    
    public List<Incident> findAllOpen();
        
    public List<Incident> findByStatus(String statusName);    
    
    public Incident findByRefNumber(String refNumber);

}
