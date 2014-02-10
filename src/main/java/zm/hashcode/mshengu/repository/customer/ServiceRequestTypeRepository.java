/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.customer;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.customer.ServiceRequestType;

/**
 *
 * @author Ferox
 */
public interface ServiceRequestTypeRepository extends PagingAndSortingRepository<ServiceRequestType, String> {
     public ServiceRequestType findByName(String name);

   
}
