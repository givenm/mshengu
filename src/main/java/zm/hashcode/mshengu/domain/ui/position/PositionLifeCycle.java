/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.position;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.mongodb.core.mapping.DBRef;
import zm.hashcode.mshengu.domain.ui.util.Status;

/**
 *
 * @author boniface
 */
public final class PositionLifeCycle implements Serializable, Comparable<PositionLifeCycle> {

    private String id;
    private Date dateofAction;
    @DBRef
    private Status status;

    private PositionLifeCycle() {
    }

    private PositionLifeCycle(Builder builder) {
        this.id = builder.id;
        this.dateofAction = builder.dateofAction;
        this.status = builder.status;
    }

    @Override
    public int compareTo(PositionLifeCycle o) {
        return dateofAction.compareTo(o.dateofAction);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final PositionLifeCycle other = (PositionLifeCycle) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final Date dateofAction;
        private Status status;

        public Builder(Date value) {
            this.dateofAction = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder status(Status value) {
            this.status = value;
            return this;
        }

        public PositionLifeCycle build() {
            return new PositionLifeCycle(this);
        }
    }

    public String getId() {
        return id;
    }

    public Date getDateofAction() {
        return dateofAction;
    }

    public Status getStatus() {
        return status;
    }
}
