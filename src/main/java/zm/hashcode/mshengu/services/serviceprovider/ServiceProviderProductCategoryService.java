/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.serviceprovider;

import java.util.List;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProductCategory;

/**
 *
 * @author Ferox
 */
public interface ServiceProviderProductCategoryService {

    public List<ServiceProviderProductCategory> findAll();

    public void persist(ServiceProviderProductCategory productCategory);

    public void merge(ServiceProviderProductCategory productCategory);

    public ServiceProviderProductCategory findById(String id);

    public void delete(ServiceProviderProductCategory productCategory);
    
    public ServiceProviderProductCategory findByCategoryName(String categoryName);
}
