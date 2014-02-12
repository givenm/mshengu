/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.office.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.office.Office;
import zm.hashcode.mshengu.repository.office.OfficeRepository;
import zm.hashcode.mshengu.services.office.OfficeService;

/**
 *
 * @author lucky
 */
@Service
public class OfficeServiceImpl implements OfficeService{

    @Autowired
    private OfficeRepository repository;

    @Override
    public List<Office> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(Office office) {

        repository.save(office);

    }

    @Override
    public void merge(Office office) {
        if (office.getId() != null) {
            repository.save(office);
        }
    }

    @Override
    public Office findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Office office) {
        repository.delete(office);
    }
    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }
}
