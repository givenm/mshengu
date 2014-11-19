/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.external;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.incident.UserAction;

/**
 *
 * @author Ferox
 */
@Document
public class ContactUS implements Serializable, Comparable<ContactUS> {

    @Id
    private String id;
    private String refNumber;
    private String contactPersonFirstname;
    private String contactPersonLastname;
    private String company;
    private String email;
    private String phone;
    private String faxNumber;
    private String hearAboutUs;
    private String message;
    private Date dateOfAction;
    private boolean closed;
    @DBRef(lazy = true)
    private MailNotifications mailNotifications;
    @DBRef(lazy = true)
    private Set<UserAction> userAction = new HashSet<>();

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getContactPersonFirstname() {
        return contactPersonFirstname;
    }

    public void setContactPersonFirstname(String contactPersonFirstname) {
        this.contactPersonFirstname = contactPersonFirstname;
    }

    public String getContactPersonLastname() {
        return contactPersonLastname;
    }

    public void setContactPersonLastname(String contactPersonLastname) {
        this.contactPersonLastname = contactPersonLastname;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the faxNumber
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * @return the hearAboutUs
     */
    public String getHearAboutUs() {
        return hearAboutUs;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the dateOfAction
     */
    public Date getDateOfAction() {
        return dateOfAction;
    }

    /**
     * @return the mailNotifications
     */
    public MailNotifications getMailNotifications() {
        return mailNotifications;
    }

    @Override
    public int compareTo(ContactUS o) {
        return -1 * dateOfAction.compareTo(o.getDateOfAction());
    }

    /**
     * @return the userAction
     */
    private List<UserAction> sortUserActions() {
        List<UserAction> userActionList = new ArrayList<>();
        userActionList.addAll(userAction);
        Collections.sort(userActionList);
        return userActionList;
    }

    public Set<UserAction> getUserAction() {
        if (userAction != null) {
            return ImmutableSet.copyOf(sortUserActions());
        } else {
            return null;
        }
    }

    public UserAction getLastUserAction() {
        if (userAction != null) {
            if (userAction.size() > 0) {
                return sortUserActions().get(0);
            }
        }

        return null;
    }

    private ContactUS() {
    }

    public ContactUS(Builder builder) {
        this.id = builder.id;
        this.contactPersonFirstname = builder.contactPersonFirstname;
        this.contactPersonLastname = builder.contactPersonLastname;
        this.company = builder.company;
        this.email = builder.email;
        this.phone = builder.phone;
        this.faxNumber = builder.faxNumber;
        this.hearAboutUs = builder.hearAboutUs;
        this.message = builder.message;
        this.dateOfAction = builder.dateOfAction;
        this.mailNotifications = builder.mailNotifications;
        this.userAction = builder.userAction;
        this.closed = builder.closed;
        this.refNumber = builder.refNumber;
    }

    /**
     * @return the closed
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * @return the refNumber
     */
    public String getRefNumber() {
        return refNumber;
    }

    public static class Builder {

        private String id;
        private String contactPersonFirstname;
        private String contactPersonLastname;
        private String company;
        private String email;
        private String phone;
        private String faxNumber;
        private String hearAboutUs;
        private String message;
        private String reason;
        private final Date dateOfAction;
        private MailNotifications mailNotifications;
        private Set<UserAction> userAction = new HashSet<>();
        private boolean closed;
        private String refNumber;

        public Builder(Date value) {
            this.dateOfAction = value;
        }

        public Builder contactUS(ContactUS contactUS) {
            this.id = contactUS.id;
            this.contactPersonFirstname = contactUS.contactPersonFirstname;
            this.contactPersonLastname = contactUS.contactPersonLastname;
            this.company = contactUS.company;
            this.email = contactUS.email;
            this.phone = contactUS.phone;
            this.faxNumber = contactUS.faxNumber;
            this.hearAboutUs = contactUS.hearAboutUs;
            this.message = contactUS.message;
            this.mailNotifications = contactUS.mailNotifications;
            this.userAction = contactUS.getUserAction();
            this.closed = contactUS.isClosed();
            this.refNumber = contactUS.getRefNumber();
//        this.dateOfAction =  contactUS.dateOfAction;
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder refNumber(String value) {
            this.refNumber = value;
            return this;
        }

        public Builder contactPersonFirstname(String value) {
            this.contactPersonFirstname = value;
            return this;
        }

        public Builder contactPersonLastname(String value) {
            this.contactPersonLastname = value;
            return this;
        }

        public Builder company(String value) {
            this.company = value;
            return this;
        }

        public Builder email(String value) {
            this.email = value;
            return this;
        }

        public Builder phone(String value) {
            this.phone = value;
            return this;
        }

        public Builder faxNumber(String value) {
            this.faxNumber = value;
            return this;
        }

        public Builder hearAboutUs(String value) {
            this.hearAboutUs = value;
            return this;
        }

        public Builder message(String value) {
            this.message = value;
            return this;
        }

        public Builder reason(String value) {
            this.reason = value;
            return this;
        }

        public Builder closed(boolean value) {
            this.closed = value;
            return this;
        }

        public Builder mailNotifications(MailNotifications value) {
            this.mailNotifications = value;
            return this;
        }

        public Builder userAction(Set<UserAction> value) {
            this.userAction = value;
            return this;
        }

        public ContactUS build() {
            return new ContactUS(this);
        }
    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getLastUserActionStatusId() {
        if (!isNullObject(getLastUserAction())) {
            UserAction lastUserActionStatus = getLastUserAction();
            return lastUserActionStatus.getId();
        } else {
            return null;
        }
    }

    public String getLastUserActionStatusName() {
        if (!isNullObject(getLastUserAction())) {
            UserAction lastUserActionStatus = getLastUserAction();
            return lastUserActionStatus.getUserActionStatusName();
        } else {
            return null;
        }
    }

    public String getMailNotificationsId() {
        if (!isNullObject(mailNotifications)) {
            return mailNotifications.getId();
        } else {
            return null;
        }
    }

    public String getMailNotificationsName() {
        if (!isNullObject(mailNotifications)) {
            return mailNotifications.getName();
        } else {
            return null;
        }
    }
}