/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.job;

import java.util.List;
import java.util.Set;
import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.job.Job;

/**
 *
 * @author lucky
 */
public interface JobRepository extends PagingAndSortingRepository<Job, String>{
    
}
