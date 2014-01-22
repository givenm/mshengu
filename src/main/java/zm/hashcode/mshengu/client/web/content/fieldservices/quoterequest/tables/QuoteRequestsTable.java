/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fieldservices.quoterequest.tables;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.external.IncomingRFQFacade;
import zm.hashcode.mshengu.app.facade.incident.IncidentFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.UITableIconHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.external.IncomingRFQ;
import zm.hashcode.mshengu.domain.incident.Incident;
import zm.hashcode.mshengu.domain.incident.UserAction;

/**
 *
 * @author Luckbliss
 */
public class QuoteRequestsTable extends Table {

    private final MshenguMain main;
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
    private UITableIconHelper iconHelper = new UITableIconHelper();

    public QuoteRequestsTable(MshenguMain app) {
        this.main = app;

        addContainerProperty("Action Date", String.class, null);
        addContainerProperty("Company", String.class, null);
        addContainerProperty("Firstname", String.class, null);
        addContainerProperty("Lastname", String.class, null);
        addContainerProperty("Event Type", String.class, null);
        addContainerProperty("Event Name", String.class, null);
        addContainerProperty("Event Date", String.class, null);
        addContainerProperty("Delivery Date ", String.class, null);


        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
        setSizeFull();

        loadQuoteRequests();;



    }

    public final void loadQuoteRequests() {
        List<IncomingRFQ> incomingRFQs = IncomingRFQFacade.getIncomingRFQService().findAll();

        if (incomingRFQs != null) {
            for (IncomingRFQ incomingRFQ : incomingRFQs) {
                addItem(new Object[]{
                    //                userAction.getStatus(),
                    formatHelper.getDayMonthYear(incomingRFQ.getDateOfAction()),
                    incomingRFQ.getCompanyName(),
                    incomingRFQ.getContactPersonFirstname(),
                    incomingRFQ.getContactPersonLastname(),
                    incomingRFQ.getEventType(),
                    incomingRFQ.getEventName(),
                    formatHelper.getDayMonthYear(incomingRFQ.getEventDate()),
                    formatHelper.getDayMonthYear(incomingRFQ.getDeliveryDate()),}, incomingRFQ.getId());
            }
        }
    }
}
