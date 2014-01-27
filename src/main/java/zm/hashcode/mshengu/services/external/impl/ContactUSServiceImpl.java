/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.external.impl;

import com.google.common.collect.Collections2;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.predicates.contactus.ContactUSActivationStatusPredicate;
import zm.hashcode.mshengu.app.util.predicates.contactus.ContactUSStatusPredicate;
import zm.hashcode.mshengu.domain.external.ContactUS;
import zm.hashcode.mshengu.repository.external.ContactUSRepository;
import zm.hashcode.mshengu.services.external.ContactUSService;

/**
 *
 * @author Ferox
 */
@Service
public class ContactUSServiceImpl implements ContactUSService {

    @Autowired
    private ContactUSRepository repository;

    @Override
    public List<ContactUS> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByDate()));
    }

    @Override
    public void persist(ContactUS contactUS) {

        repository.save(contactUS);

    }

    @Override
    public void merge(ContactUS contactUS) {
        if (contactUS.getId() != null) {
            repository.save(contactUS);
        }
    }

    @Override
    public ContactUS findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(ContactUS contactUS) {
        repository.delete(contactUS);
    }

    private Sort sortByDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "dateOfAction"));
    }

    @Override
    public ContactUS findByRefNumber(String refNumber) {
        return repository.findByRefNumber(refNumber);
    }

    @Override
    public List<ContactUS> findAllClosed() {
        List<ContactUS> contactUSList = (List<ContactUS>) repository.findAll(sortByDate());
        Collection<ContactUS> contactUSsFilteredList = Collections2.filter(contactUSList, new ContactUSActivationStatusPredicate(true));
        return ImmutableList.copyOf(contactUSsFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<ContactUS> findAllOpen() {
        List<ContactUS> contactUSList = (List<ContactUS>) repository.findAll(sortByDate());
        Collection<ContactUS> contactUSsFilteredList = Collections2.filter(contactUSList, new ContactUSActivationStatusPredicate(false));
        return ImmutableList.copyOf(contactUSsFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<ContactUS> findByStatus(String statusName) {
        List<ContactUS> contactUSList = (List<ContactUS>) repository.findAll(sortByDate());
        Collection<ContactUS> contactUSsFilteredList = Collections2.filter(contactUSList, new ContactUSStatusPredicate(statusName));
        return ImmutableList.copyOf(contactUSsFilteredList);
//        return ImmutableList.copyOf();
    }
}
