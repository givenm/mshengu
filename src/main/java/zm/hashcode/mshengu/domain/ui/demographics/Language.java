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
public final class Language implements Serializable, Comparable<Language> {

    @Id
    private String id;
    private String name;

    private Language() {
    }

    private Language(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    @Override
    public int compareTo(Language o) {
        return name.compareToIgnoreCase(o.name);
    }

    public static class Builder {

        private String id;
        //competency type Computer Skills, specific competencies could include Data Entry, Software Use and Document Formatting
        private final String name;

        public Builder(String value) {
            this.name = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Language build() {
            return new Language(this);
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
        if (!(object instanceof Language)) {
            return false;
        }
        Language other = (Language) object;
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
}
