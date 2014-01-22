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
import zm.hashcode.mshengu.domain.ui.education.EducationType;
import zm.hashcode.mshengu.repository.ui.education.EducationTypeRepository;
import zm.hashcode.mshengu.services.ui.education.EducationTypeService;

/**
 *
 * @author lucky
 */
@Service
public class EducationTypeServiceImpl implements EducationTypeService{

    @Autowired
    private EducationTypeRepository repository;

    @Override
    public List<EducationType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }
    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(EducationType educationType) {

        repository.save(educationType);

    }

    @Override
    public void merge(EducationType educationType) {
        if (educationType.getId() != null) {
            repository.save(educationType);
        }
    }

    @Override
    public EducationType findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(EducationType educationType) {
        repository.delete(educationType);
    }
}
