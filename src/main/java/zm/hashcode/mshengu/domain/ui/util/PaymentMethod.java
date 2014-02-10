/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.util;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Ferox
 */
@Document
public class PaymentMethod implements Serializable, Comparable<PaymentMethod> {

    @Id
    private String id;
    private String paymentMethod;

    private PaymentMethod() {
    }

    private PaymentMethod(Builder builder) {
        this.id = builder.id;
        this.paymentMethod = builder.paymentMethod;
    }

    @Override
    public int compareTo(PaymentMethod o) {
        return paymentMethod.compareTo(o.paymentMethod);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PaymentMethod other = (PaymentMethod) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public static class Builder {

        private String id;
        private final String paymentMethod;

        public Builder(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public PaymentMethod build() {
            return new PaymentMethod(this);
        }
    }

    public String getId() {
        return id;
    }

}
