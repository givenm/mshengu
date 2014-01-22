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
public final class LocationType implements Serializable, Comparable<LocationType> {

    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String code;

    private LocationType() {
    }

    private LocationType(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.code = builder.code;
    }

    @Override
    public int compareTo(LocationType o) {
        return name.compareToIgnoreCase(o.name);
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
        final LocationType other = (LocationType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final String name;
        private String code;

        public Builder(String name) {
            this.name = name;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder code(String value) {
            this.code = value;
            return this;
        }

        public LocationType build() {
            return new LocationType(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
