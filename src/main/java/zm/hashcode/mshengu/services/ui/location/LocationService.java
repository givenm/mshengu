/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.location;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.location.Location;

/**
 *
 * @author lucky
 */
public interface LocationService {
    public List<Location> findAll();
    public void persist(Location location);
    public void merge(Location location);
    public Location findById(String id);
    public void delete(Location location);
    
    public List<Location> findAllSuburbs(); 
    public List<Location> findAllRegions(); 
    public List<Location> findAllCities(); 
    public List<Location> findAllProvinces(); 
    public List<Location> findAllCountries(); 
    public Location findByName(String name);
public List<Location> findByName(String value, String fieldName);

/*
 *    @Override
    public List<Location> findAllSuburbs(); {
        personList = (List<Location>) repository.findAll();
        Collection<Person> personsFilteredList = Collections2.filter(personList, new PersonDriverPredicate());
        return ImmutableList.copyOf(personsFilteredList);
//        return ImmutableList.copyOf();
    }
}
 */
}
