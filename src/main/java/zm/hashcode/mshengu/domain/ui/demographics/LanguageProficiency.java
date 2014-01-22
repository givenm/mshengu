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
 * @author stud
 */
@Document
public final class LanguageProficiency implements Serializable, Comparable<LanguageProficiency> {

    @Id
    private String id;
    private String proficiency;

    private LanguageProficiency() {
    }

    private LanguageProficiency(Builder builder) {
        this.id = builder.id;
        this.proficiency = builder.proficiency;
    }

    @Override
    public int compareTo(LanguageProficiency o) {
        return proficiency.compareToIgnoreCase(o.proficiency);
    }

    public static class Builder {

        private String id;
        //competency type Computer Skills, specific competencies could include Data Entry, Software Use and Document Formatting
        private final String proficiency;

        public Builder(String value) {
            this.proficiency = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public LanguageProficiency build() {
            return new LanguageProficiency(this);
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
        if (!(object instanceof LanguageProficiency)) {
            return false;
        }
        LanguageProficiency other = (LanguageProficiency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getProficiency() {
        return proficiency;
    }
}
