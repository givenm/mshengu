/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import zm.hashcode.mshengu.app.facade.procurement.RequestForQuoteFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form.SendQuotePDFForm;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.views.RFQListTab;
import zm.hashcode.mshengu.domain.procurement.RequestForQuote;
import zm.hashcode.mshengu.domain.procurement.ResponseToRFQ;

/**
 *
 * @author Luckbliss
 */
public class ViewResponseTable extends Table {

    private MshenguMain main;

    public ViewResponseTable(MshenguMain app, final RFQListTab tab, String rfqId) {
        this.main = app;
        setSizeFull();

        addContainerProperty("Company Name", String.class, null);
        addContainerProperty("Company Type", String.class, null);
        addContainerProperty("Vat Registration Number", String.class, null);
        addContainerProperty("WebSite", String.class, null);
        addContainerProperty("View Details", Button.class, null);

        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);

        RequestForQuote quote = RequestForQuoteFacade.getRequestForQuoteService().findById(rfqId);
        for (ResponseToRFQ responseToRFQ : quote.getResponseToRFQ()) {
            Button showDetails = new Button("Details");
            showDetails.setData(responseToRFQ.getId());
            showDetails.setStyleName(Reindeer.BUTTON_LINK);
            showDetails.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    tab.removeAllComponents();
                    SendQuotePDFForm form = new SendQuotePDFForm(RequestForQuoteFacade.getRequestForQuoteService().findById((String) event.getButton().getData()), main);
                    tab.addComponent(form);
                }
            });
            addItem(new Object[]{
                responseToRFQ.getCompanyName(),
                responseToRFQ.getCompanyType(),
                responseToRFQ.getVatRegistrationNumber(),
                responseToRFQ.getWebSite(),
                showDetails,}, responseToRFQ.getId());
        }
    }
}
