/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.rest.api.resources;

/**
 *
 * @author given
 */
public class PublicVendorRegistration {

    private String companyName;
    private String companyregistrationnumber;
    private String companyType;
    private int yearEstablishment;
    private String chiefExecutiveFirstname;
    private String chiefExecutiveLastname;
    private boolean hasVat;
    private String vatRegistrationNumber;
    private String webSite;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private Long postalCode;
    private String contactPersonFirstname;
    private String contactPersonLastname;
    private String telephoneNumber;
    private String faxNumber;
    private String mobileNumber;
    private String email;
    private String vendorCategory;
    private String bank;
    private String accountNumber;
    private String branchCode;

    public boolean hasVat() {
        return hasVat;
    }

    public void hasVat(boolean hasVat) {
        this.hasVat = hasVat;
    }    
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyRegistrationnumber() {
        return companyregistrationnumber;
    }

    public void setCompanyRegistrationnumber(String companyregistrationnumber) {
        this.companyregistrationnumber = companyregistrationnumber;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public int getYearEstablishment() {
        return yearEstablishment;
    }

    public void setYearEstablishment(int yearEstablishment) {
        this.yearEstablishment = yearEstablishment;
    }

    public String getVatRegistrationNumber() {
        return vatRegistrationNumber;
    }

    public void setVatRegistrationNumber(String vatRegistrationNumber) {
        this.vatRegistrationNumber = vatRegistrationNumber;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVendorCategory() {
        return vendorCategory;
    }

    public void setVendorCategory(String vendorCategory) {
        this.vendorCategory = vendorCategory;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
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

    public String getCompanyregistrationnumber() {
        return companyregistrationnumber;
    }

    public void setCompanyregistrationnumber(String companyregistrationnumber) {
        this.companyregistrationnumber = companyregistrationnumber;
    }

    public String getChiefExecutiveFirstname() {
        return chiefExecutiveFirstname;
    }

    public void setChiefExecutiveFirstname(String chiefExecutiveFirstname) {
        this.chiefExecutiveFirstname = chiefExecutiveFirstname;
    }

    public String getChiefExecutiveLastname() {
        return chiefExecutiveLastname;
    }

    public void setChiefExecutiveLastname(String chiefExecutiveLastname) {
        this.chiefExecutiveLastname = chiefExecutiveLastname;
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

    @Override
    public String toString() {
        return "PublicVendorRegistration{" + "companyName=" + companyName + ", companyregistrationnumber=" + companyregistrationnumber + ", companyType=" + companyType + ", yearEstablishment=" + yearEstablishment + ", chiefExecutiveFirstname=" + chiefExecutiveFirstname + ", chiefExecutiveLastname=" + chiefExecutiveLastname + ", vatRegistrationNumber=" + vatRegistrationNumber + ", webSite=" + webSite + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city + ", postalCode=" + postalCode + ", contactPersonFirstname=" + contactPersonFirstname + ", contactPersonLastname=" + contactPersonLastname + ", telephoneNumber=" + telephoneNumber + ", faxNumber=" + faxNumber + ", mobileNumber=" + mobileNumber + ", email=" + email + ", vendorCategory=" + vendorCategory + ", bank=" + bank + ", accountNumber=" + accountNumber + ", branchCode=" + branchCode + '}';
    }   
}