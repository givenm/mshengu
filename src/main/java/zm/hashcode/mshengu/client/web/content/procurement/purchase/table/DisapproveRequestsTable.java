/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.math.BigDecimal;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.form.DisapprovedRequestsForm;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.views.DisapprovedRequestsTab;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class DisapproveRequestsTable extends Table {

    private DisapprovedRequestsTab tab;

    public DisapproveRequestsTable(DisapprovedRequestsTab tab) {

        this.tab = tab;
        setSizeFull();

        addContainerProperty("Purchasing Person", String.class, null);
        addContainerProperty("Company Name", String.class, null);
        addContainerProperty("Total", BigDecimal.class, null);
        addContainerProperty("Details", Button.class, null);

        displayRequests();

        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    private void displayRequests() {
        if (RequestFacade.getRequestService().findAll() != null) {
            for (Request request : RequestFacade.getRequestService().findAll()) {
                if (request.getReasonForDisapproval() != null) {
                    Button showDetails = new Button("More Details");
                    showDetails.setData(request.getId());
                    showDetails.setStyleName(Reindeer.BUTTON_LINK);
                    showDetails.addClickListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            String itemId = event.getButton().getData().toString();
                            DisapprovedRequestsForm form = new DisapprovedRequestsForm(itemId, tab);
                            tab.removeAllComponents();
                            tab.addComponent(form);
                        }
                    });
                    addItem(new Object[]{
                        request.getPersonName(),
                        request.getServiceProviderName(),
                        request.getTotal(),
                        showDetails,}, request.getId());
                }
            }
        }
    }
}
