/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.security;

import org.springframework.data.domain.AuditorAware;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author boniface
 */
public class SecurityAuditing implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        Person person = new GetUserCredentials().getLoggedInPerson();
        return person.getFirstname()+ "  " + person.getLastname()+ " with Username " + person.getUsername();
    }
}
