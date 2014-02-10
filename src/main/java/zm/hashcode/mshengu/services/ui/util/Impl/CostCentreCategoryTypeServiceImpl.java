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
import zm.hashcode.mshengu.domain.ui.util.CostCentreCategoryType;
import zm.hashcode.mshengu.repository.ui.util.CostCentreCategoryTypeRepository;
import zm.hashcode.mshengu.services.ui.util.CostCentreCategoryTypeService;

/**
 *
 * @author Luckbliss
 */
@Service
public class CostCentreCategoryTypeServiceImpl implements CostCentreCategoryTypeService{
    @Autowired
    private CostCentreCategoryTypeRepository repository;

    @Override
    public List<CostCentreCategoryType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(CostCentreCategoryType costCentreCategoryType) {
        repository.save(costCentreCategoryType);
    }

    @Override
    public void merge(CostCentreCategoryType costCentreCategoryType) {
        if (costCentreCategoryType.getId() != null) {
            repository.save(costCentreCategoryType);
        }
    }

    @Override
    public CostCentreCategoryType findById(String id) {
        try{
        return repository.findOne(id);
        }catch(IllegalArgumentException iaEx){
            return null;
        }
    }

    @Override
    public void delete(CostCentreCategoryType costCentreCategoryType) {
        repository.delete(costCentreCategoryType);
    }
}
