/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicerequest.models;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Ferox
 */
@Document
public class ServiceRequestBean {


    private String id;
    private Date dateofAction;
    private Date requestDate;
    private Date deliveryDate;
    private String deliveryTime;
    private Date collectionDate;    
    private boolean indefinitePeriod;
    private String refNumber;
    private boolean closed;
    private String userActionId; 
    private String contactPersonId;
    @NotNull
    private String paymentMethodId;
    private BigDecimal paymentAmout;
    @NotNull
    private String contractTypeId;
    @NotNull
    private String mailNotificationsId;
    @NotNull
    private String customerId;
    @NotNull
    private String siteId;
    @NotNull
    private String deliveryAddress;
    private String deliveryInstruction; 
    @NotNull
    private String serviceRequestTypeId;
    
    private int basicAtlasQty;
    private int executiveFlsuhQty;
    private int excPlusHandBasinQty;
    private int standardNonFlushQty;
    private int wheelChairQty;
    private int builderAtlasQty;
    
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String mainNumber;
    private String otherNumber;
    private String emailAddress;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the dateofAction
     */
    public Date getDateofAction() {
        return dateofAction;
    }

    /**
     * @param dateofAction the dateofAction to set
     */
    public void setDateofAction(Date dateofAction) {
        this.dateofAction = dateofAction;
    }

    /**
     * @return the deliveryDate
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param deliveryDate the deliveryDate to set
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * @return the deliveryTime
     */
    public String getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * @param deliveryTime the deliveryTime to set
     */
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * @return the collectionDate
     */
    public Date getCollectionDate() {
        return collectionDate;
    }

    /**
     * @param collectionDate the collectionDate to set
     */
    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    /**
     * @return the refNumber
     */
    public String getRefNumber() {
        return refNumber;
    }

    /**
     * @param refNumber the refNumber to set
     */
    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    /**
     * @return the closed
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * @param closed the closed to set
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * @return the userActionId
     */
    public String getUserActionId() {
        return userActionId;
    }

    /**
     * @param userActionId the userActionId to set
     */
    public void setUserActionId(String userActionId) {
        this.userActionId = userActionId;
    }

    /**
     * @return the contactPersonId
     */
    public String getContactPersonId() {
        return contactPersonId;
    }

