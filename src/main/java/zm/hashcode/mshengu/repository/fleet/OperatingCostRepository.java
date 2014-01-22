/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.fleet;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;

/**
 *
 * @author Ferox
 */
public interface OperatingCostRepository extends PagingAndSortingRepository<OperatingCost , String>{
    
}
