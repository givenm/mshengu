/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.serviceprovider;

import java.util.List;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceCategory;

/**
 *
 * @author Ferox
 */
public interface ServiceCategoryService {

    public List<ServiceCategory> findAll();

    public void persist(ServiceCategory serviceCategory);

    public void merge(ServiceCategory serviceCategory);

    public ServiceCategory findById(String id);

    public void delete(ServiceCategory serviceCategory);
    
    public ServiceCategory findByName(String name);
}
