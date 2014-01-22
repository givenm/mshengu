/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.procurement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Colin
 */
@Document
public class AnnualDataFleetMaintenanceCost implements Serializable, Comparable<AnnualDataFleetMaintenanceCost> {

    @Id
    private String id;
    private String truckId;
    private String driverPersonId;
    private Date transactionMonth;
    private BigDecimal monthlyMaintenanceCost;

    public AnnualDataFleetMaintenanceCost() {
    }

    public AnnualDataFleetMaintenanceCost(Builder builder) {
        this.id = builder.id;
        this.truckId = builder.truckId;
        this.driverPersonId = builder.driverPersonId;
        this.transactionMonth = builder.transactionMonth;
        this.monthlyMaintenanceCost = builder.monthlyMaintenanceCost;
    }

    public static class Builder {

        private String id;
        private final Date transactionMonth;
        private BigDecimal monthlyMaintenanceCost;
        private String truckId;
        private String driverPersonId;

        public Builder(Date value) {
            this.transactionMonth = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder monthlyMaintenanceCost(BigDecimal value) {
            this.monthlyMaintenanceCost = value;
            return this;
        }

        public Builder truckId(String value) {
            this.truckId = value;
            return this;
        }

        public Builder driverPersonId(String value) {
            this.driverPersonId = value;
            return this;
        }

        public AnnualDataFleetMaintenanceCost build() {
            return new AnnualDataFleetMaintenanceCost(this);
        }
    }

    @Override
    public int compareTo(AnnualDataFleetMaintenanceCost o) {
        return truckId.compareTo(o.truckId);
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
        final AnnualDataFleetMaintenanceCost other = (AnnualDataFleetMaintenanceCost) obj;
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
     * @return the truckId
     */
    public String getTruckId() {
        return truckId;
    }

    /**
     * @return the driverPersonId
     */
    public String getDriverPersonId() {
        return driverPersonId;
    }

    /**
     * @return the transactionMonth
     */
    public Date getTransactionMonth() {
        return transactionMonth;
    }

    /**
     * @return the monthlyMaintenanceCost
     */
    public BigDecimal getMonthlyMaintenanceCost() {
        return monthlyMaintenanceCost;
    }
}
