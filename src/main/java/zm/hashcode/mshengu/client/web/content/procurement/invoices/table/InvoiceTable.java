/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.form.UpdatePaymentForm;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.views.InvoicesTab;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class InvoiceTable extends Table {

    private DecimalFormat f = new DecimalFormat("###,###.00");
    private BigDecimal grandTotal;
    private final InvoicesTab tab;
    private final MshenguMain main;

    public InvoiceTable(InvoicesTab tab, final MshenguMain main) {
        setSizeFull();
        this.tab = tab;
        this.main = main;
        addContainerProperty("PO Date", String.class, null);
        addContainerProperty("Delivery Date", String.class, null);
        addContainerProperty("Order Number", String.class, null);
        addContainerProperty("Supplier", String.class, null);
        addContainerProperty("Invoice", String.class, null);
        addContainerProperty("Total", String.class, null);
        addContainerProperty("Update", Button.class, null);

        loadTable(RequestFacade.getRequestService().getProcessedRequestsWithInvoiceNumber());
    }

    public final void loadTable(List<Request> requests) {
        grandTotal = new BigDecimal("0.00");
        if (requests != null) {
            for (Request request: requests) {
                Button update = new Button("Effect Payment");
                update.setData(request.getId());
                update.setStyleName(Reindeer.BUTTON_LINK);
                update.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        String itemId = event.getButton().getData().toString();
                        Request request = RequestFacade.getRequestService().findById(itemId);
                        UpdatePaymentForm form = new UpdatePaymentForm(main, request, tab);
                        tab.removeAllComponents();
                        tab.addComponent(form);
                    }
                });
                addItem(new Object[]{
                    getDelivery(request.getOrderDate()),
                    getDelivery(request.getDeliveryDate()),
                    request.getOrderNumber(),
                    request.getServiceProviderName(),
                    request.getInvoiceNumber(),
                    f.format(request.getTotal()),
                    update,}, request.getId());
                grandTotal = grandTotal.add(request.getTotal());
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