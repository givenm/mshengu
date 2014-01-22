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
import zm.hashcode.mshengu.domain.ui.education.Competency;
import zm.hashcode.mshengu.repository.ui.education.CompetencyRepository;
import zm.hashcode.mshengu.services.ui.education.CompetencyService;

/**
 *
 * @author lucky
 */
@Service
public class CompetencyServiceImpl implements CompetencyService {

    @Autowired
    private CompetencyRepository repository;

    @Override
    public List<Competency> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(Competency competency) {

        repository.save(competency);

    }

    @Override
    public void merge(Competency competency) {
        if (competency.getId() != null) {
            repository.save(competency);
        }
    }

    @Override
    public Competency findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Competency competency) {
        repository.delete(competency);
    }
}
