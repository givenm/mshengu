/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.serviceprovider;

import java.util.List;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderCategory;

/**
 *
 * @author Ferox
 */
public interface ServiceProviderCategoryService {

    public List<ServiceProviderCategory> findAll();

    public void persist(ServiceProviderCategory serviceProviderCategory);

    public void merge(ServiceProviderCategory providerCategory);

    public ServiceProviderCategory findById(String id);

    public void delete(ServiceProviderCategory serviceProviderCategory);
    
    public ServiceProviderCategory findByCategoryName(String categoryName);
}
