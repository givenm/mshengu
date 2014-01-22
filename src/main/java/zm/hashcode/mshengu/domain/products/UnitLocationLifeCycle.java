/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.products;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Ferox
 */
@Document
public class UnitLocationLifeCycle implements Serializable, Comparable<UnitLocationLifeCycle> {

    @Id
    private String id;
    private Date dateofAction;
    private String latitude;
    private String longitude;
    private String parentId;
    private String siteName;

    private UnitLocationLifeCycle() {
    }

    private UnitLocationLifeCycle(Builder builder) {
        this.id = builder.id;
        this.dateofAction = builder.dateofAction;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.parentId = builder.parentId;
        this.siteName = builder.siteName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UnitLocationLifeCycle other = (UnitLocationLifeCycle) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @return the siteName
     */
    public String getSiteName() {
        return siteName;
    }

    public static class Builder {

        private String id;
        private final Date dateofAction;
        private String latitude;
        private String longitude;
        private String parentId;
        private String siteName;

        
        public Builder(Date dateofAction) {
            this.dateofAction = dateofAction;
        }

        public Builder unitLocationLifeCycle(UnitLocationLifeCycle unitLocationLifeCycle) {
            this.id = unitLocationLifeCycle.getId();
//        this.dateofAction = unitLocationLifeCycle.getDateofAction();
            this.latitude = unitLocationLifeCycle.getLatitude();
            this.longitude = unitLocationLifeCycle.getLongitude();
            this.parentId = unitLocationLifeCycle.getParentId();
            this.siteName = unitLocationLifeCycle.getSiteName();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder latitude(String value) {
            this.latitude = value;
            return this;
        }

        public Builder siteName(String value) {
            this.siteName = value;
            return this;
        }

        public Builder longitude(String value) {
            this.longitude = value;
            return this;
        }

        public UnitLocationLifeCycle build() {
            return new UnitLocationLifeCycle(this);
        }
    }

    /**
     * @return the dateofAction
     */
    public Date getDateofAction() {
        return dateofAction;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    @Override
    public int compareTo(UnitLocationLifeCycle o) {
        return -1 * dateofAction.compareTo(o.dateofAction);
    }
}
