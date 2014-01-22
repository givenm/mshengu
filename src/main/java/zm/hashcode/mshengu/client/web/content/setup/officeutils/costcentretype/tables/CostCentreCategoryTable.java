/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.tables;

import com.vaadin.ui.Table;
import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.ui.util.CostCentreCategoryTypeFacade;
import zm.hashcode.mshengu.app.facade.ui.util.CostCentreTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.util.CostCentreCategoryType;
import zm.hashcode.mshengu.domain.ui.util.CostCentreType;

/**
 *
 * @author Luckbliss
 */
public class CostCentreCategoryTable extends Table {

    private final MshenguMain main;

    public CostCentreCategoryTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Cost Centre Category", String.class, null);

        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    public void loadTable(String costCentreId) {
        CostCentreType type = CostCentreTypeFacade.getCostCentreTypeService().findById(costCentreId);
        Set<CostCentreCategoryType> list = type.getCategoryTypes();
        if (list != null) {
            for (CostCentreCategoryType categoryType : list) {
                addItem(new Object[]{categoryType.getName(),}, categoryType.getId());
            }
        }
    }
}
