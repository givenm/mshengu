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

    @Override
    public int compareTo(MaintenanceSpendBySupplier o) {
        return maintenanceCost.compareTo(o.getMaintenanceCost());
    }
    public static Comparator<MaintenanceSpendBySupplier> DescendingOrderMaintenanceCostComparator = new Comparator<MaintenanceSpendBySupplier>() {
        @Override
        public int compare(MaintenanceSpendBySupplier maintenanceSpendBySupplier1, MaintenanceSpendBySupplier maintenanceSpendBySupplier2) {
            //descending order
            return maintenanceSpendBySupplier2.getMaintenanceCost().compareTo(maintenanceSpendBySupplier1.getMaintenanceCost());

            //ascending order
            // return maintenanceSpendBySupplier1.getMaintenanceCost().compareTo(maintenanceSpendBySupplier2.getMaintenanceCost());
        }
    };
    public static Comparator<MaintenanceSpendBySupplier> AscendingOrderMaintenanceCostComparator = new Comparator<MaintenanceSpendBySupplier>() {
        @Override
        public int compare(MaintenanceSpendBySupplier maintenanceSpendBySupplier1, MaintenanceSpendBySupplier maintenanceSpendBySupplier2) {
            //descending order
//            return maintenanceSpendBySupplier2.getMaintenanceCost().compareTo(maintenanceSpendBySupplier1.getMaintenanceCost());

            //ascending order
            return maintenanceSpendBySupplier1.getMaintenanceCost().compareTo(maintenanceSpendBySupplier2.getMaintenanceCost());
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
