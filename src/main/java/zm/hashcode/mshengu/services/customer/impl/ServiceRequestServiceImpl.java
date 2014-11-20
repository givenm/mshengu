/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer.impl;

import com.google.common.collect.Collections2;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.predicates.customer.ServiceRequestActivationStatusPredicate;
import zm.hashcode.mshengu.app.util.predicates.customer.ServiceRequestStatusPredicate;
import zm.hashcode.mshengu.domain.customer.ServiceRequest;
import zm.hashcode.mshengu.repository.customer.ServiceRequestRepository;
import zm.hashcode.mshengu.services.customer.ServiceRequestService;

/**
 *
 * @author Ferox
 */
@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    @Autowired
    private ServiceRequestRepository repository;

    @Override
    public List<ServiceRequest> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByDate()));
    }

    @Override
    public void persist(ServiceRequest serviceRequest) {

        repository.save(serviceRequest);

    }

    @Override
    public void merge(ServiceRequest serviceRequest) {
        if (serviceRequest.getId() != null) {
            repository.save(serviceRequest);
        }
    }

    @Override
    public ServiceRequest findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(ServiceRequest serviceRequest) {
        repository.delete(serviceRequest);
    }

    private Sort sortByDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "dateofAction"));
    }

    @Override
    public ServiceRequest findByRefNumber(String refNumber) {
        return repository.findByRefNumber(refNumber);
    }

    @Override
    public List<ServiceRequest> findAllClosed() {
        List<ServiceRequest> serviceRequestList = ImmutableList.copyOf(repository.findAll(sortByDate()));
        Collection<ServiceRequest> serviceRequestsFilteredList = Collections2.filter(serviceRequestList, new ServiceRequestActivationStatusPredicate(true));
        return ImmutableList.copyOf(serviceRequestsFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<ServiceRequest> findAllOpen() {
        List<ServiceRequest> serviceRequestList = ImmutableList.copyOf(repository.findAll(sortByDate()));
        Collection<ServiceRequest> serviceRequestsFilteredList = Collections2.filter(serviceRequestList, new ServiceRequestActivationStatusPredicate(false));
        return ImmutableList.copyOf(serviceRequestsFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<ServiceRequest> findByStatus(String statusName) {
        List<ServiceRequest> serviceRequestList = ImmutableList.copyOf(repository.findAll(sortByDate()));
        Collection<ServiceRequest> serviceRequestsFilteredList = Collections2.filter(serviceRequestList, new ServiceRequestStatusPredicate(statusName));
        return ImmutableList.copyOf(serviceRequestsFilteredList);
//        return ImmutableList.copyOf();
    }


}
