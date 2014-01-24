/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.customer.OrderType;
import zm.hashcode.mshengu.repository.customer.OrderTypeRepository;
import zm.hashcode.mshengu.services.customer.OrderTypeService;

/**
 *
 * @author Luckbliss
 */
@Service
public class OrderTypeServiceImpl implements OrderTypeService {

    @Autowired
    private OrderTypeRepository repository;

    @Override
    public List<OrderType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortById()));
    }

    @Override
    public void persist(OrderType orderType) {

        repository.save(orderType);

    }

    @Override
    public void merge(OrderType orderType) {
        if (orderType.getId() != null) {
            repository.save(orderType);
        }
    }

    @Override
    public OrderType findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(OrderType orderType) {
        repository.delete(orderType);
    }
     private Sort sortById() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "id"));
    }
}
