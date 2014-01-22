/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.siderbar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.billing.BillingMenu;

/**
 *
 * @author boniface
 */
public class BillingTree extends Tree implements ItemClickEvent.ItemClickListener{
    private final MshenguMain main;
    public static final Object BILLING = "Billing";
    private static final String LANDING_TAB = "LANDING";
    public BillingTree(MshenguMain main) {
        this.main = main;
        addItem(BILLING);

        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);
    }
    
    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (BILLING.equals(itemId)) {
                manageBillingView();
            }
        }
    }
    private void manageBillingView() {
        main.content.setSecondComponent(new BillingMenu(main, LANDING_TAB));

    }

}
