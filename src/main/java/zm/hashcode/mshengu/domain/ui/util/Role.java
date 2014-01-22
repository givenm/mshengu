/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.util;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class Role implements Serializable, Comparable<Role> {

    @Id
    private String id;
    @Indexed
    private String rolename;
    private String description;

    private Role() {
    }

    private Role(Builder builder) {
        this.id = builder.id;
        this.rolename = builder.rolename;
        this.description = builder.description;
    }

    public static class Builder {

        private String id;
        private final String rolename;
        private String description;

        public Builder(String rolename) {
            this.rolename = rolename;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder description(String value) {
            this.description = value;
            return this;
        }

        public Role build() {
            return new Role(this);
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
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Role o) {
        return rolename.compareToIgnoreCase(o.rolename);
    }

    public String getId() {
        return id;
    }

    public String getRolename() {
        return rolename;
    }

    public String getDescription() {
        return description;
    }
}
