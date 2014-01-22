/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zm.hashcode.mshengu.client.web.content.setup.locations.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;


/**
 *
 * @author Ferox
 */
public class AddressTypeBean implements Serializable {
    @Id
    private String id;
    //Home Address or Work Address 
    @NotNull
    private String addressTypeName;

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

   
    /**
     * @return the addressTypeName
     */
    public String getAddressTypeName() {
        return addressTypeName;
    }

    /**
     * @param addressTypeName the addressTypeName to set
     */
    public void setAddressTypeName(String addressTypeName) {
        this.addressTypeName = addressTypeName;
    }

}
