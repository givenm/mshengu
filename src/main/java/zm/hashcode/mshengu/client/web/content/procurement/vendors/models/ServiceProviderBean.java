/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.vendors.models;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import zm.hashcode.mshengu.domain.external.MailNotifications;

/**
 *
 * @author Ferox
 */
public final class ServiceProviderBean implements Serializable {

    private String id;
    
    private String name;
    private String vendorNumber;
    private MailNotifications mailNotifications;
    
    private String registrationNum;
    
    private String legalForm;
    
    private String yearsOfBus;
    
    private String firstNameChiefExec;
    
    private String lastNameChiefExec;
    
    private String vatNum;
    private String website;
    private boolean active;
    private boolean preferedVendor;
    private boolean vehicleMaintenance;
    private boolean registeredForVat;
    
    private String address1;
    private String address2;
    
    private String city;
    
    private String code;
    private String contactPersonId;
    
    private String firstName;
    
    private String lastName;
    
    private String mainNumber;
    private String faxNumber;
    private String otherNumber;
    
    private String emailAddress;
    
    private String serviceProviderCategoryId;
    private String bankName;
    private String accountNumber;
    private String branchCode;

    public boolean isRegisteredForVat() {
        return registeredForVat;
    }

    public void setRegisteredForVat(boolean registeredForVat) {
        this.registeredForVat = registeredForVat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isVehicleMaintenance() {
        return vehicleMaintenance;
    }

    public void setVehicleMaintenance(boolean vehicleMaintenance) {
        this.vehicleMaintenance = vehicleMaintenance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the serviceProviderCategoryId
     */
    public String getServiceProviderCategoryId() {
        return serviceProviderCategoryId;
    }

    /**
     * @param serviceProviderCategoryId the serviceProviderCategoryId to set
     */
    public void setServiceProviderCategoryId(String serviceProviderCategoryId) {
        this.serviceProviderCategoryId = serviceProviderCategoryId;
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

    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }

    public String getLegalForm() {
        return legalForm;
    }

    public void setLegalForm(String legalForm) {
        this.legalForm = legalForm;
    }

    public String getYearsOfBus() {
        return yearsOfBus;
    }

    public void setYearsOfBus(String yearsOfBus) {
        this.yearsOfBus = yearsOfBus;
    }

    public String getFirstNameChiefExec() {
        return firstNameChiefExec;
    }

    public void setFirstNameChiefExec(String firstNameChiefExec) {
        this.firstNameChiefExec = firstNameChiefExec;
    }

    public String getLastNameChiefExec() {
        return lastNameChiefExec;
    }

    public void setLastNameChiefExec(String lastNameChiefExec) {
        this.lastNameChiefExec = lastNameChiefExec;
    }

    public String getVatNum() {
        return vatNum;
    }

    public void setVatNum(String vatNum) {
        this.vatNum = vatNum;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * @return the preferedVendor
     */
    public boolean isPreferedVendor() {
        return preferedVendor;
    }

    /**
     * @param preferedVendor the preferedVendor to set
     */
    public void setPreferedVendor(boolean preferedVendor) {
        this.preferedVendor = preferedVendor;
    }

    /**
     * @return the vendorNumber
     */
    public String getVendorNumber() {
        return vendorNumber;
    }

    /**
     * @param vendorNumber the vendorNumber to set
     */
    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    /**
     * @return the mailNotifications
     */
    public MailNotifications getMailNotifications() {
        return mailNotifications;
    }

    /**
     * @param mailNotifications the mailNotifications to set
     */
    public void setMailNotifications(MailNotifications mailNotifications) {
        this.mailNotifications = mailNotifications;
    }
}