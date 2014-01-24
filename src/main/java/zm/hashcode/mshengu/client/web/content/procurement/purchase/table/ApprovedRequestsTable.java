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
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.form.SendPurchasePDFForm;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.views.ApprovedRequestsTab;
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;

/**
 *
 * @author Luckbliss
 */
public class ApprovedRequestsTable extends Table {

    private static ApprovedRequestsTab main;

    public ApprovedRequestsTable(ApprovedRequestsTab tab) {
        ApprovedRequestsTable.main = tab;


        addContainerProperty("P.O Number", String.class, null);
        addContainerProperty("Purchasing Person", String.class, null);
        addContainerProperty("Company Name", String.class, null);
        addContainerProperty("Total", BigDecimal.class, null);
        addContainerProperty("More Details", Button.class, null);

        setNullSelectionAllowed(false);
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
        setSizeFull();
        displayRequests(tab);
    }

    private void displayRequests(final ApprovedRequestsTab tab) {
        if (RequestFacade.getRequestService().findAll() != null) {
            List<Request> requests = RequestFacade.getRequestService().findAll();
            for (Request request : requests) {
                if (request.isApprovalStatus()) {
                    Button showDetails = new Button("More Details");
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
                        request.getOrderNumber(),
                        request.getPersonName(),
                        request.getServiceProviderName(),
                        request.getTotal(),
                        showDetails,}, request.getId());
                }
            }
        }
    }

    private void displayPDF(Object object) {
        Request requestt = (Request) object;
        main.removeAllComponents();
        SendPurchasePDFForm form = new SendPurchasePDFForm(requestt, main);
        main.setImmediate(true);
        main.addComponent(form);
    }
}
