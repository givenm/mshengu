/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.people.impl;

import com.google.common.collect.Collections2;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.Collection;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.predicates.person.PersonDriverPredicate;
import zm.hashcode.mshengu.app.util.predicates.person.PersonDrivesCompanyCarPredicate;
import zm.hashcode.mshengu.app.util.predicates.person.PersonRequestorPredicates;
import zm.hashcode.mshengu.app.util.predicates.person.PersonStaffPredicates;
import zm.hashcode.mshengu.app.util.predicates.person.PersonUserPredicates;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.repository.people.PersonRepository;
import zm.hashcode.mshengu.services.people.PersonService;

/**
 *
 * @author lucky
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;
    List<Person> personList;

    @Override
    @Cacheable("persons")
    public List<Person> findAll() {
        return ImmutableList.copyOf(repository.findAll());
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "persons", allEntries = true),
        @CacheEvict(value = "drivers", allEntries = true),
        @CacheEvict(value = "requestors", allEntries = true),
        @CacheEvict(value = "staff", allEntries = true)})
    public void persist(Person person) {

        repository.save(person);

    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "persons", allEntries = true),
        @CacheEvict(value = "drivers", allEntries = true),
        @CacheEvict(value = "requestors", allEntries = true),
        @CacheEvict(value = "staff", allEntries = true)})
    public void merge(Person person) {
        if (person.getId() != null) {
            repository.save(person);
        }
    }

    @Override
    public Person findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = "persons", allEntries = true),
        @CacheEvict(value = "drivers", allEntries = true),
        @CacheEvict(value = "requestors", allEntries = true),
        @CacheEvict(value = "staff", allEntries = true)})
    public void delete(Person person) {
        repository.delete(person);
    }

    @Override
    public Person findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    @Cacheable("staff")
    public List<Person> findAllStaff() {
        personList = (List<Person>) repository.findAll();
        Collection<Person> personsFilteredList = Collections2.filter(personList, new PersonStaffPredicates());
        return ImmutableList.copyOf(personsFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    @Cacheable("drivers")
    public List<Person> findAllDrivers() {
        personList = (List<Person>) repository.findAll();
        Collection<Person> personsFilteredList = Collections2.filter(personList, new PersonDriverPredicate());
        return ImmutableList.copyOf(personsFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    @Cacheable("requestors")
    public List<Person> findAllRequestors() {
        personList = (List<Person>) repository.findAll();
        Collection<Person> persons = Collections2.filter(personList, new PersonRequestorPredicates());
        return ImmutableList.copyOf(persons);
    }

    @Override
    public List<Person> findAllUsers() {
        personList = (List<Person>) repository.findAll();      
        Collection<Person> persons =personList.parallelStream().filter((Person p) -> p.isEnable()).collect(toList());
        return ImmutableList.copyOf(persons);
    }

    @Override
    public List<Person> findAllDrivingCompanyCars() {
        personList = (List<Person>) repository.findAll();        
        Collection<Person> persons = Collections2.filter(personList, new PersonDrivesCompanyCarPredicate());
        return ImmutableList.copyOf(persons);
    }

    @Override
    public Person findDriverWithEmployeeNumber(String employeeNumber) {
//        List<Person> allDrivers = findAllDrivingCompanyCars();
        List<Person> allDrivers = findAllDrivingCompanyCars();
        for (Person person : allDrivers) {
            if (person.getEmployeeNumber().equalsIgnoreCase(employeeNumber)) {
                return person;
            }
        }
        return null;
    }
}
