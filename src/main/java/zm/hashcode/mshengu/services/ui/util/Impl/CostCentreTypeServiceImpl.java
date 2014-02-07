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
import zm.hashcode.mshengu.domain.ui.util.CostCentreType;
import zm.hashcode.mshengu.repository.ui.util.CostCentreTypeRepository;
import zm.hashcode.mshengu.services.ui.util.CostCentreTypeService;

/**
 *
 * @author Luckbliss
 */
@Service
public class CostCentreTypeServiceImpl implements CostCentreTypeService {

    @Autowired
    private CostCentreTypeRepository repository;

    @Override
    public List<CostCentreType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(CostCentreType costCentreType) {
        repository.save(costCentreType);
    }

    @Override
    public void merge(CostCentreType costCentreType) {
        if (costCentreType.getId() != null) {
            repository.save(costCentreType);
        }
    }

    @Override
    public CostCentreType findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(CostCentreType costCentreType) {
        repository.delete(costCentreType);
    }
}
