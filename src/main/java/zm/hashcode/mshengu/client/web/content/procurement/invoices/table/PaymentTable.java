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
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderFacade;
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
        loadTable(null, null);
    }

    public final void loadTable(String month, String year) {
        grandTotal = new BigDecimal("0.00");
        if (month != null) {
            List<ServiceProvider> serviceProviders = ServiceProviderFacade.getServiceProviderService().findAll();
            for (ServiceProvider serviceProvider : serviceProviders) {
                List<Request> list = RequestFacade.getRequestService().findByServiceProvider(serviceProvider.getId());
                List<Request> newlist = new ArrayList<>();
                if (list != null) {
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
                    }
                }
            }
        } else {
            List<ServiceProvider> serviceProviders = ServiceProviderFacade.getServiceProviderService().findAll();
            for (ServiceProvider serviceProvider : serviceProviders) {
                List<Request> list = RequestFacade.getRequestService().findByServiceProvider(serviceProvider.getId());
                if (list.size() > 0) {
                    addItem(new Object[]{
                        serviceProvider.getName(),
                        f.format(getSupplierTotal(list)),}, serviceProvider.getId());
                    grandTotal = grandTotal.add(getSupplierTotal(list));
                }
            }
        }
    }

    public String getGrandTotal() {
        return f.format(grandTotal);
    }

    private BigDecimal getSupplierTotal(List<Request> requests) {
        BigDecimal total = new BigDecimal("0");
        for (Request request : requests) {
            if (request.getInvoiceNumber() != null) {
                total = total.add(request.getTotal());
            }
        }
        return total;
    }
}