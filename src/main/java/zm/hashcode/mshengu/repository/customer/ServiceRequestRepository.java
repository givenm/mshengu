/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.customer;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.customer.ServiceRequest;

/**
 *
 * @author Ferox
 */
public interface ServiceRequestRepository extends PagingAndSortingRepository<ServiceRequest, String> {
   public ServiceRequest findByRefNumber(String refNumber);
}
