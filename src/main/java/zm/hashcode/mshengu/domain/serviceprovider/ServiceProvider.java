/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.serviceprovider;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.external.MailNotifications;
import zm.hashcode.mshengu.domain.people.ContactPerson;

/**
 *
 * @author Ferox
 */
@Document
public final class ServiceProvider implements Serializable, Comparable<ServiceProvider> {

    @Id
    private String id;
    private String name;
    private String vendorNumber;
    private String bankName;
    private String accountNumber;
    private String branchCode;
    private String registrationNum;
    private int yearsOfBusiness;
    private String firstNameChiefExec;
    private String lastNameChiefExec;
    private String website;
    private String legalForm;
    private String vatNum;
    @DBRef
    private ServiceProviderCategory serviceProviderCategory;
    @DBRef
    private ContactPerson contactPerson;
    @DBRef
    private Set<ServiceProviderProduct> serviceProviderProduct = new HashSet<>();
    private boolean active;
    private boolean vehicleMaintenance;
    private boolean preferedVendor;
    private MailNotifications mailNotifications;

    private ServiceProvider() {
    }

    private ServiceProvider(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.contactPerson = builder.contactPerson;
        this.serviceProviderCategory = builder.serviceProviderCategory;
        this.serviceProviderProduct = builder.serviceProviderProduct;
        this.active = builder.active;
        this.bankName = builder.bankName;
        this.accountNumber = builder.accountNumber;
        this.branchCode = builder.branchCode;
        this.vehicleMaintenance = builder.vehicleMaintenance;
        this.registrationNum = builder.registrationNum;
        this.yearsOfBusiness = builder.yearsOfBusiness;
        this.firstNameChiefExec = builder.firstNameChiefExec;
        this.lastNameChiefExec = builder.lastNameChiefExec;
        this.website = builder.website;
        this.legalForm = builder.legalForm;
        this.vatNum = builder.vatNum;
        this.mailNotifications = builder.mailNotifications;
        this.preferedVendor = builder.preferedVendor;
        this.vendorNumber = builder.vendorNumber;
    }

