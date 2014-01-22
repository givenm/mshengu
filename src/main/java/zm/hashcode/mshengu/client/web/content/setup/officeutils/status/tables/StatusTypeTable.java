/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.status.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.util.StatusTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.util.StatusType;

/**
 *
 * @author Ferox
 */
public class StatusTypeTable extends Table {

    private final MshenguMain main;

    public StatusTypeTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Status Type Name", String.class, null);
        addContainerProperty("Status Type Description", String.class, null);


        // Add Data Columns
        List<StatusType> statusList = StatusTypeFacade.getStatusTypeService().findAll();
        for (StatusType statusType : statusList) {
            addItem(new Object[]{statusType.getName(),
                statusType.getDescription(),}, statusType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}