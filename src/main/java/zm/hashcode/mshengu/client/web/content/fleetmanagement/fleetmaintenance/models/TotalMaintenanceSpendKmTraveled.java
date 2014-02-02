/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author Colin
 */
public class TotalMaintenanceSpendKmTraveled implements Serializable, Comparable<TotalMaintenanceSpendKmTraveled> {

    private String id;
    private String numberPlate;
    private String vehicleNumber;
    private BigDecimal randPerKilometre = BigDecimal.ZERO;

    @Override
    public int compareTo(TotalMaintenanceSpendKmTraveled o) {
        return randPerKilometre.compareTo(o.getRandPerKilometre());
    }
    public static Comparator<TotalMaintenanceSpendKmTraveled> DescendingOrderComparator = new Comparator<TotalMaintenanceSpendKmTraveled>() {
        @Override
        public int compare(TotalMaintenanceSpendKmTraveled spendKmTraveled1, TotalMaintenanceSpendKmTraveled spendKmTraveled2) {
            //descending order
            return spendKmTraveled2.getRandPerKilometre().compareTo(spendKmTraveled1.getRandPerKilometre());

            //ascending order
            // return spendKmTraveled1.getRandPerKilometre().compareTo(spendKmTraveled2.getRandPerKilometre());
        }
    };
    public static Comparator<TotalMaintenanceSpendKmTraveled> AscendingOrderComparator = new Comparator<TotalMaintenanceSpendKmTraveled>() {
        @Override
        public int compare(TotalMaintenanceSpendKmTraveled spendKmTraveled1, TotalMaintenanceSpendKmTraveled spendKmTraveled2) {
//            //descending order
//            return spendKmTraveled2.getRandPerKilometre().compareTo(spendKmTraveled1.getRandPerKilometre());

            // ascending order
            return spendKmTraveled1.getRandPerKilometre().compareTo(spendKmTraveled2.getRandPerKilometre());
        }
    };

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
        final TotalMaintenanceSpendKmTraveled other = (TotalMaintenanceSpendKmTraveled) obj;
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
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the numberPlate
     */
    public String getNumberPlate() {
        return numberPlate;
    }

    /**
     * @param numberPlate the numberPlate to set
     */
    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    /**
     * @return the randPerKilometre
     */
    public BigDecimal getRandPerKilometre() {
        return randPerKilometre;
    }

    /**
     * @param randPerKilometre the randPerKilometre to set
     */
    public void setRandPerKilometre(BigDecimal randPerKilometre) {
        this.randPerKilometre = randPerKilometre;
    }

    /**
     * @return the vehicleNumber
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * @param vehicleNumber the vehicleNumber to set
     */
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
