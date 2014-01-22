/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.job;

import java.util.List;
import java.util.Set;
import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.job.JobClassification;

/**
 *
 * @author lucky
 */
public interface JobClassificationRepository extends PagingAndSortingRepository<JobClassification, String>{
    
}
