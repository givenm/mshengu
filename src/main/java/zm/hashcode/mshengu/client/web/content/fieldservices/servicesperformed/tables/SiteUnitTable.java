/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.tables;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Table;
import java.util.Set;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.facade.products.SiteUnitFacade;
import zm.hashcode.mshengu.app.util.UITableIconHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;

/**
 *
 * @author Ferox
 */
public class SiteUnitTable extends Table {

    private final MshenguMain main;
    private UITableIconHelper iconHelper = new UITableIconHelper();

    public SiteUnitTable(MshenguMain main) {
        this.main = main;
        setSizeFull();



        addContainerProperty("Site Name", String.class, null);
        addContainerProperty("Unit Id", String.class, null);
        addContainerProperty("Operational Staus", String.class, null);
        addContainerProperty("", Embedded.class, null);
        addContainerProperty("Latitude", String.class, null);
        addContainerProperty("Longitude ", String.class, null);

        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }

    public void loadSiteUnits(Set<SiteUnit> units) {
        // Add Data Columns

        for (SiteUnit unit : units) {
            String latitude = "Not Deployed";
            String longitude = "Not Deployed";
            String statusName = unit.getOperationalStatusName();

            UnitLocationLifeCycle unitLocationLifeCycle = SiteUnitFacade.getSiteUnitService().getUnitCurrentLocation(unit.getId());
            if (!StringUtils.isEmpty(unitLocationLifeCycle)) {
                longitude = unitLocationLifeCycle.getLongitude();
                latitude = unitLocationLifeCycle.getLatitude();
            }
//                     UnitServiceLog unitServiceLog
            addItem(new Object[]{unitLocationLifeCycle.getSiteName(),
                        unit.getUnitId(),
                        statusName,
                        iconHelper.getDeployedStatus(statusName, 20),
                        latitude,
                        longitude, //  customer.getContactPerson().get(),
                    }, unit.getId());
        }
    }
}
