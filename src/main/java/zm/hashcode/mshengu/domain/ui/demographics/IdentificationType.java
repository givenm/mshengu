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
public final class IdentificationType implements Serializable, Comparable<IdentificationType> {

    @Id
    private String id;
    private String idvalue;// E.g National ID 
    private String description; // South African Green NAtional ID BOOK

    private IdentificationType() {
    }

    private IdentificationType(Builder builder) {
        this.id = builder.id;
        this.idvalue = builder.idvalue;
        this.description = builder.description;
    }

    @Override
    public int compareTo(IdentificationType o) {
        return idvalue.compareToIgnoreCase(o.idvalue);
    }

    public static class Builder {

        private String id;
        //competency type Computer Skills, specific competencies could include Data Entry, Software Use and Document Formatting
        private final String idvalue;
        private String description;

        public Builder(String value) {
            this.idvalue = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder description(String value) {
            this.description = value;
            return this;
        }

        public IdentificationType build() {
            return new IdentificationType(this);
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
        if (!(object instanceof IdentificationType)) {
            return false;
        }
        IdentificationType other = (IdentificationType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getIdvalue() {
        return idvalue;
    }

    public String getDescription() {
        return description;
    }
}
