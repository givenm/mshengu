/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.products.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.products.UnitType;
import zm.hashcode.mshengu.repository.products.UnitTypeRepository;
import zm.hashcode.mshengu.services.products.UnitTypeService;

/**
 *
 * @author Luckbliss
 */
@Service
public class UnitTypeServiceImpl implements UnitTypeService {

    @Autowired
    private UnitTypeRepository repository;

    @Override
    public List<UnitType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(UnitType unitType) {

        repository.save(unitType);

    }

    @Override
    public void merge(UnitType unitType) {
        if (unitType.getId() != null) {
            repository.save(unitType);
        }
    }

    @Override
    public UnitType findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(UnitType unitType) {
        repository.delete(unitType);
    }
    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }
    
    @Override
    public UnitType findByName(String name){
        return repository.findByName(name);
    }
 
}
