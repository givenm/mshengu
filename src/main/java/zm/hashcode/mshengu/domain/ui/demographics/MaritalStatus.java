/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.demographics;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class MaritalStatus implements Serializable, Comparable<MaritalStatus> {

    @Id
    private String id;
    private String statusName;

    private MaritalStatus() {
    }

    private MaritalStatus(Builder builder) {
        this.id = builder.id;
        this.statusName = builder.statusName;
    }

    @Override
    public int compareTo(MaritalStatus o) {
        return statusName.compareToIgnoreCase(o.statusName);
    }

    public static class Builder {

        private String id;
        //competency type Computer Skills, specific competencies could include Data Entry, Software Use and Document Formatting
        private final String statusName;

        public Builder(String value) {
            this.statusName = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public MaritalStatus build() {
            return new MaritalStatus(this);
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
        if (!(object instanceof MaritalStatus)) {
            return false;
        }
        MaritalStatus other = (MaritalStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getStatusName() {
        return statusName;
    }
}
