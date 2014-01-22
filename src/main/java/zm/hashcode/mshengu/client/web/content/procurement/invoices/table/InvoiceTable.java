/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.table;

import com.vaadin.ui.Table;
import java.util.Date;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class InvoiceTable extends Table {

    public InvoiceTable() {
        setSizeFull();
        addContainerProperty("Date", String.class, null);
        addContainerProperty("Order Number", String.class, null);
        addContainerProperty("Invoice", String.class, null);
        addContainerProperty("Total", String.class, null);
        loadTable();
    }

    public final void loadTable() {
        if (RequestFacade.getRequestService().findAll() != null) {
            for (Request request : RequestFacade.getRequestService().findAll()) {
                if (request.getInvoiceNumber() != null) {
                    addItem(new Object[]{
                        getDelivery(request.getDeliveryDate()),
                        request.getOrderNumber(),
                        request.getInvoiceNumber(),
                        request.getTotal(),}, request.getId());
                }
            }
        }
    }

    private String getDelivery(Date date) {
        if (date != null) {
            return new DateTimeFormatHelper().getDayMonthYear(date);
        }
        return null;
    }
}