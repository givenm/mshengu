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
public class MonthlySpendData implements Serializable, Comparable<MonthlySpendData> {

    private String id;
    private String transactionDate;
    private Date transactDate;
    private String vehicleNumber;
    private String numberPlate;
    private BigDecimal truckMonthlySpendTotal = BigDecimal.ZERO;

    @Override
    public int compareTo(MonthlySpendData o) {  // CompareTo for int and Integer types
        return this.truckMonthlySpendTotal.compareTo(o.getTruckMonthlySpendTotal());
    }
    public static Comparator<MonthlySpendData> AscOrderVehicleNumberAscOrderDateComparator = new Comparator<MonthlySpendData>() {
        @Override
        public int compare(MonthlySpendData monthlySpendData1, MonthlySpendData monthlySpendData2) {
            // Ascending order by Vehicle Number
            int compareOne = monthlySpendData1.getVehicleNumber().compareTo(monthlySpendData2.getVehicleNumber());
            // Ascending Order by Date
            int compareTwo = monthlySpendData1.getTransactDate().compareTo(monthlySpendData2.getTransactDate());

            return ((compareOne == 0) ? compareTwo : compareOne);
        }
    };
    public static Comparator<MonthlySpendData> AscOrderDateAscOrderVehicleNumberComparator = new Comparator<MonthlySpendData>() {
        @Override
        public int compare(MonthlySpendData monthlySpendData1, MonthlySpendData monthlySpendData2) {
            // Ascending Order by Date
            int compareOne = monthlySpendData1.getTransactDate().compareTo(monthlySpendData2.getTransactDate());
            // Ascending order by Vehicle Number
            int compareTwo = monthlySpendData1.getVehicleNumber().compareTo(monthlySpendData2.getVehicleNumber());


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
        final MonthlySpendData other = (MonthlySpendData) obj;
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
     * @return the truckMonthlySpendTotal
     */
    public BigDecimal getTruckMonthlySpendTotal() {
        return truckMonthlySpendTotal;
    }

    /**
     * @param truckMonthlySpendTotal the truckMonthlySpendTotal to set
     */
    public void setTruckMonthlySpendTotal(BigDecimal truckMonthlySpendTotal) {
        this.truckMonthlySpendTotal = truckMonthlySpendTotal;
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
}
