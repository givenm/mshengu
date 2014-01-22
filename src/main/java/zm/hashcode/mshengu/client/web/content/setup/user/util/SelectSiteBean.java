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
public final class SelectSiteBean implements Serializable {

    @NotNull
    private String siteId;

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
}