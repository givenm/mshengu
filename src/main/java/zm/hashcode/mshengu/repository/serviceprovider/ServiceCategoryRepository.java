/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.serviceprovider;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceCategory;

/**
 *
 * @author Ferox
 */
public interface ServiceCategoryRepository extends PagingAndSortingRepository<ServiceCategory, String>{
    public ServiceCategory findByName(String name);
    
}
