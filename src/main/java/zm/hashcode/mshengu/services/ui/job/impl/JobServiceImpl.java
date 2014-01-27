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
import zm.hashcode.mshengu.domain.ui.job.Job;
import zm.hashcode.mshengu.repository.ui.job.JobRepository;
import zm.hashcode.mshengu.services.ui.job.JobService;

/**
 *
 * @author lucky
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository repository;

    @Override
    public List<Job> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByTitle()));
    }

    private Sort sortByTitle() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "title"));
    }

    @Override
    public void persist(Job job) {

        repository.save(job);

    }

    @Override
    public void merge(Job job) {
        if (job.getId() != null) {
            repository.save(job);
        }
    }

    @Override
    public Job findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Job job) {
        repository.delete(job);
    }
}
