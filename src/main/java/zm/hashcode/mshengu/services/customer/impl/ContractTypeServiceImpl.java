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
import zm.hashcode.mshengu.domain.customer.ContractType;
import zm.hashcode.mshengu.repository.customer.ContractTypeRepository;
import zm.hashcode.mshengu.services.customer.ContractTypeService;

/**
 *
 * @author Ferox
 */
@Service
public class ContractTypeServiceImpl implements ContractTypeService {

    @Autowired
    private ContractTypeRepository repository;

    @Override
    public List<ContractType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByType()));
    }

    private Sort sortByType() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "type"));
    }

    @Override
    public void persist(ContractType contractType) {

        repository.save(contractType);

    }

    @Override
    public void merge(ContractType contractType) {
        if (contractType.getId() != null) {
            repository.save(contractType);
        }
    }

    @Override
    public ContractType findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(ContractType contractType) {
        repository.delete(contractType);
    }

    @Override
    public ContractType findByType(String type) {
        return repository.findByType(type);
    }
}
