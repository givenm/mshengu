/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.costcentretype.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.util.CostCentreTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.util.CostCentreType;

/**
 *
 * @author Luckbliss
 */
public class CostCentreTypeTable extends Table {

    private final MshenguMain main;

    public CostCentreTypeTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Cost Centre Name", String.class, null);

        // Add Data Columns
        List<CostCentreType> list = CostCentreTypeFacade.getCostCentreTypeService().findAll();
        if (list != null) {
            for (CostCentreType type : list) {
                addItem(new Object[]{type.getName(),}, type.getId());
            }
        }

        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }
}
