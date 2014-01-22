/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.customer;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.external.MailNotifications;
import zm.hashcode.mshengu.domain.incident.UserAction;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.domain.ui.util.PaymentMethod;

/**
 *
 * @author Ferox
 */
@Document
public class ServiceRequest implements Serializable, Comparable<ServiceRequest> {

    @Id
    private String id;
    private Date dateofAction;
    private Date requestDate;
    private Date deliveryDate;
    private String deliveryTime;
    private Date collectionDate;
    private String refNumber;
    private boolean indefinitePeriod;
    private boolean closed;
    @DBRef
    private Set<UserAction> userAction = new HashSet<>();
    @DBRef
    private ContactPerson contactPerson;
    @DBRef
    private PaymentMethod paymentMethod;
    private BigDecimal paymentAmout;
    @DBRef
    private ContractType contractType;
    @DBRef
    private MailNotifications mailNotifications;
    @DBRef
    private ServiceRequestType serviceRequestType;
    private String customerId;
    private String siteId;
    private String deliveryAddress;
    private String deliveryInstruction;
    private String basicAtlas;
    private String executiveFlsuh;
    private String excPlusHandBasin;
    private String standardNonFlush;
    private String wheelChair;
    private String builderAtlas;
    private int basicAtlasQty;
    private int executiveFlsuhQty;
    private int excPlusHandBasinQty;
    private int standardNonFlushQty;
    private int wheelChairQty;
    private int builderAtlasQty;

    private ServiceRequest() {
    }

    private ServiceRequest(Builder builder) {
        this.id = builder.id;
        this.dateofAction = builder.dateofAction;
        this.requestDate = builder.requestDate;
        this.deliveryDate = builder.deliveryDate;
        this.deliveryTime = builder.deliveryTime;
        this.collectionDate = builder.collectionDate;
        this.indefinitePeriod = builder.indefinitePeriod;
        this.refNumber = builder.refNumber;
        this.closed = builder.closed;
        this.userAction = builder.userAction;
        this.contactPerson = builder.contactPerson;
        this.paymentMethod = builder.paymentMethod;
        this.paymentAmout = builder.paymentAmout;
        this.contractType = builder.contractType;
        this.serviceRequestType = builder.serviceRequestType;
        this.mailNotifications = builder.mailNotifications;
        this.customerId = builder.customerId;
        this.siteId = builder.siteId;
        this.deliveryAddress = builder.deliveryAddress;
        this.deliveryInstruction = builder.deliveryInstruction;
//        this.basicAtlas = builder.basicAtlas;
//        this.executiveFlsuh = builder.executiveFlsuh;
//        this.excPlusHandBasin = builder.excPlusHandBasin;
//        this.standardNonFlush = builder.standardNonFlush;
//        this.wheelChair = builder.wheelChair;
//        this.builderAtlas = builder.builderAtlas;
        this.basicAtlasQty = builder.basicAtlasQty;
        this.executiveFlsuhQty = builder.executiveFlsuhQty;
        this.excPlusHandBasinQty = builder.excPlusHandBasinQty;
        this.standardNonFlushQty = builder.standardNonFlushQty;
        this.wheelChairQty = builder.wheelChairQty;
        this.builderAtlasQty = builder.builderAtlasQty;
    }

