    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.util;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class Country implements Serializable, Comparable<Country> {

    @Id
    private String id;
    //Full Time, PartTime, Causal , Hourly
    private String name;
    private String nationality;

    private Country() {
    }

    private Country(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.nationality = builder.nationality;
    }

    @Override
    public int compareTo(Country o) {
        return name.compareToIgnoreCase(o.name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Country other = (Country) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    public static class Builder {

        private String id;
        private final String name;
        private String nationality;

        public Builder(String name) {
            this.name = name;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder nationality(String value) {
            this.nationality = value;
            return this;
        }

        public Country build() {
            return new Country(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
