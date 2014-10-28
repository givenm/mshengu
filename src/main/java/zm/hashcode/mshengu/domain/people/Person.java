/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.people;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.ui.location.Address;
import zm.hashcode.mshengu.domain.ui.location.Contact;
import zm.hashcode.mshengu.domain.ui.util.Role;

/**
 *
 * @author boniface
 */
@Document
public final class Person implements Serializable, Comparable<Person> {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String othername;
    private Date dateofbirth;
    private String password;
    @DBRef(lazy = true)
    private Set<Role> role = new HashSet<>();
    @Indexed(unique = true)
    private String username;
    private String institutionId;
    private boolean enable;
    private boolean user;
    private boolean requestor;
    private String initials;
    @DBRef(lazy = true)
    private Address address;
    @DBRef(lazy = true)
    private Set<Contact> contact = new HashSet<>();
    @DBRef(lazy = true)
    private EmployeeDetail employeeDetails;
    @DBRef(lazy = true)
    private Set<ContactPerson> contactPerson = new HashSet<>();

    private Person() {
    }

    private Person(Builder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.othername = builder.othername;
        this.dateofbirth = builder.dateofbirth;
        this.password = builder.password;
        this.role = builder.role;
        this.username = builder.username;
//        this.demography = builder.demography;
        this.enable = builder.enable;
        this.institutionId = builder.institutionId;
        this.initials = builder.initials;
        this.address = builder.address;
        this.contact = builder.contact;
        this.employeeDetails = builder.employeeDetails;
        this.contactPerson = builder.contactPerson;
        this.institutionId = builder.institutionId;
        this.requestor = builder.requestor;
        this.user = builder.user;
    }

    @Override
    public int compareTo(Person o) {
        return firstname.compareToIgnoreCase(o.firstname);
    }

    /**
     * @return the user
     */
    public boolean isUser() {
        return user;
    }

    public static class Builder {

        private String id;
        //competency type Computer Skills, specific competencies could include Data Entry, Software Use and Document Formatting
        private String firstname;
        private final String lastname;
        private String othername;
        private Date dateofbirth;
        private boolean enable;
        private String password;
        private Set<Role> role = new HashSet<>();
        private String username;
        private String institutionId;
        private String initials;
        private Address address;
        private Set<Contact> contact = new HashSet<>();
        private EmployeeDetail employeeDetails;
        private Set<ContactPerson> contactPerson = new HashSet<>();
        private boolean requestor;
        private boolean user;

        public Builder(String lastname) {
            this.lastname = lastname;
        }

        public Builder person(Person person) {
            this.id = person.id;
            this.firstname = person.getFirstname();
            this.othername = person.getOthername();
            this.dateofbirth = person.getDateofbirth();
            this.password = person.getPassword();
            this.role = person.getRole();
            this.username = person.getUsername();
            this.enable = person.isEnable();
            this.institutionId = person.getInstitutionId();
            this.initials = person.getInitials();
            this.address = person.getAddress();
            this.contact = person.getContact();
            this.employeeDetails = person.getEmployeeDetails();
            this.contactPerson = person.getContactPerson();
            this.institutionId = person.getInstitutionId();
            this.requestor = person.isRequestor();
            this.user = person.isUser();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder requestor(boolean value) {
            this.requestor = value;
            return this;
        }

        public Builder institutionId(String value) {
            this.institutionId = value;
            return this;
        }

        public Builder address(Address value) {
            this.address = value;
            return this;
        }

        public Builder employeeDetails(EmployeeDetail value) {
            this.employeeDetails = value;
            return this;
        }

        public Builder firstname(String value) {
            this.firstname = value;
            return this;
        }

        public Builder initials(String value) {
            this.initials = value;
            return this;
        }

        public Builder othername(String value) {
            this.othername = value;
            return this;
        }

        public Builder dateofbirth(Date value) {
            this.dateofbirth = value;
            return this;
        }

        public Builder enable(boolean value) {
            this.enable = value;
            return this;
        }

        public Builder user(boolean value) {
            this.user = value;
            return this;
        }

        public Builder password(String value) {
            this.password = value;
            return this;
        }

        public Builder role(Set<Role> value) {
            this.role = value;
            return this;
        }

        public Builder contactPerson(Set<ContactPerson> value) {
            this.contactPerson = value;
            return this;
        }

        public Builder username(String value) {
            this.username = value;
            return this;
        }

        public Builder contact(Set<Contact> value) {
            this.contact = value;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getOthername() {
        return othername;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRole() {
        return ImmutableSet.copyOf(role);
    }

    public String getUsername() {
        return username;
    }

    public boolean isRequestor() {
        return requestor;
    }

//    public PersonDemography getDemography() {
//        return demography;
//    }
    public boolean isEnable() {
        return enable;
    }

    /**
     * @return the institutionId
     */
    public String getInstitutionId() {
        return institutionId;
    }

    /**
     * @return the initials
     */
    public String getInitials() {
        return initials;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return the contact
     */
    public Set<Contact> getContact() {
        return ImmutableSet.copyOf(contact);
    }

    /**
     * @return the employeeDetails
     */
    public EmployeeDetail getEmployeeDetails() {
        return employeeDetails;
    }

    /**
     * @return the contactPerson
     */
    public Set<ContactPerson> getContactPerson() {
        return ImmutableSet.copyOf(contactPerson);
    }

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

    public String getEmployeeNumber() {
        if (!isNullObject(employeeDetails)) {
            return employeeDetails.getEmployeeNumber();
        } else {
            return null;
        }
    }

    public String getPayrolNumber() {
        if (!isNullObject(employeeDetails)) {
            return employeeDetails.getPayrollNumber();
        } else {
            return null;
        }
    }

    public String getEmployeeDetailsId() {
        if (!isNullObject(employeeDetails)) {
            return employeeDetails.getId();
        } else {
            return null;
        }
    }

    public String getEmployeeDetailsIdNumber() {
        if (!isNullObject(employeeDetails)) {
            return employeeDetails.getIdNumber();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", othername=" + othername + ", dateofbirth=" + dateofbirth + ", password=" + password + ", role=" + role + ", username=" + username + ", institutionId=" + institutionId + ", enable=" + enable + ", user=" + user + ", requestor=" + requestor + ", initials=" + initials + ", address=" + address + ", contact=" + contact + ", employeeDetails=" + employeeDetails + ", contactPerson=" + contactPerson + '}';
    }

}
