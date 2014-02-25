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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.InvoicesMenu;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.form.UpdateForm;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.views.InvoicePaidTab;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class InvoicePaidTable extends Table {

    private DecimalFormat f = new DecimalFormat("###,###.00");
    private BigDecimal grandTotal;
    private final InvoicePaidTab tab;
    private final MshenguMain main;

    public InvoicePaidTable(InvoicePaidTab tab, final MshenguMain main) {
        setSizeFull();
        this.tab = tab;
        this.main = main;
        addContainerProperty("PO Date", String.class, null);
        addContainerProperty("Delivery Date", String.class, null);
        addContainerProperty("Order Number", String.class, null);
        addContainerProperty("Supplier", String.class, null);
        addContainerProperty("Invoice", String.class, null);
        addContainerProperty("Total", String.class, null);
        addContainerProperty("Payment Date", String.class, null);
        addContainerProperty("Payment Amount", BigDecimal.class, null);
        addContainerProperty("Update", Button.class, null);

        String datemonth = new SimpleDateFormat("MMMM").format(new Date());
        String dateyear = new SimpleDateFormat("YYYY").format(new Date());

        loadTable(RequestFacade.getRequestService().findAll(), datemonth, dateyear);
    }

    public final void loadTable(List<Request> requests, String month, String year) {
        grandTotal = new BigDecimal("0.00");
        if (requests != null) {
            for (int i = requests.size() - 1; i >= 0; i--) {
                if (requests.get(i).getInvoiceNumber() != null && requests.get(i).getPaymentDate() != null) {
                    String datemonth = new SimpleDateFormat("MMMM").format(requests.get(i).getDeliveryDate());
                    String dateyear = new SimpleDateFormat("YYYY").format(requests.get(i).getDeliveryDate());
                    if (datemonth.equals(month) && year.equals(dateyear)) {
                        Button update = new Button("Effect Payment");
                        update.setData(requests.get(i).getId());
                        update.setStyleName(Reindeer.BUTTON_LINK);
                        update.addClickListener(new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                String itemId = event.getButton().getData().toString();
                                Request request = RequestFacade.getRequestService().findById(itemId);
                                UpdateForm form = new UpdateForm(main, request, tab);
                                tab.removeAllComponents();
                                tab.addComponent(form);
                            }
                        });
                        addItem(new Object[]{
                            getDelivery(requests.get(i).getOrderDate()),
                            getDelivery(requests.get(i).getDeliveryDate()),
                            requests.get(i).getOrderNumber(),
                            requests.get(i).getServiceProviderName(),
                            requests.get(i).getInvoiceNumber(),
                            f.format(requests.get(i).getTotal()),
                            getDelivery(requests.get(i).getPaymentDate()),
                            requests.get(i).getPaymentAmount(),
                            update,}, requests.get(i).getId());
                        grandTotal = grandTotal.add(requests.get(i).getPaymentAmount());
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

    private void getHome() {
        main.content.setSecondComponent(new InvoicesMenu(main, "PAYMENT"));
    }
}