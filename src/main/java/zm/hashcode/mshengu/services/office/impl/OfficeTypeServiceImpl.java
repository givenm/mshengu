/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.office.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.office.OfficeType;
import zm.hashcode.mshengu.repository.office.OfficeTypeRepository;
import zm.hashcode.mshengu.services.office.OfficeTypeService;

/**
 *
 * @author lucky
 */
@Service
public class OfficeTypeServiceImpl implements OfficeTypeService {

    @Autowired
    private OfficeTypeRepository repository;

    @Override
    public List<OfficeType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByName()));
    }

    @Override
    public void persist(OfficeType officeType) {

        repository.save(officeType);

    }

    @Override
    public void merge(OfficeType officeType) {
        if (officeType.getId() != null) {
            repository.save(officeType);
        }
    }

    @Override
    public OfficeType findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(OfficeType officeType) {
        repository.delete(officeType);
    }

    private Sort sortByName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "name"));
    }
}
