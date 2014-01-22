/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author geek
 */
public class DailyTrackerBean implements Serializable {

    private Date transactionDate;
    private String truckId;
    private String driverId;
    private Double operatingSpec = new Double("0.0");
    private Double manufacturerSpec = new Double("0.0");
    private BigDecimal targetSpec = BigDecimal.ZERO;
    private BigDecimal operationalAllowance = BigDecimal.ZERO;
    private BigDecimal mtd = BigDecimal.ZERO;

    /**
     * @return the truckId
     */
    public String getTruckId() {
        return truckId;
    }

    /**
     * @param truckId the truckId to set
     */
    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    /**
     * @return the driverId
     */
    public String getDriverId() {
        return driverId;
    }

    /**
     * @param driverId the driverId to set
     */
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    /**
     * @return the operatingSpec
     */
    public Double getOperatingSpec() {
        return operatingSpec;
    }

    /**
     * @param operatingSpec the operatingSpec to set
     */
    public void setOperatingSpec(Double operatingSpec) {
        this.operatingSpec = operatingSpec;
    }

    /**
     * @return the manufacturerSpec
     */
    public Double getManufacturerSpec() {
        return manufacturerSpec;
    }

    /**
     * @param manufacturerSpec the manufacturerSpec to set
     */
    public void setManufacturerSpec(Double manufacturerSpec) {
        this.manufacturerSpec = manufacturerSpec;
    }

    /**
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the operationalAllowance
     */
    public BigDecimal getOperationalAllowance() {
        return operationalAllowance;
    }

    /**
     * @param operationalAllowance the operationalAllowance to set
     */
    public void setOperationalAllowance(BigDecimal operationalAllowance) {
        this.operationalAllowance = operationalAllowance;
    }

    /**
     * @return the targetSpec
     */
    public BigDecimal getTargetSpec() {
        return targetSpec;
    }

    /**
     * @param targetSpec the targetSpec to set
     */
    public void setTargetSpec(BigDecimal targetSpec) {
        this.targetSpec = targetSpec;
    }

    /**
     * @return the mtd
     */
    public BigDecimal getMtd() {
        return mtd;
    }

    /**
     * @param mtd the mtd to set
     */
    public void setMtd(BigDecimal mtd) {
        this.mtd = mtd;
    }
}
