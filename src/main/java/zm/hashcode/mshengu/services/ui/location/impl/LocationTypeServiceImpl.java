/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.location.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.location.LocationType;
import zm.hashcode.mshengu.repository.ui.location.LocationTypeRepository;
import zm.hashcode.mshengu.services.ui.location.LocationTypeService;

/**
 *
 * @author lucky
 */
@Service
public class LocationTypeServiceImpl implements LocationTypeService {

    @Autowired
    private LocationTypeRepository repository;

    @Override
    public List<LocationType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(LocationType locationType) {

        repository.save(locationType);

    }

    @Override
    public void merge(LocationType locationType) {
        if (locationType.getId() != null) {
            repository.save(locationType);
        }
    }

    @Override
    public LocationType findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(LocationType locationType) {
        repository.delete(locationType);
    }

    @Override
    public LocationType findByName(String name) {
        return repository.findByName(name);
    }
}
