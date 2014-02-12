/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.location;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.location.Address;

/**
 *
 * @author Ferox
 */
public interface AddressService {
    public List<Address> findAll();
    public void persist(Address address);
    public void merge(Address address);
    public Address findById(String id);
    public void delete(Address address);
}
