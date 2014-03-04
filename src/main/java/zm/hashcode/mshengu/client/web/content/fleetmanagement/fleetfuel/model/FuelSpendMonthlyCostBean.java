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
public class FuelSpendMonthlyCostBean implements Serializable, Comparable<FuelSpendMonthlyCostBean> {

    private String id;
    private Date transactionMonth;
    private String month;
//    private String truckId;
    private BigDecimal monthRandPerLiter = BigDecimal.ZERO;
    private BigDecimal monthlyAmountSpend = BigDecimal.ZERO;

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
     * @return the monthlyAmountSpend
     */
    public BigDecimal getMonthlyAmountSpend() {
        return monthlyAmountSpend;
    }

    /**
     * @param monthlyAmountSpend the monthlyAmountSpend to set
     */
    public void setMonthlyAmountSpend(BigDecimal monthlyAmountSpend) {
        this.monthlyAmountSpend = monthlyAmountSpend;
    }

    @Override
    public int compareTo(FuelSpendMonthlyCostBean o) {
        return getTransactionMonth().compareTo(o.getTransactionMonth());
    }
    public static Comparator<FuelSpendMonthlyCostBean> AscOrderTransactionDateComparator = new Comparator<FuelSpendMonthlyCostBean>() {
        @Override
        public int compare(FuelSpendMonthlyCostBean fuelSpendMonthlyCostBean1, FuelSpendMonthlyCostBean FuelSpendMonthlyCostBean2) {
//            //Ascending order by Supplier
//            int compareOne = fuelSpendMonthlyCostBean1.getSupplierName().compareTo(FuelSpendMonthlyCostBean2.getSupplierName());
//            // Ascending Order by Truck
//            int compareTwo = fuelSpendMonthlyCostBean1.getVehicleNumber().compareTo(FuelSpendMonthlyCostBean2.getVehicleNumber());
//
//            return ((compareOne == 0) ? compareTwo : compareOne);
//            return compareOne;

            return fuelSpendMonthlyCostBean1.getTransactionMonth().compareTo(FuelSpendMonthlyCostBean2.getTransactionMonth());
        }
    };

    /**
     * @return the monthRandPerLiter
     */
    public BigDecimal getMonthRandPerLiter() {
        return monthRandPerLiter;
    }

    /**
     * @param monthRandPerLiter the monthRandPerLiter to set
     */
    public void setMonthRandPerLiter(BigDecimal monthRandPerLiter) {
        this.monthRandPerLiter = monthRandPerLiter;
    }
}
