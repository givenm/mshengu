/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.position;

import java.util.List;
import java.util.Set;
import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.position.PositionType;

/**
 *
 * @author lucky
 */
public interface PositionTypeRepository extends PagingAndSortingRepository<PositionType, String> {
}
