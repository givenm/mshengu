/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.customer;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class ContractType implements Serializable, Comparable<ContractType> {

    @Id
    private String id;
    private String type;

    private ContractType() {
    }

    private ContractType(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
    }

    @Override
    public int compareTo(ContractType o) {
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
        final ContractType other = (ContractType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    public static class Builder {

        private String id;
        private final String type;

        public Builder(String type) {
            this.type = type;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public ContractType build() {
            return new ContractType(this);
        }
    }

    public String getId() {
        return id;
    }
}
