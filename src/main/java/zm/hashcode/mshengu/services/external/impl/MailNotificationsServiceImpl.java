/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.external.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.external.MailNotifications;
import zm.hashcode.mshengu.repository.external.MailNotificationsRepository;
import zm.hashcode.mshengu.services.external.MailNotificationsService;

/**
 *
 * @author Ferox
 */
@Service
public class MailNotificationsServiceImpl implements MailNotificationsService {

    @Autowired
    private MailNotificationsRepository repository;

    @Override
    public List<MailNotifications> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(MailNotifications mailNotifications) {

        repository.save(mailNotifications);

    }

    @Override
    public void merge(MailNotifications mailNotifications) {
        if (mailNotifications.getId() != null) {
            repository.save(mailNotifications);
        }
    }

    @Override
    public MailNotifications findById(String id) {
        return repository.findOne(id);
    }
    
        @Override
    public MailNotifications findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void delete(MailNotifications mailNotifications) {
        repository.delete(mailNotifications);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

   
}
