/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author geek
 */
public class FuelAndOilPriceBean implements Serializable {

    private String id;
    private Date entryDate;
    private BigDecimal fuelPrice = new BigDecimal(BigInteger.ZERO);
    private Date fuelEffectDate;
    private BigDecimal engineOilPrice = new BigDecimal(BigInteger.ZERO);
    private Date engineOilEffectDate;

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
     * @return the entryDate
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * @param entryDate the entryDate to set
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * @return the fuelPrice
     */
    public BigDecimal getFuelPrice() {
        return fuelPrice;
    }

    /**
     * @param fuelPrice the fuelPrice to set
     */
    public void setFuelPrice(BigDecimal fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    /**
     * @return the fuelEffectDate
     */
    public Date getFuelEffectDate() {
        return fuelEffectDate;
    }

    /**
     * @param fuelEffectDate the fuelEffectDate to set
     */
    public void setFuelEffectDate(Date fuelEffectDate) {
        this.fuelEffectDate = fuelEffectDate;
    }

    /**
     * @return the engineOilPrice
     */
    public BigDecimal getEngineOilPrice() {
        return engineOilPrice;
    }

    /**
     * @param engineOilPrice the engineOilPrice to set
     */
    public void setEngineOilPrice(BigDecimal engineOilPrice) {
        this.engineOilPrice = engineOilPrice;
    }

    /**
     * @return the engineOilEffectDate
     */
    public Date getEngineOilEffectDate() {
        return engineOilEffectDate;
    }

    /**
     * @param engineOilEffectDate the engineOilEffectDate to set
     */
    public void setEngineOilEffectDate(Date engineOilEffectDate) {
        this.engineOilEffectDate = engineOilEffectDate;
    }
}
