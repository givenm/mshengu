/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util.Impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.util.StatusType;
import zm.hashcode.mshengu.repository.ui.util.StatusTypeRepository;
import zm.hashcode.mshengu.services.ui.util.StatusTypeService;

/**
 *
 * @author boniface
 */
@Service
public class StatusTypeServiceImpl implements StatusTypeService {

    @Autowired
    private StatusTypeRepository repository;

    @Override
    public List<StatusType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(StatusType statusType) {
        repository.save(statusType);
    }

    @Override
    public void merge(StatusType statusType) {
        if (statusType.getId() != null) {
            repository.save(statusType);
        }
    }

    @Override
    public StatusType findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(StatusType statusType) {
        repository.delete(statusType);
    }

    @Override
    public StatusType findByName(String name) {
        return repository.findByName(name);
    }
}
