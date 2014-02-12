/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.table;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author Luckbliss
 */
public class PaymentTable extends Table {

    private BigDecimal grandTotal;
    private DecimalFormat f = new DecimalFormat("###,###.00");

    public PaymentTable() {
        setSizeFull();

        addContainerProperty("Supplier", String.class, null);
        addContainerProperty("Total", String.class, null);

        String datemonth = new SimpleDateFormat("MMMM").format(new Date());
        String dateyear = new SimpleDateFormat("YYYY").format(new Date());

        loadTable(datemonth, dateyear);
    }

    public final void loadTable(String month, String year) {
        grandTotal = new BigDecimal("0.00");
//        final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
        if (month != null) {
            List<ServiceProvider> serviceProviders = ServiceProviderFacade.getServiceProviderService().findAll();
            for (ServiceProvider serviceProvider : serviceProviders) {
                List<Request> list = RequestFacade.getRequestService().findByServiceProvider(serviceProvider.getId());
                // Repository get requests by ServiceProvieder withing the Month/year specified and whose InvoiceNumber is NotNull
//                List<Request> list = RequestFacade.getRequestService().getTransactedRequestsByServiceProviderByMonth(serviceProvider, dateTimeFormatHelper.getDate(Integer.parseInt(year), Integer.parseInt(month)));

                List<Request> newlist = new ArrayList<>();
//                if (!list.isEmpty()) {
                for (Request request : list) {
                    if (request.getInvoiceNumber() != null) {
                        String datemonth = new SimpleDateFormat("MMMM").format(request.getDeliveryDate());
                        String dateyear = new SimpleDateFormat("YYYY").format(request.getDeliveryDate());
                        if (datemonth.equals(month) && year.equals(dateyear)) {
                            newlist.add(request);
                        }
                    }
                }
                if (newlist.size() > 0) {
                    addItem(new Object[]{
                        serviceProvider.getName(),
                        f.format(getSupplierTotal(newlist)),}, serviceProvider.getId());
                    grandTotal = grandTotal.add(getSupplierTotal(newlist));
//                    }
                }
            }
        }
    }

    public String getGrandTotal() {
        return f.format(grandTotal);
    }

    private BigDecimal getSupplierTotal(List<Request> requests) {
        BigDecimal total = BigDecimal.ZERO;
        for (Request request : requests) {
            // Null check done at Repository Level

            if (request.getInvoiceNumber() != null) {
                total = total.add(request.getTotal());
            }
        }
        return total;
    }
}
