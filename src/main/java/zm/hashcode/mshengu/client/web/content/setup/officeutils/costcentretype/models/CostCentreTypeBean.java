/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.models;

import java.util.Set;
import zm.hashcode.mshengu.domain.ui.util.CostCentreCategoryType;

/**
 *
 * @author Luckbliss
 */
public class CostCentreTypeBean {

    private String id;
    private String name;
    private Set<CostCentreCategoryType> categoryTypes;

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

    public Set<CostCentreCategoryType> getCategoryTypes() {
        return categoryTypes;
    }

    public void setCategoryTypes(Set<CostCentreCategoryType> categoryTypes) {
        this.categoryTypes = categoryTypes;
    }
}
