/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.incident.impl;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Ordering;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.predicates.incident.IncidentActivationStatusPredicate;
import zm.hashcode.mshengu.app.util.predicates.incident.IncidentStatusPredicate;
import zm.hashcode.mshengu.domain.incident.Incident;
import zm.hashcode.mshengu.domain.incident.UserAction;
import zm.hashcode.mshengu.repository.incident.IncidentRepository;
import zm.hashcode.mshengu.services.incident.IncidentService;

/**
 *
 * @author Ferox
 */
@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepository repository;

    @Override
    public List<Incident> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByDate()));
    }

    @Override
    public void persist(Incident incident) {

        repository.save(incident);

    }

    @Override
    public void merge(Incident incident) {
        if (incident.getId() != null) {
            repository.save(incident);
        }
    }

    @Override
    public Incident findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Incident incident) {
        repository.delete(incident);
    }

    private Sort sortByDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "actionDate"));
    }

    @Override
    public Incident findByRefNumber(String refNumber) {
        return repository.findByRefNumber(refNumber);
    }

    @Override
    public UserAction getLatestIncidentAction(String id) {
        Incident incident = findById(id);
        Set<UserAction> incidentActionList = incident.getUserAction();

        return getLatestIncidentCation(incidentActionList);
    }

    private UserAction getLatestIncidentCation(Set<UserAction> incidentActionList) {

        Ordering<UserAction> ordering;
        ordering = Ordering.natural().nullsLast().onResultOf(new Function<UserAction, Long>() {
            @Override
            public Long apply(UserAction incidentAction) {
                return incidentAction.getActionDate().getTime();
            }
        });

        List<UserAction> sortedList = ordering.immutableSortedCopy(incidentActionList).reverse();
        if (sortedList.isEmpty()) {
            UserAction incidentActionNull = null;
            return incidentActionNull;
        } else {
            return sortedList.get(0);
        }
    }

    @Override
    public List<Incident> findAllClosed() {
        List<Incident> incidentList = (List<Incident>) repository.findAll(sortByDate());
        Collection<Incident> incidentsFilteredList = Collections2.filter(incidentList, new IncidentActivationStatusPredicate(true));
        return ImmutableList.copyOf(incidentsFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Incident> findAllOpen() {
        List<Incident> incidentList = (List<Incident>) repository.findAll(sortByDate());
        Collection<Incident> incidentsFilteredList = Collections2.filter(incidentList, new IncidentActivationStatusPredicate(false));
        return ImmutableList.copyOf(incidentsFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Incident> findByStatus(String statusName) {
        List<Incident> incidentList = (List<Incident>) repository.findAll(sortByDate());
        Collection<Incident> incidentsFilteredList = Collections2.filter(incidentList, new IncidentStatusPredicate(statusName));
        return ImmutableList.copyOf(incidentsFilteredList);
//        return ImmutableList.copyOf();
    }
}
