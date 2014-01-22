/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.views;

import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.table.ApproveRequestsTable;

/**
 *
 * @author Luckbliss
 */
public class ApproveRequestsTab extends VerticalLayout {

    public final MshenguMain main;
    private ApproveRequestsTable table;

    public ApproveRequestsTab(MshenguMain main) {
        setSizeFull();
        this.main = main;
        table = new ApproveRequestsTable(this, main);
        addComponent(table);
    }
}