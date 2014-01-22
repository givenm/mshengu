/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.siderbar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.consumables.ConsumablesMenu;
import zm.hashcode.mshengu.client.web.content.procurement.goodsreceived.GoodsReceivedMenu;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.InvoicesMenu;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.PurchaseMenu;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.RFQMenu;
import zm.hashcode.mshengu.client.web.content.procurement.suppliers.SupplierMenu;
import zm.hashcode.mshengu.client.web.content.procurement.vendors.ServiceProviderMenu;

/**
 *
 * @author boniface
 */
public class ProcurementTree extends Tree implements ItemClickEvent.ItemClickListener {

    private final MshenguMain main;
    public static final Object RFQ = "Request For Quote (RFQ)"; 
    public static final Object PURCHASE = "Purchase Request"; 
    public static final Object GOODS_RECEIVED = "Goods Received";
    public static final Object INVOICES_FOR_PROCESSING = "Invoices For Processing";
    public static final Object MANAGE_SERVICE_PROVIDERS = "Vendor Database";
    public static final Object CONSUMABLES = "Consumables";
    private static final String LANDING_TAB = "LANDING";

    public ProcurementTree(MshenguMain main) {
        this.main = main;
        addItem(RFQ);
        addItem(PURCHASE);
        addItem(GOODS_RECEIVED);
        addItem(INVOICES_FOR_PROCESSING);
        addItem(MANAGE_SERVICE_PROVIDERS);
        addItem(CONSUMABLES);

        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (MANAGE_SERVICE_PROVIDERS.equals(itemId)) {
                manageServiceProviders();
            } else if (CONSUMABLES.equals(itemId)) {
                manageConsumables();
            } else if (PURCHASE.equals(itemId)) {
                managePurchase();
            } else if (RFQ.equals(itemId)) {
                manageRFQ();
            } else if (GOODS_RECEIVED.equals(itemId)) {
                manageGoods();
            } else if (INVOICES_FOR_PROCESSING.equals(itemId)) {
                manageInvoices();
            }
        }
    }
    private void managePurchase() {
        main.content.setSecondComponent(new PurchaseMenu(main, LANDING_TAB));

    }
    private void manageRFQ() {
        main.content.setSecondComponent(new RFQMenu(main, LANDING_TAB));

    }
    private void manageInvoices() {
        main.content.setSecondComponent(new InvoicesMenu(main, LANDING_TAB));

    }
    private void manageGoods() {
        main.content.setSecondComponent(new GoodsReceivedMenu(main, LANDING_TAB));

    }

    private void manageServiceProviders() {
        main.content.setSecondComponent(new ServiceProviderMenu(main, LANDING_TAB));

    }

    private void manageConsumables() {
        main.content.setSecondComponent(new ConsumablesMenu(main, LANDING_TAB));

    }
}
