/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.goodsreceived;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.views.GoodsReceivedTab;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.views.UpdateInvoiceTab;

/**
 *
 * @author Luckbliss
 */
public class GoodsReceivedMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private GoodsReceivedTab goodsTab;
    private UpdateInvoiceTab invoiceTab;

    public GoodsReceivedMenu(MshenguMain app, String selectedTab) {
        main = app;
        goodsTab = new GoodsReceivedTab(app);
        invoiceTab = new UpdateInvoiceTab(app);
        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(goodsTab, "Goods Received Note (GRN)", null);
        tab.addTab(invoiceTab, "Update Mismatched Invoice", null);
        addComponent(tab);
        switch (selectedTab) {
            case "LANDING":
                tab.setSelectedTab(goodsTab);
                break;
        }
    }
}
