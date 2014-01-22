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
public final class Degree implements Serializable, Comparable<Degree> {

    @Id
    private String id;
    private String studyField;
    @DBRef
    private EducationType educationType;
    //Bachelor , Diploma , Masters , pHD
    private String degreeName;

    private Degree() {
    }

    private Degree(Builder builder) {
        this.id = builder.id;
        this.studyField = builder.studyField;
        this.educationType = builder.educationType;
        this.degreeName = builder.degreeName;
    }

    @Override
    public int compareTo(Degree o) {
        return studyField.compareToIgnoreCase(o.studyField);
    }

    public static class Builder {

        private String id;
        private String degreeName;
        private String studyField;
        private EducationType educationType;

        public Builder(String value) {
            this.degreeName = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder studyField(String value) {
            this.studyField = value;
            return this;
        }

        public Builder educationType(EducationType value) {
            this.educationType = value;
            return this;
        }

        public Degree build() {
            return new Degree(this);
        }
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
        final Degree other = (Degree) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getStudyField() {
        return studyField;
    }

    public EducationType getEducationType() {
        return educationType;
    }

    public String getDegreeName() {
        return degreeName;
    }
}
