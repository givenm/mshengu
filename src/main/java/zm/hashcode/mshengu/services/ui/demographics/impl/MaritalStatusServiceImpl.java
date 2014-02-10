/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.demographics.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.demographics.MaritalStatus;
import zm.hashcode.mshengu.repository.ui.demographics.MaritalStatusRepository;
import zm.hashcode.mshengu.services.ui.demographics.MaritalStatusService;

/**
 *
 * @author lucky
 */
@Service
public class MaritalStatusServiceImpl implements MaritalStatusService{

    @Autowired
    private MaritalStatusRepository repository;

    @Override
    public List<MaritalStatus> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByStatusName()));
    }

    @Override
    public void persist(MaritalStatus maritalStatus) {

        repository.save(maritalStatus);

    }

    @Override
    public void merge(MaritalStatus maritalStatus) {
        if (maritalStatus.getId() != null) {
            repository.save(maritalStatus);
        }
    }

    @Override
    public MaritalStatus findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(MaritalStatus maritalStatus) {
        repository.delete(maritalStatus);
    }
    private Sort sortByStatusName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "statusName"));
    }
}
