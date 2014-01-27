/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase;

import com.vaadin.data.Property;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.views.ApproveRequestsTab;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.views.ApprovedRequestsTab;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.views.DisapprovedRequestsTab;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.views.RequestPurchaseTab;

/**
 *
 * @author Luckbliss
 */
public class PurchaseMenu extends VerticalLayout implements
        Property.ValueChangeListener, SelectedTabChangeListener {

    private MshenguMain main;
    private TabSheet tab;
    private RequestPurchaseTab purchaseTab;
    private ApproveRequestsTab approveRequestsTab;
    private ApprovedRequestsTab approvedRequestsTab;
    private DisapprovedRequestsTab disapprovedRequestsTab;

    public PurchaseMenu(MshenguMain app, String selectedTab) {
        main = app;
        purchaseTab = new RequestPurchaseTab(app);
        approveRequestsTab = new ApproveRequestsTab(app);
        approvedRequestsTab = new ApprovedRequestsTab(app);
        disapprovedRequestsTab = new DisapprovedRequestsTab(app);

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(purchaseTab, "Request Form", null);
        tab.addTab(approveRequestsTab, "Requests' Pending Approvals", null);
        tab.addTab(disapprovedRequestsTab, "Disapproved Requests", null);
        tab.addTab(approvedRequestsTab, "Successfully Approved Requests", null);
        addComponent(tab);

        switch (selectedTab) {
            case "LANDING":
                tab.setSelectedTab(purchaseTab);
                break;
            case "APPROVE_REQUESTS":
                tab.setSelectedTab(approveRequestsTab);
                break;
            case "DISSAPPROVED_REQUESTS":
                tab.setSelectedTab(disapprovedRequestsTab);
                break;
            case "APPROVED_REQUESTS":
                tab.setSelectedTab(approvedRequestsTab);
                break;

        }

    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
    }

    @Override
    public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
    }
}
