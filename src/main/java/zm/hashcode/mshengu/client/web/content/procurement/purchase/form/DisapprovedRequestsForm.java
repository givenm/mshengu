/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.form;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.util.Set;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.table.DisapproveRequestsTable;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.table.DisplayItemsTable;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.views.DisapprovedRequestsTab;
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.domain.procurement.RequestPurchaseItem;
import zm.hashcode.mshengu.domain.ui.util.CostCentreType;

/**
 *
 * @author Luckbliss
 */
public class DisapprovedRequestsForm extends FormLayout implements
        Button.ClickListener {

    public Button back = new Button("Back");
    private DisapprovedRequestsTab tab;

    public DisapprovedRequestsForm(String itemId, DisapprovedRequestsTab tab) {
        this.tab = tab;
        Request request = RequestFacade.getRequestService().findById(itemId);

        GridLayout layout = new GridLayout(3, 15);
        layout.setSizeFull();
        Label requesterInfo = new Label("Requester Information");
        Label person = new Label("Person Requesting: " + request.getPersonName());

        Label account = new Label("Cost Centre: " + returnCostCentre(request.getCostCentreType()));

        layout.addComponent(requesterInfo, 0, 0);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);
        layout.addComponent(person, 0, 2);
        layout.addComponent(account, 1, 2);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 3);

        Label vendorInfo = new Label("Vendor Information");
        Label vendor = new Label("Vendor: " + request.getServiceProvider().getName());
        Label address = new Label("Address: " + request.getServiceProvider().getContactAddress1());
        Label phoneNumber = new Label("Phone Number: " + request.getServiceProvider().getContactPersonMainNumber());
        Label deliveryInstructions = new Label("Delivery Instructions: " + request.getDeliveryInstructions());
        Label reason = new Label("Disapproval Reason: " + request.getReasonForDisapproval());

        layout.addComponent(vendorInfo, 0, 4);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 5);
        layout.addComponent(vendor, 0, 6);
        layout.addComponent(address, 1, 6);
        layout.addComponent(phoneNumber, 2, 6);
        deliveryInstructions.setSizeFull();
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 7);
        layout.addComponent(deliveryInstructions, 0, 8, 1, 8);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 9);
        layout.addComponent(reason, 0, 10);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 11);

        DisplayItemsTable table = new DisplayItemsTable(request.getRequestPurchaseItems(), request.getTotal());
        table.setSizeFull();
        layout.addComponent(table, 0, 12, 2, 12);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSizeFull();
        back.setSizeFull();
        buttons.addComponent(back);

        layout.addComponent(buttons, 0, 13, 2, 13);
        addComponent(layout);
        addListeners();
    }

    private String returnCostCentre(CostCentreType costCentre) {
        if (costCentre != null) {
            return costCentre.getName();
        }
        return null;
    }

    private void addListeners() {
        back.addClickListener((Button.ClickListener) this);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == back) {
            tab.removeAllComponents();
            DisapproveRequestsTable table = new DisapproveRequestsTable(tab);
            tab.addComponent(table);
        }
    }
}
