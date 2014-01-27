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
import zm.hashcode.mshengu.domain.incident.IncidentType;
import zm.hashcode.mshengu.repository.incident.IncidentTypeRepository;
import zm.hashcode.mshengu.services.incident.IncidentTypeService;

/**
 *
 * @author Luckbliss
 */
@Service
public class IncidentTypeServiceImpl implements IncidentTypeService {

    @Autowired
    private IncidentTypeRepository repository;

    @Override
    public List<IncidentType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(IncidentType incidentType) {

        repository.save(incidentType);

    }

    @Override
    public void merge(IncidentType incidentType) {
        if (incidentType.getId() != null) {
            repository.save(incidentType);
        }
    }

    @Override
    public IncidentType findById(String id) {        
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(IncidentType incidentType) {
        repository.delete(incidentType);
    }
    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public IncidentType findByName(String name) {
        return repository.findByName(name);
        }
    
    
}
