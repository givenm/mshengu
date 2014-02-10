/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.customer;

import java.util.List;
import java.util.Set;
import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.customer.InvoiceType;

/**
 *
 * @author Luckbliss
 */
public interface InvoiceTypeRepository extends PagingAndSortingRepository<InvoiceType, String> {
   
}
