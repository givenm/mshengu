/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.clientlogin.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.products.SiteUnitFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;

/**
 *
 * @author Ferox
 */
public class UnitTable2 extends Table {

    private final MshenguMain main;

    public UnitTable2(MshenguMain main) {
        this.main = main;
        setSizeFull();

        String latitude = "Not Deployed";
        String longitude = "Not Deployed";
        
        addContainerProperty("Unit Id", String.class, null);
//        addContainerProperty("Is Deployed?", Boolean.class, null);
        addContainerProperty("Latitude", String.class, null);
        addContainerProperty("Longitude ", String.class, null);
        // Add Data Columns
        List<SiteUnit> units = SiteUnitFacade.getSiteUnitService().findAll();
        for (SiteUnit unit : units) {
                     UnitLocationLifeCycle unitLocationLifeCycle =  SiteUnitFacade.getSiteUnitService().getUnitCurrentLocation(unit.getId());
                     if(unitLocationLifeCycle != null){
                         longitude = unitLocationLifeCycle.getLongitude();
                         latitude = unitLocationLifeCycle.getLatitude();
                     }
//                     UnitServiceLog unitServiceLog
            addItem(new Object[]{unit.getUnitId(),
//                                unit.isDeployed(),
                              latitude,
                              longitude,
                              //  customer.getContactPerson().get(),
                                }, unit.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }


    }