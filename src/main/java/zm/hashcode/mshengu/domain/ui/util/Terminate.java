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
 * @author Luckbliss
 */
@Document
public final class Terminate implements Serializable, Comparable<Terminate> {

    @Id
    private String id;
    private String code;
    private String reason;

    private Terminate() {
    }

    private Terminate(Builder builder) {
        this.id = builder.id;
        this.code = builder.code;
        this.reason = builder.reason;
    }

    @Override
    public int compareTo(Terminate o) {
        return code.compareToIgnoreCase(o.code);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Terminate other = (Terminate) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final String code;
        private String reason;

        public Builder(String value) {
            this.code = value;
        }
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder reason(String value) {
            this.reason = value;
            return this;
        }

        public Terminate build() {
            return new Terminate(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }
}
