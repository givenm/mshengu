/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.incidents.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.incident.IncidentActionStatusFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.incident.IncidentActionStatus;

/**
 *
 * @author Ferox
 */
public class IncidentActionStatusTable extends Table {

    private final MshenguMain main;

    public IncidentActionStatusTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Incident Action Status", String.class, null);


        // Add Data Columns
        List<IncidentActionStatus> incidentActionStatusList = IncidentActionStatusFacade.getIncidentActionStatusService().findAll();
        for (IncidentActionStatus incidentActionStatus : incidentActionStatusList) {
            addItem(new Object[]{incidentActionStatus.getName(),}, incidentActionStatus.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}