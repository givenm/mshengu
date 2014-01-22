/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.models;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferox
 */
public final class SiteUnitSiteUnitDetailsBean implements Serializable{

    private String id;
    @NotNull
    private String unitId;
    private String description;
    @NotNull
    private String operationalStatus; // OUT ORDER
    @NotNull
    private String unitTypeId;
    private Date dateofAction;
    private String latitude;
    private String longitude;

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
     * @return the operationalStatus
     */
    public String getOperationalStatus() {
        return operationalStatus;
    }

    /**
     * @param operationalStatus the operationalStatus to set
     */
    public void setOperationalStatus(String operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    /**
     * @return the unitTypeId
     */
    public String getUnitTypeId() {
        return unitTypeId;
    }

    /**
     * @param unitTypeId the unitTypeId to set
     */
    public void setUnitTypeId(String unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    /**
     * @return the dateofAction
     */
    public Date getDateofAction() {
        return dateofAction;
    }

    /**
     * @param dateofAction the dateofAction to set
     */
    public void setDateofAction(Date dateofAction) {
        this.dateofAction = dateofAction;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    

    /**
     * @return the unitId
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * @param unitId the unitId to set
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

}
