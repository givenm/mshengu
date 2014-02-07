/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.user.util;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferox
 */
public final class CustomerSiteUnitBean implements Serializable {

    
    private String contractType;
    
    @NotNull
    private String customerId;
    @NotNull
    private String siteId;
    @NotNull
    private String siteUnitId;
    private Date startDate;
    private Date endDate;
    
    
    
     
    private String qtyAsServiceSchedule;
    private String qtyDeployed; 
    private String unitIdRange;
    private int customerTotal;

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the siteId
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * @param siteId the siteId to set
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * @return the siteUnitId
     */
    public String getSiteUnitId() {
        return siteUnitId;
    }

    /**
     * @param siteUnitId the siteUnitId to set
     */
    public void setSiteUnitId(String siteUnitId) {
        this.siteUnitId = siteUnitId;
    }

    /**
     * @return the contractType
     */
    public String getContractType() {
        return contractType;
    }

    /**
     * @param contractType the contractType to set
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    /**
     * @return the qtyAsServiceSchedule
     */
    public String getQtyAsServiceSchedule() {
        return qtyAsServiceSchedule;
    }

    /**
     * @param qtyAsServiceSchedule the qtyAsServiceSchedule to set
     */
    public void setQtyAsServiceSchedule(String qtyAsServiceSchedule) {
        this.qtyAsServiceSchedule = qtyAsServiceSchedule;
    }

    /**
     * @return the qtyDeployed
     */
    public String getQtyDeployed() {
        return qtyDeployed;
    }

    /**
     * @param qtyDeployed the qtyDeployed to set
     */
    public void setQtyDeployed(String qtyDeployed) {
        this.qtyDeployed = qtyDeployed;
    }

    /**
     * @return the unitIdRange
     */
    public String getUnitIdRange() {
        return unitIdRange;
    }

    /**
     * @param unitIdRange the unitIdRange to set
     */
    public void setUnitIdRange(String unitIdRange) {
        this.unitIdRange = unitIdRange;
    }

    /**
     * @return the customerTotal
     */
    public int getCustomerTotal() {
        return customerTotal;
    }

    /**
     * @param customerTotal the customerTotal to set
     */
    public void setCustomerTotal(int customerTotal) {
        this.customerTotal = customerTotal;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}