/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.customer;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class OrderType implements Serializable, Comparable<OrderType>{

    private String id;

    private OrderType() {
    }

    private OrderType(Builder builder) {
        this.id = builder.id;
    }

    @Override
    public int compareTo(OrderType o) {
        return id.compareToIgnoreCase(o.id);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final OrderType other = (OrderType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;

        public Builder(String value) {
            this.id = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public OrderType build() {
            return new OrderType(this);
        }
    }

    public String getId() {
        return id;
    }
}
