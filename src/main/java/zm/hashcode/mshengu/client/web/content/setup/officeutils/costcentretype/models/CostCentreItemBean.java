/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.models;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Luckbliss
 */
public class CostCentreItemBean {

    private String id;
    @NotNull
    private String costCentreId;
    @NotNull
    private String costCentreCategoryId;
    @NotNull
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCostCentreId() {
        return costCentreId;
    }

    public void setCostCentreId(String costCentreId) {
        this.costCentreId = costCentreId;
    }

    public String getCostCentreCategoryId() {
        return costCentreCategoryId;
    }

    public void setCostCentreCategoryId(String costCentreCategoryId) {
        this.costCentreCategoryId = costCentreCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
