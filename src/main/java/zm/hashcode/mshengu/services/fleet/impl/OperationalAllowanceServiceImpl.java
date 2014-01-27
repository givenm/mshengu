/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet.impl;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.fleet.OperationalAllowance;
import zm.hashcode.mshengu.repository.fleet.OperationalAllowanceRepository;
import zm.hashcode.mshengu.services.fleet.OperationalAllowanceService;

/**
 *
 * @author geek
 */
@Service
public class OperationalAllowanceServiceImpl implements OperationalAllowanceService {

    @Autowired
    private OperationalAllowanceRepository repository;

    @Override
    public List<OperationalAllowance> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByDate()));
    }

    @Override
    public void persist(OperationalAllowance operationalAllowance) {
        repository.save(operationalAllowance);
    }

    @Override
    public void merge(OperationalAllowance operationalAllowance) {
        if (operationalAllowance.getId() != null) {
            repository.save(operationalAllowance);
        }
    }

    @Override
    public OperationalAllowance findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(OperationalAllowance operationalAllowance) {
        repository.delete(operationalAllowance);
    }

    private Sort sortByDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "date"));
    }
}
