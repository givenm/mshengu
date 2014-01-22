/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.tables;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.util.List;
import zm.hashcode.mshengu.app.facade.products.UnitTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.products.UnitType;



/**
 *
 * @author Ferox
 */
public class SiteUnitUnitTypeTable extends Table {

    private final MshenguMain main;
    public SiteUnitUnitTypeTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Unit Type", String.class, null);
        addContainerProperty("Unit Price", BigDecimal.class, null);
        
        // Add Data Columns
        List<UnitType> unitTypes = UnitTypeFacade.getUnitTypeService().findAll();
        for (UnitType unitType : unitTypes) {
            addItem(new Object[]{unitType.getName(),
                        unitType.getUnitPrice(),}, unitType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }


}