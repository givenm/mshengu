/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.position.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.position.DepartureReason;
import zm.hashcode.mshengu.repository.ui.position.DepartureReasonRepository;
import zm.hashcode.mshengu.services.ui.position.DepartureReasonService;

/**
 *
 * @author lucky
 */
@Service
public class DepartureReasonServiceImpl implements DepartureReasonService {

    @Autowired
    private DepartureReasonRepository repository;

    @Override
    public List<DepartureReason> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByReasonName()));
    }

    private Sort sortByReasonName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "reasonName"));
    }

    @Override
    public void persist(DepartureReason departureReason) {

        repository.save(departureReason);

    }

    @Override
    public void merge(DepartureReason departureReason) {
        if (departureReason.getId() != null) {
            repository.save(departureReason);
        }
    }

    @Override
    public DepartureReason findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(DepartureReason departureReason) {
        repository.delete(departureReason);
    }
}
