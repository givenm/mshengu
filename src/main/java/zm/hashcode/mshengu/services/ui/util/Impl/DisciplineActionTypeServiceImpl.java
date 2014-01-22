/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util.Impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.util.DisciplineActionType;
import zm.hashcode.mshengu.repository.ui.util.DisciplineActionTypeRepository;
import zm.hashcode.mshengu.services.ui.util.DisciplineActionTypeService;

/**
 *
 * @author lucky
 */
@Service
public class DisciplineActionTypeServiceImpl implements DisciplineActionTypeService {

    @Autowired
    private DisciplineActionTypeRepository repository;

    @Override
    public List<DisciplineActionType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(DisciplineActionType disciplineActionType) {
        repository.save(disciplineActionType);
    }

    @Override
    public void merge(DisciplineActionType disciplineActionType) {
        if (disciplineActionType.getId() != null) {
            repository.save(disciplineActionType);
        }
    }

    @Override
    public DisciplineActionType findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(DisciplineActionType disciplineActionType) {
        repository.delete(disciplineActionType);
    }
}
