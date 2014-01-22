/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.position.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.ui.position.Position;
import zm.hashcode.mshengu.domain.ui.position.PositionLifeCycle;
import zm.hashcode.mshengu.repository.ui.position.PositionRepository;
import zm.hashcode.mshengu.services.people.PersonService;
import zm.hashcode.mshengu.services.ui.position.PositionService;

/**
 *
 * @author lucky
 */
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository repository;

    @Override
    public List<Position> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByPositionTitle()));
    }

    private Sort sortByPositionTitle() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "positionTitle"));
    }

    @Override
    public void persist(Position position) {
        repository.save(position);
    }

    @Override
    public void merge(Position position) {
        if (position.getId() != null) {
            repository.save(position);
        }
    }

    @Override
    public Position findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Position position) {
        if (!checkIfPositionHasPerson(position)) {
            repository.delete(position);
        }
    }

    public boolean checkIfPositionHasPerson(Position position) {
        PersonService personService = PersonFacade.getPersonService();
        List<Person> persons = personService.findAll();
        for (Person person : persons) {
            if (person.equals(position.getCurrentOccupant())) {
                return true;
            }
        }
        return false;
    }
}
