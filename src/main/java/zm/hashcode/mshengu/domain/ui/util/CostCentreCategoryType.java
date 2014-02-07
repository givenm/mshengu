/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.util;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Luckbliss
 */
public class CostCentreCategoryType implements Serializable, Comparable<CostCentreCategoryType> {

    @Id
    private String id;
    private String name;
    private Set<ItemCategoryType> itemCategoryTypes;

    private CostCentreCategoryType() {
    }

    private CostCentreCategoryType(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.itemCategoryTypes = builder.itemCategoryTypes;
    }

    @Override
    public int compareTo(CostCentreCategoryType o) {
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
        final CostCentreCategoryType other = (CostCentreCategoryType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private final String name;
        private String id;
        private Set<ItemCategoryType> itemCategoryTypes;

        public Builder(String value) {
            this.name = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder categoryTypes(Set<ItemCategoryType> value) {
            this.itemCategoryTypes = value;
            return this;
        }

        public CostCentreCategoryType build() {
            return new CostCentreCategoryType(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<ItemCategoryType> getItemCategoryTypes() {
        return itemCategoryTypes;
    }
}
