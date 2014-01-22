/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.people;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author lucky
 */
public interface PersonRepository extends PagingAndSortingRepository<Person, String> {

    public Person findByUsername(String username);
}
