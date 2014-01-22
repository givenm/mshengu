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
import zm.hashcode.mshengu.domain.ui.education.CompetencyType;
import zm.hashcode.mshengu.repository.ui.education.CompetencyTypeRepository;
import zm.hashcode.mshengu.services.ui.education.CompetencyTypeService;

/**
 *
 * @author lucky
 */
@Service
public class CompetencyTypeServiceImpl implements CompetencyTypeService {

    @Autowired
    private CompetencyTypeRepository repository;

    @Override
    public List<CompetencyType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(CompetencyType competencyType) {

        repository.save(competencyType);

    }

    @Override
    public void merge(CompetencyType competencyType) {
        if (competencyType.getId() != null) {
            repository.save(competencyType);
        }
    }

    @Override
    public CompetencyType findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(CompetencyType competencyType) {
        repository.delete(competencyType);
    }
}
