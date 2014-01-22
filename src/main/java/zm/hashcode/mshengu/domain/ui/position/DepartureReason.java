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
public final class DepartureReason implements Serializable, Comparable<DepartureReason> {

    @Id
    private String id;
    private String reasonName;
    private String description;

    private DepartureReason() {
    }

    private DepartureReason(Builder builder) {
        this.id = builder.id;
        this.reasonName = builder.reasonName;
        this.description = builder.description;
    }

    @Override
    public int compareTo(DepartureReason o) {
        return reasonName.compareToIgnoreCase(o.reasonName);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final DepartureReason other = (DepartureReason) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private final String reasonName;
        private String id;
        private String description;

        public Builder(String value) {
            this.reasonName = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder description(String value) {
            this.description = value;
            return this;
        }

        public DepartureReason build() {
            return new DepartureReason(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getReasonName() {
        return reasonName;
    }

    public String getDescription() {
        return description;
    }
}
