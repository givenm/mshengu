/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.form.SendPurchasePDFForm;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.views.ApprovedRequestsTab;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class ApprovedRequestsTable extends Table {

    private static ApprovedRequestsTab tab;
    private final MshenguMain main;

    public ApprovedRequestsTable(MshenguMain main, ApprovedRequestsTab tab) {
        ApprovedRequestsTable.tab = tab;
        this.main = main;

        addContainerProperty("Approver", String.class, null);
        addContainerProperty("PO Date", String.class, null);
        addContainerProperty("PO Number", String.class, null);
        addContainerProperty("Purchasing Person", String.class, null);
        addContainerProperty("Company Name", String.class, null);
        addContainerProperty("Total", BigDecimal.class, null);
        addContainerProperty("Email Status", String.class, null);
        addContainerProperty("GRN Status", String.class, null);
        addContainerProperty("More Details", Button.class, null);

        setNullSelectionAllowed(false);
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
        setSizeFull();

        String datemonth = new SimpleDateFormat("MMMM").format(new Date());
        String dateyear = new SimpleDateFormat("YYYY").format(new Date());
        loadTable(RequestFacade.getRequestService().findAll(), datemonth, dateyear);
    }

    private void displayPDF(Object object) {
        Request requestt = (Request) object;
        tab.removeAllComponents();
        SendPurchasePDFForm form = new SendPurchasePDFForm(main, requestt, tab);
        tab.setImmediate(true);
        tab.addComponent(form);
    }

    public final void loadTable(List<Request> items, String month, String year) {
        for (int i = items.size() - 1; i >= 0; i--) {
            if (items.get(i).isApprovalStatus() && items.get(i).getOrderDate() != null) {
                String datemonth = new SimpleDateFormat("MMMM").format(items.get(i).getOrderDate());
                String dateyear = new SimpleDateFormat("YYYY").format(items.get(i).getOrderDate());
                if (datemonth.equals(month) && year.equals(dateyear)) {
                    Button showDetails = new Button("View PO");
                    showDetails.setData(items.get(i));
                    showDetails.setStyleName(Reindeer.BUTTON_LINK);
                    showDetails.setImmediate(true);
                    showDetails.addClickListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            displayPDF(event.getButton().getData());
                        }
                    });
                    addItem(new Object[]{
                        items.get(i).getApprover(),
                        getDelivery(items.get(i).getOrderDate()),
                        items.get(i).getOrderNumber(),
                        items.get(i).getPersonName(),
                        items.get(i).getServiceProviderName(),
                        items.get(i).getTotal(),
                        getEmailMessage(items.get(i)),
                        getGRNMessage(items.get(i)),
                        showDetails,}, items.get(i).getId());
                }
            }
        }
    }

    private String getEmailMessage(Request request) {
        if (request.isEmailstatus()) {
            return "sent";
        } else {
            return "not sent";
        }
    }
    private String getGRNMessage(Request request) {
        if (request.getInvoiceNumber() != null) {
            return "Yes";
        } else {
            return "No";
        }
    }

    private String getDelivery(Date date) {
        if (date != null) {
            return new DateTimeFormatHelper().getDayMonthYear(date);
        }
        return null;
    }
}
