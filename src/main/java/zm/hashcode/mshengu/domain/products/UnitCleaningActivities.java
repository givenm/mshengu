/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.products;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class UnitCleaningActivities implements Serializable, Comparable<UnitCleaningActivities> {

    private String id;
    private String name;
    private String parentId;

    private UnitCleaningActivities() {
    }

    private UnitCleaningActivities(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.parentId = builder.parentId;
    }

    @Override
    public int compareTo(UnitCleaningActivities o) {
        return name.compareToIgnoreCase(o.name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final UnitCleaningActivities other = (UnitCleaningActivities) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    public static class Builder {

        private String id;
        private final String name;
        private String parentId;

        public Builder(String value) {
            this.name = value;
        }

        public Builder unitCleaningActivities(UnitCleaningActivities unitCleaningActivities) {
            this.id = unitCleaningActivities.getId();
//        this.name = unitCleaningActivities.name;
            this.parentId = unitCleaningActivities.getParentId();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public UnitCleaningActivities build() {
            return new UnitCleaningActivities(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
