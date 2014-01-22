/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.demographics.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.demographics.IdentificationType;
import zm.hashcode.mshengu.repository.ui.demographics.IdentificationTypeRepository;
import zm.hashcode.mshengu.services.ui.demographics.IdentificationTypeService;

/**
 *
 * @author lucky
 */
@Service
public class IdentificationTypeServiceImpl implements IdentificationTypeService{

   @Autowired
    private IdentificationTypeRepository repository;

    @Override
    public List<IdentificationType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByIdvalue()));
    }

    @Override
    public void persist(IdentificationType identificationType) {

        repository.save(identificationType);

    }

    @Override
    public void merge(IdentificationType identificationType) {
        if (identificationType.getId() != null) {
            repository.save(identificationType);
        }
    }

    @Override
    public IdentificationType findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(IdentificationType identificationType) {
        repository.delete(identificationType);
    }
    private Sort sortByIdvalue() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "idvalue"));
    }
}
