/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.education.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.education.Degree;
import zm.hashcode.mshengu.repository.ui.education.DegreeRepository;
import zm.hashcode.mshengu.services.ui.education.DegreeService;

/**
 *
 * @author lucky
 */
@Service
public class DegreeServiceImpl implements DegreeService{

    @Autowired
    private DegreeRepository repository;

    @Override
    public List<Degree> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByDegreeName()));
    }
    private Sort sortByDegreeName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "degreeName"));
    }

    @Override
    public void persist(Degree degree) {

        repository.save(degree);

    }

    @Override
    public void merge(Degree degree) {
        if (degree.getId() != null) {
            repository.save(degree);
        }
    }

    @Override
    public Degree findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Degree degree) {
        repository.delete(degree);
    }
}
