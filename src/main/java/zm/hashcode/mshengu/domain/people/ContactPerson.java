/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.people;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Ferox
 */
@Document
public class ContactPerson implements Serializable, Comparable<ContactPerson> {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String mainNumber;
    private String otherNumber;
    private String faxNumber;
    private String emailAddress;
    private String address1;
    private String address2;
    private String city;
    private String code;
    private String parentId;
    private String position;

    private ContactPerson() {
    }

    private ContactPerson(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.mainNumber = builder.mainNumber;
        this.otherNumber = builder.otherNumber;
        this.emailAddress = builder.emailAddress;
        this.address1 = builder.address1;
        this.address2 = builder.address2;
        this.faxNumber = builder.faxNumber;
        this.city = builder.city;
        this.code = builder.code;
        this.parentId = builder.parentId;
        this.position = builder.position;
    }

    @Override
    public int compareTo(ContactPerson o) {
        return lastName.compareToIgnoreCase(o.lastName);
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    public static class Builder {

        private String id;
        //competency type Computer Skills, specific competencies could include Data Entry, Software Use and Document Formatting
        private String firstName;
        private final String lastName;
        private String mainNumber;
        private String otherNumber;
        private String faxNumber;
        private String emailAddress;
        private String address1;
        private String address2;
        private String city;
        private String code;
        private String parentId;
        private String position;

        public Builder(String firstName, String lastName) {
            this.lastName = lastName;
            this.firstName = firstName;
        }

        public Builder ContactPerson(ContactPerson contactPerson) {
            this.id = contactPerson.getId();
            this.mainNumber = contactPerson.getMainNumber();
            this.otherNumber = contactPerson.getOtherNumber();
            this.faxNumber = contactPerson.getFaxNumber();
            this.emailAddress = contactPerson.getEmailAddress();
            this.address1 = contactPerson.getAddress1();
            this.address2 = contactPerson.getAddress2();
            this.city = contactPerson.getCity();
            this.parentId = contactPerson.getParentId();
            this.position = contactPerson.getPosition();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder parentId(String value) {
            this.parentId = value;
            return this;
        }
        
        public Builder position(String value) {
            this.position = value;
            return this;
        }

        public Builder faxNumber(String value) {
            this.faxNumber = value;
            return this;
        }

        public Builder address2(String value) {
            this.address2 = value;
            return this;
        }

        public Builder city(String value) {
            this.city = value;
            return this;
        }

        public Builder code(String value) {
            this.code = value;
            return this;
        }

        public Builder mainNumber(String value) {
            this.mainNumber = value;
            return this;
        }

        public Builder otherNumber(String value) {
            this.otherNumber = value;
            return this;
        }

        public Builder emailAddress(String value) {
            this.emailAddress = value;
            return this;
        }

        public Builder address1(String value) {
            this.address1 = value;
            return this;
        }

        public ContactPerson build() {
            return new ContactPerson(this);
        }
    }

    /**
     * @return the id
     */
    
    public String getPosition() {
        return position;
    }

    
    public String getId() {
        return id;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the mainNumber
     */
    public String getMainNumber() {
        return mainNumber;
    }

    /**
     * @return the otherNumber
     */
    public String getOtherNumber() {
        return otherNumber;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @return the address
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @return the position
     */
    public String getCode() {
        return code;
    }
}
