/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author boniface
 */
public final class ContactBean implements Serializable {

  
    private String id;
    @NotNull
    private String name;// e.g email, cell phone, skype, yahoo 

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}