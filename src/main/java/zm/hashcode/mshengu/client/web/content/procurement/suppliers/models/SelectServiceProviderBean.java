/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.suppliers.models;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferox
 */
public final class SelectServiceProviderBean implements Serializable {

    private String id;
    @NotNull
    private String serviceProviderId;

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
}
