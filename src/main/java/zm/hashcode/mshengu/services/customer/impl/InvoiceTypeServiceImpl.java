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
import zm.hashcode.mshengu.domain.customer.InvoiceType;
import zm.hashcode.mshengu.repository.customer.InvoiceTypeRepository;
import zm.hashcode.mshengu.services.customer.InvoiceTypeService;

/**
 *
 * @author Luckbliss
 */
@Service
public class InvoiceTypeServiceImpl implements InvoiceTypeService {

    @Autowired
    private InvoiceTypeRepository repository;

    @Override
    public List<InvoiceType> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByType()));
    }

    @Override
    public void persist(InvoiceType invoiceType) {

        repository.save(invoiceType);

    }

    @Override
    public void merge(InvoiceType invoiceType) {
        if (invoiceType.getId() != null) {
            repository.save(invoiceType);
        }
    }

    @Override
    public InvoiceType findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(InvoiceType invoiceType) {
        repository.delete(invoiceType);
    }

    private Sort sortByType() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "type"));
    }
}
