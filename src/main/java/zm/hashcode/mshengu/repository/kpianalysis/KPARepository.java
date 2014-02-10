/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.kpianalysis;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.kpianalysis.KPA;

/**
 *
 * @author Luckbliss
 */
public interface KPARepository extends PagingAndSortingRepository<KPA, String> {

    public KPA findByTab(String name);
}
