/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.models;

import java.util.Date;

/**
 *
 * @author Ferox
 */
public class SiteUnitLocationLifeCycleBean {
    private String id;
    private Date dateofAction;
    private String latitude;
    private String longitude;

    /**
     * @return the dateofAction
     */
    public Date getDateofAction() {
        return dateofAction;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
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
     * @param dateofAction the dateofAction to set
     */
    public void setDateofAction(Date dateofAction) {
        this.dateofAction = dateofAction;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
