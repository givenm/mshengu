/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.job.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.job.JobClassification;
import zm.hashcode.mshengu.repository.ui.job.JobClassificationRepository;
import zm.hashcode.mshengu.services.ui.job.JobClassificationService;

/**
 *
 * @author lucky
 */
@Service
public class JobClassificationServiceImpl implements JobClassificationService {

    @Autowired
    private JobClassificationRepository repository;

    @Override
    public List<JobClassification> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByCurrentTitle()));
    }

    private Sort sortByCurrentTitle() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "currentTitle"));
    }

    @Override
    public void persist(JobClassification jobClassification) {

        repository.save(jobClassification);

    }

    @Override
    public void merge(JobClassification jobClassification) {
        if (jobClassification.getId() != null) {
            repository.save(jobClassification);
        }
    }

    @Override
    public JobClassification findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(JobClassification jobClassification) {
        repository.delete(jobClassification);
    }
}
