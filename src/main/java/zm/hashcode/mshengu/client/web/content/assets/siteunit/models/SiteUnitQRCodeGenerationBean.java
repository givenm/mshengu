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
public final class SiteUnitQRCodeGenerationBean implements Serializable {

    private String id;
    @NotNull
    private String sequenceId;
    @NotNull
    private int startUnitNumber;
    @NotNull
    private int endUnitNumber;

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
     * @return the startUnitNumber
     */
    public int getStartUnitNumber() {
        return startUnitNumber;
    }

    /**
     * @param startUnitNumber the startUnitNumber to set
     */
    public void setStartUnitNumber(int startUnitNumber) {
        this.startUnitNumber = startUnitNumber;
    }

    /**
     * @return the endUnitNumber
     */
    public int getEndUnitNumber() {
        return endUnitNumber;
    }

    /**
     * @param endUnitNumber the endUnitNumber to set
     */
    public void setEndUnitNumber(int endUnitNumber) {
        this.endUnitNumber = endUnitNumber;
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