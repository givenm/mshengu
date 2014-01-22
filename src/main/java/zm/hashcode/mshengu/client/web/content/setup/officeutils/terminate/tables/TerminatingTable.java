/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.terminate.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.util.TerminateFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.util.Terminate;

/**
 *
 * @author Luckbliss
 */
public class TerminatingTable extends Table {

    private final MshenguMain main;

    public TerminatingTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Code", String.class, null);
        addContainerProperty("Reason", String.class, null);

        // Add Data Columns
        List<Terminate> list = TerminateFacade.getTerminateService().findAll();
        for (Terminate terminate : list) {
            addItem(new Object[]{terminate.getCode(),
                terminate.getReason(),}, terminate.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }
}
