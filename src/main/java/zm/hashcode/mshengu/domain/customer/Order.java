/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.customer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import zm.hashcode.mshengu.domain.products.Site;

/**
 *
 * @author boniface
 */
@Document
public final class Order implements Serializable, Comparable<Order> {

    private String id;
    private Date startDate;
    private Date endDate;
    private String status;
    @DBRef(lazy = true)
    private OrderType contractType;
    
    private Set<Site> sites;

    private Order() {
    }

    private Order(Builder builder) {
        this.id = builder.id;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.status = builder.status;
        this.contractType = builder.contractType;

    }

    @Override
    public int compareTo(Order o) {
        return startDate.compareTo(o.startDate);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        private String id;
        private final Date startDate;
        private Date endDate;
        private String status;
        @DBRef
        private OrderType contractType;
        private Set<Site> sites;

        public Builder(Date value) {
            this.startDate = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder endDate(Date value) {
            this.endDate = value;
            return this;
        }

        public Builder status(String value) {
            this.status = value;
            return this;
        }

        public Builder contractType(OrderType value) {
            this.contractType = value;
            return this;
        }

        public Builder sites(Set<Site> value) {
            this.sites = value;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public String getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public OrderType getContractType() {
        return contractType;
    }

    public Set<Site> getSites() {
        return ImmutableSet.copyOf(sites);
    }

    private boolean isNullObject(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getContractTypeName() {
        if (!isNullObject(contractType)) {
            return null; //contractType.getType();
        } else {
            return null;
        }
    }

    public String getContractTypeId() {
        if (!isNullObject(contractType)) {
            return contractType.getId();
        } else {
            return null;
        }
    }
}
