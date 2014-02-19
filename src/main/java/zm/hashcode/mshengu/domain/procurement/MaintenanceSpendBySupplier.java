/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.domain.procurement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Colin
 */
@Document
public class MaintenanceSpendBySupplier implements Serializable, Comparable<MaintenanceSpendBySupplier> {

    @Id
    private String id;
    private String truckId;
    private String supplierId;
    private Date transactionDate;
    private BigDecimal maintenanceCost;

    private MaintenanceSpendBySupplier() {
    }

    public MaintenanceSpendBySupplier(Builder builder) {
        this.id = builder.id;
        this.supplierId = builder.supplierId;
        this.maintenanceCost = builder.maintenanceCost;
        this.transactionDate = builder.transactionDate;
        this.truckId = builder.truckId;
    }

    public static class Builder {

        private String id;
        private String truckId;
        private String supplierId;
        private Date transactionDate;
        private BigDecimal maintenanceCost;

        public Builder(Date value) {
            this.transactionDate = value;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder truckId(String value) {
            this.truckId = value;
            return this;
        }

        public Builder supplierId(String value) {
            this.supplierId = value;
            return this;
        }

        public Builder maintenanceCost(BigDecimal value) {
            this.maintenanceCost = value;
            return this;
        }

        public MaintenanceSpendBySupplier build() {
            return new MaintenanceSpendBySupplier(this);
        }
    }

    @Override
    public int compareTo(MaintenanceSpendBySupplier o) {
        return this.transactionDate.compareTo(o.transactionDate);
    }
//    public static Comparator<MaintenanceSpendBySupplier> DescendingOrderMaintenanceCostComparator = new Comparator<MaintenanceSpendBySupplier>() {
//        @Override
//        public int compare(MaintenanceSpendBySupplier maintenanceSpendBySupplier1, MaintenanceSpendBySupplier maintenanceSpendBySupplier2) {
//            //descending order
//            return maintenanceSpendBySupplier2.getMaintenanceCost().compareTo(maintenanceSpendBySupplier1.getMaintenanceCost());
//
//            //ascending order
//            // return maintenanceSpendBySupplier1.getMaintenanceCost().compareTo(maintenanceSpendBySupplier2.getMaintenanceCost());
//        }
//    };
//    public static Comparator<MaintenanceSpendBySupplier> AscendingOrderMaintenanceCostComparator = new Comparator<MaintenanceSpendBySupplier>() {
//        @Override
//        public int compare(MaintenanceSpendBySupplier maintenanceSpendBySupplier1, MaintenanceSpendBySupplier maintenanceSpendBySupplier2) {
//            //descending order
////            return maintenanceSpendBySupplier2.getMaintenanceCost().compareTo(maintenanceSpendBySupplier1.getMaintenanceCost());
//
//            //ascending order
//            return maintenanceSpendBySupplier1.getMaintenanceCost().compareTo(maintenanceSpendBySupplier2.getMaintenanceCost());
//        }
//    };
//    public static Comparator<MaintenanceSpendBySupplier> AscOrderTruckAscOrderDateComparator = new Comparator<MaintenanceSpendBySupplier>() {
//        @Override
//        public int compare(MaintenanceSpendBySupplier maintenanceSpendBySupplier1, MaintenanceSpendBySupplier maintenanceSpendBySupplier2) {
//            // Ascending Order by Truck
//            int compareOne = maintenanceSpendBySupplier1.getTruckId().compareTo(maintenanceSpendBySupplier2.getTruckId());
//            // Ascending order by Date
//            int compareTwo = maintenanceSpendBySupplier1.getTransactionDate().compareTo(maintenanceSpendBySupplier2.getTransactionDate());
//            return ((compareOne == 0) ? compareTwo : compareOne);
//        }
//    };
    public static Comparator<MaintenanceSpendBySupplier> AscOrderSupplierComparator = new Comparator<MaintenanceSpendBySupplier>() {
        @Override
        public int compare(MaintenanceSpendBySupplier maintenanceSpendBySupplier1, MaintenanceSpendBySupplier maintenanceSpendBySupplier2) {
            return maintenanceSpendBySupplier1.getSupplierId().compareTo(maintenanceSpendBySupplier2.getSupplierId());
        }
    };

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
        final MaintenanceSpendBySupplier other = (MaintenanceSpendBySupplier) obj;
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
     * @return the supplierId
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @return the maintenanceCost
     */
    public BigDecimal getMaintenanceCost() {
        return maintenanceCost;
    }
}
