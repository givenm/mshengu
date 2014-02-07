/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;
import zm.hashcode.mshengu.repository.procurement.RequestPurchaseItemRepository;
import zm.hashcode.mshengu.services.procurement.RequestPurchaseItemService;

/**
 *
 * @author Luckbliss
 */
@Service
public class RequestPurchaseItemServiceImpl implements RequestPurchaseItemService {

    @Autowired
    private RequestPurchaseItemRepository repository;

    @Override
    public List<RequestPurchaseItem> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByQuantity()));
    }

    @Override
    public void persist(RequestPurchaseItem requestPurchaseItem) {
        repository.save(requestPurchaseItem);

    }

    @Override
    public void merge(RequestPurchaseItem requestPurchaseItem) {
        if (requestPurchaseItem.getId() != null) {
            repository.save(requestPurchaseItem);
        }
    }

    @Override
    public RequestPurchaseItem findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(RequestPurchaseItem requestPurchaseItem) {
        repository.delete(requestPurchaseItem);
    }

    private Sort sortByQuantity() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "quantity"));
    }
}
