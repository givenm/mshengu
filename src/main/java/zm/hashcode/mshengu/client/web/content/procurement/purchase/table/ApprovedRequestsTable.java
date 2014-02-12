/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.form.ApproveRequestsForm;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.form.SendPurchasePDFForm;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.views.ApprovedRequestsTab;
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;

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
        addContainerProperty("PO Number", String.class, null);
        addContainerProperty("Purchasing Person", String.class, null);
        addContainerProperty("Company Name", String.class, null);
        addContainerProperty("Total", BigDecimal.class, null);
        addContainerProperty("Email Status", String.class, null);
        addContainerProperty("More Details", Button.class, null);

        setNullSelectionAllowed(false);
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
        setSizeFull();
        displayRequests(tab);
    }

    private void displayRequests(final ApprovedRequestsTab tab) {
        String message;
        if (RequestFacade.getRequestService().findAll() != null) {
            List<Request> items = RequestFacade.getRequestService().findAll();
            for (int i = items.size() - 1; i >= 0; i--) {
                if (items.get(i).isApprovalStatus()) {
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
                    if (items.get(i).isEmailstatus()) {
                        message = "sent";
                    } else {
                        message = "not sent";
                    }
                    addItem(new Object[]{
                        items.get(i).getApprover(),
                        items.get(i).getOrderNumber(),
                        items.get(i).getPersonName(),
                        items.get(i).getServiceProviderName(),
                        items.get(i).getTotal(),
                        message,
                        showDetails,}, items.get(i).getId());
                    message = "";
                }
            }
        }
    }

    private void displayPDF(Object object) {
        Request requestt = (Request) object;
        tab.removeAllComponents();
        SendPurchasePDFForm form = new SendPurchasePDFForm(main, requestt, tab);
        tab.setImmediate(true);
        tab.addComponent(form);
    }

    public void loadTable(List<Request> items, String month, String year) {
        String message;
        for (int i = items.size() - 1; i >= 0; i--) {
            if (items.get(i).isApprovalStatus()) {
                String datemonth = new SimpleDateFormat("MMMM").format(items.get(i).getDeliveryDate());
                String dateyear = new SimpleDateFormat("YYYY").format(items.get(i).getDeliveryDate());
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
                    if (items.get(i).isEmailstatus()) {
                        message = "sent";
                    } else {
                        message = "not sent";
                    }
                    addItem(new Object[]{
                        items.get(i).getApprover(),
                        items.get(i).getOrderNumber(),
                        items.get(i).getPersonName(),
                        items.get(i).getServiceProviderName(),
                        items.get(i).getTotal(),
                        message,
                        showDetails,}, items.get(i).getId());
                    message = "";
                }
            }
        }
    }
}
