/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zm.hashcode.mshengu.client.web.content.setup.locations.model;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;


/**
 *
 * @author Ferox
 */
public class RegistrationBodyBean implements Serializable {
    @Id
    private String id;
    @NotNull
    private String name;
    private String description;
    private String coreActivity;
    private String active;
    @NotNull
    private Date asOfDate;

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

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the coreActivity
     */
    public String getCoreActivity() {
        return coreActivity;
    }

    /**
     * @param coreActivity the coreActivity to set
     */
    public void setCoreActivity(String coreActivity) {
        this.coreActivity = coreActivity;
    }

    /**
     * @return the active
     */
    public String getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     * @return the asOfDate
     */
    public Date getAsOfDate() {
        return asOfDate;
    }

    /**
     * @param asOfDate the asOfDate to set
     */
    public void setAsOfDate(Date asOfDate) {
        this.asOfDate = asOfDate;
    }
    
}