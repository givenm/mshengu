/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.fleet;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Ferox
 */
@Document
public class TruckCategory implements Serializable, Comparable<TruckCategory> {

    @Id
    private String id;
    private String categoryName;
    private String namingCode;
    private int value;

    private TruckCategory() {
    }

    private TruckCategory(Builder builder) {
        this.id = builder.id;
        this.categoryName = builder.categoryName;
        this.namingCode = builder.namingCode;
        this.value = builder.value;
    }

    @Override
    public int compareTo(TruckCategory o) {
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
        final TruckCategory other = (TruckCategory) obj;
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

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the namingCode
     */
    public String getNamingCode() {
        return namingCode;
    }

    public static class Builder {

        private String id;
        private final String categoryName;
        private String namingCode;
        private int value;

        public Builder(String categoryName) {
            this.categoryName = categoryName;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public TruckCategory build() {
            return new TruckCategory(this);
        }

        public Builder namingCode(String value) {
            this.namingCode = value;
            return this;
        }

        public Builder value(int value) {
            this.value = value;
            return this;
        }
    }

    public String getId() {
        return id;
    }
}
