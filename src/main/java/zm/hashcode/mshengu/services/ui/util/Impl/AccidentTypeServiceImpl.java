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
import zm.hashcode.mshengu.domain.ui.util.AccidentType;
import zm.hashcode.mshengu.repository.ui.util.AccidentTypeRepository;
import zm.hashcode.mshengu.services.ui.util.AccidentTypeService;

/**
 *
 * @author lucky
 */
@Service
public class AccidentTypeServiceImpl implements AccidentTypeService {

    @Autowired
    private AccidentTypeRepository repository;

    @Override
    public List<AccidentType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(AccidentType accidentType) {
        repository.save(accidentType);
    }

    @Override
    public void merge(AccidentType accidentType) {
        if (accidentType.getId() != null) {
            repository.save(accidentType);
        }
    }

    @Override
    public AccidentType findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(AccidentType accidentType) {
        repository.delete(accidentType);
    }
}
