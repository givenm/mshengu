/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.serviceprovider;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author Ferox
 */
public interface ServiceProviderRepository extends PagingAndSortingRepository<ServiceProvider , String>{
    
}
