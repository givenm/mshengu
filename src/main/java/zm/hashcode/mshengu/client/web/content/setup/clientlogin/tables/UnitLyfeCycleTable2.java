/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.clientlogin.tables;

import com.vaadin.ui.Table;
import java.util.Date;
import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.products.UnitLocationLifeCycleFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;

/**
 *
 * @author Ferox
 */
public class UnitLyfeCycleTable2 extends Table {

    private final MshenguMain main;

    public UnitLyfeCycleTable2(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Date of Action", Date.class, null);
        addContainerProperty("Longitude", String.class, null);
        addContainerProperty("Latitude", String.class, null);
        
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }

    public void loadUnitLifeCycle(List<UnitLocationLifeCycle> unitLocationLifeCycleList) {
        
        // Add Data Columns
        
        for (UnitLocationLifeCycle unitLocationLifeCycle : unitLocationLifeCycleList) {
//                     UnitServiceLog unitServiceLog
            addItem(new Object[]{unitLocationLifeCycle.getDateofAction(),
                        unitLocationLifeCycle.getLatitude(),
                        unitLocationLifeCycle.getLongitude(),}, unitLocationLifeCycle.getId());
        }

    }
}