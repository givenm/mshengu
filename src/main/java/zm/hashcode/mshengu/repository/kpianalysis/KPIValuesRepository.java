/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.kpianalysis;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.kpianalysis.KPIValues;

/**
 *
 * @author Luckbliss
 */
public interface KPIValuesRepository extends PagingAndSortingRepository<KPIValues, String> {
}

