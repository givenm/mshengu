    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.position;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class PositionType implements Serializable, Comparable<PositionType> {

    @Id
    private String id;
    //Full Time, PartTime, Causal , Hourly
    private String name;

    private PositionType() {
    }

    private PositionType(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    @Override
    public int compareTo(PositionType o) {
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
        final PositionType other = (PositionType) obj;
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

        public PositionType build() {
            return new PositionType(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
