/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.people;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.mongodb.core.mapping.DBRef;
import zm.hashcode.mshengu.domain.ui.education.Degree;

/**
 *
 * @author boniface
 */
public final class PersonEducation implements Serializable {

    @DBRef
    private Degree degree;
    private String institution;
    private Date graduation;

    private PersonEducation() {
    }

    private PersonEducation(Builder builder) {
        this.degree = builder.degree;
        this.institution = builder.institution;
        this.graduation = builder.graduation;

    }

    public static class Builder {

        private final Degree degree;
        private String institution;
        private Date graduation;

        public Builder(Degree value) {
            this.degree = value;
        }

        public Builder institution(String value) {
            this.institution = value;
            return this;
        }

        public Builder graduation(Date value) {
            this.graduation = value;
            return this;
        }

        public PersonEducation build() {
            return new PersonEducation(this);
        }
    }

    public Degree getDegree() {
        return degree;
    }

    public String getInstitution() {
        return institution;
    }

    public Date getGraduation() {
        return graduation;
    }
}
