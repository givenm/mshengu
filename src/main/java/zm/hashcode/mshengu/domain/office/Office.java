/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.office;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.ui.location.Location;
import zm.hashcode.mshengu.domain.ui.location.LocationAddress;
import zm.hashcode.mshengu.domain.ui.position.Position;

/**
 *
 * @author boniface
 */
@Document
public final class Office implements Serializable, Comparable<Office> {

    @Id
    private String id;
    private String name;
    private String description;
    private String active;
    private Date dateEstablished;
    @DBRef
    private OfficeType officeType;
    @DBRef
    private Location city;
    private LocationAddress contact;
    @DBRef
    private Set<Position> positions;

    private Office() {
    }

    private Office(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.active = builder.active;
        this.dateEstablished = builder.dateEstablished;
        this.officeType = builder.officeType;
        this.city = builder.city;
        this.contact = builder.contact;
        this.positions = builder.positions;
    }

    public static class Builder {

        private String id;
        //competency type Computer Skills, specific competencies could include Data Entry, Software Use and Document Formatting
        private final String name;
        private String description;
        private String active;
        private Date dateEstablished;
        private OfficeType officeType;
        private Location city;
        private LocationAddress contact;
        private Set<Position> positions;

        public Builder(String value) {
            this.name = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder description(String value) {
            this.description = value;
            return this;
        }

        public Builder active(String value) {
            this.active = value;
            return this;
        }

        public Builder dateEstablished(Date value) {
            this.dateEstablished = value;
            return this;
        }

        public Builder officeType(OfficeType value) {
            this.officeType = value;
            return this;
        }

        public Builder city(Location value) {
            this.city = value;
            return this;
        }

        public Builder contact(LocationAddress value) {
            this.contact = value;
            return this;
        }

        public Builder positions(Set<Position> value) {
            this.positions = value;
            return this;
        }

        public Office build() {
            return new Office(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Office)) {
            return false;
        }
        Office other = (Office) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Office o) {
        return name.compareTo(o.name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getActive() {
        return active;
    }

    public Date getDateEstablished() {
        return dateEstablished;
    }

    public OfficeType getOfficeType() {
        return officeType;
    }

    public Location getCity() {
        return city;
    }

    public LocationAddress getContact() {
        return contact;
    }

    public Set<Position> getPositions() {
        return ImmutableSet.copyOf(positions);
    }
}
