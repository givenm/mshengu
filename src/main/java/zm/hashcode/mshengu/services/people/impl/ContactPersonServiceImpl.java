/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.people.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.repository.people.ContactPersonRepository;
import zm.hashcode.mshengu.services.people.ContactPersonService;

/**
 *
 * @author Ferox
 */
@Service
public class ContactPersonServiceImpl implements ContactPersonService{

    @Autowired
    private ContactPersonRepository repository;

    @Override
    public List<ContactPerson> findAll() {
        return ImmutableList.copyOf(repository.findAll());
    }

    @Override
    public void persist(ContactPerson contactPerson) {

        repository.save(contactPerson);

    }

    @Override
    public void merge(ContactPerson contactPerson) {
        if (contactPerson.getId() != null) {
            repository.save(contactPerson);
        }
    }

    @Override
    public ContactPerson findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(ContactPerson contactPerson) {
        repository.delete(contactPerson);
    }

    @Override
    public ContactPerson findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }
    
    
    @Override
    public ContactPerson findByParentId(String parentId){
        return repository.findByParentId(parentId);
    }

    @Override
    public ContactPerson findByEmail(String email) {
        return repository.findByEmailAddress(email);
    }
}