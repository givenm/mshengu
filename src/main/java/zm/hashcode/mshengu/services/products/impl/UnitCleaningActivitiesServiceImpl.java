/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.products.UnitCleaningActivities;
import zm.hashcode.mshengu.repository.products.UnitCleaningActivitiesRepository;
import zm.hashcode.mshengu.services.products.UnitCleaningActivitiesService;

/**
 *
 * @author Luckbliss
 */
@Service
public class UnitCleaningActivitiesServiceImpl implements UnitCleaningActivitiesService {

    @Autowired
    private UnitCleaningActivitiesRepository repository;

    @Override
    public List<UnitCleaningActivities> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(UnitCleaningActivities unitCleaningActivities) {

        repository.save(unitCleaningActivities);

    }

    @Override
    public void merge(UnitCleaningActivities unitCleaningActivities) {
        if (unitCleaningActivities.getId() != null) {
            repository.save(unitCleaningActivities);
        }
    }

    @Override
    public UnitCleaningActivities findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(UnitCleaningActivities unitCleaningActivities) {
        repository.delete(unitCleaningActivities);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }
}
