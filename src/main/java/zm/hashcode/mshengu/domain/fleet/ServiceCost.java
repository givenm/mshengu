/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.fleet;

import zm.hashcode.mshengu.domain.serviceprovider.ServiceCategory;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Ferox
 */
@Document
public class ServiceCost implements Serializable, Comparable<ServiceCost> {

    @Id
    private String id;
    private Date transactionDate;
    private String slipNo;
    private BigDecimal serviceTotalCost;
    private String comment;
    @DBRef(lazy = true)
    private ServiceProvider serviceProvider;
    @DBRef(lazy = true)
    private ServiceCategory serviceCategory;
    

    private ServiceCost() {
    }

    private ServiceCost(ServiceCost.Builder builder) {
        this.id = builder.id;
        this.transactionDate = builder.transactionDate;
        this.slipNo = builder.slipNo;
        this.serviceTotalCost = builder.serviceTotalCost;
        this.comment = builder.comment;
        this.serviceProvider = builder.serviceProvider;
        this.serviceCategory = builder.serviceCategory;
    }

    @Override
    public int compareTo(ServiceCost o) {
        return slipNo.compareTo(o.slipNo);
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
        final ServiceCost other = (ServiceCost) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the driver
     */
    public static class Builder {

        private String id;
        private final Date transactionDate;
        private String slipNo;
        private BigDecimal serviceTotalCost;
        private String comment;
        private ServiceProvider serviceProvider;
        private ServiceCategory serviceCategory;

        public Builder slipNo(String value) {
            this.slipNo = value;
            return this;
        }

        public Builder(Date transactionDate) {
            this.transactionDate = transactionDate;

        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder serviceCategory(ServiceCategory value) {
            this.serviceCategory = value;
            return this;
        }

        public Builder comment(String value) {
            this.comment = value;
            return this;
        }

        public Builder serviceProvider(ServiceProvider value) {
            this.serviceProvider = value;
            return this;
        }

        public Builder serviceTotalCost(BigDecimal value) {
            this.serviceTotalCost = value;
            return this;
        }

        public ServiceCost build() {
            return new ServiceCost(this);
        }
    }

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
     * @return the slipNo
     */
    public String getSlipNo() {
        return slipNo;
    }

    /**
     * @return the serviceTotalCost
     */
    public BigDecimal getServiceTotalCost() {
        return serviceTotalCost;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @return the serviceProvider
     */
    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    /**
     * @return the serviceCategory
     */
    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }
    
    
    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

   
    public String getServiceCategoryId() {
        if (!isNullObject(serviceCategory)) {
            return serviceCategory.getId();
        } else {
            return null;
        }
    }

    public String getServiceCategoryName() {
        if (!isNullObject(serviceCategory)) {
            return serviceCategory.getName();
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
    
}