    /**
     * @param contactPersonId the contactPersonId to set
     */
    public void setContactPersonId(String contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    /**
     * @return the paymentMethodId
     */
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    /**
     * @param paymentMethodId the paymentMethodId to set
     */
    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    /**
     * @return the paymentAmout
     */
    public BigDecimal getPaymentAmout() {
        return paymentAmout;
    }

    /**
     * @param paymentAmout the paymentAmout to set
     */
    public void setPaymentAmout(BigDecimal paymentAmout) {
        this.paymentAmout = paymentAmout;
    }

    /**
     * @return the contractTypeId
     */
    public String getContractTypeId() {
        return contractTypeId;
    }

    /**
     * @param contractTypeId the contractTypeId to set
     */
    public void setContractTypeId(String contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    /**
     * @return the mailNotificationsId
     */
    public String getMailNotificationsId() {
        return mailNotificationsId;
    }

    /**
     * @param mailNotificationsId the mailNotificationsId to set
     */
    public void setMailNotificationsId(String mailNotificationsId) {
        this.mailNotificationsId = mailNotificationsId;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the siteId
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * @param siteId the siteId to set
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * @return the deliveryInstruction
     */
    public String getDeliveryInstruction() {
        return deliveryInstruction;
    }

    /**
     * @param deliveryInstruction the deliveryInstruction to set
     */
    public void setDeliveryInstruction(String deliveryInstruction) {
        this.deliveryInstruction = deliveryInstruction;
    }

//    /**
//     * @return the basicAtlas
//     */
//    public String getBasicAtlas() {
//        return basicAtlas;
//    }
//
//    /**
//     * @param basicAtlas the basicAtlas to set
//     */
//    public void setBasicAtlas(String basicAtlas) {
//        this.basicAtlas = basicAtlas;
//    }
//
//    /**
//     * @return the executiveFlsuh
//     */
//    public String getExecutiveFlsuh() {
//        return executiveFlsuh;
//    }
//
//    /**
//     * @param executiveFlsuh the executiveFlsuh to set
//     */
//    public void setExecutiveFlsuh(String executiveFlsuh) {
//        this.executiveFlsuh = executiveFlsuh;
//    }
//
//    /**
//     * @return the excPlusHandBasin
//     */
//    public String getExcPlusHandBasin() {
//        return excPlusHandBasin;
//    }
//
//    /**
//     * @param excPlusHandBasin the excPlusHandBasin to set
//     */
//    public void setExcPlusHandBasin(String excPlusHandBasin) {
//        this.excPlusHandBasin = excPlusHandBasin;
//    }
//
//    /**
//     * @return the standardNonFlush
//     */
//    public String getStandardNonFlush() {
//        return standardNonFlush;
//    }
//
//    /**
//     * @param standardNonFlush the standardNonFlush to set
//     */
//    public void setStandardNonFlush(String standardNonFlush) {
//        this.standardNonFlush = standardNonFlush;
//    }
//
//    /**
//     * @return the wheelChair
//     */
//    public String getWheelChair() {
//        return wheelChair;
//    }
//
//    /**
//     * @param wheelChair the wheelChair to set
//     */
//    public void setWheelChair(String wheelChair) {
//        this.wheelChair = wheelChair;
//    }
//
//    /**
//     * @return the builderAtlas
//     */
//    public String getBuilderAtlas() {
//        return builderAtlas;
//    }
//
//    /**
//     * @param builderAtlas the builderAtlas to set
//     */
//    public void setBuilderAtlas(String builderAtlas) {
//        this.builderAtlas = builderAtlas;
//    }

    /**
     * @return the basicAtlasQty
     */
    public int getBasicAtlasQty() {
        return basicAtlasQty;
    }

    /**
     * @param basicAtlasQty the basicAtlasQty to set
     */
    public void setBasicAtlasQty(int basicAtlasQty) {
        this.basicAtlasQty = basicAtlasQty;
    }

    /**
     * @return the executiveFlsuhQty
     */
    public int getExecutiveFlsuhQty() {
        return executiveFlsuhQty;
    }

    /**
     * @param executiveFlsuhQty the executiveFlsuhQty to set
     */
    public void setExecutiveFlsuhQty(int executiveFlsuhQty) {
        this.executiveFlsuhQty = executiveFlsuhQty;
    }

    /**
     * @return the excPlusHandBasinQty
     */
    public int getExcPlusHandBasinQty() {
        return excPlusHandBasinQty;
    }

    /**
     * @param excPlusHandBasinQty the excPlusHandBasinQty to set
     */
    public void setExcPlusHandBasinQty(int excPlusHandBasinQty) {
        this.excPlusHandBasinQty = excPlusHandBasinQty;
    }

    /**
     * @return the standardNonFlushQty
     */
    public int getStandardNonFlushQty() {
        return standardNonFlushQty;
    }

    /**
     * @param standardNonFlushQty the standardNonFlushQty to set
     */
    public void setStandardNonFlushQty(int standardNonFlushQty) {
        this.standardNonFlushQty = standardNonFlushQty;
    }

    /**
     * @return the wheelChairQty
     */
    public int getWheelChairQty() {
        return wheelChairQty;
    }

    /**
     * @param wheelChairQty the wheelChairQty to set
     */
    public void setWheelChairQty(int wheelChairQty) {
        this.wheelChairQty = wheelChairQty;
    }

    /**
     * @return the builderAtlasQty
     */
    public int getBuilderAtlasQty() {
        return builderAtlasQty;
    }

    /**
     * @param builderAtlasQty the builderAtlasQty to set
     */
    public void setBuilderAtlasQty(int builderAtlasQty) {
        this.builderAtlasQty = builderAtlasQty;
    }

    /**
     * @return the indefinitePeriod
     */
    public boolean isIndefinitePeriod() {
        return indefinitePeriod;
    }

    /**
     * @param indefinitePeriod the indefinitePeriod to set
     */
    public void setIndefinitePeriod(boolean indefinitePeriod) {
        this.indefinitePeriod = indefinitePeriod;
    }

    /**
     * @return the requestDate
     */
    public Date getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the deliveryAddress
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * @param deliveryAddress the deliveryAddress to set
     */
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the mainNumber
     */
    public String getMainNumber() {
        return mainNumber;
    }

    /**
     * @param mainNumber the mainNumber to set
     */
    public void setMainNumber(String mainNumber) {
        this.mainNumber = mainNumber;
    }

    /**
     * @return the otherNumber
     */
    public String getOtherNumber() {
        return otherNumber;
    }

    /**
     * @param otherNumber the otherNumber to set
     */
    public void setOtherNumber(String otherNumber) {
        this.otherNumber = otherNumber;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the serviceRequestTypeId
     */
    public String getServiceRequestTypeId() {
        return serviceRequestTypeId;
    }

    /**
     * @param serviceRequestTypeId the serviceRequestTypeId to set
     */
    public void setServiceRequestTypeId(String serviceRequestTypeId) {
        this.serviceRequestTypeId = serviceRequestTypeId;
    }

    
}
