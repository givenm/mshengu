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
public final class Invoice implements Serializable, Comparable<Invoice> {

    private String id;

    private Invoice() {
    }

    private Invoice(Builder builder) {
        this.id = builder.id;
    }

    @Override
    public int compareTo(Invoice o) {
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
        final Invoice other = (Invoice) obj;
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

        public Invoice build() {
            return new Invoice(this);
        }
    }

    public String getId() {
        return id;
    }
}
