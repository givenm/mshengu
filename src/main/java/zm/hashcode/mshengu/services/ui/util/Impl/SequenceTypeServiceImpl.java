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
import zm.hashcode.mshengu.domain.ui.util.SequenceType;
import zm.hashcode.mshengu.repository.ui.util.SequenceTypeRepository;
import zm.hashcode.mshengu.services.ui.util.SequenceTypeService;

/**
 *
 * @author lucky
 */
@Service
public class SequenceTypeServiceImpl implements SequenceTypeService {

    @Autowired
    private SequenceTypeRepository repository;

    @Override
    public List<SequenceType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(SequenceType sequenceType) {
        repository.save(sequenceType);
    }

    @Override
    public void merge(SequenceType sequenceType) {
        if (sequenceType.getId() != null) {
            repository.save(sequenceType);
        }
    }

    @Override
    public SequenceType findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(SequenceType sequenceType) {
        repository.delete(sequenceType);
    }

    @Override
    public SequenceType findByName(String name) {
        return repository.findByName(name);
    }
}
