/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.serviceprovider;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;

/**
 *
 * @author Ferox
 */
public interface ServiceProviderProductRepository extends PagingAndSortingRepository<ServiceProviderProduct, String> {
}
