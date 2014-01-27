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
import zm.hashcode.mshengu.domain.ui.util.Sequence;
import zm.hashcode.mshengu.repository.ui.util.SequenceRepository;
import zm.hashcode.mshengu.services.ui.util.SequenceService;

/**
 *
 * @author Ferox
 */
@Service
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    private SequenceRepository repository;

    @Override
    public List<Sequence> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortBySequence()));
    }

    private Sort sortBySequence() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(Sequence sequence) {
        repository.save(sequence);
    }

    @Override
    public void merge(Sequence sequence) {
        if (sequence.getId() != null) {
            repository.save(sequence);
        }
    }

    @Override
    public Sequence findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Sequence sequence) {
        repository.delete(sequence);
    }
    
        @Override
    public Sequence findByName(String name) {
        return repository.findByName(name);
        }
}
