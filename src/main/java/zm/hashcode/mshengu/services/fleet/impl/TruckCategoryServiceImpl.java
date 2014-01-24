/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.fleet.TruckCategory;
import zm.hashcode.mshengu.repository.fleet.TruckCategoryRepository;
import zm.hashcode.mshengu.services.fleet.TruckCategoryService;

/**
 *
 * @author Ferox
 */
@Service
public class TruckCategoryServiceImpl implements TruckCategoryService {

    @Autowired
    private TruckCategoryRepository repository;

    @Override
    public List<TruckCategory> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(TruckCategory truck) {

        repository.save(truck);

    }

    @Override
    public void merge(TruckCategory truck) {
        if (truck.getId() != null) {
            repository.save(truck);
        }
    }

    @Override
    public TruckCategory findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(TruckCategory truck) {
        repository.delete(truck);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "categoryName"));
    }
    
    
    
    @Override
    public TruckCategory findByCategoryName(String categoryName){
        return repository.findByCategoryName(categoryName);
    }
}
