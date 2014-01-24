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
import zm.hashcode.mshengu.domain.customer.Contract;
import zm.hashcode.mshengu.repository.customer.ContractRepository;
import zm.hashcode.mshengu.services.customer.ContractService;

/**
 *
 * @author Ferox
 */
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository repository;

    @Override
    public List<Contract> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByStartDate()));
    }

    private Sort sortByStartDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.DESC, "startDate"));
    }

    @Override
    public void persist(Contract contract) {

        repository.save(contract);

    }

    @Override
    public void merge(Contract contract) {
        if (contract.getId() != null) {
            repository.save(contract);
        }
    }

    @Override
    public Contract findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Contract contract) {
        repository.delete(contract);
    }

    @Override
    public Contract findByParentId(String parentId) {
        return repository.findByParentId(parentId);
    }
}
