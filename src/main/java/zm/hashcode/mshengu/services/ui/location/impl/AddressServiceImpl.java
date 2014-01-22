/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.location.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.location.Address;
import zm.hashcode.mshengu.repository.ui.location.AddressRepository;
import zm.hashcode.mshengu.services.ui.location.AddressService;

/**
 *
 * @author Ferox
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository repository;

    @Override
    public List<Address> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByAddressName()));
    }

    private Sort sortByAddressName() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "streetAddress"));
    }

    @Override
    public void persist(Address address) {

        repository.save(address);

    }

    @Override
    public void merge(Address address) {
        if (address.getId() != null) {
            repository.save(address);
        }
    }

    @Override
    public Address findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Address address) {
        repository.delete(address);
    }
}
