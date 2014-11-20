/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.chemical;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;

/**
 *
 * @author geek
 */
@Document
public class ChemicalCost implements Serializable, Comparable<ChemicalCost> {

    @Id
    private String id;
    private Date transactionDate;
    private String invoiceNumber;
    @DBRef(lazy = true)
    private ServiceProviderProduct serviceProviderProduct;
    private Integer quantityOrdered;
    private float volume;
    private BigDecimal totalPrice;

    public ChemicalCost() {
    }

    private ChemicalCost(Builder builder) {
        this.id = builder.id;
        this.transactionDate = builder.transactionDate;
        this.invoiceNumber = builder.invoiceNumber;
        this.serviceProviderProduct = builder.serviceProviderProduct;
        this.quantityOrdered = builder.quantityOrdered;
        this.volume = builder.volume;
        this.totalPrice = builder.totalPrice;
    }

    public static class Builder {

        private String id;
        private final Date transactionDate;
        private String invoiceNumber;
        private ServiceProviderProduct serviceProviderProduct;
        private Integer quantityOrdered;
        private float volume;
        private BigDecimal totalPrice;

        public Builder(Date transactionDate) {
            this.transactionDate = transactionDate;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder invoiceNumber(String value) {
            this.invoiceNumber = value;
            return this;
        }

        public Builder serviceProviderProduct(ServiceProviderProduct value) {
            this.serviceProviderProduct = value;
            return this;
        }

        public Builder quantityOrdered(Integer value) {
            this.quantityOrdered = value;
            return this;
        }

        public Builder volume(float value) {
            this.volume = value;
            return this;
        }

        public Builder totalPrice(BigDecimal value) {
            this.totalPrice = value;
            return this;
        }

        public ChemicalCost build() {
            return new ChemicalCost(this);
        }
    }

    @Override
    public int compareTo(ChemicalCost o) {
        return getInvoiceNumber().compareTo(o.getInvoiceNumber());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final ChemicalCost other = (ChemicalCost) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @return the serviceProviderProduct
     */
    public ServiceProviderProduct getServiceProviderProduct() {
        return serviceProviderProduct;
    }

    /**
     * @return the quantityOrdered
     */
    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    /**
     * @return the volume
     */
    public float getVolume() {
        return volume;
    }

    /**
     * @return the totalPrice
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * @return the invoiceNumber
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    
    
    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public String getServiceProviderProductName() {
        if (!isNullObject(serviceProviderProduct)) {
            return serviceProviderProduct.getProductName();
        } else {
            return null;
        }
    }
    
        public BigDecimal getServiceProviderProductPrice() {
        if (!isNullObject(serviceProviderProduct)) {
            return serviceProviderProduct.getPrice();
        } else {
            return null;
        }
    }

    public String getServiceProviderProductId() {
        if (!isNullObject(serviceProviderProduct)) {
            return serviceProviderProduct.getId();
        } else {
            return null;
        }
    }
    
}
