/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.table;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class InvoiceTable extends Table {

    private DecimalFormat f = new DecimalFormat("###,###.00");
    private BigDecimal grandTotal;

    public InvoiceTable() {
        setSizeFull();
        addContainerProperty("Date", String.class, null);
        addContainerProperty("Order Number", String.class, null);
        addContainerProperty("Invoice", String.class, null);
        addContainerProperty("Total", String.class, null);
        loadTable(RequestFacade.getRequestService().findAll(), null, null);
    }

    public final void loadTable(List<Request> requests, String month, String year) {
        grandTotal = new BigDecimal("0.00");
        if (requests != null) {
            if (month != null) {
                for (Request request : requests) {
                    if (request.getInvoiceNumber() != null) {
                        String datemonth = new SimpleDateFormat("MMMM").format(request.getDeliveryDate());
                        String dateyear = new SimpleDateFormat("YYYY").format(request.getDeliveryDate());
                        if (datemonth.equals(month) && year.equals(dateyear)) {
                            addItem(new Object[]{
                                getDelivery(request.getDeliveryDate()),
                                request.getOrderNumber(),
                                request.getInvoiceNumber(),
                                f.format(request.getTotal()),}, request.getId());
                            grandTotal = grandTotal.add(request.getTotal());
                        }
                    }
                }
            } else {
                for (Request request : requests) {
                    if (request.getInvoiceNumber() != null) {
                        addItem(new Object[]{
                            getDelivery(request.getDeliveryDate()),
                            request.getOrderNumber(),
                            request.getInvoiceNumber(),
                            f.format(request.getTotal()),}, request.getId());
                        grandTotal = grandTotal.add(request.getTotal());
                    }
                }
            }

        }
    }

    public String getGrandTotal() {
        return f.format(grandTotal);
    }

    private String getDelivery(Date date) {
        if (date != null) {
            return new DateTimeFormatHelper().getDayMonthYear(date);
        }
        return null;
    }
}