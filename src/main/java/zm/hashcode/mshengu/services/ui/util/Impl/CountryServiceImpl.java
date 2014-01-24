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
import zm.hashcode.mshengu.domain.ui.util.Country;
import zm.hashcode.mshengu.repository.ui.util.CountryRepository;
import zm.hashcode.mshengu.services.ui.util.CountryService;

/**
 *
 * @author Ferox
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository repository;

    @Override
    public List<Country> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(Country positionType) {

        repository.save(positionType);

    }

    @Override
    public void merge(Country positionType) {
        if (positionType.getId() != null) {
            repository.save(positionType);
        }
    }

    @Override
    public Country findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Country positionType) {
        repository.delete(positionType);
    }
    
    @Override
    public Country findByName(String name){
        return repository.findByName(name);
    }
}
