/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.serviceprovider.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;
import zm.hashcode.mshengu.repository.serviceprovider.ServiceProviderProductRepository;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderProductService;

/**
 *
 * @author Ferox
 */
@Service
public class ServiceProviderProductServiceImpl implements ServiceProviderProductService {

    @Autowired
    private ServiceProviderProductRepository repository;

    @Override
    public List<ServiceProviderProduct> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(ServiceProviderProduct serviceProviderProduct) {

        repository.save(serviceProviderProduct);

    }

    @Override
    public void merge(ServiceProviderProduct serviceProviderProduct) {
        if (serviceProviderProduct.getId() != null) {
            repository.save(serviceProviderProduct);
        }
    }

    @Override
    public ServiceProviderProduct findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(ServiceProviderProduct serviceProviderProduct) {
        repository.delete(serviceProviderProduct);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }
}
