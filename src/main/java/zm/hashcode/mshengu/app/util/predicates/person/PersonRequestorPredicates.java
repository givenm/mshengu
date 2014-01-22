/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.predicates.person;

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author Luckbliss
 */
public class PersonRequestorPredicates implements Predicate<Person> {

    
     @Override
    public boolean apply(Person person) {
        if (true == isRequestor(person)) {
            return true;
        }
        return false;
    }

    private boolean isRequestor(Person person) {
        if (person != null) {
            return person.isRequestor();
        }
        return false;
    }
}
