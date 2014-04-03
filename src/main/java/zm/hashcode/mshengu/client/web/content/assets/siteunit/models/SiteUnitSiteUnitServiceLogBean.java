/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.models;

import java.io.Serializable;
import java.util.Date;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author boniface
 */
public final class SiteUnitSiteUnitServiceLogBean implements Serializable {
    private String id;
    private Date serviceDate;
    private Date serviceTime;
    private String servicedBy;
   
    private String statusMessage;
    private String  incident;
    
    private boolean pumpOut;
    private boolean washBucket;
    private boolean suctionOut;
    private boolean scrubFloor;
    private boolean rechargeBacket;
    private boolean cleanPerimeter;
    
    
    
    private String pumpOutText;
    private String washBucketText;
    private String suctionOutText;
    private String scrubFloorText;
    private String rechargeBacketText;
    private String cleanPerimeterText;

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
     * @return the serviceDate
     */
    public Date getServiceDate() {
        return serviceDate;
    }

    /**
     * @param serviceDate the serviceDate to set
     */
    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }



    /**
     * @return the servicedBy
     */
    public String getServicedBy() {
        return servicedBy;
    }

    /**
     * @param servicedBy the servicedBy to set
     */
    public void setServicedBy(String servicedBy) {
        this.servicedBy = servicedBy;
    }

  
    /**
     * @return the serviceTime
     */
    public Date getServiceTime() {
        return serviceTime;
    }

    /**
     * @param serviceTime the serviceTime to set
     */
    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

  

    /**
     * @return the pumpOut
     */
    public boolean isPumpOut() {
        return pumpOut;
    }

    /**
     * @param pumpOut the pumpOut to set
     */
    public void setPumpOut(boolean pumpOut) {
        this.pumpOut = pumpOut;
    }

    /**
     * @return the flush
     */
  

    /**
     * @return the incident
     */
    public String getIncident() {
        return incident;
    }

    /**
     * @param incident the incident to set
     */
    public void setIncident(String incident) {
        this.incident = incident;
    }

    /**
     * @return the pumpOutText
     */
    public String getPumpOutText() {
        return pumpOutText;
    }

    /**
     * @param pumpOutText the pumpOutText to set
     */
    public void setPumpOutText(String pumpOutText) {
        this.pumpOutText = pumpOutText;
    }

  
    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * @return the washBucket
     */
    public boolean isWashBucket() {
        return washBucket;
    }

    /**
     * @param washBucket the washBucket to set
     */
    public void setWashBucket(boolean washBucket) {
        this.washBucket = washBucket;
    }

    /**
     * @return the suctionOut
     */
    public boolean isSuctionOut() {
        return suctionOut;
    }

    /**
     * @param suctionOut the suctionOut to set
     */
    public void setSuctionOut(boolean suctionOut) {
        this.suctionOut = suctionOut;
    }

    /**
     * @return the scrubFloor
     */
    public boolean isScrubFloor() {
        return scrubFloor;
    }

    /**
     * @param scrubFloor the scrubFloor to set
     */
    public void setScrubFloor(boolean scrubFloor) {
        this.scrubFloor = scrubFloor;
    }

    /**
     * @return the rechargeBacket
     */
    public boolean isRechargeBacket() {
        return rechargeBacket;
    }

    /**
     * @param rechargeBacket the rechargeBacket to set
     */
    public void setRechargeBacket(boolean rechargeBacket) {
        this.rechargeBacket = rechargeBacket;
    }

    /**
     * @return the cleanPerimeter
     */
    public boolean isCleanPerimeter() {
        return cleanPerimeter;
    }

    /**
     * @param cleanPerimeter the cleanPerimeter to set
     */
    public void setCleanPerimeter(boolean cleanPerimeter) {
        this.cleanPerimeter = cleanPerimeter;
    }

    /**
     * @return the washBucketText
     */
    public String getWashBucketText() {
        return washBucketText;
    }

    /**
     * @param washBucketText the washBucketText to set
     */
    public void setWashBucketText(String washBucketText) {
        this.washBucketText = washBucketText;
    }

    /**
     * @return the suctionOutText
     */
    public String getSuctionOutText() {
        return suctionOutText;
    }

    /**
     * @param suctionOutText the suctionOutText to set
     */
    public void setSuctionOutText(String suctionOutText) {
        this.suctionOutText = suctionOutText;
    }

    /**
     * @return the scrubFloorText
     */
    public String getScrubFloorText() {
        return scrubFloorText;
    }

    /**
     * @param scrubFloorText the scrubFloorText to set
     */
    public void setScrubFloorText(String scrubFloorText) {
        this.scrubFloorText = scrubFloorText;
    }

    /**
     * @return the rechargeBacketText
     */
    public String getRechargeBacketText() {
        return rechargeBacketText;
    }

    /**
     * @param rechargeBacketText the rechargeBacketText to set
     */
    public void setRechargeBacketText(String rechargeBacketText) {
        this.rechargeBacketText = rechargeBacketText;
    }

    /**
     * @return the cleanPerimeterText
     */
    public String getCleanPerimeterText() {
        return cleanPerimeterText;
    }

    /**
     * @param cleanPerimeterText the cleanPerimeterText to set
     */
    public void setCleanPerimeterText(String cleanPerimeterText) {
        this.cleanPerimeterText = cleanPerimeterText;
    }
    
}
