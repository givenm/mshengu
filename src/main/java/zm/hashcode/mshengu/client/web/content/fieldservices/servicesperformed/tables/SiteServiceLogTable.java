/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.servicesperformed.tables;

import com.vaadin.ui.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
public class SiteServiceLogTable extends Table {

    private final MshenguMain main;
    private final DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
    private final DateTimeFormatWeeklyHelper dtfwh;
    Date date = formatHelper.getDate(22, 10, 2013);
    Date startDate;
    Date endDate;

    public SiteServiceLogTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        this.dtfwh = new DateTimeFormatWeeklyHelper();
        dtfwh.setDate(date);
        startDate = dtfwh.getDateYesterday();
        endDate = dtfwh.getDateToday();

        addContainerProperty("Site", String.class, null);
        addContainerProperty("Service Date", String.class, null);
        addContainerProperty("Service Time", String.class, null);
//        addContainerProperty("Serviced By", String.class, null);
//        addContainerProperty("Truck Used", String.class, null);
        addContainerProperty("Service Status", String.class, null);
        addContainerProperty("Completion Status", String.class, null);
        addContainerProperty("Total Units Serviced", Integer.class, null);
        addContainerProperty("Total Units Not Serviced", Integer.class, null);
         List<SiteServiceLog> siteServiceLogs = SiteServiceLogFacade.getSiteServiceLogService().getAllServiceLogs(startDate, endDate);
        loadSiteServiceLog(siteServiceLogs);

    }

    public final void loadSiteServiceLog(List<SiteServiceLog> siteServiceLogs) {
        setNullSelectionAllowed(true);
        setSelectable(false);
        setImmediate(false);
        removeAllItems();

        for (SiteServiceLog siteServiceLog : sortSiteServiceLog(siteServiceLogs)) {
            addItem(new Object[]{
                siteServiceLog.getParentId(),
                formatHelper.getYearMonthDay(siteServiceLog.getServiceDate()),
                formatHelper.getHourMinute(siteServiceLog.getServiceTime()),
//                getDriverNmeNullCheck(siteServiceLog.getServicedBy()),
//                siteServiceLog.getVehicleNumber(),
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
        List<SiteServiceLog> siteServiceLogs = SiteServiceLogFacade.getSiteServiceLogService().getAllSiteServiceLogs(siteId, startDate, endDate);
        loadSiteServiceLog(siteServiceLogs);
//        table
    }
    
      private List<SiteServiceLog> sortSiteServiceLog(List<SiteServiceLog> siteServiceLogs) {
        List<SiteServiceLog> siteServiceLogList = new ArrayList<>();
        siteServiceLogList.addAll(siteServiceLogs);
        Collections.sort(siteServiceLogList);
        return siteServiceLogList;
    }
}
