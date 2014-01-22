/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.workscheduling.models;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferox
 */
public class WorkScheduleBeanBean implements Serializable{
    
   
    private String id;
    @NotNull
    private String truckId;
    @NotNull
    private String driverName;

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

    /**
     * @return the driverName
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * @param driverName the driverName to set
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}