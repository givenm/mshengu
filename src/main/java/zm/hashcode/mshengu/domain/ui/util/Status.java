/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.util;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public final class Status implements Serializable, Comparable<Status> {

    @Id
    private String id;
    private StatusType statusType;
    private String name;

    private Status() {
    }

    private Status(Builder builder) {
        id = builder.id;
        statusType = builder.statusType;
        name = builder.name;
    }

    /**
     * @return the statusType
     */
    public StatusType getStatusType() {
        return statusType;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public static class Builder {

        private String id;
        private StatusType statusType;
        private final String name;

        public Builder(String statusName) {
            this.name = statusName;
        }

        public Status.Builder status(Status status) {
            this.id = status.getId();
            this.statusType = status.getStatusType();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder statusType(StatusType value) {
            this.statusType = value;
            return this;
        }

        public Status build() {
            return new Status(this);
        }
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Status o) {
        return getName().compareToIgnoreCase(o.getName());
    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getStatusTypeName() {
        if (!isNullObject(statusType)) {
            return statusType.getName();
        } else {
            return null;
        }
    }

    public String getStatusTypeId() {
        if (!isNullObject(statusType)) {
            return statusType.getId();
        } else {
            return null;
        }
    }
}
