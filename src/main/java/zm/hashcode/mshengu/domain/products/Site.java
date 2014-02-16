/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.products;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.domain.ui.location.Address;
import zm.hashcode.mshengu.domain.ui.location.Location;

/**
 *
 * @author boniface
 */
@Document
public final class Site implements Serializable, Comparable<Site> {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    @DBRef
    private Address address;
    @DBRef
    private Location location;
    @DBRef
    private ContactPerson contactPerson;
    @DBRef
    private ServiceProvider serviceProvider;
    @DBRef
    private Set<SiteServiceContractLifeCycle> siteServiceContractLifeCycle = new HashSet<>();
    @DBRef
    private Set<SiteServiceLog> siteServiceLog = new HashSet<>();
    private String parentId;
    private String status;
    private boolean active;

    private Site() {
    }

    private Site(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.address = builder.address;
        this.location = builder.location;
        this.contactPerson = builder.contactPerson;
        this.siteServiceContractLifeCycle = builder.siteServiceContractLifeCycle;
        this.siteServiceLog = builder.siteServiceLog;
        this.parentId = builder.parentId;
        this.status = builder.status;
        this.serviceProvider = builder.serviceProvider;
        this.active = builder.active;
    }

    @Override
    public int compareTo(Site o) {
        return getName().compareToIgnoreCase(o.getName());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.getId());
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
        final Site other = (Site) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the serviceProvider
     */
    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    public static class Builder {

        private String id;
        private final String name;
        private Address address;
        private Location location;
        private ContactPerson contactPerson;
        private ServiceProvider serviceProvider;
        private Set<SiteServiceContractLifeCycle> siteServiceContractLifeCycle = new HashSet<>();
        private Set<SiteServiceLog> siteServiceLog = new HashSet<>();
        private String parentId;
        private String status;
        private boolean active;

        public Builder(String name) {
            this.name = name;
        }

        public Builder site(Site site) {
            this.id = site.getId();
//        this.name = site.name;
            this.address = site.getAddress();
            this.location = site.getLocation();
            this.contactPerson = site.getContactPerson();
            this.siteServiceContractLifeCycle = site.getSiteServiceContractLifeCycle();
            this.siteServiceLog = site.getSiteServiceLog();
            this.parentId = site.getParentId();
            this.status = site.getStatus();
            this.serviceProvider = site.getServiceProvider();
            this.active = site.isActive();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder address(Address value) {
            this.address = value;
            return this;
        }

        public Builder location(Location value) {
            this.location = value;
            return this;
        }

        public Builder contactPerson(ContactPerson value) {
            this.contactPerson = value;
            return this;
        }

        public Builder serviceProvider(ServiceProvider value) {
            this.serviceProvider = value;
            return this;
        }

        public Builder siteServiceContractLifeCycle(Set<SiteServiceContractLifeCycle> value) {
            this.siteServiceContractLifeCycle = value;
            return this;
        }

        public Builder siteServiceLog(Set<SiteServiceLog> value) {
            this.siteServiceLog = value;
            return this;
        }

        public Builder parentId(String value) {
            this.parentId = value;
            return this;
        }

        public Builder status(String value) {
            this.status = value;
            return this;
        }

        public Builder active(boolean value) {
            this.active = value;
            return this;
        }

        public Site build() {
            return new Site(this);
        }
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    private List<SiteServiceLog> sortSiteServiceLog() {
        List<SiteServiceLog> siteServiceLogList = new ArrayList<>();
        siteServiceLogList.addAll(siteServiceLog);
        Collections.sort(siteServiceLogList);
        return siteServiceLogList;
    }

    /**
     * @return the siteServiceContractLifeCycle
     */
    public Set<SiteServiceLog> getSiteServiceLog() {
        if (siteServiceLog != null) {
            return ImmutableSet.copyOf(sortSiteServiceLog());
        } else {
            return ImmutableSet.copyOf(siteServiceLog);
        }
    }

    public SiteServiceLog getLastSiteServiceLog() {
        if (siteServiceLog.size() > 0) {
            return sortSiteServiceLog().get(0);
        } else {
            return null;
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @return the contactPerson
     */
    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    private List<SiteServiceContractLifeCycle> sortSiteServiceContractLifeCycle() {
        List<SiteServiceContractLifeCycle> serviceContractLifeCyclesSortedList = new ArrayList<>();
        serviceContractLifeCyclesSortedList.addAll(siteServiceContractLifeCycle);
        Collections.sort(serviceContractLifeCyclesSortedList);
        return serviceContractLifeCyclesSortedList;
    }

    /**
     * @return the siteServiceContractLifeCycle
     */
    public Set<SiteServiceContractLifeCycle> getSiteServiceContractLifeCycle() {
        if (siteServiceContractLifeCycle != null) {
            return ImmutableSet.copyOf(sortSiteServiceContractLifeCycle());
        } else {
            return ImmutableSet.copyOf(siteServiceContractLifeCycle);
        }
    }

    public SiteServiceContractLifeCycle getLastSiteServiceContractLifeCycle() {
        if (siteServiceContractLifeCycle.size() > 0) {
            return sortSiteServiceContractLifeCycle().get(0);
        } else {
            return null;
        }
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    //   return ImmutableSet.copyOf(siteServiceContractLifeCycle);

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getAddressStreetAddress() {
        if (!isNullObject(address)) {
            return address.getStreetAddress();
        } else {
            return null;
        }
    }

    public String getAddressPostalCode() {
        if (!isNullObject(address)) {
            return address.getPostalCode();
        } else {
            return null;
        }
    }

    public String getAddressId() {
        if (!isNullObject(address)) {
            return address.getId();
        } else {
            return null;
        }
    }

    public String getLocationId() {
        if (!isNullObject(location)) {
            return location.getId();
        } else {
            return null;
        }
    }

    public String getLocationName() {
        if (!isNullObject(location)) {
            return location.getName();
        } else {
            return null;
        }
    }

    public String getLocationLatitude() {
        if (!isNullObject(location)) {
            return location.getLatitude();
        } else {
            return null;
        }
    }

    public String getLocationLongitude() {
        if (!isNullObject(location)) {
            return location.getLongitude();
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

    public int getNumberOfTotalUnits() {
        SiteServiceContractLifeCycle lastContractLifeCycle = getLastSiteServiceContractLifeCycle();
        if (lastContractLifeCycle != null) {
            return lastContractLifeCycle.getExpectedNumberOfUnits();
        } else {
            return 0;
        }
    }
     public String getLastLifeCycleContractType() {
        SiteServiceContractLifeCycle lastContractLifeCycle = getLastSiteServiceContractLifeCycle();
        if (lastContractLifeCycle != null) {
            return lastContractLifeCycle.getContractType();
        } else {
            return "";
        }
    } 
}
