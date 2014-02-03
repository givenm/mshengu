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
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();

    public SiteServiceLogTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Service Date", String.class, null);
        addContainerProperty("Service Time", String.class, null);
        addContainerProperty("Serviced By", String.class, null);
        addContainerProperty("Truck Used", String.class, null);
        addContainerProperty("Service Performed", String.class, null);
        addContainerProperty("Total Units Serviced", Integer.class, null);
        addContainerProperty("Total Units Not Serviced", Integer.class, null);

    }

    public void loadSiteServiceLog(List<SiteServiceLog> siteServiceLogs) {
        setNullSelectionAllowed(true);
        setSelectable(false);
        setImmediate(false);
        removeAllItems();

        for (SiteServiceLog siteServiceLog : siteServiceLogs) {
//            SiteServiceContractLifeCycle contractLifeCycle = SiteFacade.getSiteService().getSitetCurrentContract(site.getId());
//            String noOfUnits = getNoOfUnits(contractLifeCycle.getNumberOfUnits(), contractLifeCycle.getExpectedNumberOfUnits());
            addItem(new Object[]{
                formatHelper.getYearMonthDay(siteServiceLog.getServiceDate()),
                formatHelper.getHourMinute(siteServiceLog.getServiceTime()),
                getDriverNmeNullCheck(siteServiceLog.getServicedBy()),
                siteServiceLog.getNumberPlate(),
                siteServiceLog.getStatus(), //  customer.getContactPerson().get(),
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
        List<SiteServiceLog> siteServiceLogs = SiteServiceLogFacade.getSiteServiceLogService().getServiceLogs(siteId, startDate, endDate, "CLOSED");
        loadSiteServiceLog(siteServiceLogs);
//        table
    }
}
