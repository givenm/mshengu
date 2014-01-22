/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.status.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.util.StatusFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.util.Status;

/**
 *
 * @author Ferox
 */
public class StatusTable extends Table {

    private final MshenguMain main;

    public StatusTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Status Name", String.class, null);
        addContainerProperty("Status Type", String.class, null);


        // Add Data Columns
        List<Status> statusList = StatusFacade.getStatusService().findAll();
        for (Status status : statusList) {
            addItem(new Object[]{status.getName(),
                        status.getStatusTypeName(),}, status.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

}