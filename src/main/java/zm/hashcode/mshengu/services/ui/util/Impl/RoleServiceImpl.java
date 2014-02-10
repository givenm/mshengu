/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util.Impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.ui.util.Role;
import zm.hashcode.mshengu.repository.ui.util.RoleRepository;
import zm.hashcode.mshengu.services.people.PersonService;
import zm.hashcode.mshengu.services.ui.util.RoleService;

/**
 *
 * @author lucky
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public List<Role> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByRolename()));
    }

    private Sort sortByRolename() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "rolename"));
    }

    @Override
    public void persist(Role role) {
        repository.save(role);
    }

    @Override
    public void merge(Role role) {
        if (role.getId() != null) {
            repository.save(role);
        }
    }

    @Override
    public Role findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Role role) {
        if (!checkIfPersonHasRole(role)) {
            repository.delete(role);
        }
    }

    public boolean checkIfPersonHasRole(Role role) {
        PersonService personService = PersonFacade.getPersonService();
        List<Person> persons = personService.findAll();
        for (Person person : persons) {
            Set<Role> roles = person.getRole();
            for (Role r : roles) {
                if (r.equals(role)) {
                    return true;
                }
            }
        }
        return false;
    }
}
