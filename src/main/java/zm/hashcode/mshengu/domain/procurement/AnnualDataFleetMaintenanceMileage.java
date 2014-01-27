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
public class AnnualDataFleetMaintenanceMileage implements Serializable, Comparable<AnnualDataFleetMaintenanceMileage> {

    @Id
    private String id;
    private String truckId;
    private String driverPersonId;
    private Date transactionMonth;
    private Integer monthlyMileage;

    public AnnualDataFleetMaintenanceMileage() {
    }

    public AnnualDataFleetMaintenanceMileage(Builder builder) {
        this.id = builder.id;
        this.truckId = builder.truckId;
        this.driverPersonId = builder.driverPersonId;
        this.transactionMonth = builder.transactionMonth;
        this.monthlyMileage = builder.monthlyMileage;
    }

    public static class Builder {

        private String id;
        private String truckId;
        private String driverPersonId;
        private Date transactionMonth;
        private Integer monthlyMileage;

        public Builder(Date value) {
            this.transactionMonth = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder monthlyMileage(Integer value) {
            this.monthlyMileage = value;
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

        public AnnualDataFleetMaintenanceMileage build() {
            return new AnnualDataFleetMaintenanceMileage(this);
        }
    }

    @Override
    public int compareTo(AnnualDataFleetMaintenanceMileage o) { // i wanna sort by "truckid" then by "transactionMonth"
////        return this.truckId.compareTo(o.truckId);
//        int varInt = this.truckId.compareTo(o.truckId);
//        return varInt == 0 ? this.truckId.compareTo(o.truckId) : varInt;

        if (o.truckId.equals(this.truckId)) {
//            if (o.transactionMonth.before(this.transactionMonth)) {

            return (o.transactionMonth).compareTo(this.transactionMonth);
//            }
        } else {
            return o.truckId.compareTo(this.truckId);
        }

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
        final AnnualDataFleetMaintenanceMileage other = (AnnualDataFleetMaintenanceMileage) obj;
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
     * @return the monthlyMileage
     */
    public Integer getMonthlyMileage() {
        return monthlyMileage;
    }
}