    @Override
    public int compareTo(ServiceProvider o) {
        return name.compareTo(o.name);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final ServiceProvider other = (ServiceProvider) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private final String name;
        private String vendorNumber;
        private ContactPerson contactPerson;
        private ServiceProviderCategory serviceProviderCategory;
        private Set<ServiceProviderProduct> serviceProviderProduct = new HashSet<>();
        private String id;
        private boolean active;
        private String bankName;
        private String accountNumber;
        private String branchCode;
        private boolean vehicleMaintenance;
        private String registrationNum;
        private int yearsOfBusiness;
        private String firstNameChiefExec;
        private String lastNameChiefExec;
        private String website;
        private String legalForm;
        private String vatNum;
        private boolean preferedVendor;
        private MailNotifications mailNotifications;

        public Builder(String name) {
            this.name = name;
        }

        public Builder ServiceProvider(ServiceProvider serviceProvider) {
            this.id = serviceProvider.getId();
            this.contactPerson = serviceProvider.getContactPerson();
            this.serviceProviderCategory = serviceProvider.getServiceProviderCategory();
            this.serviceProviderProduct = serviceProvider.getServiceProviderProduct();
            this.active = serviceProvider.isActive();
            this.bankName = serviceProvider.getBankName();
            this.accountNumber = serviceProvider.getAccountNumber();
            this.branchCode = serviceProvider.getBranchCode();
            this.vehicleMaintenance = serviceProvider.isVehicleMaintenance();
            this.registrationNum = serviceProvider.getRegistrationNum();
            this.yearsOfBusiness = serviceProvider.getYearsOfBusiness();
            this.firstNameChiefExec = serviceProvider.getFirstNameChiefExec();
            this.lastNameChiefExec = serviceProvider.getLastNameChiefExec();
            this.website = serviceProvider.getWebsite();
            this.legalForm = serviceProvider.getLegalForm();
            this.vatNum = serviceProvider.getVatNum();
            this.mailNotifications = serviceProvider.getMailNotifications();
            this.preferedVendor = serviceProvider.isPreferedVendor();
            this.vendorNumber = serviceProvider.getVendorNumber();
            return this;
        }

        public Builder contactPerson(ContactPerson contactPerson) {
            this.contactPerson = contactPerson;
            return this;
        }

        public Builder vendorNumber(String value) {
            this.vendorNumber = value;
            return this;
        }

        public Builder bankName(String value) {
            this.bankName = value;
            return this;
        }

        public Builder vatNum(String value) {
            this.vatNum = value;
            return this;
        }

        public Builder registrationNum(String value) {
            this.registrationNum = value;
            return this;
        }

        public Builder yearsOfBusiness(int value) {
            this.yearsOfBusiness = value;
            return this;
        }

        public Builder firstNameChiefExec(String value) {
            this.firstNameChiefExec = value;
            return this;
        }

        public Builder lastNameChiefExec(String value) {
            this.lastNameChiefExec = value;
            return this;
        }

        public Builder website(String value) {
            this.website = value;
            return this;
        }

        public Builder legalForm(String value) {
            this.legalForm = value;
            return this;
        }

        public Builder accountNumber(String value) {
            this.accountNumber = value;
            return this;
        }

        public Builder branchCode(String value) {
            this.branchCode = value;
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder active(boolean value) {
            this.active = value;
            return this;
        }

        public Builder vehicleMaintenance(boolean value) {
            this.vehicleMaintenance = value;
            return this;
        }

        public Builder serviceProviderCategory(ServiceProviderCategory value) {
            this.serviceProviderCategory = value;
            return this;
        }

        public Builder serviceProviderProduct(Set<ServiceProviderProduct> value) {
            this.serviceProviderProduct = value;
            return this;
        }

        public Builder mailNotifications(MailNotifications value) {
            this.mailNotifications = value;

            return this;
        }

        public Builder preferedVendor(boolean value) {
            this.preferedVendor = value;

            return this;
        }

        public ServiceProvider build() {
            return new ServiceProvider(this);
        }
    }

    public String getVatNum() {
        return vatNum;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public String getId() {
        return id;
    }

    public String getRegistrationNum() {
        return registrationNum;
    }

    public int getYearsOfBusiness() {
        return yearsOfBusiness;
    }

    public String getFirstNameChiefExec() {
        return firstNameChiefExec;
    }

    public String getLastNameChiefExec() {
        return lastNameChiefExec;
    }

    public String getWebsite() {
        return website;
    }

    public String getLegalForm() {
        return legalForm;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public boolean isVehicleMaintenance() {
        return vehicleMaintenance;
    }

    /**
     * @return the serviceProviderCategory
     */
    public ServiceProviderCategory getServiceProviderCategory() {
        return serviceProviderCategory;
    }

    /**
     * @return the serviceProviderProduct
     */
    public Set<ServiceProviderProduct> getServiceProviderProduct() {
        return ImmutableSet.copyOf(serviceProviderProduct);
//        return serviceProviderProduct;
    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getServiceProviderCategoryName() {
        if (!isNullObject(serviceProviderCategory)) {
            return serviceProviderCategory.getCategoryName();
        } else {
            return null;
        }
    }

    public String getServiceProvideCategoyId() {
        if (!isNullObject(serviceProviderCategory)) {
            return serviceProviderCategory.getId();
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

    public String getContactFaxNumber() {
        if (!isNullObject(contactPerson)) {
            return contactPerson.getFaxNumber();
        } else {
            return null;
        }
    }

    public String getContactAddress1() {
        if (!isNullObject(contactPerson)) {
            return contactPerson.getAddress1();
        } else {
            return null;
        }
    }

    public String getContactAddress2() {
        if (!isNullObject(contactPerson)) {
            return contactPerson.getAddress2();
        } else {
            return null;
        }
    }

    public String getContactCity() {
        if (!isNullObject(contactPerson)) {
            return contactPerson.getCity();
        } else {
            return null;
        }
    }

    /**
     * @return the mailNotifications
     */
    public MailNotifications getMailNotifications() {
        return mailNotifications;
    }

    /**
     * @return the preferedVendor
     */
    public boolean isPreferedVendor() {
        return preferedVendor;
    }

    /**
     * @return the vendorNumber
     */
    public String getVendorNumber() {
        return vendorNumber;
    }
}
