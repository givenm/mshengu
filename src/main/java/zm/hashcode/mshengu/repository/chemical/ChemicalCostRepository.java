/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.chemical;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.chemical.ChemicalCost;

/**
 *
 * @author geek
 */
public interface ChemicalCostRepository extends PagingAndSortingRepository<ChemicalCost, String> {
}
