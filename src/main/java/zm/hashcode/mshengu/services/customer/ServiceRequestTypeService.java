/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer;

import java.util.List;
import zm.hashcode.mshengu.domain.customer.ServiceRequestType;

/**
 *
 * @author Ferox
 */
public interface ServiceRequestTypeService {

    public List<ServiceRequestType> findAll();

    public void persist(ServiceRequestType serviceRequestType);

    public void merge(ServiceRequestType serviceRequestType);

    public ServiceRequestType findById(String id);

    public void delete(ServiceRequestType serviceRequestType);
    public ServiceRequestType findByName(String name);
}
