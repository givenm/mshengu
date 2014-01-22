/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.job;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.job.JobClassification;

/**
 *
 * @author lucky
 */
public interface JobClassificationService {
    public List<JobClassification> findAll();
    public void persist(JobClassification jobClassification);
    public void merge(JobClassification jobClassification);
    public JobClassification findById(String id);
    public void delete(JobClassification jobClassification);
}
