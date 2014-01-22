/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Colin
 */
public class TotalMaintenanceSpendMonthly implements Serializable, Comparable<TotalMaintenanceSpendMonthly> {

    private String id;
    private Date month;
    private String monthYear;
    private BigDecimal total = BigDecimal.ZERO;

    @Override
    public int compareTo(TotalMaintenanceSpendMonthly o) {
        return month.compareTo(o.month);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final TotalMaintenanceSpendMonthly other = (TotalMaintenanceSpendMonthly) obj;
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
     * @return the month
     */
    public Date getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(Date month) {
        this.month = month;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the monthYear
     */
    public String getMonthYear() {
        return monthYear;
    }

    /**
     * @param monthYear the monthYear to set
     */
    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }
}
