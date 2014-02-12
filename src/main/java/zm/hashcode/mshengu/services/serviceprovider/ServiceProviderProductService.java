/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.serviceprovider;

import java.util.List;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;

/**
 *
 * @author Ferox
 */
public interface ServiceProviderProductService {

    public List<ServiceProviderProduct> findAll();

    public void persist(ServiceProviderProduct serviceProviderProduct);

    public void merge(ServiceProviderProduct serviceProviderProduct);

    public ServiceProviderProduct findById(String id);

    public void delete(ServiceProviderProduct serviceProviderProduct);
}
