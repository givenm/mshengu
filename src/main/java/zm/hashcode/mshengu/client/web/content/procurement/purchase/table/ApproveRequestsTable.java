/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.table;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.math.BigDecimal;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.PurchaseMenu;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.form.ApproveRequestsForm;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.views.ApproveRequestsTab;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class ApproveRequestsTable extends Table {

    private ApproveRequestsTab tab;
    private MshenguMain main;

    public ApproveRequestsTable(ApproveRequestsTab tab, MshenguMain main) {
        this.tab = tab;
        this.main = main;
        setSizeFull();

        addContainerProperty("Purchasing Person", String.class, null);
        addContainerProperty("Company Name", String.class, null);
        addContainerProperty("Total", BigDecimal.class, null);
        addContainerProperty("Approve/Disapprove", Button.class, null);

        displayRequests();

        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);
    }

    private void displayRequests() {
        List<Request> requestList = RequestFacade.getRequestService().getPendingRequests();
        if (requestList != null) {
            for (Request request : requestList) {
                Button approve = new Button("More Details");
                approve.setData(request.getId());
                approve.setStyleName(Reindeer.BUTTON_LINK);
                approve.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        String itemId = event.getButton().getData().toString();
                        ApproveRequestsForm form = new ApproveRequestsForm(itemId, tab, main);
                        tab.removeAllComponents();
                        tab.addComponent(form);
                    }
                });
                addItem(new Object[]{
                    request.getPersonName(),
                    request.getServiceProviderName(),
                    request.getTotal(),
                    approve,}, request.getId());
            }
        }
    }
}
