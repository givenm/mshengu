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
import zm.hashcode.mshengu.domain.serviceprovider.ServiceCategory;
import zm.hashcode.mshengu.repository.serviceprovider.ServiceCategoryRepository;
import zm.hashcode.mshengu.services.serviceprovider.ServiceCategoryService;

/**
 *
 * @author Ferox
 */
@Service
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    @Autowired
    private ServiceCategoryRepository repository;

    @Override
    public List<ServiceCategory> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(ServiceCategory serviceCategory) {

        repository.save(serviceCategory);

    }

    @Override
    public void merge(ServiceCategory serviceCategory) {
        if (serviceCategory.getId() != null) {
            repository.save(serviceCategory);
        }
    }

    @Override
    public ServiceCategory findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(ServiceCategory serviceCategory) {
        repository.delete(serviceCategory);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }
    
    @Override 
    public ServiceCategory findByName(String name){
        return repository.findByName(name);
    }
}
