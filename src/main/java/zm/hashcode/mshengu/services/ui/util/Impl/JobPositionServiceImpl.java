/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util.Impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.util.JobPosition;
import zm.hashcode.mshengu.repository.ui.util.JobPositionRepository;
import zm.hashcode.mshengu.services.ui.util.JobPositionService;

/**
 *
 * @author Ferox
 */
@Service
public class JobPositionServiceImpl implements JobPositionService {

    @Autowired
    private JobPositionRepository repository;

    @Override
    public List<JobPosition> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(JobPosition positionType) {

        repository.save(positionType);

    }

    @Override
    public void merge(JobPosition positionType) {
        if (positionType.getId() != null) {
            repository.save(positionType);
        }
    }

    @Override
    public JobPosition findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(JobPosition positionType) {
        repository.delete(positionType);
    }
    
    @Override
     public JobPosition findByName(String name){
      return  repository.findByName(name);
    }
}
