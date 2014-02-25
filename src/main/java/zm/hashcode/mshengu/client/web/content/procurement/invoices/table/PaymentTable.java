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

    public PaymentTable() {
        setSizeFull();

        addContainerProperty("Supplier", String.class, null);
        addContainerProperty("Total", String.class, null);
        addContainerProperty("Paid To Date", String.class, null);
        addContainerProperty("Balance Outstanding", String.class, null);

        String datemonth = new SimpleDateFormat("MMMM").format(new Date());
        String dateyear = new SimpleDateFormat("YYYY").format(new Date());

        loadTable(datemonth, dateyear);
    }

    private void addSubtotalRow(String month, String year) {
        List<Request> newlist = new ArrayList<>();
        List<ServiceProvider> serviceProviders = ServiceProviderFacade.getServiceProviderService().findAll();
        if (month.equalsIgnoreCase("all")) {
            for (ServiceProvider serviceProvider : serviceProviders) {
                List<Request> list = RequestFacade.getRequestService().findByServiceProvider(serviceProvider.getId());

                for (Request request : list) {
                    if (request.getInvoiceNumber() != null) {
                        newlist.add(request);
                    }
                }
            }
        } else {
            for (ServiceProvider serviceProvider : serviceProviders) {
                List<Request> list = RequestFacade.getRequestService().findByServiceProvider(serviceProvider.getId());

                for (Request request : list) {
                    if (request.getInvoiceNumber() != null) {
                        String datemonth = new SimpleDateFormat("MMMM").format(request.getDeliveryDate());
                        String dateyear = new SimpleDateFormat("YYYY").format(request.getDeliveryDate());
                        if (datemonth.equals(month) && year.equals(dateyear)) {
                            newlist.add(request);
                        }
                    }
                }
            }
        }


        paidTotal = paidTotal.add(getSupplierTotal(newlist));
        balanceTotal = balanceTotal.add(getPaidTotal(newlist));
        addItem(new Object[]{
            "MTD Suppliers Total",
            f.format(paidTotal),
            f.format(balanceTotal),
            f.format(paidTotal.subtract(balanceTotal)),}, "subtotal");
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

    public final void loadTable(String month, String year) {
        grandTotal = new BigDecimal("0.00");
        paidTotal = new BigDecimal("0.00");
        balanceTotal = new BigDecimal("0.00");
        addSubtotalRow(month, year);
        if (month != null && !month.equalsIgnoreCase("all")) {
            List<ServiceProvider> serviceProviders = ServiceProviderFacade.getServiceProviderService().findAll();
            for (ServiceProvider serviceProvider : serviceProviders) {
                List<Request> list = RequestFacade.getRequestService().findByServiceProvider(serviceProvider.getId());
                List<Request> newlist = new ArrayList<>();
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
                        f.format(getSupplierTotal(newlist)),
                        f.format(getPaidTotal(newlist)),
                        f.format(getSupplierTotal(newlist).subtract(getPaidTotal(newlist)))}, serviceProvider.getId());
                    grandTotal = grandTotal.add(getSupplierTotal(newlist).subtract(getPaidTotal(newlist)));
                }
            }
        } else {
            List<ServiceProvider> serviceProviders = ServiceProviderFacade.getServiceProviderService().findAll();
            for (ServiceProvider serviceProvider : serviceProviders) {
                List<Request> list = RequestFacade.getRequestService().findByServiceProvider(serviceProvider.getId());
                List<Request> newlist = new ArrayList<>();
                for (Request request : list) {
                    if (request.getInvoiceNumber() != null) {
                        newlist.add(request);
                    }
                }
                if (newlist.size() > 0) {
                    addItem(new Object[]{
                        serviceProvider.getName(),
                        f.format(getSupplierTotal(newlist)),
                        f.format(getPaidTotal(newlist)),
                        f.format(getSupplierTotal(newlist).subtract(getPaidTotal(newlist)))}, serviceProvider.getId());
                    grandTotal = grandTotal.add(getSupplierTotal(newlist).subtract(getPaidTotal(newlist)));
                }
            }
        }
        performRowStyling();
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

    private BigDecimal getPaidTotal(List<Request> requests) {
        BigDecimal total = BigDecimal.ZERO;
        for (Request request : requests) {
            if (request.getInvoiceNumber() != null) {
                if (request.getPaymentAmount() != null) {
                    total = total.add(request.getPaymentAmount());
                }
            }
        }
        return total;
    }
}
