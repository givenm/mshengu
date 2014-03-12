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
public class NonServiceVehiclesTwelveMonthBean implements Serializable, Comparable<NonServiceVehiclesTwelveMonthBean> {

    private String id;
    private Date transactionMonth;
    private String month;
    private BigDecimal nonOperationalTotalAmount;
    private BigDecimal OperationalTotalAmount;

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
     * @return the nonOperationalTotalAmount
     */
    public BigDecimal getNonOperationalTotalAmount() {
        return nonOperationalTotalAmount;
    }

    /**
     * @param nonOperationalTotalAmount the nonOperationalTotalAmount to set
     */
    public void setNonOperationalTotalAmount(BigDecimal nonOperationalTotalAmount) {
        this.nonOperationalTotalAmount = nonOperationalTotalAmount;
    }

    /**
     * @return the OperationalTotalAmount
     */
    public BigDecimal getOperationalTotalAmount() {
        return OperationalTotalAmount;
    }

    /**
     * @param OperationalTotalAmount the OperationalTotalAmount to set
     */
    public void setOperationalTotalAmount(BigDecimal OperationalTotalAmount) {
        this.OperationalTotalAmount = OperationalTotalAmount;
    }

    @Override
    public int compareTo(NonServiceVehiclesTwelveMonthBean o) {
        return getTransactionMonth().compareTo(o.getTransactionMonth());
    }
}
