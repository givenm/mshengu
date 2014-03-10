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
public class MonthlyFuelExpenseBean implements Serializable, Comparable<MonthlyFuelExpenseBean> {

    private String id;
    private Date transactionMonth;
    private String month;
    private BigDecimal nonOperationalTrucksFuelTotal;
    private BigDecimal operationalTrucksFuelTotal;
    private BigDecimal serviceTrucksFuelTotal;
    private BigDecimal allTrucksFuelTotal;

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
     * @return the nonOperationalTrucksFuelTotal
     */
    public BigDecimal getNonOperationalTrucksFuelTotal() {
        return nonOperationalTrucksFuelTotal;
    }

    /**
     * @param nonOperationalTrucksFuelTotal the nonOperationalTrucksFuelTotal to
     * set
     */
    public void setNonOperationalTrucksFuelTotal(BigDecimal nonOperationalTrucksFuelTotal) {
        this.nonOperationalTrucksFuelTotal = nonOperationalTrucksFuelTotal;
    }

    /**
     * @return the operationalTrucksFuelTotal
     */
    public BigDecimal getOperationalTrucksFuelTotal() {
        return operationalTrucksFuelTotal;
    }

    /**
     * @param operationalTrucksFuelTotal the operationalTrucksFuelTotal to set
     */
    public void setOperationalTrucksFuelTotal(BigDecimal operationalTrucksFuelTotal) {
        this.operationalTrucksFuelTotal = operationalTrucksFuelTotal;
    }

    /**
     * @return the serviceTrucksFuelTotal
     */
    public BigDecimal getServiceTrucksFuelTotal() {
        return serviceTrucksFuelTotal;
    }

    /**
     * @param serviceTrucksFuelTotal the serviceTrucksFuelTotal to set
     */
    public void setServiceTrucksFuelTotal(BigDecimal serviceTrucksFuelTotal) {
        this.serviceTrucksFuelTotal = serviceTrucksFuelTotal;
    }

    /**
     * @return the allTrucksFuelTotal
     */
    public BigDecimal getAllTrucksFuelTotal() {
        return allTrucksFuelTotal;
    }

    /**
     * @param allTrucksFuelTotal the allTrucksFuelTotal to set
     */
    public void setAllTrucksFuelTotal(BigDecimal allTrucksFuelTotal) {
        this.allTrucksFuelTotal = allTrucksFuelTotal;
    }

    @Override
    public int compareTo(MonthlyFuelExpenseBean o) {
        return getTransactionMonth().compareTo(o.getTransactionMonth());
    }
}
