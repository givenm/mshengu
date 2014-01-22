/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.serviceprovider;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProductCategory;

/**
 *
 * @author Ferox
 */
public interface ServiceProviderProductCategoryRepository extends PagingAndSortingRepository<ServiceProviderProductCategory, String> {    
    public ServiceProviderProductCategory findByCategoryName(String categoryName);
}
