/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.models;

import java.util.Set;
import javax.validation.constraints.NotNull;
import zm.hashcode.mshengu.domain.ui.util.CostCentreCategoryType;
import zm.hashcode.mshengu.domain.ui.util.ItemCategoryType;

/**
 *
 * @author Luckbliss
 */
public class CostCentreCategoryBean {

    private String id;
    @NotNull
    private String costCentreId;
    @NotNull
    private String name;
    private Set<ItemCategoryType> categoryTypes;

    public String getCostCentreId() {
        return costCentreId;
    }

    public void setCostCentreId(String costCentreId) {
        this.costCentreId = costCentreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<ItemCategoryType> getCategoryTypes() {
        return categoryTypes;
    }

    public void setCategoryTypes(Set<ItemCategoryType> categoryTypes) {
        this.categoryTypes = categoryTypes;
    }
}
