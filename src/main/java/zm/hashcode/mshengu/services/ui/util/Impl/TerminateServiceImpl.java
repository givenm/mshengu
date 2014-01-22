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
import zm.hashcode.mshengu.domain.ui.util.Terminate;
import zm.hashcode.mshengu.repository.ui.util.TerminateRepository;
import zm.hashcode.mshengu.services.ui.util.TerminateService;

/**
 *
 * @author Luckbliss
 */
@Service
public class TerminateServiceImpl implements TerminateService {

    @Autowired
    private TerminateRepository repository;

    @Override
    public List<Terminate> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "code"));
    }

    @Override
    public void persist(Terminate terminate) {
        repository.save(terminate);
    }

    @Override
    public void merge(Terminate terminate) {
        if (terminate.getId() != null) {
            repository.save(terminate);
        }
    }

    @Override
    public Terminate findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Terminate terminate) {
        repository.delete(terminate);
    }
}
