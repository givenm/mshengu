/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Colin
 */
public class TotalMaintenanceSpendBySupplier implements Serializable, Comparable<TotalMaintenanceSpendBySupplier> {

    private String id;
    private Date month;
//    private String monthYear;
    private String supplierName;
    private String vendorNumber;
    private BigDecimal spendPercentage;
    private BigDecimal total = BigDecimal.ZERO;

    @Override
    public int compareTo(TotalMaintenanceSpendBySupplier o) {
        return getMonth().compareTo(o.getMonth());
    }
    public static Comparator<TotalMaintenanceSpendBySupplier> AscOrderAmountSpendComparator = new Comparator<TotalMaintenanceSpendBySupplier>() {
        @Override
        public int compare(TotalMaintenanceSpendBySupplier totalMaintenanceSpendMonthly1, TotalMaintenanceSpendBySupplier totalMaintenanceSpendMonthly2) {
//            //Ascending order by Supplier
//            int compareOne = totalMaintenanceSpendMonthly1.getSupplierName().compareTo(totalMaintenanceSpendMonthly2.getSupplierName());
//            // Ascending Order by Truck
//            int compareTwo = totalMaintenanceSpendMonthly1.getVehicleNumber().compareTo(totalMaintenanceSpendMonthly2.getVehicleNumber());
//
//            return ((compareOne == 0) ? compareTwo : compareOne);
//            return compareOne;

            return totalMaintenanceSpendMonthly1.getTotal().compareTo(totalMaintenanceSpendMonthly2.getTotal());
        }
    };

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the month
     */
    public Date getMonth() {
        return month;
    }

    /**
     * @return the supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * @return the vendorNumber
     */
    public String getVendorNumber() {
        return vendorNumber;
    }

    /**
     * @return the spendPercentage
     */
    public BigDecimal getSpendPercentage() {
        return spendPercentage;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(Date month) {
        this.month = month;
    }

    /**
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @param vendorNumber the vendorNumber to set
     */
    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    /**
     * @param spendPercentage the spendPercentage to set
     */
    public void setSpendPercentage(BigDecimal spendPercentage) {
        this.spendPercentage = spendPercentage;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
