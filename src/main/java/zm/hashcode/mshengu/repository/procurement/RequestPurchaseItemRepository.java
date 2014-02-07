/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.procurement;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;

/**
 *
 * @author Luckbliss
 */
public interface RequestPurchaseItemRepository extends PagingAndSortingRepository<RequestPurchaseItem, String>{
    
}
