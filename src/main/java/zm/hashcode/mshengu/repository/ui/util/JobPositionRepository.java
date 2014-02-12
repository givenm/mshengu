/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.util;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.util.JobPosition;

/**
 *
 * @author lucky
 */
public interface JobPositionRepository extends PagingAndSortingRepository<JobPosition, String> {   
    public JobPosition findByName(String name);
}
