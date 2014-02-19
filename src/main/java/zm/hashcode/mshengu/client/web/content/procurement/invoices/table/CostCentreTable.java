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
import zm.hashcode.mshengu.app.facade.ui.util.CostCentreTypeFacade;
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.domain.ui.util.CostCentreType;

/**
 *
 * @author Luckbliss
 */
public class CostCentreTable extends Table {

    private BigDecimal grandTotal;
    private DecimalFormat f = new DecimalFormat("###,###.00");

    public CostCentreTable() {
        setSizeFull();

        addContainerProperty("CostCentre", String.class, null);
        addContainerProperty("Total", String.class, null);
        addContainerProperty("Paid To Date", String.class, null);

        String datemonth = new SimpleDateFormat("MMMM").format(new Date());
        String dateyear = new SimpleDateFormat("YYYY").format(new Date());

        loadTable(null, datemonth, dateyear);
    }

    public final void loadTable(String costCentreId, String month, String year) {
        grandTotal = new BigDecimal("0");
        if (costCentreId != null) {
            List<Request> newlist = new ArrayList<>();
            CostCentreType centreType = CostCentreTypeFacade.getCostCentreTypeService().findById(costCentreId);
            List<Request> list = RequestFacade.getRequestService().findAll();
            for (Request request : list) {
                if (request.getInvoiceNumber() != null) {
                    String datemonth = new SimpleDateFormat("MMMM").format(request.getDeliveryDate());
                    String dateyear = new SimpleDateFormat("YYYY").format(request.getDeliveryDate());
                    if (datemonth.equals(month) && year.equals(dateyear) && request.getCostCentreType().equals(centreType)) {
                        newlist.add(request);
                    }
                }
            }
            addItem(new Object[]{
                centreType.getName(),
                f.format(getSupplierTotal(newlist)),
                f.format(getPaidTotal(newlist)),}, centreType.getId());
            grandTotal = grandTotal.add(getSupplierTotal(newlist).subtract(getPaidTotal(newlist)));
        } else {
            List<CostCentreType> centreTypes = CostCentreTypeFacade.getCostCentreTypeService().findAll();
            for (CostCentreType centreType : centreTypes) {
                List<Request> list = RequestFacade.getRequestService().findAll();
                List<Request> newlist = new ArrayList<>();
                for (Request request : list) {
                    if (request.getInvoiceNumber() != null) {
                        String datemonth = new SimpleDateFormat("MMMM").format(request.getDeliveryDate());
                        String dateyear = new SimpleDateFormat("YYYY").format(request.getDeliveryDate());
                        if (datemonth.equals(month) && year.equals(dateyear) && request.getCostCentreType().equals(centreType)) {
                            newlist.add(request);
                        }
                    }
                }
                addItem(new Object[]{
                    centreType.getName(),
                    f.format(getSupplierTotal(newlist)),
                    f.format(getPaidTotal(newlist)),}, centreType.getId());
                grandTotal = grandTotal.add(getSupplierTotal(newlist).subtract(getPaidTotal(newlist)));
            }
        }

    }

    public String getGrandTotal() {
        return f.format(grandTotal);
    }

    private BigDecimal getSupplierTotal(List<Request> requests) {
        BigDecimal total = BigDecimal.ZERO;
        for (Request request : requests) {
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
