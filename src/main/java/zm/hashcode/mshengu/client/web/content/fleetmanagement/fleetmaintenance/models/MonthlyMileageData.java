/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Colin
 */
public class MonthlyMileageData implements Serializable, Comparable<MonthlyMileageData> {

    private String id;
    private String transactionDate;
    private Date transactDate;
    private String vehicleNumber;
    private String numberPlate;
    private Integer truckMonthlyMileageTotal = Integer.parseInt("0");

    @Override
    public int compareTo(MonthlyMileageData o) {  // CompareTo for int and Integer types
        return this.truckMonthlyMileageTotal.compareTo(o.getTruckMonthlyMileageTotal());
    }
    public static Comparator<MonthlyMileageData> AscOrderVehicleNumberAscOrderDateComparator = new Comparator<MonthlyMileageData>() {
        @Override
        public int compare(MonthlyMileageData monthlyMileageData1, MonthlyMileageData monthlyMileageData2) {
            // Ascending order by Vehicle Number
            int compareOne = monthlyMileageData1.getVehicleNumber().compareTo(monthlyMileageData2.getVehicleNumber());
            // Ascending Order by Date
            int compareTwo = monthlyMileageData1.getTransactDate().compareTo(monthlyMileageData2.getTransactDate());

            return ((compareOne == 0) ? compareTwo : compareOne);
        }
    };
    public static Comparator<MonthlyMileageData> AscOrderDateAscOrderVehicleNumberComparator = new Comparator<MonthlyMileageData>() {
        @Override
        public int compare(MonthlyMileageData monthlyMileageData1, MonthlyMileageData monthlyMileageData2) {
            // Ascending Order by Date
            int compareOne = monthlyMileageData1.getTransactDate().compareTo(monthlyMileageData2.getTransactDate());
            // Ascending order by Vehicle Number
            int compareTwo = monthlyMileageData1.getVehicleNumber().compareTo(monthlyMileageData2.getVehicleNumber());


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
        final MonthlyMileageData other = (MonthlyMileageData) obj;
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
     * @return the truckMonthlyMileageTotal
     */
    public Integer getTruckMonthlyMileageTotal() {
        return truckMonthlyMileageTotal;
    }

    /**
     * @param truckMonthlyMileageTotal the truckMonthlyMileageTotal to set
     */
    public void setTruckMonthlyMileageTotal(Integer truckMonthlyMileageTotal) {
        this.truckMonthlyMileageTotal = truckMonthlyMileageTotal;
    }
}
