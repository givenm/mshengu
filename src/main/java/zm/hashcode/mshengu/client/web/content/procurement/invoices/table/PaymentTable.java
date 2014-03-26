/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.table;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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

    private BigDecimal grandTotal = new BigDecimal("0");
    private BigDecimal paidTotal = new BigDecimal("0");
    private BigDecimal balanceTotal = new BigDecimal("0");
    private DecimalFormat f = new DecimalFormat("###,###.00");
    private List<ServiceProvider> serviceProviders = ServiceProviderFacade.getServiceProviderService().findAll();

    public PaymentTable() {
        setSizeFull();

        addContainerProperty("Supplier", String.class, null);
        addContainerProperty("Total", String.class, null);
        addContainerProperty("Paid To Date", String.class, null);
        addContainerProperty("Balance Outstanding", String.class, null);

        loadTable(new Date(), null);
    }

    private void addSubtotalRow(Date date, String all) {
        List<Request> newlist = null;
        if (all != null) {
            for (ServiceProvider serviceProvider : serviceProviders) {
                newlist = RequestFacade.getRequestService().getTransactedRequestsByServiceProvider(serviceProvider);
                getTotal(newlist);
                newlist = new ArrayList<>();
            }
        } else {
            for (ServiceProvider serviceProvider : serviceProviders) {
                newlist = RequestFacade.getRequestService().getTransactedRequestsByServiceProviderByMonth(serviceProvider, date);
                getTotal(newlist);
                newlist = new ArrayList<>();
            }
        }
        addItem(new Object[]{
            "MTD Suppliers Total",
            f.format(paidTotal),
            f.format(balanceTotal),
            f.format(paidTotal.subtract(balanceTotal)),}, "subtotal");
    }

    private void getTotal(List<Request> newlist) {
        paidTotal = paidTotal.add(getSupplierTotal(newlist));
        balanceTotal = balanceTotal.add(getPaidTotal(newlist));
    }

    private void performRowStyling() {
        setCellStyleGenerator(new Table.CellStyleGenerator() {
            @Override
            public String getStyle(Table source, Object itemId, Object propertyId) {
                if (propertyId != null) {
                    return null;
                }
                String rowId = ((String) itemId).toString();
                if (rowId.equals("subtotal")) {
                    return "yellowrow";
                }
                return null;
            }
        });
    }

    public final void loadTable(Date date, String all) {
        grandTotal = new BigDecimal("0.00");
        paidTotal = new BigDecimal("0.00");
        balanceTotal = new BigDecimal("0.00");
        addSubtotalRow(date, all);
        List<Request> newlist = null;
        if (all != null) {
            for (ServiceProvider serviceProvider : serviceProviders) {
                newlist = RequestFacade.getRequestService().getTransactedRequestsByServiceProvider(serviceProvider);
                if (newlist.size() > 0) {
                    addItemToTable(serviceProvider, newlist);
                }
                newlist = new ArrayList<>();
            }
        } else {
            for (ServiceProvider serviceProvider : serviceProviders) {
                newlist = RequestFacade.getRequestService().getTransactedRequestsByServiceProviderByMonth(serviceProvider, date);
                if (newlist.size() > 0) {
                    addItemToTable(serviceProvider, newlist);
                }
                newlist = new ArrayList<>();
            }
        }
        performRowStyling();
    }

    private void addItemToTable(ServiceProvider serviceProvider, List<Request> newlist) {
        addItem(new Object[]{
            serviceProvider.getName(),
            f.format(getSupplierTotal(newlist)),
            f.format(getPaidTotal(newlist)),
            f.format(getSupplierTotal(newlist).subtract(getPaidTotal(newlist)))}, serviceProvider.getId());
        grandTotal = grandTotal.add(getSupplierTotal(newlist).subtract(getPaidTotal(newlist)));


    }

    public String getGrandTotal() {
        return f.format(grandTotal);
    }

    private BigDecimal getSupplierTotal(List<Request> requests) {
        BigDecimal total = BigDecimal.ZERO;
        for (Request request : requests) {
            total = total.add(request.getTotal());
        }
        return total;
    }

    private BigDecimal getPaidTotal(List<Request> requests) {
        BigDecimal total = BigDecimal.ZERO;
        for (Request request : requests) {
            if (request.getPaymentAmount() != null) {
                total = total.add(request.getPaymentAmount());
            }
        }
        return total;
    }
}
