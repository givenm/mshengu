/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.location;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class AddressType implements Serializable, Comparable<AddressType> {

    @Id
    private String id;
    //Home Address or Work Address 
    @NotNull
    private String addressTypeName;

    private AddressType() {
    }

    private AddressType(Builder builder) {
        this.id = builder.id;
        this.addressTypeName = builder.addressTypeName;
    }

    @Override
    public int compareTo(AddressType o) {
        return addressTypeName.compareToIgnoreCase(o.addressTypeName);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final AddressType other = (AddressType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final String addressTypeName;

        public Builder(String value) {
            this.addressTypeName = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public AddressType build() {
            return new AddressType(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getAddressTypeName() {
        return addressTypeName;
    }
}
