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
    private List<CostCentreType> centreTypes = CostCentreTypeFacade.getCostCentreTypeService().findAll();

    public CostCentreTable() {
        setSizeFull();

        addContainerProperty("CostCentre", String.class, null);
        addContainerProperty("Total", String.class, null);
        addContainerProperty("Paid To Date", String.class, null);

        loadTable(null, new Date());
    }

    public final void loadTable(String costCentreId, Date date) {
        List<Request> newlist = new ArrayList<>();
        grandTotal = new BigDecimal("0");
        if (costCentreId != null && !costCentreId.equalsIgnoreCase("all")) {
            CostCentreType centreType = CostCentreTypeFacade.getCostCentreTypeService().findById(costCentreId);
            newlist = RequestFacade.getRequestService().getProcessedRequestsByCostCentreType(centreType, date);
            addItem(new Object[]{
                centreType.getName(),
                f.format(getSupplierTotal(newlist)),
                f.format(getPaidTotal(newlist)),}, centreType.getId());
            grandTotal = grandTotal.add(getSupplierTotal(newlist).subtract(getPaidTotal(newlist)));
        } else {
            for (CostCentreType centreType : centreTypes) {
                newlist = RequestFacade.getRequestService().getProcessedRequestsByCostCentreType(centreType, date);
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
