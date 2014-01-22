/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.serviceprovider;

import java.util.List;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author Ferox
 */
public interface ServiceProviderService {

    public List<ServiceProvider> findAll();

    public void persist(ServiceProvider serviceProvider);

    public void merge(ServiceProvider serviceProvider);

    public ServiceProvider findById(String id);

    public void delete(ServiceProvider serviceProvider);

    public List<ServiceProvider> findAllServiceProvider();

    public List<ServiceProvider> findAllPreferedVendors();

    public List<ServiceProvider> findAllSubcontractors();

    public List<ServiceProvider> findAllSuppliers();
}
