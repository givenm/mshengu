/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.tables;

import com.vaadin.ui.Table;
import java.util.Date;
import java.util.List;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.products.SiteServiceLogFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.DateTimeFormatWeeklyHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;

/**
 *
 * @author boniface
 */
public class NotServicedServiceLogsTable extends Table {

    private final MshenguMain main;
    private final DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
    Date startDate;
    Date endDate;

    public NotServicedServiceLogsTable(MshenguMain main, Date startDate, Date endDate) {
        this.main = main;
        this.startDate = startDate;
        this.endDate = endDate;
        setSizeFull();


        addContainerProperty("Site", String.class, null);
        addContainerProperty("Service Date", String.class, null);
        addContainerProperty("Service Time", String.class, null);
        addContainerProperty("Serviced By", String.class, null);
        addContainerProperty("Truck Used", String.class, null);
        addContainerProperty("Service Status", String.class, null);
        addContainerProperty("Completion Status", String.class, null);
        addContainerProperty("Total Units Serviced", Integer.class, null);
        addContainerProperty("Total Units Not Serviced", Integer.class, null);
//        PENDING, SERVICED, OUTSTANDING
        List<SiteServiceLog> siteServiceLogs = SiteServiceLogFacade.getSiteServiceLogService().getAllServiceLogsException(startDate, endDate, "NOT_SERVICED");
        loadSiteServiceLog(siteServiceLogs);

    }

    public final void loadSiteServiceLog(List<SiteServiceLog> siteServiceLogs) {
        setNullSelectionAllowed(true);
        setSelectable(false);
        setImmediate(false);
        removeAllItems();

        for (SiteServiceLog siteServiceLog : siteServiceLogs) {
            addItem(new Object[]{
                siteServiceLog.getParentId(),
                formatHelper.getYearMonthDay(siteServiceLog.getServiceDate()),
                formatHelper.getHourMinute(siteServiceLog.getServiceTime()),
                getDriverNmeNullCheck(siteServiceLog.getServicedBy()),
                siteServiceLog.getVehicleNumber(),
                siteServiceLog.getStatus(), //  customer.getContactPerson().get(),
                siteServiceLog.getCompletionStatus(),
                siteServiceLog.getNumberOfUnitsServiced(),
                siteServiceLog.getNumberOfUnitsNotServiced(),}, siteServiceLog.getId());
        }

        // Allow selecting items from the table.
        setNullSelectionAllowed(false);
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);

    }

    public String getDriverNmeNullCheck(Truck truck) {
        if (truck != null) {
            Person driver = truck.getDriver();
            if (driver != null) {
                String driverName = driver.getFirstname() + " " + driver.getLastname();
                return driverName;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void loadServiceLogDetails(String siteId, Date startDate, Date endDate) {
//        table
    }
}
