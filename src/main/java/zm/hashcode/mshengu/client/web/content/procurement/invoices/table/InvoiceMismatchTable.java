/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.table;

import com.vaadin.ui.Table;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.views.InvoiceMismatchTab;

/**
 *
 * @author Luckbliss
 */
public class InvoiceMismatchTable extends Table {

    private InvoiceMismatchTab main;

    public InvoiceMismatchTable(InvoiceMismatchTab tab) {
        this.main = tab;
        setSizeFull();

        addContainerProperty("Date", String.class, null);
        addContainerProperty("Order Number", String.class, null);
        addContainerProperty("Supplier", String.class, null);
        addContainerProperty("Invoice Number", String.class, null);
        addContainerProperty("Total", String.class, null);

        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }
}
