/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer;

import java.util.List;
import zm.hashcode.mshengu.domain.customer.InvoiceType;

/**
 *
 * @author Luckbliss
 */
public interface InvoiceTypeService {

    public List<InvoiceType> findAll();

    public void persist(InvoiceType invoiceType);

    public void merge(InvoiceType invoiceType);

    public InvoiceType findById(String id);

    public void delete(InvoiceType invoiceType);
}
