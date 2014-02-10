/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.position;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.position.Position;
import zm.hashcode.mshengu.domain.ui.util.Country;

/**
 *
 * @author lucky
 */
public interface PositionRepository extends PagingAndSortingRepository<Position, String>{   
    public Country findByName(String name);
}
