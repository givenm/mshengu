/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.util;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Luckbliss
 */
@Document
public class CostCentreType implements Serializable, Comparable<CostCentreType> {

    @Id
    private String id;
    private String name;
    private Set<CostCentreCategoryType> categoryTypes;

    private CostCentreType() {
    }

    private CostCentreType(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.categoryTypes = builder.categoryTypes;
    }

    @Override
    public int compareTo(CostCentreType o) {
        return name.compareToIgnoreCase(o.name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final CostCentreType other = (CostCentreType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private final String name;
        private String id;
        private Set<CostCentreCategoryType> categoryTypes;

        public Builder(String value) {
            this.name = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder costCentreType(CostCentreType type) {
            this.id = type.getId();
            this.categoryTypes = type.getCategoryTypes();
            return this;
        }

        public Builder categoryTypes(Set<CostCentreCategoryType> value) {
            this.categoryTypes = value;
            return this;
        }

        public CostCentreType build() {
            return new CostCentreType(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<CostCentreCategoryType> getCategoryTypes() {
        return categoryTypes;
    }
}
