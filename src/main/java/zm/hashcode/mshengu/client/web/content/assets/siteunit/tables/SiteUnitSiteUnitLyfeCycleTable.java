/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.assets.siteunit.tables;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Table;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.facade.products.SiteUnitFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.UITableIconHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.products.UnitLocationLifeCycle;

/**
 *
 * @author Ferox
 */
public class SiteUnitSiteUnitLyfeCycleTable extends Table {

    private final MshenguMain main;
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
    private UITableIconHelper tableIconHelper = new UITableIconHelper();

    public SiteUnitSiteUnitLyfeCycleTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Site Name", String.class, null);
        addContainerProperty("Unit ID", String.class, null);
        addContainerProperty("Date of Action", String.class, null);
        addContainerProperty("Deployment Time", String.class, null);
        addContainerProperty("Latitude", String.class, null);
        addContainerProperty("Longitude", String.class, null);
        addContainerProperty("Active", Embedded.class, null);

        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }


    public void loadUnitLifeCycle(String siteUnitId) {
    boolean active = true;
        // Add Data Columns
        removeAllItems();
        final SiteUnit siteUnit = SiteUnitFacade.getSiteUnitService().findById(siteUnitId);
        if (!StringUtils.isEmpty(siteUnit)) {
            if (!StringUtils.isEmpty(siteUnit.getUnitLocationLifeCycle())) {
                for (UnitLocationLifeCycle unitLocationLifeCycle : siteUnit.getUnitLocationLifeCycle()) {
                    addItem(new Object[]{unitLocationLifeCycle.getSiteName(),
                                siteUnit.getUnitId(),
                                formatHelper.getDayMonthYear(unitLocationLifeCycle.getDateofAction()),
                                formatHelper.getHourMinute(unitLocationLifeCycle.getDateofAction()),
                                unitLocationLifeCycle.getLatitude(),
                                unitLocationLifeCycle.getLongitude(),
                    tableIconHelper.getCheckOrBlank(active),}, unitLocationLifeCycle.getId());
                    active = false;
                }
            }
        }
    }
}
