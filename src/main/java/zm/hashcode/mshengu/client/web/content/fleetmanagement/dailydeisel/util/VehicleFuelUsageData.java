/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author ColinWa
 */
public class VehicleFuelUsageData implements Serializable {

    private String truckId;
    private String date;
    private String truckNumber;
    private String truckPlateNumber;
    private String driverName;
    private BigDecimal totalFuelCost = BigDecimal.ZERO;
    private Integer totalMileage = 0;
    //    private Double fuelSpec;
//    private Double operationalAllowance;
    private Double target;
    private Double mtdAct;

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
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the truckNumber
     */
    public String getTruckNumber() {
        return truckNumber;
    }

    /**
     * @param truckNumber the truckNumber to set
     */
    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    /**
     * @return the truckPlateNumber
     */
    public String getTruckPlateNumber() {
        return truckPlateNumber;
    }

    /**
     * @param truckPlateNumber the truckPlateNumber to set
     */
    public void setTruckPlateNumber(String truckPlateNumber) {
        this.truckPlateNumber = truckPlateNumber;
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

    /**
     * @return the target
     */
    public Double getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(Double target) {
        this.target = target;
    }

    /**
     * @return the mtdAct
     */
    public Double getMtdAct() {
        return mtdAct;
    }

    /**
     * @param mtdAct the mtdAct to set
     */
    public void setMtdAct(Double mtdAct) {
        this.mtdAct = mtdAct;
    }

    /**
     * @return the totalFuelCost
     */
    public BigDecimal getTotalFuelCost() {
        return totalFuelCost;
    }

    /**
     * @param totalFuelCost the totalFuelCost to set
     */
    public void setTotalFuelCost(BigDecimal totalFuelCost) {
        this.totalFuelCost = totalFuelCost;
    }

    /**
     * @return the totalMileage
     */
    public Integer getTotalMileage() {
        return totalMileage;
    }

    /**
     * @param totalMileage the totalMileage to set
     */
    public void setTotalMileage(Integer totalMileage) {
        this.totalMileage = totalMileage;
    }
}
