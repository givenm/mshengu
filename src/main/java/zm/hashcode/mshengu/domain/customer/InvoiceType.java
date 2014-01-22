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
public final class InvoiceType implements Serializable, Comparable<InvoiceType> {

    private String id;
    private String type;

    private InvoiceType() {
    }

    private InvoiceType(Builder builder) {
        this.type = builder.type;
        this.id = builder.id;
    }

    @Override
    public int compareTo(InvoiceType o) {
        return type.compareToIgnoreCase(o.type);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final InvoiceType other = (InvoiceType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final String type;

        public Builder(String value) {
            this.type = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public InvoiceType build() {
            return new InvoiceType(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
