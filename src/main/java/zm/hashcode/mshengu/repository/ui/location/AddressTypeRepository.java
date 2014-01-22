/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.location;

import java.util.List;
import java.util.Set;
import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.location.AddressType;

/**
 *
 * @author lucky
 */
public interface AddressTypeRepository extends PagingAndSortingRepository<AddressType, String>{
    
}
