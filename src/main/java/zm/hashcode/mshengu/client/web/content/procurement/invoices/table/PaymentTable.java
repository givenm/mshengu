/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.table;

import com.vaadin.ui.Table;

/**
 *
 * @author Luckbliss
 */
public class PaymentTable extends Table {

    public PaymentTable() {
        setSizeFull();

        addContainerProperty("Supplier", String.class, null);
        addContainerProperty("Total", String.class, null);
    }
}