/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.kpianalysis;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.kpianalysis.KPI;

/**
 *
 * @author Luckbliss
 */
public interface KPIRepository extends PagingAndSortingRepository<KPI, String> {

    public KPI findByTab(String name);
}
