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
public class TruckBean implements Serializable {

    private String id;
    @NotNull
    private String numberPlate;
    @NotNull
    private String vehicleNumber;
    @NotNull
    private Integer startMileage;
    @NotNull
    private String categoryId;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private int registerYear;
    @NotNull
    private Date dateOfExpire;
    @NotNull
    private Double manufacturingSpec; // in Ltrs/Km
    private String vinNo;
    private String engineNo;
    private String tare;
    private String trackerGPS;
    private String radioSerialNumber;
    private String receiptNo;
    private Date paymentDate;
    private BigDecimal vehicleCost = new BigDecimal(BigInteger.ZERO);
    private String paymentMethodId;
    private String description;
    private boolean isActive;
    
    private String driverId;
//    @NotNull
//    private Double operatingSpec; // in R/km
//    @NotNull
//    private BigDecimal operationalAllowance = new BigDecimal(BigInteger.ZERO);
//    @NotNull
//    private Integer fuelSpec;

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
     * @return the numberPlate
     */
    public String getNumberPlate() {
        return numberPlate;
    }

    /**
     * @param numberPlate the numberPlate to set
     */
    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    /**
     * @return the vehicleNumber
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * @param vehicleNumber the vehicleNumber to set
     */
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    /**
     * @return the vinNo
     */
    public String getVinNo() {
        return vinNo;
    }

    /**
     * @param vinNo the vinNo to set
     */
    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    /**
     * @return the engineNo
     */
    public String getEngineNo() {
        return engineNo;
    }

    /**
     * @param engineNo the engineNo to set
     */
    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    /**
     * @return the categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the tare
     */
    public String getTare() {
        return tare;
    }

    /**
     * @param tare the tare to set
     */
    public void setTare(String tare) {
        this.tare = tare;
    }

    /**
     * @return the trackerGPS
     */
    public String getTrackerGPS() {
        return trackerGPS;
    }

    /**
     * @param trackerGPS the trackerGPS to set
     */
    public void setTrackerGPS(String trackerGPS) {
        this.trackerGPS = trackerGPS;
    }

    /**
     * @return the radioSerialNumber
     */
    public String getRadioSerialNumber() {
        return radioSerialNumber;
    }

    /**
     * @param radioSerialNumber the radioSerialNumber to set
     */
    public void setRadioSerialNumber(String radioSerialNumber) {
        this.radioSerialNumber = radioSerialNumber;
    }

    /**
     * @return the receiptNo
     */
    public String getReceiptNo() {
        return receiptNo;
    }

    /**
     * @param receiptNo the receiptNo to set
     */
    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    /**
     * @return the paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * @return the paymentMethodId
     */
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    /**
     * @param paymentMethodId the paymentMethodId to set
     */
    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the dateOfExpire
     */
    public Date getDateOfExpire() {
        return dateOfExpire;
    }

    /**
     * @param dateOfExpire the dateOfExpire to set
     */
    public void setDateOfExpire(Date dateOfExpire) {
        this.dateOfExpire = dateOfExpire;
    }

    /**
     * @return the isActive
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
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
     * @return the vehicleCost
     */
    public BigDecimal getVehicleCost() {
        return vehicleCost;
    }

    /**
     * @param vehicleCost the vehicleCost to set
     */
    public void setVehicleCost(BigDecimal vehicleCost) {
        this.vehicleCost = vehicleCost;
    }

    /**
     * @return the registerYear
     */
    public int getRegisterYear() {
        return registerYear;
    }

    /**
     * @param registerYear the registerYear to set
     */
    public void setRegisterYear(int registerYear) {
        this.registerYear = registerYear;
    }

    /**
     * @return the startMileage
     */
    public Integer getStartMileage() {
        return startMileage;
    }

    /**
     * @param startMileage the startMileage to set
     */
    public void setStartMileage(Integer startMileage) {
        this.startMileage = startMileage;
    }

//    /**
//     * @return the operatingSpec
//     */
//    public Double getOperatingSpec() {
//        return operatingSpec;
//    }
//
//    /**
//     * @param operatingSpec the operatingSpec to set
//     */
//    public void setOperatingSpec(Double operatingSpec) {
//        this.operatingSpec = operatingSpec;
//    }
    /**
     * @return the manufacturingSpec
     */
    public Double getManufacturingSpec() {
        return manufacturingSpec;
    }

    /**
     * @param manufacturingSpec the manufacturingSpec to set
     */
    public void setManufacturingSpec(Double manufacturingSpec) {
        this.manufacturingSpec = manufacturingSpec;
    }
//    /**
//     * @return the operationalAllowance
//     */
//    public BigDecimal getOperationalAllowance() {
//        return operationalAllowance;
//    }
//
//    /**
//     * @param operationalAllowance the operationalAllowance to set
//     */
//    public void setOperationalAllowance(BigDecimal operationalAllowance) {
//        this.operationalAllowance = operationalAllowance;
//    }
//
//    /**
//     * @return the fuelSpec
//     */
//    public Integer getFuelSpec() {
//        return fuelSpec;
//    }
//
//    /**
//     * @param fuelSpec the fuelSpec to set
//     */
//    public void setFuelSpec(Integer fuelSpec) {
//        this.fuelSpec = fuelSpec;
//    }
}
