/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.location.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.location.Contact;
import zm.hashcode.mshengu.repository.ui.location.ContactRepository;
import zm.hashcode.mshengu.services.ui.location.ContactService;

/**
 *
 * @author lucky
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository repository;

    @Override
    public List<Contact> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(Contact contact) {

        repository.save(contact);

    }

    @Override
    public void merge(Contact contact) {
        if (contact.getId() != null) {
            repository.save(contact);
        }
    }

    @Override
    public Contact findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Contact contact) {
        repository.delete(contact);
    }
}
