/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.location;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class Location implements Serializable, Comparable<Location> {

    @Id
    private String id;
    private String name;
    private String code;
    private String latitude;
    private String longitude;
    @DBRef
    private LocationType locationType;
    @DBRef(lazy = true)
    private Set<Location> children;
    @DBRef
    private Location parent;

    private Location() {
    }

    private Location(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.code = builder.code;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.locationType = builder.locationType;
        this.children = builder.children;
        this.parent = builder.parent;
    }

    @Override
    public int compareTo(Location o) {
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
        final Location other = (Location) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final String name;
        private String code;
        private String latitude;
        private String longitude;
        private LocationType locationType;
        private Set<Location> children;
        private Location parent;

        public Builder(String name) {
            this.name = name;
        }

        public Builder location(Location location) {
            this.id = location.getId();
            this.code = location.getCode();
            this.latitude = location.getLatitude();
            this.longitude = location.getLongitude();
            this.locationType = location.getLocationType();
            this.children = location.getChildren();
            this.parent = location.getParent();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder code(String value) {
            this.code = value;
            return this;
        }

        public Builder latitude(String value) {
            this.latitude = value;
            return this;
        }

        public Builder longitude(String value) {
            this.longitude = value;
            return this;
        }

        public Builder locationType(LocationType value) {
            this.locationType = value;
            return this;
        }

        public Builder children(Set<Location> value) {
            this.children = value;
            return this;
        }

        public Builder parent(Location value) {
            this.parent = value;
            return this;
        }

        public Location build() {
            return new Location(this);
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

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public Set<Location> getChildren() {
        return ImmutableSet.copyOf(children);
    }

    public Location getParent() {
        return parent;
    }
    
    
    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

       public String getLocationTypeId() {
        if (!isNullObject(locationType)) {
            return locationType.getId();
        } else {
            return null;
        }
    }

    public String getLocationTypeName() {
        if (!isNullObject(locationType)) {
            return locationType.getName();
        } else {
            return null;
        }
    }

    public String getParentLocationId() {
        if (!isNullObject(parent)) {
            return parent.getId();
        } else {
            return null;
        }
    }

    public String getParentLocationName() {
        if (!isNullObject(parent)) {
            return parent.getName();
        } else {
            return null;
        }
    }

    public String getParentLocationLatitude() {
        if (!isNullObject(parent)) {
            return parent.getLatitude();
        } else {
            return null;
        }
    }

    public String getParentLocationLongitude() {
        if (!isNullObject(parent)) {
            return parent.getLongitude();
        } else {
            return null;
        }
    }
}
