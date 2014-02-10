/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer;

import java.util.List;
import zm.hashcode.mshengu.domain.customer.ServiceRequest;

/**
 *
 * @author Ferox
 */
public interface ServiceRequestService {

    public List<ServiceRequest> findAll();

    public void persist(ServiceRequest serviceRequest);

    public void merge(ServiceRequest serviceRequest);

    public ServiceRequest findById(String id);

    public void delete(ServiceRequest serviceRequest);    
    
    public List<ServiceRequest> findAllClosed();
    
    public List<ServiceRequest> findAllOpen();
        
    public List<ServiceRequest> findByStatus(String statusName);    
    
    public ServiceRequest findByRefNumber(String refNumber);

}
