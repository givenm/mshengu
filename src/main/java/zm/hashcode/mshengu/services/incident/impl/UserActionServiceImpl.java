/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.incident.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.incident.UserAction;
import zm.hashcode.mshengu.repository.incident.UserActionRepository;
import zm.hashcode.mshengu.services.incident.UserActionService;

/**
 *
 * @author Luckbliss
 */
@Service
public class UserActionServiceImpl implements UserActionService {

    @Autowired
    private UserActionRepository repository;

    @Override
    public List<UserAction> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(UserAction userAction) {

        repository.save(userAction);

    }

    @Override
    public void merge(UserAction userAction) {
        if (userAction.getId() != null) {
            repository.save(userAction);
        }
    }

    @Override
    public UserAction findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(UserAction userAction) {
        repository.delete(userAction);
    }
    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "actionDate"));
    }
 
}
