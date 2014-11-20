/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.customer;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.domain.products.Site;

/**
 *
 * @author boniface
 */
@Document
public final class Customer implements Serializable, Comparable<Customer> {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private boolean isContract;
    @DBRef(lazy = true)
    private ContactPerson contactPerson;
    @DBRef(lazy = true)
    Set<Order> orders = new HashSet<>();
    @DBRef(lazy = true)
    private Set<Invoice> invoices = new HashSet<>();
    @DBRef(lazy = true)
    private Set<Site> sites = new HashSet<>();
    @DBRef(lazy = true)
    private Set<Contract> contracts = new HashSet<>();

    private Customer() {
    }

    private Customer(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.contactPerson = builder.contactPerson;
        this.orders = builder.orders;
        this.invoices = builder.invoices;
        this.contracts = builder.contracts;
        this.sites = builder.sites;
        this.isContract = builder.isContract;
    }

    @Override
    public int compareTo(Customer o) {
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the contracts
     */
    private List<Contract> sortContracts() {
        List<Contract> contractsList = new ArrayList<>();
        contractsList.addAll(contracts);
        Collections.sort(contractsList);
        return contractsList;
    }

    public Set<Contract> getContracts() {
        if (contracts != null) {
            return ImmutableSet.copyOf(sortContracts());
        } else {
            return ImmutableSet.copyOf(contracts);
        }
    }

    public Contract getLastContract() {
        if (contracts.size() > 0) {
            return sortContracts().get(0);
        } else {
            return null;
        }
    }

    /**
     * @return the sites
     */
    public Set<Site> getSites() {
        if (sites != null) {
            List<Site> siteList = new ArrayList<>();
            siteList.addAll(sites);
            Collections.sort(siteList);
            return ImmutableSet.copyOf(siteList);
        } else {
            return ImmutableSet.copyOf(sites);
        }
    }

    /**
     * @return the isContract
     */
    public boolean isIsContract() {
        return isContract;
    }

    public static class Builder {

        private final String name;
        private ContactPerson contactPerson;
        private String id;
        private Set<Order> orders;
        private Set<Invoice> invoices;
        private Set<Contract> contracts;
        private Set<Site> sites;
        private boolean isContract;

        public Builder(String name) {
            this.name = name;
        }

        public Builder contactPerson(ContactPerson contactPerson) {
            this.contactPerson = contactPerson;
            return this;
        }

        public Builder customer(Customer customer) {
            this.contactPerson = customer.getContactPerson();
            this.id = customer.getId();
            this.orders = customer.getOrders();
            this.invoices = customer.getInvoices();
            this.contracts = customer.getContracts();
            this.sites = customer.getSites();
            this.isContract = customer.isIsContract();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder isContract(boolean value) {
            this.isContract = value;
            return this;
        }

        public Builder orders(Set<Order> value) {
            this.orders = value;
            return this;
        }

        public Builder sites(Set<Site> value) {
            this.sites = value;
            return this;
        }

        public Builder invoices(Set<Invoice> value) {
            this.invoices = value;
            return this;
        }

        public Builder contracts(Set<Contract> value) {
            this.contracts = value;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public Set<Order> getOrders() {
        return ImmutableSet.copyOf(orders);
    }

    public Set<Invoice> getInvoices() {
        return ImmutableSet.copyOf(invoices);
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

    public String getLastContractID() {
        Contract lastContract = getLastContract();
        if (!isNullObject(lastContract)) {
            return lastContract.getId();
        } else {
            return null;
        }
    }

    public String getLastContactTypeName() {
        Contract lastContract = getLastContract();
        if (!isNullObject(lastContract)) {
            return lastContract.getContractTypeName();
        } else {
            return null;
        }
    }
    
    public Date getLastContactDateOfAction() {
        Contract lastContract = getLastContract();
        if (!isNullObject(lastContract)) {
            return lastContract.getDateofAction();
        } else {
            return null;
        }
    }
}
