/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.demographics.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.demographics.Gender;
import zm.hashcode.mshengu.repository.ui.demographics.GenderRepository;
import zm.hashcode.mshengu.services.ui.demographics.GenderService;

/**
 *
 * @author lucky
 */
@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository repository;

    @Override
    public List<Gender> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByGender()));
    }

    private Sort sortByGender() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "gender"));
    }

    @Override
    public void persist(Gender gender) {

        repository.save(gender);

    }

    @Override
    public void merge(Gender gender) {
        if (gender.getId() != null) {
            repository.save(gender);
        }
    }

    @Override
    public Gender findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Gender gender) {
        repository.delete(gender);
    }
}
