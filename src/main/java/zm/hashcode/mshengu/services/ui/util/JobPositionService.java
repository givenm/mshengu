/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.util.JobPosition;

/**
 *
 * @author Ferox
 */
public interface JobPositionService {
    public List<JobPosition> findAll();
    public void persist(JobPosition positionType);
    public void merge(JobPosition positionType);
    public JobPosition findById(String id);
    public void delete(JobPosition positionType);
     public JobPosition findByName(String name);
}
