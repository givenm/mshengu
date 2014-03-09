/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Colin
 */
public class DriverEfficiencyBean implements Serializable, Comparable<DriverEfficiencyBean> {

    private String id;
    private Date transactionMonth;
    private String month;
    private BigDecimal monthlyEfficiencyValue = BigDecimal.ZERO;
    private String driverName;
    private String monthlyEfficiencyColor;

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
     * @return the transactionMonth
     */
    public Date getTransactionMonth() {
        return transactionMonth;
    }

    /**
     * @param transactionMonth the transactionMonth to set
     */
    public void setTransactionMonth(Date transactionMonth) {
        this.transactionMonth = transactionMonth;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the monthlyEfficiencyValue
     */
    public BigDecimal getMonthlyEfficiencyValue() {
        return monthlyEfficiencyValue;
    }

    /**
     * @param monthlyEfficiencyValue the monthlyEfficiencyValue to set
     */
    public void setMonthlyEfficiencyValue(BigDecimal monthlyEfficiencyValue) {
        this.monthlyEfficiencyValue = monthlyEfficiencyValue;
    }

    /**
     * @return the driverName
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * @param driverName the driverName to set
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public int compareTo(DriverEfficiencyBean o) {
        return monthlyEfficiencyValue.compareTo(o.getMonthlyEfficiencyValue());
    }
    public static Comparator<DriverEfficiencyBean> DescendingEfficiencyComparator = new Comparator<DriverEfficiencyBean>() {
        @Override
        public int compare(DriverEfficiencyBean DriverEfficiencyBean1, DriverEfficiencyBean DriverEfficiencyBean2) {
//            //Ascending order by Supplier
//            int compareOne = fuelSpendMonthlyCostBean1.getSupplierName().compareTo(FuelSpendMonthlyCostBean2.getSupplierName());
//            // Ascending Order by Truck
//            int compareTwo = fuelSpendMonthlyCostBean1.getVehicleNumber().compareTo(FuelSpendMonthlyCostBean2.getVehicleNumber());
//
//            return ((compareOne == 0) ? compareTwo : compareOne);


            return DriverEfficiencyBean2.getMonthlyEfficiencyValue().compareTo(DriverEfficiencyBean1.getMonthlyEfficiencyValue());
        }
    };

    /**
     * @return the monthlyEfficiencyColor
     */
    public String getMonthlyEfficiencyColor() {
        return monthlyEfficiencyColor;
    }

    /**
     * @param monthlyEfficiencyColor the monthlyEfficiencyColor to set
     */
    public void setMonthlyEfficiencyColor(String monthlyEfficiencyColor) {
        this.monthlyEfficiencyColor = monthlyEfficiencyColor;
    }
}
