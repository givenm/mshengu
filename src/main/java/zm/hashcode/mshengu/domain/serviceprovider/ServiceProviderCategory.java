/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.serviceprovider;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Ferox
 */
@Document
public class ServiceProviderCategory implements Serializable, Comparable<ServiceProviderCategory> {

    @Id
    private String id;
    private String categoryName;

    private ServiceProviderCategory() {
    }

    private ServiceProviderCategory(Builder builder) {
        this.id = builder.id;
        this.categoryName = builder.categoryName;
    }

    @Override
    public int compareTo(ServiceProviderCategory o) {
        return getCategoryName().compareTo(o.getCategoryName());
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
        final ServiceProviderCategory other = (ServiceProviderCategory) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    public static class Builder {

        private String id;
        private final String categoryName;

        public Builder(String categoryName) {
            this.categoryName = categoryName;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public ServiceProviderCategory build() {
            return new ServiceProviderCategory(this);
        }
    }

    public String getId() {
        return id;
    }

}
