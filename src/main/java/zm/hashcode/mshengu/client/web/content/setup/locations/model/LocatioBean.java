/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations.model;

import java.io.Serializable;
import java.util.Set;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Ferox
 */
public final class LocatioBean implements Serializable {

    @Id
    private String id;
    private String name;
    private String code;
    private String latitude;
    private String longitude;
   
    private LocationTypeBean locationTypeId;
    
    private Set<LocatioBean> children;
    
    private LocatioBean parent;

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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the locationTypeId
     */
    public LocationTypeBean getLocationTypeId() {
        return locationTypeId;
    }

    /**
     * @param locationTypeId the locationTypeId to set
     */
    public void setLocationTypeId(LocationTypeBean locationTypeId) {
        this.locationTypeId = locationTypeId;
    }

    /**
     * @return the children
     */
    public Set<LocatioBean> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(Set<LocatioBean> children) {
        this.children = children;
    }

    /**
     * @return the parent
     */
    public LocatioBean getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(LocatioBean parent) {
        this.parent = parent;
    }

}