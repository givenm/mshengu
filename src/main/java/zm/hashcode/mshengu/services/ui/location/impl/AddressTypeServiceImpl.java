/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.location.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.location.AddressType;
import zm.hashcode.mshengu.repository.ui.location.AddressTypeRepository;
import zm.hashcode.mshengu.services.ui.location.AddressTypeService;

/**
 *
 * @author lucky
 */
@Service
public class AddressTypeServiceImpl implements AddressTypeService {

    @Autowired
    private AddressTypeRepository repository;

    @Override
    public List<AddressType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByAddressTypeName()));
    }

    private Sort sortByAddressTypeName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "addressTypeName"));
    }

    @Override
    public void persist(AddressType addressType) {

        repository.save(addressType);

    }

    @Override
    public void merge(AddressType addressType) {
        if (addressType.getId() != null) {
            repository.save(addressType);
        }
    }

    @Override
    public AddressType findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(AddressType addressType) {
        repository.delete(addressType);
    }
}
