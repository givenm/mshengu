/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.customer.ServiceRequestType;
import zm.hashcode.mshengu.repository.customer.ServiceRequestTypeRepository;
import zm.hashcode.mshengu.services.customer.ServiceRequestTypeService;

/**
 *
 * @author Luckbliss
 */
@Service
public class ServiceRequestTypeServiceImpl implements ServiceRequestTypeService {

    @Autowired
    private ServiceRequestTypeRepository repository;

    @Override
    public List<ServiceRequestType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(ServiceRequestType serviceRequestType) {

        repository.save(serviceRequestType);

    }

    @Override
    public void merge(ServiceRequestType serviceRequestType) {
        if (serviceRequestType.getId() != null) {
            repository.save(serviceRequestType);
        }
    }

    @Override
    public ServiceRequestType findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(ServiceRequestType serviceRequestType) {
        repository.delete(serviceRequestType);
    }
    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public ServiceRequestType findByName(String name) {
        return repository.findByName(name);
        }
    
    
}
