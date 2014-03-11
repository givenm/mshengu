/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.monthlyfuelexpense;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Colin
 */
public class ServiceVehiclesTwelveMonthBean implements Serializable, Comparable<ServiceVehiclesTwelveMonthBean> {

    private String id;
    private Date transactionMonth;
    private String month;
    private BigDecimal totalAmount;

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
     * @return the totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public int compareTo(ServiceVehiclesTwelveMonthBean o) {
        return getTransactionMonth().compareTo(o.getTransactionMonth());
    }
}
