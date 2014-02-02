/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Colin
 */
public class TruckAnnualDataSpend implements Serializable, Comparable<TruckAnnualDataSpend> {

    private String id;
    private String transactionDate;
    private Date transactDate;
    private String vehicleNumber;
    private String numberPlate;
    private BigDecimal truckAnnualSpendTotal = BigDecimal.ZERO;

    @Override
    public int compareTo(TruckAnnualDataSpend o) {  // CompareTo for int and Integer types
        return this.truckAnnualSpendTotal.compareTo(o.getTruckAnnualSpendTotal());
    }
    public static Comparator<TruckAnnualDataSpend> AscOrderVehicleNumberAscOrderDateComparator = new Comparator<TruckAnnualDataSpend>() {
        @Override
        public int compare(TruckAnnualDataSpend truckAnnualDataSpend1, TruckAnnualDataSpend truckAnnualDataSpend2) {
            // Ascending order by Vehicle Number
            int compareOne = truckAnnualDataSpend1.getVehicleNumber().compareTo(truckAnnualDataSpend2.getVehicleNumber());
            // Ascending Order by Date
            int compareTwo = truckAnnualDataSpend1.getTransactDate().compareTo(truckAnnualDataSpend2.getTransactDate());

            return ((compareOne == 0) ? compareTwo : compareOne);
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
        final TruckAnnualDataSpend other = (TruckAnnualDataSpend) obj;
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
     * @return the transactionDate
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the transactDate
     */
    public Date getTransactDate() {
        return transactDate;
    }

    /**
     * @param transactDate the transactDate to set
     */
    public void setTransactDate(Date transactDate) {
        this.transactDate = transactDate;
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
     * @return the truckAnnualSpendTotal
     */
    public BigDecimal getTruckAnnualSpendTotal() {
        return truckAnnualSpendTotal;
    }

    /**
     * @param truckAnnualSpendTotal the truckAnnualSpendTotal to set
     */
    public void setTruckAnnualSpendTotal(BigDecimal truckAnnualSpendTotal) {
        this.truckAnnualSpendTotal = truckAnnualSpendTotal;
    }
}
