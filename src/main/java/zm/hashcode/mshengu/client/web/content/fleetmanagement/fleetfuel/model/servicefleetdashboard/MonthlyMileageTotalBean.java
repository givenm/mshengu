/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.servicefleetdashboard;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Colin
 */
public class MonthlyMileageTotalBean implements Serializable, Comparable<MonthlyMileageTotalBean> {

    private String id;
    private Date transactionMonth;
    private String month;
    private Integer monthlyMileageTotal = new Integer("0");

    @Override
    public int compareTo(MonthlyMileageTotalBean o) {
        return transactionMonth.compareTo(o.getTransactionMonth());
    }
//    public static Comparator<MonthlyMileageTotalBean> AscOrderTransactionDateComparator = new Comparator<MonthlyMileageTotalBean>() {
//        @Override
//        public int compare(MonthlyMileageTotalBean monthlyMileageTotalBean1, MonthlyMileageTotalBean monthlyMileageTotalBean2) {
////            //Ascending order by Supplier
////            int compareOne = fuelSpendMonthlyCostBean1.getSupplierName().compareTo(FuelSpendMonthlyCostBean2.getSupplierName());
////            // Ascending Order by Truck
////            int compareTwo = fuelSpendMonthlyCostBean1.getVehicleNumber().compareTo(FuelSpendMonthlyCostBean2.getVehicleNumber());
////
////            return ((compareOne == 0) ? compareTwo : compareOne);
////            return compareOne;
//
//            return monthlyMileageTotalBean1.getTransactionMonth().compareTo(monthlyMileageTotalBean2.getTransactionMonth());
//        }
//    };

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
     * @return the monthlyMileageTotal
     */
    public Integer getMonthlyMileageTotal() {
        return monthlyMileageTotal;
    }

    /**
     * @param monthlyMileageTotal the monthlyMileageTotal to set
     */
    public void setMonthlyMileageTotal(Integer monthlyMileageTotal) {
        this.monthlyMileageTotal = monthlyMileageTotal;
    }
}
