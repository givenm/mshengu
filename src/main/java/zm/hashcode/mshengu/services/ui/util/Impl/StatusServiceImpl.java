/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util.Impl;

import com.google.common.collect.Collections2;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.predicates.status.StatusPredicate;
import zm.hashcode.mshengu.domain.ui.util.Status;
import zm.hashcode.mshengu.repository.ui.util.StatusRepository;
import zm.hashcode.mshengu.services.ui.util.StatusService;

/**
 *
 * @author boniface
 */
@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository repository;
    List<Status> statusList;

    @Override
    public List<Status> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(Status status) {
        repository.save(status);
    }

    @Override
    public void merge(Status status) {
        if (status.getId() != null) {
            repository.save(status);
        }
    }

    @Override
    public Status findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Status status) {
        repository.delete(status);
    }

    @Override
    public Status findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Status> findUnitOperationalStatus() {
        statusList = (List<Status>) repository.findAll(sortByName());
        Collection<Status> statussFilteredList = Collections2.filter(statusList, new StatusPredicate("OPERATIONAL_STATUS"));
        return ImmutableList.copyOf(statussFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Status> findEmploymentStatus() {
        statusList = (List<Status>) repository.findAll(sortByName());
        Collection<Status> statussFilteredList = Collections2.filter(statusList, new StatusPredicate("EMPLOYMENT_STATUS"));
        return ImmutableList.copyOf(statussFilteredList);
//        return ImmutableList.copyOf();
    }
    
        @Override
    public List<Status> findContractStatus() {
        statusList = (List<Status>) repository.findAll(sortByName());
        Collection<Status> statussFilteredList = Collections2.filter(statusList, new StatusPredicate("CONTRACT_STATUS"));
        return ImmutableList.copyOf(statussFilteredList);
//        return ImmutableList.copyOf();
    }
        
          @Override
    public List<Status> findActivationStatus() {
        statusList = (List<Status>) repository.findAll(sortByName());
        Collection<Status> statussFilteredList = Collections2.filter(statusList, new StatusPredicate("ACTIVATION_STATUS"));
        return ImmutableList.copyOf(statussFilteredList);
//        return ImmutableList.copyOf();
    }
          
          
        
                       @Override
    public List<Status> findIncidentStatus() {
        statusList = (List<Status>) repository.findAll(sortByName());
        Collection<Status> statussFilteredList = Collections2.filter(statusList, new StatusPredicate("INCIDENT_STATUS"));
        return ImmutableList.copyOf(statussFilteredList);
//        return ImmutableList.copyOf();
    }
            @Override
    public List<Status> findServiceRequestStatus() {
        statusList = (List<Status>) repository.findAll(sortByName());
        Collection<Status> statussFilteredList = Collections2.filter(statusList, new StatusPredicate("SERVICE_REQUEST_STATUS"));
        return ImmutableList.copyOf(statussFilteredList);
//        return ImmutableList.copyOf();
    }
            
            
                @Override
    public List<Status> findContactUSStatus() {
        statusList = (List<Status>) repository.findAll(sortByName());
        Collection<Status> statussFilteredList = Collections2.filter(statusList, new StatusPredicate("CONTACT_US_STATUS"));
        return ImmutableList.copyOf(statussFilteredList);
//        return ImmutableList.copyOf();
    }
          
          
          
            @Override
    public List<Status> findIncominRFQStatus() {
        statusList = (List<Status>) repository.findAll(sortByName());
        Collection<Status> statussFilteredList = Collections2.filter(statusList, new StatusPredicate("RFQ_IN_STATUS"));
        return ImmutableList.copyOf(statussFilteredList);
//        return ImmutableList.copyOf();
    }
}
