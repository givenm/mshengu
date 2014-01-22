/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.models;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferox
 */
public final class SiteUnitUnitBulkTagBean implements Serializable {

    private String id;
    @NotNull
    private String siteUnitTypeId;
    @NotNull
    private int quantity;
    @NotNull
    private String sequenceId;

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
     * @return the siteUnitTypeId
     */
    public String getSiteUnitTypeId() {
        return siteUnitTypeId;
    }

    /**
     * @param siteUnitTypeId the siteUnitTypeId to set
     */
    public void setSiteUnitTypeId(String siteUnitTypeId) {
        this.siteUnitTypeId = siteUnitTypeId;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the sequenceId
     */
    public String getSequenceId() {
        return sequenceId;
    }

    /**
     * @param sequenceId the sequenceId to set
     */
    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

}