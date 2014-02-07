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
import zm.hashcode.mshengu.domain.customer.Invoice;
import zm.hashcode.mshengu.repository.customer.InvoiceRepository;
import zm.hashcode.mshengu.services.customer.InvoiceService;

/**
 *
 * @author Luckbliss
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Override
    public List<Invoice> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortById()));
    }

    @Override
    public void persist(Invoice invoice) {

        repository.save(invoice);

    }

    @Override
    public void merge(Invoice invoice) {
        if (invoice.getId() != null) {
            repository.save(invoice);
        }
    }

    @Override
    public Invoice findById(String id) {
        try {
            return repository.findOne(id);
        } catch (IllegalArgumentException iaEx) {
            return null;
        }
    }

    @Override
    public void delete(Invoice invoice) {
        repository.delete(invoice);
    }

    private Sort sortById() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "id"));
    }
}
