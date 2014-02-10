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
public class ServiceProviderProductCategory implements Serializable, Comparable<ServiceProviderProductCategory> {

    @Id
    private String id;
    private String categoryName;

    private ServiceProviderProductCategory() {
    }

    private ServiceProviderProductCategory(Builder builder) {
        this.id = builder.id;
        this.categoryName = builder.categoryName;
    }

    @Override
    public int compareTo(ServiceProviderProductCategory o) {
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
        final ServiceProviderProductCategory other = (ServiceProviderProductCategory) obj;
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

        public ServiceProviderProductCategory build() {
            return new ServiceProviderProductCategory(this);
        }
    }

    public String getId() {
        return id;
    }

}
