/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.location.LocationFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.location.Location;

/**
 *
 * @author boniface
 */
public class CountryTable extends Table {

    private final MshenguMain main;
    public CountryTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Country Name", String.class, null);
        addContainerProperty("Latitude", String.class, null);
        addContainerProperty("Longitude", String.class, null);
        addContainerProperty("Type", String.class, null);
        // Add Data Columns
        List<Location> locations = LocationFacade.getLocationService().findAllCountries();
        for (Location location : locations) {
            addItem(new Object[]{location.getName(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationTypeName()}, location.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

}
