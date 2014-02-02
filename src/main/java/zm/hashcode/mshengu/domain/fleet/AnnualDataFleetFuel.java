/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.fleet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author ColinWa
 */
@Document
public class AnnualDataFleetFuel implements Serializable {

    @Id
    private String id;
    private Date transactionDate;
    private BigDecimal monthlyFuelCost;
    private Integer closingMileage = 0;
    private String truckId;
    private String driverPersonId;

    private AnnualDataFleetFuel() {
    }

    private AnnualDataFleetFuel(Builder builder) {
        this.id = builder.id;
        this.transactionDate = builder.transactionDate;
        this.monthlyFuelCost = builder.monthlyFuelCost;
        this.closingMileage = builder.closingMileage;
        this.truckId = builder.truckId;
        this.driverPersonId = builder.driverPersonId;
    }

    public static class Builder {

        private String id;
        private Date transactionDate;
        private BigDecimal monthlyFuelCost;
        private final Integer closingMileage;
        private String truckId;
        private String driverPersonId;

        public Builder(Integer value) {
            this.closingMileage = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder transactionDate(Date value) {
            this.transactionDate = value;
            return this;
        }

        public Builder monthlyFuelCost(BigDecimal value) {
            this.monthlyFuelCost = value;
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

        public AnnualDataFleetFuel build() {
            return new AnnualDataFleetFuel(this);
        }
    }

//    @Override
//    public int compareTo(OperatingCost o) {
//        return getSlipNo().compareTo(o.getSlipNo());
//    }
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
        final AnnualDataFleetFuel other = (AnnualDataFleetFuel) obj;
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
     * @return the monthlyFuelCost
     */
    public BigDecimal getMonthlyFuelCost() {
        return monthlyFuelCost;
    }

    /**
     * @return the closingMileage
     */
    public Integer getClosingMileage() {
        return closingMileage;
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
}
