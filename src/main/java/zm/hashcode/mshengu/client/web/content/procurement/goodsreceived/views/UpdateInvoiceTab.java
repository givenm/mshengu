/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.views;

import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.table.UpdateInvoiceTable;

/**
 *
 * @author Luckbliss
 */
public class UpdateInvoiceTab extends VerticalLayout {

    private UpdateInvoiceTable table;
    private MshenguMain main;

    public UpdateInvoiceTab(MshenguMain main) {
        setSizeFull();
        table = new UpdateInvoiceTable(main);
        this.main = main;
        addComponent(table);
    }
}
