/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.procurement;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Luckbliss
 */
@Document
public final class PurchaseOrderNumber implements Serializable, Comparable<PurchaseOrderNumber> {

    @Id
    private String id;
    private Long orderNumber;

    private PurchaseOrderNumber() {
    }

    private PurchaseOrderNumber(Builder builder) {
        this.id = builder.id;
        this.orderNumber = builder.orderNumber;
    }

    public static class Builder {

        private String id;
        private final Long orderNumber;

        public Builder(Long value) {
            this.orderNumber = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public PurchaseOrderNumber build() {
            return new PurchaseOrderNumber(this);
        }
    }

    @Override
    public int compareTo(PurchaseOrderNumber o) {
        return getOrderNumber().compareTo(o.getOrderNumber());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.getId());
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
        final PurchaseOrderNumber other = (PurchaseOrderNumber) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }
}
