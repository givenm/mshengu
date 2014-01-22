/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.util.Status;

/**
 *
 * @author boniface
 */
public interface StatusService {

    public List<Status> findAll();

    public void persist(Status status);

    public void merge(Status staus);

    public Status findById(String id);

    public void delete(Status status);

    public Status findByName(String name);

    public List<Status> findUnitOperationalStatus();

    public List<Status> findEmploymentStatus();

    public List<Status> findContractStatus();

    public List<Status> findActivationStatus();

    public List<Status> findIncidentStatus(); 
    public List<Status> findServiceRequestStatus();    
    public List<Status> findContactUSStatus() ;
    public List<Status> findIncominRFQStatus();
}
