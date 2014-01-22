/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.office;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class Department implements Serializable, Comparable<Department> {

    @Id
    private String id;
    private String name;
    private String description;
    private boolean active;
    private Date dateEstablished;

    private Department() {
    }

    private Department(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.active = builder.active;
        this.dateEstablished = builder.dateEstablished;
    }

    @Override
    public int compareTo(Department o) {
        return name.compareToIgnoreCase(o.name);
    }

    public static class Builder {

        private String id;
        //competency type Computer Skills, specific competencies could include Data Entry, Software Use and Document Formatting
        private final String name;
        private String description;
        private boolean active;
        private Date dateEstablished;

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

        public Builder active(boolean value) {
            this.active = value;
            return this;
        }

        public Builder dateEstablished(Date value) {
            this.dateEstablished = value;
            return this;
        }

        public Department build() {
            return new Department(this);
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
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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

    public boolean isActive() {
        return active;
    }

    public Date getDateEstablished() {
        return dateEstablished;
    }
}
