/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.customer;

import java.util.List;
import zm.hashcode.mshengu.domain.customer.Invoice;

/**
 *
 * @author Luckbliss
 */
public interface InvoiceService {

    public List<Invoice> findAll();

    public void persist(Invoice invoice);

    public void merge(Invoice invoice);

    public Invoice findById(String id);

    public void delete(Invoice invoice);
}
