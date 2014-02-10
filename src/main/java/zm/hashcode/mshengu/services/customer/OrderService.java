/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer;

import java.util.List;
import zm.hashcode.mshengu.domain.customer.Order;

/**
 *
 * @author Luckbliss
 */
public interface OrderService {

    public List<Order> findAll();

    public void persist(Order order);

    public void merge(Order order);

    public Order findById(String id);

    public void delete(Order order);
}
