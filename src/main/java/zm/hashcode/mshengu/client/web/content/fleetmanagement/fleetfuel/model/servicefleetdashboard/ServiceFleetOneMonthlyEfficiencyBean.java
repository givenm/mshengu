/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.servicefleetdashboard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Colin
 */
public class ServiceFleetOneMonthlyEfficiencyBean implements Serializable, Comparable<ServiceFleetOneMonthlyEfficiencyBean> {

    private String id;
    private Date transactionMonth;
    private String month;
    private BigDecimal monthlyEfficiencyValue = BigDecimal.ZERO;

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

    @Override
    public int compareTo(ServiceFleetOneMonthlyEfficiencyBean o) {
        return transactionMonth.compareTo(o.getTransactionMonth());
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
}
