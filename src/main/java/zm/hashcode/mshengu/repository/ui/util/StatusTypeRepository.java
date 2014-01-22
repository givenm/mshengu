/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.util;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.util.StatusType;

/**
 *
 * @author Ferox
 */
public interface StatusTypeRepository extends PagingAndSortingRepository<StatusType, String> { 
    StatusType findByName(String name); 
}