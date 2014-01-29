/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestForQuoteFacade;
import zm.hashcode.mshengu.app.facade.procurement.ResponseToRFQFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form.SendQuotePDFForm;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form.ViewResponseForm;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.views.RFQListTab;
import zm.hashcode.mshengu.domain.procurement.RequestForQuote;
import zm.hashcode.mshengu.domain.procurement.ResponseToRFQ;

/**
 *
 * @author Luckbliss
 */
public class ViewQuoteTable extends Table {

    private MshenguMain main;

    public ViewQuoteTable(MshenguMain app, final RFQListTab tab) {
        this.main = app;
        setSizeFull();

        addContainerProperty("Date Requested", String.class, null);
        addContainerProperty("RFQ Number", String.class, null);
        addContainerProperty("Person Requesting", String.class, null);
        addContainerProperty("View More Details", Button.class, null);
        addContainerProperty("View Responses", Button.class, null);

        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
        for (RequestForQuote requestForQuote : RequestForQuoteFacade.getRequestForQuoteService().findAll()) {

            if (requestForQuote != null) {
                Button showDetails = new Button("Details");
                showDetails.setData(requestForQuote.getId());
                showDetails.setStyleName(Reindeer.BUTTON_LINK);
                showDetails.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        tab.removeAllComponents();
                        SendQuotePDFForm form = new SendQuotePDFForm(RequestForQuoteFacade.getRequestForQuoteService().findById((String) event.getButton().getData()), main);
                        tab.addComponent(form);
                    }
                });
                if (requestForQuote.getResponseToRFQ() != null) {
                    Button responses = new Button("Responses");
                    responses.setData(requestForQuote.getId());
                    responses.setStyleName(Reindeer.BUTTON_LINK);
                    responses.addClickListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            tab.removeAllComponents();
                            ViewResponseForm form = new ViewResponseForm(main, tab, (String) event.getButton().getData());
                            tab.addComponent(form);
                        }
                    });
                    addItem(new Object[]{
                        getDate(requestForQuote.getDeliveryDate()),
                        requestForQuote.getRfqNumber(),
                        requestForQuote.getPerson().getFirstname() + " " + requestForQuote.getPerson().getLastname(),
                        showDetails,
                        responses,}, requestForQuote.getId());
                } else {
                    Button responses = new Button("No Responses");
                    responses.setStyleName(Reindeer.BUTTON_LINK);
                    addItem(new Object[]{
                        getDate(requestForQuote.getDeliveryDate()),
                        requestForQuote.getRfqNumber(),
                        requestForQuote.getPerson().getFirstname() + " " + requestForQuote.getPerson().getLastname(),
                        showDetails,
                        responses,}, requestForQuote.getId());
                }
            }
        }
    }

    private String getDate(Date dateofbirth) {
        if (dateofbirth != null) {
            return new DateTimeFormatHelper().getDayMonthYear(dateofbirth);
        }
        return null;
    }
}
