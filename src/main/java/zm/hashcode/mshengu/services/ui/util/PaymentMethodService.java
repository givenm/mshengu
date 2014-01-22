/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util;

import java.util.List;
import zm.hashcode.mshengu.domain.ui.util.PaymentMethod;

/**
 *
 * @author Ferox
 */
public interface PaymentMethodService {
    public List<PaymentMethod> findAll();
    public void persist(PaymentMethod paymentMethod);
    public void merge(PaymentMethod paymentMethod);
    public PaymentMethod findById(String id);
    public void delete(PaymentMethod paymentMethod);
     public PaymentMethod findByPaymentMethod(String paymentMethod);
}
