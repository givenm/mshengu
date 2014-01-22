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
import zm.hashcode.mshengu.domain.products.UnitServiceLog;

/**
 *
 * @author Ferox
 */
public class SiteUnitSiteUnitServiceLogTable extends Table {

    private final MshenguMain main;
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
    private UITableIconHelper iconHelper = new UITableIconHelper();

    public SiteUnitSiteUnitServiceLogTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Site Name", String.class, null);
        addContainerProperty("Unit ID", String.class, null);
        addContainerProperty("Date", String.class, null);
        addContainerProperty("Time", String.class, null);

        addContainerProperty("Status Message", String.class, null);
        addContainerProperty("Pump Out", Embedded.class, null);
        addContainerProperty("Suction Out", Embedded.class, null);
        addContainerProperty("Washed Bucket ", Embedded.class, null);
        addContainerProperty("Scrub Floor", Embedded.class, null);
        addContainerProperty("Recharge Backet", Embedded.class, null);
        addContainerProperty("Clean Perimeter", Embedded.class, null);

        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }

    public void loadServiceLogs(String siteUnitId) {

        // Add Data Columns
        removeAllItems();
        final SiteUnit siteUnit = SiteUnitFacade.getSiteUnitService().findById(siteUnitId);
        if (!StringUtils.isEmpty(siteUnit)) {
            if (!StringUtils.isEmpty(siteUnit.getUnityLogs())) {
                for (UnitServiceLog unitServiceLog : siteUnit.getUnityLogs()) {
                    addItem(new Object[]{unitServiceLog.getSiteName(),
                        siteUnit.getUnitId(),
                        formatHelper.getYearMonthDay(unitServiceLog.getServiceDate()),
                        formatHelper.getHourMinute(unitServiceLog.getServiceTime()),
                        unitServiceLog.getStatusMessage(),
                        iconHelper.getCheckOrCross(unitServiceLog.isPumpOut(), 24),
                        iconHelper.getCheckOrCross(unitServiceLog.isWashBucket(), 24),
                        iconHelper.getCheckOrCross(unitServiceLog.isSuctionOut(), 24),
                        iconHelper.getCheckOrCross(unitServiceLog.isScrubFloor(), 24),
                        iconHelper.getCheckOrCross(unitServiceLog.isRechargeBacket(), 24),
                        iconHelper.getCheckOrCross(unitServiceLog.isCleanPerimeter(), 24),}, unitServiceLog.getId());
                }
            }
        }
    }

    public String wasDoneWithinPerimeter(boolean flag) {

        if (flag) {
            return "Submission Within Perimeter";
        } else {
            return "Submission Outside Perimeter";
        }
    }

    public String wasServicePerformed(boolean flag) {

        if (flag) {
            return "YES";
        } else {
            return "NO";
        }
    }
}
