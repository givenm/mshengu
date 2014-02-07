/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.incident.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.incident.IncidentActionStatus;
import zm.hashcode.mshengu.repository.incident.IncidentActionStatusRepository;
import zm.hashcode.mshengu.services.incident.IncidentActionStatusService;


/**
 *
 * @author Luckbliss
 */
@Service
public class IncidentActionStatusServiceImpl implements IncidentActionStatusService {

    @Autowired
    private IncidentActionStatusRepository repository;

    @Override
    public List<IncidentActionStatus> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(IncidentActionStatus incidentActionStatus) {

        repository.save(incidentActionStatus);

    }

    @Override
    public void merge(IncidentActionStatus incidentActionStatus) {
        if (incidentActionStatus.getId() != null) {
            repository.save(incidentActionStatus);
        }
    }

    @Override
    public IncidentActionStatus findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(IncidentActionStatus incidentActionStatus) {
        repository.delete(incidentActionStatus);
    }
    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "actionDate"));
    }
 
}
