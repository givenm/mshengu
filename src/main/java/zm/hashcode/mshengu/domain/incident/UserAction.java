/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.incident;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.ui.util.Status;

/**
 *
 * @author Ferox
 */
@Document
public final class UserAction implements Serializable, Comparable<UserAction> {

    private String id;
    private Date actionDate;
    @DBRef(lazy = true)
    private Status userActionStatus;//(ongoing outstanding, resolved) 
    private String comment;
    private Date resolvedDate;
    private Date qualityAssuranceDate;
    @DBRef(lazy = true)
    private Person staff;
    //date,status (ongoing outstanding, resolved) ,resolved date,qa date, completed date, comments

    private UserAction() {
    }

    private UserAction(Builder builder) {
        this.id = builder.id;
        this.actionDate = builder.actionDate;
        this.userActionStatus = builder.userActionStatus;
        this.comment = builder.comment;
        this.resolvedDate = builder.resolvedDate;
        this.qualityAssuranceDate = builder.qualityAssuranceDate;
        this.staff = builder.staff;
    }

    @Override
    public int compareTo(UserAction o) {
        return -1 * actionDate.compareTo(o.actionDate);
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
        final UserAction other = (UserAction) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the actionDate
     */
    public Date getActionDate() {
        return actionDate;
    }

    /**
     * @return the userActionStatus
     */
    public Status getUserActionStatus() {
        return userActionStatus;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @return the resolvedDate
     */
    public Date getResolvedDate() {
        return resolvedDate;
    }

    /**
     * @return the qualityAssuranceDate
     */
    public Date getQualityAssuranceDate() {
        return qualityAssuranceDate;
    }

    /**
     * @return the staff
     */
    public Person getStaff() {
        return staff;
    }

    public static class Builder {

        private String id;
        private final Date actionDate;
        private Status userActionStatus;//(ongoing outstanding, resolved) 
        private String comment;
        private Date resolvedDate;
        private Date qualityAssuranceDate;
        private Person staff;

        public Builder(Date actionDate) {
            this.actionDate = actionDate;
        }

        public Builder userAction(UserAction userAction) {
            this.id = userAction.id;
//            this.actionDate = builder.actionDate;
            this.userActionStatus = userAction.getUserActionStatus();
            this.comment = userAction.getComment();
            this.resolvedDate = userAction.getResolvedDate();
            this.qualityAssuranceDate = userAction.getQualityAssuranceDate();
            this.staff = userAction.getStaff();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder UserActionStatus(Status value) {
            this.userActionStatus = value;
            return this;
        }

        public Builder resolvedDate(Date value) {
            this.resolvedDate = value;
            return this;
        }

        public Builder qualityAssuranceDate(Date value) {
            this.qualityAssuranceDate = value;
            return this;
        }

        public Builder comment(String value) {
            this.comment = value;
            return this;
        }

        public Builder staff(Person value) {
            this.staff = value;
            return this;
        }

        public UserAction build() {
            return new UserAction(this);
        }
    }

    public String getId() {
        return id;
    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getUserActionStatusId() {
        if (!isNullObject(userActionStatus)) {
            return userActionStatus.getId();
        } else {
            return null;
        }
    }

    public String getUserActionStatusName() {
        if (!isNullObject(userActionStatus)) {
            return userActionStatus.getName();
        } else {
            return null;
        }
    }

    public String geStaffId() {
        if (!isNullObject(staff)) {
            return staff.getId();
        } else {
            return null;
        }
    }

    public String getStaffName() {
        if (!isNullObject(staff)) {
            return staff.getFirstname() + " " + staff.getLastname();
        } else {
            return null;
        }
    }
}
