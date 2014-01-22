/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.ui.util;

import org.springframework.data.repository.PagingAndSortingRepository;
import zm.hashcode.mshengu.domain.ui.util.PaymentMethod;

/**
 *
 * @author Ferox
 */
public interface PaymentMethodRepository extends PagingAndSortingRepository<PaymentMethod, String>{
    public PaymentMethod findByPaymentMethod(String paymentMethod);
}
