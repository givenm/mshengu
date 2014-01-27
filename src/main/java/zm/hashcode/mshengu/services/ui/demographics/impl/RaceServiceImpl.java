/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.demographics.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.demographics.Race;
import zm.hashcode.mshengu.repository.ui.demographics.RaceRepository;
import zm.hashcode.mshengu.services.ui.demographics.RaceService;

/**
 *
 * @author lucky
 */
@Service
public class RaceServiceImpl implements RaceService{

    @Autowired
    private RaceRepository repository;

    @Override
    public List<Race> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByRaceName()));
    }

    @Override
    public void persist(Race race) {

        repository.save(race);

    }

    @Override
    public void merge(Race race) {
        if (race.getId() != null) {
            repository.save(race);
        }
    }

    @Override
    public Race findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Race race) {
        repository.delete(race);
    }
    private Sort sortByRaceName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "raceName"));
    }
}
