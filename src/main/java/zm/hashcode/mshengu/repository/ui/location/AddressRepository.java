/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.location;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.location.Address;

/**
 *
 * @author Ferox
 */
public interface AddressRepository extends PagingAndSortingRepository<Address, String>{
    
}
