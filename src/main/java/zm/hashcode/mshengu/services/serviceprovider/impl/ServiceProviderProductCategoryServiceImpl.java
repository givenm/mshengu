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
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProductCategory;
import zm.hashcode.mshengu.repository.serviceprovider.ServiceProviderProductCategoryRepository;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderProductCategoryService;

/**
 *
 * @author Luckbliss
 */
@Service
public class ServiceProviderProductCategoryServiceImpl implements ServiceProviderProductCategoryService {

    @Autowired
    private ServiceProviderProductCategoryRepository repository;

    @Override//
    public List<ServiceProviderProductCategory> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(ServiceProviderProductCategory serviceProviderProductCategory) {

        repository.save(serviceProviderProductCategory);

    }

    @Override
    public void merge(ServiceProviderProductCategory serviceProviderProductCategory) {
        if (serviceProviderProductCategory.getId() != null) {
            repository.save(serviceProviderProductCategory);
        }
    }

    @Override
    public ServiceProviderProductCategory findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(ServiceProviderProductCategory serviceProviderProductCategory) {
        repository.delete(serviceProviderProductCategory);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "categoryName"));
    }
    
    @Override
    public ServiceProviderProductCategory findByCategoryName(String categoryName){
        return repository.findByCategoryName(categoryName);
    }
}
