/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.ui.location;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 *
 * @author boniface
 */
@Document
public class Address implements Serializable, Comparable<Address>{
  
    @Id
    private String id;
    private String streetAddress;
    private String postalCode;
    
    private Address() {
    }

    private Address(Builder builder) {
        this.id = builder.id;
        this.streetAddress = builder.streetAddress;
         this.postalCode = builder.postalCode;
    }

    @Override
    public int compareTo(Address o) {
        return streetAddress.compareToIgnoreCase(o.streetAddress);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final String streetAddress;
        private String postalCode;

        public Builder(String streetAddress) {
            this.streetAddress = streetAddress;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }
        
        public Builder postalCode(String value) {
            this.postalCode = value;
            return this;
        }
        
        
       
        public Address build() {
            return new Address(this);
        }
    }

 
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the streetAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }


   

    
}
