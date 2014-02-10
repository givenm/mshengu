/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.location.impl;

import com.google.common.collect.Collections2;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.app.util.predicates.location.CityPredicate;
import zm.hashcode.mshengu.app.util.predicates.location.CountryPredicate;
import zm.hashcode.mshengu.app.util.predicates.location.ProvincePredicate;
import zm.hashcode.mshengu.app.util.predicates.location.RegionPredicate;
import zm.hashcode.mshengu.app.util.predicates.location.SuburbPredicate;
import zm.hashcode.mshengu.domain.ui.location.Location;
import zm.hashcode.mshengu.repository.ui.location.LocationRepository;
import zm.hashcode.mshengu.services.ui.location.LocationService;

/**
 *
 * @author lucky
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;
    
    List<Location> locationList;

    @Override
    public List<Location> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(Location location) {

        repository.save(location);

    }

    @Override
    public void merge(Location location) {
        if (location.getId() != null) {
            repository.save(location);
        }
    }

    @Override
    public Location findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Location location) {
        repository.delete(location);
    }

    @Override
    public List<Location> findAllSuburbs() {
        locationList = (List<Location>) repository.findAll(sortByName());
        Collection<Location> locationFilteredList = Collections2.filter(locationList, new SuburbPredicate());
        return ImmutableList.copyOf(locationFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Location> findAllRegions() {
        locationList = (List<Location>) repository.findAll(sortByName());
        Collection<Location> locationFilteredList = Collections2.filter(locationList, new RegionPredicate());
        return ImmutableList.copyOf(locationFilteredList);
//        return ImmutableList.copyOf();
    }

      @Override
    public List<Location> findAllCities() {
        locationList = (List<Location>) repository.findAll(sortByName());
        Collection<Location> locationFilteredList = Collections2.filter(locationList, new CityPredicate());
        return ImmutableList.copyOf(locationFilteredList);
//        return ImmutableList.copyOf();
    }
      
    @Override
    public List<Location> findAllProvinces() {
        locationList = (List<Location>) repository.findAll(sortByName());
        Collection<Location> locationFilteredList = Collections2.filter(locationList, new ProvincePredicate());
        return ImmutableList.copyOf(locationFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public List<Location> findAllCountries() {
        locationList = (List<Location>) repository.findAll(sortByName());
        Collection<Location> locationFilteredList = Collections2.filter(locationList, new CountryPredicate());
        return ImmutableList.copyOf(locationFilteredList);
//        return ImmutableList.copyOf();
    }

    @Override
    public Location findByName(String name) {
        return repository.findByName(name);
    }
    
        @Override
    public List<Location> findByName(String value, String fieldName) {
        return repository.findByName(value, fieldName );
    }
}