/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.tables;

import com.vaadin.ui.Table;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.ui.util.CostCentreCategoryTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.util.CostCentreCategoryType;
import zm.hashcode.mshengu.domain.ui.util.ItemCategoryType;

/**
 *
 * @author Luckbliss
 */
public class CostCentreItemTable extends Table {

    private final MshenguMain main;

    public CostCentreItemTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Item Category", String.class, null);

        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    public void loadTable(String costCentreId) {
        CostCentreCategoryType type = CostCentreCategoryTypeFacade.getCostCentreCategoryTypeService().findById(costCentreId);
        Set<ItemCategoryType> list = type.getItemCategoryTypes();
        if (list != null) {
            for (ItemCategoryType itemCategoryType : list) {
                addItem(new Object[]{itemCategoryType.getName(),}, itemCategoryType.getId());
            }
        }
    }
}
