/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestForQuoteFacade;
import zm.hashcode.mshengu.app.facade.procurement.ResponseToRFQFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form.ApproveRFQForm;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form.ViewResponseForm;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.views.RFQListTab;
import zm.hashcode.mshengu.domain.procurement.RequestForQuote;
import zm.hashcode.mshengu.domain.procurement.ResponseToRFQ;

/**
 *
 * @author Luckbliss
 */
public class ViewResponseTable extends Table {

    private MshenguMain main;
    private final RFQListTab tab;
    private final ViewResponseForm form;

    public ViewResponseTable(MshenguMain app, final RFQListTab tab, String rfqId, final ViewResponseForm form) {
        this.main = app;
        this.tab = tab;
        this.form = form;
        setSizeFull();

        addContainerProperty("Company Name", String.class, null);
        addContainerProperty("Company Type", String.class, null);
        addContainerProperty("Vat Registration Number", String.class, null);
        addContainerProperty("WebSite", String.class, null);
        addContainerProperty("Response Status", String.class, null);
        addContainerProperty("View Details", Button.class, null);

        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);

        loadTable(rfqId);
    }

    private void loadTable(String rfqId) {
        final RequestForQuote quote = RequestForQuoteFacade.getRequestForQuoteService().findById(rfqId);

        for (ResponseToRFQ responseToRFQ : quote.getResponseToRFQ()) {
            Button showDetails = new Button("Details");
            showDetails.setData(responseToRFQ.getId());
            showDetails.setStyleName(Reindeer.BUTTON_LINK);
            showDetails.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    String itemId = event.getButton().getData().toString();
                    ResponseToRFQ responseToRFQ = ResponseToRFQFacade.getResponseToRFQService().findById(itemId);
                    tab.removeAllComponents();
                    ApproveRFQForm approveRFQForm = new ApproveRFQForm(tab, responseToRFQ, quote);
                    tab.addComponent(approveRFQForm);
                }
            });
            if (responseToRFQ.isAccepted()) {
                addItem(new Object[]{
                    responseToRFQ.getCompanyName(),
                    responseToRFQ.getCompanyType(),
                    responseToRFQ.getVatRegistrationNumber(),
                    responseToRFQ.getWebSite(),
                    "accepted",
                    showDetails,}, responseToRFQ.getId());
            } else {
                addItem(new Object[]{
                    responseToRFQ.getCompanyName(),
                    responseToRFQ.getCompanyType(),
                    responseToRFQ.getVatRegistrationNumber(),
                    responseToRFQ.getWebSite(),
                    "pending",
                    showDetails,}, responseToRFQ.getId());
            }
        }
    }
}
