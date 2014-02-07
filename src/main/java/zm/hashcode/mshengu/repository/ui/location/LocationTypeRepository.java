/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.location;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.location.LocationType;

/**
 *
 * @author lucky
 */
public interface LocationTypeRepository extends PagingAndSortingRepository<LocationType, String> {
    public LocationType findByName(String name);
}
