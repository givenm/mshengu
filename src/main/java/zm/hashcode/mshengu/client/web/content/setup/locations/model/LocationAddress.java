/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations.model;

import zm.hashcode.mshengu.domain.ui.location.*;
import java.io.Serializable;

/**
 *
 * @author boniface
 */
public final class LocationAddress implements Serializable, Comparable<LocationAddress> {

    private String postalAddress;
    private String physicalAddress;
    private String contactNumber;
    private String postalCode;
    private String emailAddress;

    private LocationAddress() {
    }

    private LocationAddress(Builder builder) {
        this.postalAddress = builder.postalAddress;
        this.physicalAddress = builder.physicalAddress;
        this.contactNumber = builder.contactNumber;
        this.postalCode = builder.postalCode;
        this.emailAddress = builder.emailAddress;
    }

    @Override
    public int compareTo(LocationAddress o) {
        return physicalAddress.compareToIgnoreCase(o.physicalAddress);
    }

    public static class Builder {

        private final String postalAddress;
        private String physicalAddress;
        private String contactNumber;
        private String postalCode;
        private String emailAddress;

        public Builder(String value) {
            this.postalAddress = value;
        }

        public Builder physicalAddress(String value) {
            this.physicalAddress = value;
            return this;
        }

        public Builder contactNumber(String value) {
            this.contactNumber = value;
            return this;
        }

        public Builder postalCode(String value) {
            this.postalCode = value;
            return this;
        }

        public Builder emailAddress(String value) {
            this.emailAddress = value;
            return this;
        }

        public LocationAddress build() {
            return new LocationAddress(this);
        }
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
