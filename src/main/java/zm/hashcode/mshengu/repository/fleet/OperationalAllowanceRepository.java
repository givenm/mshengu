/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.fleet;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.fleet.OperationalAllowance;

/**
 *
 * @author geek
 */
public interface OperationalAllowanceRepository extends PagingAndSortingRepository<OperationalAllowance, String> {
}
