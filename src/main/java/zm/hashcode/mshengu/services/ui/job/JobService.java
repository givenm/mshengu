/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.job;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.job.Job;

/**
 *
 * @author lucky
 */
public interface JobService {
    public List<Job> findAll();
    public void persist(Job job);
    public void merge(Job job);
    public Job findById(String id);
    public void delete(Job job);
}
