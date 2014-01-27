/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.people.EmployeeDetail;
import zm.hashcode.mshengu.repository.customer.EmployeeDetailRepository;
import zm.hashcode.mshengu.services.customer.EmployeeDetailService;


/**
 *
 * @author Ferox
 */
@Service
public class EmployeeDetailServiceImpl implements EmployeeDetailService {

    @Autowired
    private EmployeeDetailRepository repository;

    @Override
    public List<EmployeeDetail> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByStartDate()));
    }

    private Sort sortByStartDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "nationality"));
    }

    @Override
    public void persist(EmployeeDetail contract) {

        repository.save(contract);

    }

    @Override
    public void merge(EmployeeDetail contract) {
        if (contract.getId() != null) {
            repository.save(contract);
        }
    }

    @Override
    public EmployeeDetail findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(EmployeeDetail contract) {
        repository.delete(contract);
    }
}
