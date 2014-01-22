/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.location;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.location.LocationType;

/**
 *
 * @author lucky
 */
public interface LocationTypeService {
    public List<LocationType> findAll();
    public void persist(LocationType locationType);
    public void merge(LocationType locationType);
    public LocationType findById(String id);
    public void delete(LocationType locationType);
    public LocationType findByName(String name);
}
