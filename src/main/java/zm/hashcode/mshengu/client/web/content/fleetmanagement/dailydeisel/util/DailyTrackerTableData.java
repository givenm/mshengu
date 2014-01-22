/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.util;

import com.vaadin.ui.Embedded;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author geek
 */
public class DailyTrackerTableData implements Serializable {

    private String id;
    private String truckId;
    private String driverId;
    private String truckPlateNumber;
    private String driverName;
    private String transactionDate;
    private Date transactDate;
    private BigDecimal amount = BigDecimal.ZERO;
    private Double litres;
    private BigDecimal randsPerLiter = BigDecimal.ZERO;
    private Integer closingMileage = 0;
    private Integer trip = 0;
    private BigDecimal randsPerKilometer = BigDecimal.ZERO;
    private Embedded rating;
    private Double litresPerKilometer;

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
     * @return the transactionDate
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the Amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param Amount the Amount to set
     */
    public void setAmount(BigDecimal Amount) {
        this.amount = Amount;
    }

    /**
     * @return the litres
     */
    public Double getLitres() {
        return litres;
    }

    /**
     * @param litres the litres to set
     */
    public void setLitres(Double litres) {
        this.litres = litres;
    }

    /**
     * @return the randsPerLiter
     */
    public BigDecimal getRandsPerLiter() {
        return randsPerLiter;
    }

    /**
     * @param randsPerLiter the randsPerLiter to set
     */
    public void setRandsPerLiter(BigDecimal randsPerLiter) {
        this.randsPerLiter = randsPerLiter;
    }

    /**
     * @return the closingMileage
     */
    public Integer getClosingMileage() {
        return closingMileage;
    }

    /**
     * @param closingMileage the closingMileage to set
     */
    public void setClosingMileage(Integer closingMileage) {
        this.closingMileage = closingMileage;
    }

    /**
     * @return the trip
     */
    public Integer getTrip() {
        return trip;
    }

    /**
     * @param trip the trip to set
     */
    public void setTrip(Integer trip) {
        this.trip = trip;
    }

    /**
     * @return the randsPerKilometer
     */
    public BigDecimal getRandsPerKilometer() {
        return randsPerKilometer;
    }

    /**
     * @param randsPerKilometer the randsPerKilometer to set
     */
    public void setRandsPerKilometer(BigDecimal randsPerKilometer) {
        this.randsPerKilometer = randsPerKilometer;
    }

    /**
     * @return the rating
     */
    public Embedded getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(Embedded rating) {
        this.rating = rating;
    }

    /**
     * @return the litresPerKilometer
     */
    public Double getLitresPerKilometer() {
        return litresPerKilometer;
    }

    /**
     * @param litresPerKilometer the litresPerKilometer to set
     */
    public void setLitresPerKilometer(Double litresPerKilometer) {
        this.litresPerKilometer = litresPerKilometer;
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
     * @return the transactDate
     */
    public Date getTransactDate() {
        return transactDate;
    }

    /**
     * @param transactDate the transactDate to set
     */
    public void setTransactDate(Date transactDate) {
        this.transactDate = transactDate;
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
}
