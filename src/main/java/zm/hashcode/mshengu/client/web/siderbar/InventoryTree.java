/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.siderbar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.assets.siteunit.UnitMenu;

/**
 *
 * @author boniface
 */
public class InventoryTree extends Tree implements ItemClickEvent.ItemClickListener {

    private final MshenguMain main;
    public static final Object MANAGE_UNITS = "MANAGE UNITS";
    private static final String LANDING_TAB = "LANDING";

    public InventoryTree(MshenguMain main) {
        this.main = main;
        addItem(MANAGE_UNITS);

        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (MANAGE_UNITS.equals(itemId)) {
                manageUnitView();
            }
        }
    }


    private void manageUnitView() {
        main.content.setSecondComponent(new UnitMenu(main, LANDING_TAB));

    }
}
