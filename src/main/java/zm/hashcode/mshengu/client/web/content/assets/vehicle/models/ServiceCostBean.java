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
public class ServiceCostBean implements Serializable {

    private String id;
    @NotNull
    private Date transactionDate;
    @NotNull
    private String slipNo;
    @NotNull
    private BigDecimal serviceTotalCost = new BigDecimal(BigInteger.ZERO);
    private String comment;
    @NotNull
    private String serviceProviderId;
    @NotNull
    private String serviceCategoryId;
    @NotNull
    private String truckId;

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
     * @return the serviceTotalCost
     */
    public BigDecimal getServiceTotalCost() {
        return serviceTotalCost;
    }

    /**
     * @param serviceTotalCost the serviceTotalCost to set
     */
    public void setServiceTotalCost(BigDecimal serviceTotalCost) {
        this.serviceTotalCost = serviceTotalCost;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the serviceProviderId
     */
    public String getServiceProviderId() {
        return serviceProviderId;
    }

    /**
     * @param serviceProviderId the serviceProviderId to set
     */
    public void setServiceProviderId(String serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    /**
     * @return the serviceCategoryId
     */
    public String getServiceCategoryId() {
        return serviceCategoryId;
    }

    /**
     * @param serviceCategoryId the serviceCategoryId to set
     */
    public void setServiceCategoryId(String serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

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
}
