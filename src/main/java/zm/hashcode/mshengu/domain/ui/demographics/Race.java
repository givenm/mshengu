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
public final class Race implements Serializable, Comparable<Race> {

    @Id
    private String id;
    private String raceName;

    private Race() {
    }

    private Race(Builder builder) {
        this.id = builder.id;
        this.raceName = builder.raceName;
    }

    @Override
    public int compareTo(Race o) {
        return raceName.compareToIgnoreCase(o.raceName);
    }

    public static class Builder {

        private String id;
        //competency type Computer Skills, specific competencies could include Data Entry, Software Use and Document Formatting
        private final String raceName;

        public Builder(String value) {
            this.raceName = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Race build() {
            return new Race(this);
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
        if (!(object instanceof Race)) {
            return false;
        }
        Race other = (Race) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getRaceName() {
        return raceName;
    }
}
