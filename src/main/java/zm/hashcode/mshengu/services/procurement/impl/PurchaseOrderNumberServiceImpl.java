/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement.impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.procurement.PurchaseOrderNumber;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;
import zm.hashcode.mshengu.repository.procurement.PurchaseOrderNumberRepository;
import zm.hashcode.mshengu.services.procurement.PurchaseOrderNumberService;

/**
 *
 * @author Luckbliss
 */
@Service
public class PurchaseOrderNumberServiceImpl implements PurchaseOrderNumberService {

    @Autowired
    private PurchaseOrderNumberRepository repository;

    @Override
    public List<PurchaseOrderNumber> findAll() {
        return ImmutableList.copyOf(repository.findAll());
    }

    @Override
    public void persist(PurchaseOrderNumber purchaseOrderNumber) {
        repository.save(purchaseOrderNumber);

    }

    @Override
    public void merge(PurchaseOrderNumber purchaseOrderNumber) {
        if (purchaseOrderNumber.getId() != null) {
            repository.save(purchaseOrderNumber);
        }
    }

    @Override
    public PurchaseOrderNumber findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(PurchaseOrderNumber purchaseOrderNumber) {
        repository.delete(purchaseOrderNumber);
    }
}
