/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.people;

import java.util.List;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author lucky
 */
public interface PersonService {

    public List<Person> findAll();

    public void persist(Person person);

    public void merge(Person person);

    public Person findById(String id);

    public void delete(Person person);

    public Person findByUsername(String username);

    public List<Person> findAllStaff();

    public List<Person> findAllRequestors();

    public List<Person> findAllDrivers();

    public List<Person> findAllUsers();

    public List<Person> findAllDrivingCompanyCars();

    public Person findDriverWithEmployeeNumber(String employeeNumber);
}
