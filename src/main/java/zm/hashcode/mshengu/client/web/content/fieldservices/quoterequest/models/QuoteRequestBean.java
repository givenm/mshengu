/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.quoterequest.models;

import java.util.Date;

/**
 *
 * @author Luckbliss
 */
public class QuoteRequestBean {

    private String id;
    private String refNumber;
    private Date actionDate;
    private Date resolvedDate;
    private Date qualityAssuranceDate;

    private String companyNameNonRequired;

    private String contactPersonFirstname;

    private String contactPersonLastname;
    private String telephoneNumberNonRequired;
    private String contactNumber;
    private String faxNumber;
    private String email;
    private String billingAddress;
    private String deliveryAddress;
    private String vatRegistrationNumberUnrequired;
    private String eventType;
    private String eventName;
    private Date eventDate;
    private String toiletsRequired1;
    private int quantityRequired1;
    private String toiletsRequired2;
    private int quantityRequired2;
    private String toiletsRequired3;
    private int quantityRequired3;
    private int numberOfJanitors;
    private int numberOfToiletRolls;
    private Date deliveryDate;
    private Date collectionDate;
    private int daysRental;
    private String comment;
    private boolean serviceFrequencyMon;
    private boolean serviceFrequencyTue;
    private boolean serviceFrequencyWed;
    private boolean serviceFrequencyThur;
    private boolean serviceFrequencyFri;
    private boolean serviceFrequencySat;
    private boolean serviceFrequencySun;
    private String status;

    public Date getQualityAssuranceDate() {
        return qualityAssuranceDate;
    }

    public void setQualityAssuranceDate(Date qualityAssuranceDate) {
        this.qualityAssuranceDate = qualityAssuranceDate;
    }

    public Date getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(Date resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getCompanyNameNonRequired() {
        return companyNameNonRequired;
    }

    public void setCompanyNameNonRequired(String companyNameNonRequired) {
        this.companyNameNonRequired = companyNameNonRequired;
    }

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

    public String getTelephoneNumberNonRequired() {
        return telephoneNumberNonRequired;
    }

    public void setTelephoneNumberNonRequired(String telephoneNumberNonRequired) {
        this.telephoneNumberNonRequired = telephoneNumberNonRequired;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getVatRegistrationNumberUnrequired() {
        return vatRegistrationNumberUnrequired;
    }

    public void setVatRegistrationNumberUnrequired(String vatRegistrationNumberUnrequired) {
        this.vatRegistrationNumberUnrequired = vatRegistrationNumberUnrequired;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getNumberOfJanitors() {
        return numberOfJanitors;
    }

    public void setNumberOfJanitors(int numberOfJanitors) {
        this.numberOfJanitors = numberOfJanitors;
    }

    public int getNumberOfToiletRolls() {
        return numberOfToiletRolls;
    }

    public void setNumberOfToiletRolls(int numberOfToiletRolls) {
        this.numberOfToiletRolls = numberOfToiletRolls;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isServiceFrequencyMon() {
        return serviceFrequencyMon;
    }

    public void setServiceFrequencyMon(boolean serviceFrequencyMon) {
        this.serviceFrequencyMon = serviceFrequencyMon;
    }

    public boolean isServiceFrequencyTue() {
        return serviceFrequencyTue;
    }

    public void setServiceFrequencyTue(boolean serviceFrequencyTue) {
        this.serviceFrequencyTue = serviceFrequencyTue;
    }

    public boolean isServiceFrequencyWed() {
        return serviceFrequencyWed;
    }

    public void setServiceFrequencyWed(boolean serviceFrequencyWed) {
        this.serviceFrequencyWed = serviceFrequencyWed;
    }

    public boolean isServiceFrequencyThur() {
        return serviceFrequencyThur;
    }

    public void setServiceFrequencyThur(boolean serviceFrequencyThur) {
        this.serviceFrequencyThur = serviceFrequencyThur;
    }

    public boolean isServiceFrequencyFri() {
        return serviceFrequencyFri;
    }

    public void setServiceFrequencyFri(boolean serviceFrequencyFri) {
        this.serviceFrequencyFri = serviceFrequencyFri;
    }

    public boolean isServiceFrequencySat() {
        return serviceFrequencySat;
    }

    public void setServiceFrequencySat(boolean serviceFrequencySat) {
        this.serviceFrequencySat = serviceFrequencySat;
    }

    public boolean isServiceFrequencySun() {
        return serviceFrequencySun;
    }

    public void setServiceFrequencySun(boolean serviceFrequencySun) {
        this.serviceFrequencySun = serviceFrequencySun;
    }

    /**
     * @return the toiletsRequired1
     */
    public String getToiletsRequired1() {
        return toiletsRequired1;
    }

    /**
     * @param toiletsRequired1 the toiletsRequired1 to set
     */
    public void setToiletsRequired1(String toiletsRequired1) {
        this.toiletsRequired1 = toiletsRequired1;
    }

    /**
     * @return the quantityRequired1
     */
    public int getQuantityRequired1() {
        return quantityRequired1;
    }

    /**
     * @param quantityRequired1 the quantityRequired1 to set
     */
    public void setQuantityRequired1(int quantityRequired1) {
        this.quantityRequired1 = quantityRequired1;
    }

    /**
     * @return the toiletsRequired2
     */
    public String getToiletsRequired2() {
        return toiletsRequired2;
    }

    /**
     * @param toiletsRequired2 the toiletsRequired2 to set
     */
    public void setToiletsRequired2(String toiletsRequired2) {
        this.toiletsRequired2 = toiletsRequired2;
    }

    /**
     * @return the quantityRequired2
     */
    public int getQuantityRequired2() {
        return quantityRequired2;
    }

    /**
     * @param quantityRequired2 the quantityRequired2 to set
     */
    public void setQuantityRequired2(int quantityRequired2) {
        this.quantityRequired2 = quantityRequired2;
    }

    /**
     * @return the toiletsRequired3
     */
    public String getToiletsRequired3() {
        return toiletsRequired3;
    }

    /**
     * @param toiletsRequired3 the toiletsRequired3 to set
     */
    public void setToiletsRequired3(String toiletsRequired3) {
        this.toiletsRequired3 = toiletsRequired3;
    }

    /**
     * @return the quantityRequired3
     */
    public int getQuantityRequired3() {
        return quantityRequired3;
    }

    /**
     * @param quantityRequired3 the quantityRequired3 to set
     */
    public void setQuantityRequired3(int quantityRequired3) {
        this.quantityRequired3 = quantityRequired3;
    }

    /**
     * @return the daysRental
     */
    public int getDaysRental() {
        return daysRental;
    }

    /**
     * @param daysRental the daysRental to set
     */
    public void setDaysRental(int daysRental) {
        this.daysRental = daysRental;
    }
}
