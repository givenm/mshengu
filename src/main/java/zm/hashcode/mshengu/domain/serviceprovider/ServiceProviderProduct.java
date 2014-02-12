/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.serviceprovider;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Ferox
 */
@Document
public class ServiceProviderProduct implements Serializable, Comparable<ServiceProviderProduct> {

    @Id
    private String id;
    private String productName;
    private BigDecimal price;
    @DBRef
    private ServiceProviderProductCategory productCategory;
    private String itemNumber;
    private String unit;
    private String volume;

    private ServiceProviderProduct() {
    }

    private ServiceProviderProduct(Builder builder) {
        this.id = builder.id;
        this.productName = builder.productName;
        this.price = builder.price;
        this.productCategory = builder.productCategory;
        this.itemNumber = builder.itemNumber;
        this.unit = builder.unit;
        this.volume = builder.volume;
    }

    @Override
    public int compareTo(ServiceProviderProduct o) {
        return getProductName().compareTo(o.getProductName());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.getId());
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
        final ServiceProviderProduct other = (ServiceProviderProduct) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public String getUnit() {
        return unit;
    }

    public String getVolume() {
        return volume;
    }

    
    
    /**
     * @return the productCategory
     */
    public ServiceProviderProductCategory getProductCategory() {
        return productCategory;
    }

    public static class Builder {

        private String id;
        private String productName;
        private BigDecimal price;
        private ServiceProviderProductCategory productCategory;
        private String itemNumber;
        private String unit;
        private String volume;

        public Builder(String productName) {
            this.productName = productName;
        }

        public Builder serviceProviderProduct(ServiceProviderProduct serviceProviderProduct) {
            this.id = serviceProviderProduct.getId();
            this.productName = serviceProviderProduct.getProductName();
            this.price = serviceProviderProduct.getPrice();
            this.productCategory = serviceProviderProduct.getProductCategory();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder price(BigDecimal value) {
            this.price = value;
            return this;
        }

        public Builder productCategory(ServiceProviderProductCategory value) {
            this.productCategory = value;
            return this;
        }
        
        public Builder itemNumber(String value) {
            this.itemNumber = value;
            return this;
        }

        public Builder unit(String value) {
            this.unit = value;
            return this;
        }

        public Builder volume(String value) {
            this.volume = value;
            return this;
        }

        public ServiceProviderProduct build() {
            return new ServiceProviderProduct(this);
        }
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
    
    public String getServiceProviderProductCategoryName() {
        if (!isNullObject(productCategory)) {
            return productCategory.getCategoryName();
        } else {
            return null;
        }
    }

    public String getServiceProviderProductCategoryId() {
        if (!isNullObject(productCategory)) {
            return productCategory.getId();
        } else {
            return null;
        }
    }
	
    
}
