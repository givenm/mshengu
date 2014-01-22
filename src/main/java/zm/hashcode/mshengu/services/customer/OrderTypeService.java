/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer;

import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.domain.customer.OrderType;

/**
 *
 * @author Luckbliss
 */
public interface OrderTypeService {

    public List<OrderType> findAll();

    public void persist(OrderType orderType);

    public void merge(OrderType orderType);

    public OrderType findById(String id);

    public void delete(OrderType orderType);
}
