/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.serviceprovider.impl;

import com.google.common.base.Supplier;
import com.google.common.collect.Collections2;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.predicates.serviceprovivers.PreferedVendorPredicate;
import zm.hashcode.mshengu.app.util.predicates.serviceprovivers.SubcontractorsPredicate;
import zm.hashcode.mshengu.app.util.predicates.serviceprovivers.SuppliersPredicate;
import zm.hashcode.mshengu.app.util.predicates.serviceprovivers.ServiceProviderPredicate;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.repository.serviceprovider.ServiceProviderRepository;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderService;

/**
 *
 * @author Ferox
 */
@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {

    @Autowired
    private ServiceProviderRepository repository;
    private List<ServiceProvider> vendorsList;

    @Override
    public List<ServiceProvider> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(ServiceProvider serviceProvider) {

        repository.save(serviceProvider);

    }

    @Override
    public void merge(ServiceProvider serviceProvider) {
        if (serviceProvider.getId() != null) {
            repository.save(serviceProvider);
        }
    }

    @Override
    public ServiceProvider findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(ServiceProvider serviceProvider) {
        repository.delete(serviceProvider);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public List<ServiceProvider> findAllSubcontractors() {
        vendorsList = (List<ServiceProvider>) repository.findAll();
        Collection<ServiceProvider> personsFilteredList = Collections2.filter(vendorsList, new SubcontractorsPredicate());
        return ImmutableList.copyOf(personsFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<ServiceProvider> findAllSuppliers() {
        vendorsList = (List<ServiceProvider>) repository.findAll();
        Collection<ServiceProvider> personsFilteredList = Collections2.filter(vendorsList, new SuppliersPredicate());
        return ImmutableList.copyOf(personsFilteredList);
//        return ImmutableList.copyOf();
    }
    

    @Override
    public List<ServiceProvider> findAllServiceProvider() {
        vendorsList = (List<ServiceProvider>) repository.findAll();
        Collection<ServiceProvider> personsFilteredList = Collections2.filter(vendorsList, new ServiceProviderPredicate());
        return ImmutableList.copyOf(personsFilteredList);
        
    }

    @Override
    public List<ServiceProvider> findAllPreferedVendors() {
        vendorsList = (List<ServiceProvider>) repository.findAll();
        Collection<ServiceProvider> personsFilteredList = Collections2.filter(vendorsList, new PreferedVendorPredicate());
        return ImmutableList.copyOf(personsFilteredList); //To change body of generated methods, choose Tools | Templates.
    }
}
