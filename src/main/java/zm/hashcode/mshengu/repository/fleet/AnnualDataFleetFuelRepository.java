/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.fleet;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.fleet.AnnualDataFleetFuel;

/**
 *
 * @author ColinWa
 */
public interface AnnualDataFleetFuelRepository extends PagingAndSortingRepository<AnnualDataFleetFuel, String> {
}
