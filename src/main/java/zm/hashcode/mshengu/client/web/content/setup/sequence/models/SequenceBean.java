/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.sequence.models;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ferox
 */
public class SequenceBean implements Serializable{

   
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String namingCode;
    @NotNull
    private int value;
    @NotNull
    private String sequenceType;

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
     * @return the sequenceType
     */
    public String getSequenceType() {
        return sequenceType;
    }

    /**
     * @param sequenceType the sequenceType to set
     */
    public void setSequenceType(String sequenceType) {
        this.sequenceType = sequenceType;
    }


}