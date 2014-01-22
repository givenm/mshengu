/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.predicates.person;

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author boniface
 */
public class PersonStaffPredicates implements Predicate<Person> {
    @Override
    public boolean apply(Person person) {
        if (person.getEmployeeDetails() !=  null) {
            return true;
        }
        return false;
    }

   
}
