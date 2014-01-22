/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.dailydeisel.tables;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.OperationalAllowanceFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.fleet.OperationalAllowance;

/**
 *
 * @author geek
 */
public class OperationalAllowanceTable extends Table {

    private final MshenguMain main;

    public OperationalAllowanceTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Operational Allowance", BigDecimal.class, null);

        // Add Data Columns
        List<OperationalAllowance> operationalAllowanceList = OperationalAllowanceFacade.getOperationalAllowanceService().findAll();
        for (OperationalAllowance operationalAllowance : operationalAllowanceList) {
            addItem(new Object[]{
                operationalAllowance.getOperationalAllowance()},
                    operationalAllowance.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);

    }
}
