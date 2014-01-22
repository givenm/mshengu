/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.incident;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Ferox
 */
 @Document
public final class IncidentActionStatus implements Serializable, Comparable<IncidentActionStatus> {

    private String id;
    private String name;

    private IncidentActionStatus() {
    }

    private IncidentActionStatus(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    @Override
    public int compareTo(IncidentActionStatus o) {
        return name.compareToIgnoreCase(o.name);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final IncidentActionStatus other = (IncidentActionStatus) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    public static class Builder {

        private String id;
        private final String name;

        public Builder(String value) {
            this.name = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }



        public IncidentActionStatus build() {
            return new IncidentActionStatus(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
