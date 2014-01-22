/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.clientlogin.tables;

import com.vaadin.ui.Table;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.products.UnitServiceLog;

/**
 *
 * @author Ferox
 */
public class UnitServiceLogTable2 extends Table {

    private final MshenguMain main;

    public UnitServiceLogTable2(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Unit Id", String.class, null);
        addContainerProperty("Service Date", Date.class, null);
        addContainerProperty("Service Time", Date.class, null);
//        addContainerProperty("Serviced By", String.class, null);
        addContainerProperty("Status Message", String.class, null);
        addContainerProperty("Pump Out", String.class, null);
        addContainerProperty("Suction Out", String.class, null);
        addContainerProperty("Scrub Floor", String.class, null);
        addContainerProperty("Recharge Backet", String.class, null);
        addContainerProperty("CleanPerimeter", String.class, null);

        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }

    public void loadServiceLogs(List<UnitServiceLog> unitServiceLogs) {

        // Add Data Columns
        for (UnitServiceLog unitServiceLog : unitServiceLogs) {
            addItem(new Object[]{
                unitServiceLog.getServiceDate(),
                unitServiceLog.getServiceTime(),
                //                        unitServiceLog.getServicedBy().getLastname(),
                unitServiceLog.getStatusMessage(),
                wasServicePerformed(unitServiceLog.isPumpOut()),
                wasServicePerformed(unitServiceLog.isWashBucket()),
                wasServicePerformed(unitServiceLog.isSuctionOut()),
                wasServicePerformed(unitServiceLog.isScrubFloor()),
                wasServicePerformed(unitServiceLog.isRechargeBacket()),
                wasServicePerformed(unitServiceLog.isCleanPerimeter()),}, unitServiceLog.getId());
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
            return "Service Performed";
        } else {
            return "Service Not Performed";
        }
    }
}
