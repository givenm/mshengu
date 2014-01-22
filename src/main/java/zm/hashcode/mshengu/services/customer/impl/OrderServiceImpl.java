/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.customer.Order;
import zm.hashcode.mshengu.repository.customer.OrderRepository;
import zm.hashcode.mshengu.services.customer.OrderService;

/**
 *
 * @author Luckbliss
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public List<Order> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByStartDate()));
    }

    @Override
    public void persist(Order order) {

        repository.save(order);

    }

    @Override
    public void merge(Order order) {
        if (order.getId() != null) {
            repository.save(order);
        }
    }

    @Override
    public Order findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Order order) {
        repository.delete(order);
    }

    private Sort sortByStartDate() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "startDate"));
    }
}
