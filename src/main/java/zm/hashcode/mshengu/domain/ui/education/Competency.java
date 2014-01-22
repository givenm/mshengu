/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.education;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class Competency implements Serializable, Comparable<Competency> {

    @Id
    private String id;
    //competency type Computer Skills, specific competencies could include Data Entry, Software Use and Document Formatting
    private String name;
    @DBRef
    private CompetencyType competencyType;
    private String notes;

    private Competency() {
    }

    private Competency(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.competencyType = builder.competencyType;
        this.notes = builder.notes;
    }

    public static class Builder {

        private String id;
        //competency type Computer Skills, specific competencies could include Data Entry, Software Use and Document Formatting
        private final String name;
        private CompetencyType competencyType;
        private String notes;

        public Builder(String value) {
            this.name = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder competencyType(CompetencyType value) {
            this.competencyType = value;
            return this;
        }

        public Builder notes(String value) {
            this.notes = value;
            return this;
        }

        public Competency build() {
            return new Competency(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CompetencyType getCompetencyType() {
        return competencyType;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public int compareTo(Competency o) {
        return name.compareToIgnoreCase(o.name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Competency other = (Competency) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
