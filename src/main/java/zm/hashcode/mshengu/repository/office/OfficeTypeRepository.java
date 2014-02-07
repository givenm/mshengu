/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.office;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.office.OfficeType;

/**
 *
 * @author lucky
 */
public interface OfficeTypeRepository extends PagingAndSortingRepository<OfficeType, String>{
    
}