    @Override
    public int compareTo(ServiceRequest o) {
        return -1 * getDateofAction().compareTo(o.getDateofAction());
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the dateofAction
     */
    public Date getDateofAction() {
        return dateofAction;
    }

    /**
     * @return the deliveryDate
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @return the deliveryTime
     */
    public String getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * @return the collectionDate
     */
    public Date getCollectionDate() {
        return collectionDate;
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

    /**
     * @return the contactPerson
     */
    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    /**
     * @return the paymentMethod
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @return the paymentAmout
     */
    public BigDecimal getPaymentAmout() {
        return paymentAmout;
    }

    /**
     * @return the contractType
     */
    public ContractType getContractType() {
        return contractType;
    }

    /**
     * @return the mailNotifications
     */
    public MailNotifications getMailNotifications() {
        return mailNotifications;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @return the siteId
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * @return the deliveryInstruction
     */
    public String getDeliveryInstruction() {
        return deliveryInstruction;
    }

    /**
     * @return the basicAtlas
     */
    public String getBasicAtlas() {
        return basicAtlas;
    }

    /**
     * @return the executiveFlsuh
     */
    public String getExecutiveFlsuh() {
        return executiveFlsuh;
    }

    /**
     * @return the excPlusHandBasin
     */
    public String getExcPlusHandBasin() {
        return excPlusHandBasin;
    }

    /**
     * @return the standardNonFlush
     */
    public String getStandardNonFlush() {
        return standardNonFlush;
    }

    /**
     * @return the wheelChair
     */
    public String getWheelChair() {
        return wheelChair;
    }

    /**
     * @return the builderAtlas
     */
    public String getBuilderAtlas() {
        return builderAtlas;
    }

    /**
     * @return the basicAtlasQty
     */
    public int getBasicAtlasQty() {
        return basicAtlasQty;
    }

    /**
     * @return the executiveFlsuhQty
     */
    public int getExecutiveFlsuhQty() {
        return executiveFlsuhQty;
    }

    /**
     * @return the excPlusHandBasinQty
     */
    public int getExcPlusHandBasinQty() {
        return excPlusHandBasinQty;
    }

    /**
     * @return the standardNonFlushQty
     */
    public int getStandardNonFlushQty() {
        return standardNonFlushQty;
    }

    /**
     * @return the wheelChairQty
     */
    public int getWheelChairQty() {
        return wheelChairQty;
    }

    /**
     * @return the builderAtlasQty
     */
    public int getBuilderAtlasQty() {
        return builderAtlasQty;
    }

    /**
     * @return the refNumber
     */
    public String getRefNumber() {
        return refNumber;
    }

    /**
     * @return the closed
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * @return the indefinitePeriod
     */
    public boolean isIndefinitePeriod() {
        return indefinitePeriod;
    }

    /**
     * @return the requestDate
     */
    public Date getRequestDate() {
        return requestDate;
    }

    /**
     * @return the deliveryAddress
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * @return the serviceRequestType
     */
    public ServiceRequestType getServiceRequestType() {
        return serviceRequestType;
    }

    
    public static class Builder {

        private String id;
        private final Date dateofAction;
        private Date requestDate;
        private Date deliveryDate;
        private String deliveryTime;
        private Date collectionDate;
        private String refNumber;
        private boolean indefinitePeriod;
        private boolean closed;
        private Set<UserAction> userAction = new HashSet<>();
        private ContactPerson contactPerson;
        private PaymentMethod paymentMethod;
        private BigDecimal paymentAmout;
        private ContractType contractType;
        private ServiceRequestType serviceRequestType;
        private MailNotifications mailNotifications;
        private String customerId;
        private String siteId;
        private String deliveryAddress;
        private String deliveryInstruction;
//        private String basicAtlas;
//        private String executiveFlsuh;
//        private String excPlusHandBasin;
//        private String standardNonFlush;
//        private String wheelChair;
//        private String builderAtlas;
        private int basicAtlasQty;
        private int executiveFlsuhQty;
        private int excPlusHandBasinQty;
        private int standardNonFlushQty;
        private int wheelChairQty;
        private int builderAtlasQty;

        public Builder(Date dateofAction) {
            this.dateofAction = dateofAction;
        }

        public Builder serviceRequest(ServiceRequest serviceRequest) {
            this.id = serviceRequest.getId();
//            this.dateofAction = serviceRequest.getDateofAction();
            this.requestDate = serviceRequest.getRequestDate();
            this.deliveryDate = serviceRequest.getDeliveryDate();
            this.deliveryTime = serviceRequest.getDeliveryTime();
            this.collectionDate = serviceRequest.getCollectionDate();
            this.indefinitePeriod = serviceRequest.isIndefinitePeriod();
            this.refNumber = serviceRequest.getRefNumber();
            this.closed = serviceRequest.isClosed();
            this.userAction = serviceRequest.getUserAction();
            this.contactPerson = serviceRequest.getContactPerson();
            this.paymentMethod = serviceRequest.getPaymentMethod();
            this.paymentAmout = serviceRequest.getPaymentAmout();
            this.contractType = serviceRequest.getContractType();
            this.serviceRequestType = serviceRequest.getServiceRequestType();
            this.mailNotifications = serviceRequest.getMailNotifications();
            this.customerId = serviceRequest.getCustomerId();
            this.siteId = serviceRequest.getSiteId();
            this.deliveryAddress = serviceRequest.getDeliveryAddress();
            this.deliveryInstruction = serviceRequest.getDeliveryInstruction();
//            this.basicAtlas = serviceRequest.getBasicAtlas();
//            this.executiveFlsuh = serviceRequest.getExecutiveFlsuh();
//            this.excPlusHandBasin = serviceRequest.getExcPlusHandBasin();
//            this.standardNonFlush = serviceRequest.getStandardNonFlush();
//            this.wheelChair = serviceRequest.getWheelChair();
//            this.builderAtlas = serviceRequest.getBuilderAtlas();

            this.basicAtlasQty = serviceRequest.getBasicAtlasQty();
            this.executiveFlsuhQty = serviceRequest.getExecutiveFlsuhQty();
            this.excPlusHandBasinQty = serviceRequest.getExcPlusHandBasinQty();
            this.standardNonFlushQty = serviceRequest.getStandardNonFlushQty();
            this.wheelChairQty = serviceRequest.getWheelChairQty();
            this.builderAtlasQty = serviceRequest.getBuilderAtlasQty();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder requestDate(Date value) {
            this.requestDate = value;
            return this;
        }

        public Builder deliveryDate(Date value) {
            this.deliveryDate = value;
            return this;
        }

        public Builder deliveryTime(String value) {
            this.deliveryTime = value;
            return this;
        }

        public Builder collectionDate(Date value) {
            this.collectionDate = value;
            return this;
        }

        public Builder refNumber(String value) {
            this.refNumber = value;
            return this;
        }

        public Builder indefinitePeriod(boolean value) {
            this.indefinitePeriod = value;
            return this;
        }

        public Builder closed(boolean value) {
            this.closed = value;
            return this;
        }

        public Builder userAction(Set<UserAction> value) {
            this.userAction = value;
            return this;
        }

        public Builder contactPerson(ContactPerson value) {
            this.contactPerson = value;
            return this;
        }

        public Builder paymentMethod(PaymentMethod value) {
            this.paymentMethod = value;
            return this;
        }

        public Builder paymentAmout(BigDecimal value) {
            this.paymentAmout = value;
            return this;
        }

        public Builder contractType(ContractType value) {
            this.contractType = value;
            return this;
        }

        public Builder serviceRequestType(ServiceRequestType value) {
            this.serviceRequestType = value;
            return this;
        }
        
         public Builder mailNotifications(MailNotifications value) {
            this.mailNotifications = value;
            return this;
        }

        public Builder customerId(String value) {
            this.customerId = value;
            return this;
        }

        public Builder siteId(String value) {
            this.siteId = value;
            return this;
        }

        public Builder deliveryAddress(String value) {
            this.deliveryAddress = value;
            return this;
        }

        public Builder deliveryInstruction(String value) {
            this.deliveryInstruction = value;
            return this;
        }

//        public Builder basicAtlas(String value) {
//            this.basicAtlas = value;
//            return this;
//        }
//
//        public Builder executiveFlsuh(String value) {
//            this.executiveFlsuh = value;
//            return this;
//        }
//
//        public Builder excPlusHandBasin(String value) {
//            this.excPlusHandBasin = value;
//            return this;
//        }
//
//        public Builder standardNonFlush(String value) {
//            this.standardNonFlush = value;
//            return this;
//        }
//
//        public Builder wheelChair(String value) {
//            this.wheelChair = value;
//            return this;
//        }
//
//        public Builder builderAtlas(String value) {
//            this.builderAtlas = value;
//            return this;
//        }
        public Builder basicAtlasQty(int value) {
            this.basicAtlasQty = value;
            return this;
        }

        public Builder executiveFlsuhQty(int value) {
            this.executiveFlsuhQty = value;
            return this;
        }

        public Builder excPlusHandBasinQty(int value) {
            this.excPlusHandBasinQty = value;
            return this;
        }

        public Builder standardNonFlushQty(int value) {
            this.standardNonFlushQty = value;
            return this;
        }

        public Builder wheelChairQty(int value) {
            this.wheelChairQty = value;
            return this;
        }

        public Builder builderAtlasQty(int value) {
            this.builderAtlasQty = value;
            return this;
        }

        public ServiceRequest build() {
            return new ServiceRequest(this);
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

    public String getContactPersonId() {
        if (!isNullObject(contactPerson)) {
            return contactPerson.getId();
        } else {
            return null;
        }
    }

    public String getContactPersonName() {
        if (!isNullObject(contactPerson)) {
            return contactPerson.getFirstName() + " " + contactPerson.getLastName();
        } else {
            return null;
        }
    }

    public String getContactFirstName() {
        if (!isNullObject(contactPerson)) {
            return contactPerson.getFirstName();
        } else {
            return null;
        }
    }

    public String getContactLastName() {
        if (!isNullObject(contactPerson)) {
            return contactPerson.getLastName();
        } else {
            return null;
        }
    }

    public String getContactPersonMainNumber() {
        if (!isNullObject(contactPerson)) {
            return contactPerson.getMainNumber();
        } else {
            return null;
        }
    }

    public String getContactPersonAlternativeNumber() {
        if (!isNullObject(contactPerson)) {
            return contactPerson.getOtherNumber();
        } else {
            return null;
        }
    }

    public String getContactPersonEmail() {
        if (!isNullObject(contactPerson)) {
            return contactPerson.getEmailAddress();
        } else {
            return null;
        }
    }

    public String getContractTypeId() {
        if (!isNullObject(contractType)) {
            return contractType.getId();
        } else {
            return null;
        }
    }

    public String getContactTypeName() {
        if (!isNullObject(contractType)) {
            return contractType.getType();
        } else {
            return null;
        }
    }

    public String getPaymentMethodId() {
        if (!isNullObject(paymentMethod)) {
            return paymentMethod.getId();
        } else {
            return null;
        }
    }

    public String getPaymentMethodName() {
        if (!isNullObject(paymentMethod)) {
            return paymentMethod.getPaymentMethod();
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
    
        public String getServiceRequestTypeId() {
        if (!isNullObject(serviceRequestType)) {
            return serviceRequestType.getId();
        } else {
            return null;
        }
    }

    public String getServiceRequestTypeName() {
        if (!isNullObject(serviceRequestType)) {
            return serviceRequestType.getName();
        } else {
            return null;
        }
    }
    public String getUnitsRequired(){
        
            String unitsRequired = new StringBuilder()
                    .append("----- Units Required -----").append("\n")
                    .append("Basic Atlas : ").append(basicAtlasQty).append("\n")
                    .append("Executive Flush : ").append(executiveFlsuhQty).append("\n")
                    .append("Executive Flush & Hand Basin : ").append(excPlusHandBasinQty).append("\n")
                    .append("Standard Non-Flush : ").append(standardNonFlushQty).append("\n")
                    .append("Wheel Chair : ").append(wheelChairQty).append("\n")
                    .append("Total : ").append(totalUnitsRequired()).append("\n")
                    .toString();
            return unitsRequired;
    }
    public int totalUnitsRequired(){
        return basicAtlasQty + executiveFlsuhQty + excPlusHandBasinQty +standardNonFlushQty + wheelChairQty;
    }
}
