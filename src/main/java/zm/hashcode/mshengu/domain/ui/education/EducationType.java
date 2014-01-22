/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.education;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class EducationType implements Serializable, Comparable<EducationType> {

    @Id
    private String id;
    //Postgraduate, Undergraduate, College, Highschool
    private String name;

    private EducationType() {
    }

    private EducationType(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    @Override
    public int compareTo(EducationType o) {
        return name.compareToIgnoreCase(o.name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final EducationType other = (EducationType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        //Postgraduate, Undergraduate, College, Highschool
        private final String name;

        public Builder(String value) {
            this.name = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public EducationType build() {
            return new EducationType(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
