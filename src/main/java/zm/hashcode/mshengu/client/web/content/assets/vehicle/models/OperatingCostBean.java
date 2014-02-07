/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferox
 */
public class OperatingCostBean implements Serializable {

    private String id;
    private Date transactionDate;
    @NotNull
    private String slipNo;
    private Integer speedometer;
    private Double fuelLitres;
    private BigDecimal fuelCost = new BigDecimal(BigInteger.ZERO);
    private Double oilLitres;
    private BigDecimal oilCost = new BigDecimal(BigInteger.ZERO);
    private BigDecimal otherCosts = new BigDecimal(BigInteger.ZERO);
    private String driverId;

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
     * @return the slipNo
     */
    public String getSlipNo() {
        return slipNo;
    }

    /**
     * @param slipNo the slipNo to set
     */
    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    /**
     * @return the speedometer
     */
    public Integer getSpeedometer() {
        return speedometer;
    }

    /**
     * @param speedometer the speedometer to set
     */
    public void setSpeedometer(Integer speedometer) {
        this.speedometer = speedometer;
    }

    /**
     * @return the fuelLitres
     */
    public Double getFuelLitres() {
        return fuelLitres;
    }

    /**
     * @param fuelLitres the fuelLitres to set
     */
    public void setFuelLitres(Double fuelLitres) {
        this.fuelLitres = fuelLitres;
    }

    /**
     * @return the fuelCost
     */
    public BigDecimal getFuelCost() {
        return fuelCost;
    }

    /**
     * @param fuelCost the fuelCost to set
     */
    public void setFuelCost(BigDecimal fuelCost) {
        this.fuelCost = fuelCost;
    }

    /**
     * @return the oilLitres
     */
    public Double getOilLitres() {
        return oilLitres;
    }

    /**
     * @param oilLitres the oilLitres to set
     */
    public void setOilLitres(Double oilLitres) {
        this.oilLitres = oilLitres;
    }

    /**
     * @return the oilCost
     */
    public BigDecimal getOilCost() {
        return oilCost;
    }

    /**
     * @param oilCost the oilCost to set
     */
    public void setOilCost(BigDecimal oilCost) {
        this.oilCost = oilCost;
    }

    /**
     * @return the otherCosts
     */
    public BigDecimal getOtherCosts() {
        return otherCosts;
    }

    /**
     * @param otherCosts the otherCosts to set
     */
    public void setOtherCosts(BigDecimal otherCosts) {
        this.otherCosts = otherCosts;
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
}
