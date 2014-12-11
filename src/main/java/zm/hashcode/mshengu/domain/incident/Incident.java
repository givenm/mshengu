/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.incident;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.external.MailNotifications;
import zm.hashcode.mshengu.domain.products.UnitType;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.domain.ui.util.Status;

/**
 *
 * @author Ferox
 */
@Document
public final class Incident implements Serializable, Comparable<Incident> {

    private String id;
    private Date actionDate;
    private String refNumber;
    private String customer;//(ongoing outstanding, resolved) 
    private String email;
    private String contactPerson;
    private String contactNumber;
    private String site;
    private String suburb;
    @DBRef
    private UnitType toiletType;
    private boolean closed;
    @DBRef
    private IncidentType incidentType;
    @DBRef(lazy = true)
    private Set<UserAction> userAction = new HashSet<>();
    @DBRef
    private ServiceProvider serviceProvider;
    private String comment;
    @DBRef(lazy = true)
    private MailNotifications mailNotifications;
    @DBRef(lazy = true)
    private Status status;
    //Date, client, site, contact details, type, provider

    private Incident() {
    }

    private Incident(Builder builder) {
        this.id = builder.id;
        this.actionDate = builder.actionDate;
        this.refNumber = builder.refNumber;
        this.customer = builder.customer;
        this.site = builder.site;
        this.email = builder.email;
        this.contactNumber = builder.contactNumber;
        this.suburb = builder.suburb;
        this.toiletType = builder.toiletType;
        this.contactPerson = builder.contactPerson;
        this.incidentType = builder.incidentType;
        this.userAction = builder.userAction;//
        this.serviceProvider = builder.serviceProvider;//
        this.comment = builder.comment;
        this.mailNotifications = builder.mailNotifications;
        this.status = builder.status;
        this.closed = builder.closed;
    }

    @Override
    public int compareTo(Incident o) {
        return -1 * actionDate.compareTo(o.actionDate);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.getId());
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
        final Incident other = (Incident) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @return the contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @return the suburb
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * @return the toiletType
     */
    public UnitType getToiletType() {
        return toiletType;
    }

    /**
     * @return the actionDate
     */
    public Date getActionDate() {
        return actionDate;
    }

    /**
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @return the site
     */
    public String getSite() {
        return site;
    }

    /**
     * @return the contactPerson
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * @return the incidentType
     */
    public IncidentType getIncidentType() {
        return incidentType;
    }

    /**
     * @return the serviceProvider
     */
    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @return the closed
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * @return the mailNotifications
     */
    public MailNotifications getMailNotifications() {
        return mailNotifications;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @return the refNumber
     */
    public String getRefNumber() {
        return refNumber;
    }

    public static class Builder {

        private String id;
        private Date actionDate;
        private String refNumber;
        private String customer;//(ongoing outstanding, resolved) 
        private String contactPerson;
        private String email;
        private String contactNumber;
        private String site;
        private String suburb;
        private UnitType toiletType;
        private IncidentType incidentType;
        private Set<UserAction> userAction = new HashSet<>();
        private ServiceProvider serviceProvider;
        private String comment;
        private MailNotifications mailNotifications;
        private Status status;
        private boolean closed;

        public Builder(Date actionDate) {
            this.actionDate = actionDate;
        }

        public Builder incident(Incident incident) {
            this.id = incident.getId();
            this.actionDate = incident.getActionDate();
            this.customer = incident.getCustomer();
            this.site = incident.getSite();
            this.contactPerson = incident.getContactPerson();
            this.incidentType = incident.getIncidentType();
            this.userAction = incident.getUserAction();//
            this.serviceProvider = incident.getServiceProvider();//
            this.comment = incident.getComment();
            this.email = incident.getEmail();
            this.contactNumber = incident.getContactNumber();
            this.suburb = incident.getSuburb();
            this.toiletType = incident.getToiletType();
            this.mailNotifications = incident.getMailNotifications();
            this.status = incident.getStatus();
            this.closed = incident.isClosed();
            this.refNumber = incident.getRefNumber();
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

        public Builder customer(String value) {
            this.customer = value;
            return this;
        }

        public Builder site(String value) {
            this.site = value;
            return this;
        }

        public Builder contactPerson(String value) {
            this.contactPerson = value;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder contactNumber(String value) {
            this.contactNumber = value;
            return this;
        }

        public Builder suburb(String value) {
            this.suburb = value;
            return this;
        }

        public Builder toiletType(UnitType value) {
            this.toiletType = value;
            return this;
        }

        public Builder incidentType(IncidentType value) {
            this.incidentType = value;
            return this;
        }

        public Builder userAction(Set<UserAction> value) {
            this.userAction = value;
            return this;
        }

        public Builder serviceProvider(ServiceProvider value) {
            this.serviceProvider = value;
            return this;
        }

        public Builder comment(String value) {
            this.comment = value;
            return this;
        }

        public Builder mailNotifications(MailNotifications value) {
            this.mailNotifications = value;
            return this;
        }

        public Builder closed(boolean value) {
            this.closed = value;
            return this;
        }

        public Builder status(Status value) {
            this.status = value;
            return this;
        }

        public Incident build() {
            return new Incident(this);
        }
    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    private List<UserAction> sortUserAction() {
        List<UserAction> userActionSortedList = new ArrayList<>();
        userActionSortedList.addAll(userAction);
        Collections.sort(userActionSortedList);
        return userActionSortedList;
    }

    /**
     * @return the userAction
     */
    public Set<UserAction> getUserAction() {
        if (userAction != null) {
            return ImmutableSet.copyOf(sortUserAction());
        } else {
            return null;
        }
    }

    public UserAction getLastUserAction() {
        if (userAction != null) {
            if (userAction.size() > 0) {
                return sortUserAction().get(0);
            }
        }
        return null;

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

    public String getServiceProviderId() {
        if (!isNullObject(serviceProvider)) {
            return serviceProvider.getId();
        } else {
            return null;
        }
    }

    public String getServiceProviderName() {
        if (!isNullObject(serviceProvider)) {
            return serviceProvider.getName();
        } else {
            return null;
        }
    }

    public String getIncidentTypeId() {
        if (!isNullObject(incidentType)) {
            return incidentType.getId();
        } else {
            return null;
        }
    }

    public String getIncidentTypeName() {
        if (!isNullObject(incidentType)) {
            return incidentType.getName();
        } else {
            return null;
        }
    }

    public String getStatusId() {
        if (!isNullObject(status)) {
            return status.getId();
        } else {
            return null;
        }
    }

    public String getStatusName() {
        if (!isNullObject(status)) {
            return status.getName();
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

    public String getToiletTypeId() {
        if (!isNullObject(toiletType)) {
            return toiletType.getId();
        } else {
            return null;
        }
    }

    public String getToiletTypeName() {
        if (!isNullObject(toiletType)) {
            return toiletType.getName();
        } else {
            return null;
        }
    }
}
