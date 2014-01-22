/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.location;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.location.Location;

/**
 *
 * @author lucky
 */
public interface LocationRepository extends PagingAndSortingRepository<Location, String> {

    public Location findByName(String name);

    public List<Location> findByName(String value, String fieldName);
}
