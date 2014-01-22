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
public class PersonUserPredicates implements Predicate<Person> {

    
     @Override
    public boolean apply(Person person) {
        if (true == isEnabled(person)) {
            return true;
        }
        return false;
    }

    private boolean isEnabled(Person person) {
        if (person != null) {
            return person.isEnable();
        }
        return false;
    }
}
