/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.location.LocationTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.location.LocationType;

/**
 *
 * @author boniface
 */
public class LocationTypeTable extends Table {
   

    private final MshenguMain main;

    public LocationTypeTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Name Type", String.class, null);
        addContainerProperty("Name Code", String.class, null);
        
         // Add Data Columns
        List<LocationType> locationTypes = LocationTypeFacade.getLocationTypeService().findAll();
        for (LocationType locationType : locationTypes) {
            addItem(new Object[]{locationType.getName(),locationType.getCode()}, locationType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);

        


    }
}
