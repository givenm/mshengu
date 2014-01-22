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
 * @author Ferox
 */
 @Document
public final class ServiceRequestType implements Serializable, Comparable<ServiceRequestType> {

    private String id;
    private String name;

    private ServiceRequestType() {
    }

    private ServiceRequestType(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    @Override
    public int compareTo(ServiceRequestType o) {
        return name.compareToIgnoreCase(o.getName());
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final ServiceRequestType other = (ServiceRequestType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    public static class Builder {

        private String id;
        private final String name;

        public Builder(String value) {
            this.name = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }



        public ServiceRequestType build() {
            return new ServiceRequestType(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
