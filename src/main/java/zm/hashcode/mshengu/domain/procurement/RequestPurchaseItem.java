/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.procurement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProduct;

/**
 *
 * @author Luckbliss
 */
@Document
public class RequestPurchaseItem implements Serializable, Comparable<RequestPurchaseItem> {

    private String id;
    @DBRef
    private ServiceProviderProduct product;
    private String quantity;
    private BigDecimal subTotal;
    private String itemDescription;
    private String itemNumber;
    private BigDecimal unitPrice;
    private String unit;
    private String volume;

    private RequestPurchaseItem() {
    }

    private RequestPurchaseItem(Builder builder) {
        this.id = builder.id;
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.subTotal = builder.subTotal;
        this.itemDescription = builder.itemDescription;
        this.itemNumber = builder.itemNumber;
        this.unitPrice = builder.unitPrice;
        this.unit = builder.unit;
        this.volume = builder.volume;
    }

    public static class Builder {

        private String id;
        private ServiceProviderProduct product;
        private final String quantity;
        private BigDecimal subTotal;
        private String itemDescription;
        private String itemNumber;
        private BigDecimal unitPrice;
        private String unit;
        private String volume;

        public Builder(String value) {
            this.quantity = value;
        }

        public Builder requestPurchaseItem(RequestPurchaseItem requestPurchaseItem) {
            this.id = requestPurchaseItem.getId();
            this.product = requestPurchaseItem.getProduct();
            this.subTotal = requestPurchaseItem.getSubTotal();
            this.itemDescription = requestPurchaseItem.getItemDescription();
            this.itemNumber = requestPurchaseItem.getItemNumber();
            this.unitPrice = requestPurchaseItem.getUnitPrice();
            this.unit = requestPurchaseItem.getUnit();
            this.volume = requestPurchaseItem.getVolume();
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder itemDescription(String value) {
            this.itemDescription = value;
            return this;
        }

        public Builder itemNumber(String value) {
            this.itemNumber = value;
            return this;
        }

        public Builder unitPrice(BigDecimal value) {
            this.unitPrice = value;
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

        public Builder product(ServiceProviderProduct value) {
            this.product = value;
            return this;
        }

        public Builder subTotal(BigDecimal value) {
            this.subTotal = value;
            return this;
        }

        public RequestPurchaseItem build() {
            return new RequestPurchaseItem(this);
        }
    }

    @Override
    public int compareTo(RequestPurchaseItem o) {
        return quantity.compareToIgnoreCase(o.quantity);
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
        final RequestPurchaseItem other = (RequestPurchaseItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public ServiceProviderProduct getProduct() {
        return product;
    }

    public String getQuantity() {
        return quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getUnit() {
        return unit;
    }

    public String getVolume() {
        return volume;
    }
    
    
    
    
    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public String getServiceProviderProductName() {
        if (!isNullObject(product)) {
            return product.getProductName();
        } else {
            return null;
        }
    }

    public String getServiceProviderProductId() {
        if (!isNullObject(product)) {
            return product.getId();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "RequestPurchaseItem{" + "id=" + id + ", product=" + product + ", quantity=" + quantity + ", subTotal=" + subTotal + ", itemDescription=" + itemDescription + ", itemNumber=" + itemNumber + ", unitPrice=" + unitPrice + ", unit=" + unit + ", volume=" + volume + '}';
    }
    
}
