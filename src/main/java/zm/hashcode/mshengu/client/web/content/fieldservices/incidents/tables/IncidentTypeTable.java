/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.incidents.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.incident.IncidentTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.incident.IncidentType;

/**
 *
 * @author Ferox
 */
public class IncidentTypeTable extends Table {

    private final MshenguMain main;

    public IncidentTypeTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Incident Type", String.class, null);


        // Add Data Columns
        List<IncidentType> incidentTypeList = IncidentTypeFacade.getIncidentTypeService().findAll();
        for (IncidentType incidentType : incidentTypeList) {
            addItem(new Object[]{incidentType.getName(),}, incidentType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}