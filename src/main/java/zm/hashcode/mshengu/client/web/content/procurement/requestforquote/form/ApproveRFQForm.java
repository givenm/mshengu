/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestForQuoteFacade;
import zm.hashcode.mshengu.app.facade.procurement.ResponseToRFQFacade;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.table.DisplayItemsTable;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.views.RFQListTab;
import zm.hashcode.mshengu.domain.procurement.RequestForQuote;
import zm.hashcode.mshengu.domain.procurement.ResponseToRFQ;

/**
 *
 * @author Luckbliss
 */
public class ApproveRFQForm extends FormLayout {

    private final RFQListTab tab;
    public Button approve = new Button("Approve");
    public Button disapprove = new Button("Disapprove");
    public Button cancel = new Button("Cancel");
    private final ResponseToRFQ responseToRFQ;
    private final RequestForQuote quote;

    public ApproveRFQForm(final RFQListTab tab, final ResponseToRFQ responseToRFQ, final RequestForQuote quote) {
        this.tab = tab;
        this.responseToRFQ = responseToRFQ;
        this.quote = quote;

        setSizeFull();
        GridLayout gridlayout = new GridLayout(3, 20);
        gridlayout.setSizeFull();

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSizeFull();
        approve.setSizeFull();
        disapprove.setSizeFull();
        cancel.setSizeFull();
        buttons.addComponent(approve);
        buttons.addComponent(disapprove);
        buttons.addComponent(cancel);

        Label companyName = new Label("Company Name: " + responseToRFQ.getCompanyName());
        Label chiefExecutive = new Label("Chief Executive: " + responseToRFQ.getChiefExecutive());
        Label companyType = new Label("Company Type: " + responseToRFQ.getCompanyType());
        Label refNumber = new Label("Reference Number: " + responseToRFQ.getRefNumber());
        Label vatRegistrationNumber = new Label("Vat Registration Number: " + responseToRFQ.getVatRegistrationNumber());
        Label yearEstablishment = new Label("Years of Establishment: " + responseToRFQ.getYearEstablishment());

        DisplayItemsTable table = new DisplayItemsTable(responseToRFQ.getItems());
        gridlayout.addComponent(companyName, 0, 0);
        gridlayout.addComponent(companyType, 1, 0);
        gridlayout.addComponent(chiefExecutive, 2, 0);
        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);
        gridlayout.addComponent(vatRegistrationNumber, 0, 2);
        gridlayout.addComponent(refNumber, 1, 2);
        gridlayout.addComponent(yearEstablishment, 2, 2);
        gridlayout.addComponent(new Label("<br>", ContentMode.HTML), 0, 3);
        gridlayout.addComponent(table, 0, 4, 2, 4);
        gridlayout.addComponent(buttons, 0, 5, 2, 5);

        addComponent(gridlayout);
        addCancel();
        addDisapprove();
        addApprove();
        validateIsAccepted();
    }

    private void addCancel() {
        cancel.addClickListener(new Button.ClickListener() {   
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getHome();
            }
        });
    }

    private void validateIsAccepted() {
        if (responseToRFQ.isAccepted()) {
            approve.setVisible(false);
            disapprove.setVisible(false);
        }
    }

    private void addDisapprove() {
        disapprove.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ResponseToRFQFacade.getResponseToRFQService().delete(responseToRFQ);
                List<ResponseToRFQ> responses = quote.getResponseToRFQ();
                responses.remove(responseToRFQ);
                RequestForQuote newQuote = new RequestForQuote.Builder(quote.getPerson())
                        .request(quote)
                        .responseToRFQ(responses)
                        .build();
                RequestForQuoteFacade.getRequestForQuoteService().merge(newQuote);
                getHome();
            }
        });
    }

    private void addApprove() {
        approve.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ResponseToRFQ newresponse = new ResponseToRFQ.Builder(responseToRFQ.getCompanyName())
                        .request(responseToRFQ)
                        .accepted(true)
                        .build();
                ResponseToRFQFacade.getResponseToRFQService().merge(newresponse);
                List<ResponseToRFQ> responses = new ArrayList<>();
                responses.add(newresponse);
                RequestForQuote newQuote = new RequestForQuote.Builder(quote.getPerson())
                        .request(quote)
                        .responseToRFQ(responses)
                        .build();
                RequestForQuoteFacade.getRequestForQuoteService().merge(newQuote);

                for (ResponseToRFQ rFQ : quote.getResponseToRFQ()) {
                    if (!rFQ.getId().equals(responseToRFQ.getId())) {
                        ResponseToRFQFacade.getResponseToRFQService().delete(rFQ);
                    }
                }
                getHome();
            }
        });
    }

    private void getHome() {
        tab.removeAllComponents();
        ViewResponseForm form = new ViewResponseForm(tab.main, tab, quote.getId());
        tab.addComponent(form);
    }
}
