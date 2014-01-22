/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.vehicle.models;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferox
 */
public class TruckCategoryBean implements Serializable{

   
    private String id;
    @NotNull
    private String categoryName;
    @NotNull
    private String namingCode;
    @NotNull
    private int value;

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
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the namingCode
     */
    public String getNamingCode() {
        return namingCode;
    }

    /**
     * @param namingCode the namingCode to set
     */
    public void setNamingCode(String namingCode) {
        this.namingCode = namingCode;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
}