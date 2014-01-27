/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.views;

import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.table.DisapproveRequestsTable;

/**
 *
 * @author Luckbliss
 */
public class DisapprovedRequestsTab extends VerticalLayout {

    private final MshenguMain main;
    private DisapproveRequestsTable table;

    public DisapprovedRequestsTab(MshenguMain app) {
        setSizeFull();
        main = app;
        table = new DisapproveRequestsTable(this);
        addComponent(table);
    }
}
