/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.serviceprovider;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderCategory;

/**
 *
 * @author Ferox
 */
public interface ServiceProviderCategoryRepository extends PagingAndSortingRepository<ServiceProviderCategory , String>{    
    public ServiceProviderCategory findByCategoryName(String categoryName);
}
