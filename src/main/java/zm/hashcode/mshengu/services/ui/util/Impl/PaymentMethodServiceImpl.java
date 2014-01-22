/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.ui.util.Impl;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zm.hashcode.mshengu.domain.ui.util.PaymentMethod;
import zm.hashcode.mshengu.repository.ui.util.PaymentMethodRepository;
import zm.hashcode.mshengu.services.ui.util.PaymentMethodService;

/**
 *
 * @author Ferox
 */
@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository repository;

    @Override
    public List<PaymentMethod> findAll() {
        return ImmutableList.copyOf(repository.findAll(sortByPaymentMethod()));
    }

    private Sort sortByPaymentMethod() {
        return new Sort(
                new Sort.Order(Sort.Direction.ASC, "paymentMethod"));
    }

    @Override
    public void persist(PaymentMethod paymentMethod) {
        repository.save(paymentMethod);
    }

    @Override
    public void merge(PaymentMethod paymentMethod) {
        if (paymentMethod.getId() != null) {
            repository.save(paymentMethod);
        }
    }

    @Override
    public PaymentMethod findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(PaymentMethod paymentMethod) {
        repository.delete(paymentMethod);
    }

    @Override
    public PaymentMethod findByPaymentMethod(String paymentMethod) {
        return repository.findByPaymentMethod(paymentMethod);
    }
}
