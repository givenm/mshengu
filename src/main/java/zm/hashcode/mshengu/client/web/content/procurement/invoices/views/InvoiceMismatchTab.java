/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.views;

import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.table.InvoiceMismatchTable;

/**
 *
 * @author Luckbliss
 */
public class InvoiceMismatchTab extends VerticalLayout {

    private InvoiceMismatchTable table;
    private MshenguMain main;

    public InvoiceMismatchTab(MshenguMain main) {
        setSizeFull();
        table = new InvoiceMismatchTable(this);
        this.main = main;
        addComponent(table);
    }
}
