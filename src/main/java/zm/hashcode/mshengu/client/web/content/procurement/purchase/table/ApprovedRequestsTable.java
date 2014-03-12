/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.math.BigDecimal;
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

        loadTable(RequestFacade.getRequestService().getApprovedRequests(new Date()));
    }

    private void displayPDF(Object object) {
        Request requestt = (Request) object;
        tab.removeAllComponents();
        SendPurchasePDFForm form = new SendPurchasePDFForm(main, requestt, tab);
        tab.setImmediate(true);
        tab.addComponent(form);
    }

    public final void loadTable(List<Request> items) {
        for (Request request: items) {
            Button showDetails = new Button("View PO");
            showDetails.setData(request);
            showDetails.setStyleName(Reindeer.BUTTON_LINK);
            showDetails.setImmediate(true);
            showDetails.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    displayPDF(event.getButton().getData());
                }
            });
            addItem(new Object[]{
                request.getApprover(),
                getDelivery(request.getOrderDate()),
                request.getOrderNumber(),
                request.getPersonName(),
                request.getServiceProviderName(),
                request.getTotal(),
                getEmailMessage(request),
                getGRNMessage(request),
                showDetails,}, request.getId());       
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
