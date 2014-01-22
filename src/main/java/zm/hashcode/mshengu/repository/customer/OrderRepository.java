/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.customer;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.customer.Order;

/**
 *
 * @author Luckbliss
 */
public interface OrderRepository extends PagingAndSortingRepository<Order, String> {

    
}
