/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.procurement;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.procurement.ResponseToRFQ;

/**
 *
 * @author Luckbliss
 */
public interface ResponseToRFQRepository extends PagingAndSortingRepository<ResponseToRFQ, String>{
    
}
