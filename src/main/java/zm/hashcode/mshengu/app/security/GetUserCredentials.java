/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.security;

import java.io.Serializable;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.services.people.PersonService;

/**
 *
 * @author boniface
 */
public class GetUserCredentials implements Serializable {

    private PersonService personService;
    public String username() {
        String username = "Anonymous";
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        }
        return username;
    }
    public boolean isPersonWithRole(String role) {
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(authority)) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
    public Person getLoggedInPerson() {
        personService = PersonFacade.getPersonService();
        Person user = personService.findByUsername(username());
        return user;
    }
}
