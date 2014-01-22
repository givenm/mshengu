/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.location;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.ui.location.AddressType;

/**
 *
 * @author lucky
 */
public interface AddressTypeService {
    public List<AddressType> findAll();
    public void persist(AddressType addressType);
    public void merge(AddressType addressType);
    public AddressType findById(String id);
    public void delete(AddressType addressType);
}
