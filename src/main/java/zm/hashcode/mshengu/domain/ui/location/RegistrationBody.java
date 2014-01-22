/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zm.hashcode.mshengu.domain.ui.location;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author boniface
 */
@Document
public class RegistrationBody implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @NotNull
    private String name;
    private String description;
    private String coreActivity;
    private String active;
    @NotNull
    private Date asOfDate;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrationBody)) {
            return false;
        }
        RegistrationBody other = (RegistrationBody) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.offices.RegistrationBody[id=" + id + "]";
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
