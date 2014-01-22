/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.user.util;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
/**
 *
 * @author Ferox
 */
public final class SelectSiteUnitBean implements Serializable {

    @NotNull
    private String siteUnitId;

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
}