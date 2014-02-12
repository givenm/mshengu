/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.position.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.position.PositionType;
import zm.hashcode.mshengu.repository.ui.position.PositionTypeRepository;
import zm.hashcode.mshengu.services.ui.position.PositionTypeService;

/**
 *
 * @author lucky
 */
@Service
public class PositionTypeServiceImpl implements PositionTypeService {

    @Autowired
    private PositionTypeRepository repository;

    @Override
    public List<PositionType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }

    @Override
    public void persist(PositionType positionType) {

        repository.save(positionType);

    }

    @Override
    public void merge(PositionType positionType) {
        if (positionType.getId() != null) {
            repository.save(positionType);
        }
    }

    @Override
    public PositionType findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(PositionType positionType) {
        repository.delete(positionType);
    }
}
