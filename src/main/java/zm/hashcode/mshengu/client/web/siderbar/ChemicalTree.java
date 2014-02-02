/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.siderbar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.chemicals.DashboardChemicalsMenu;

/**
 *
 * @author boniface
 */
public class ChemicalTree extends Tree implements ItemClickEvent.ItemClickListener {

    private final MshenguMain main;
    public static final Object CHEMICAL_USEAGE = "Chemical Usage";
    private static final String LANDING_TAB = "LANDING";

    public ChemicalTree(MshenguMain main) {
        this.main = main;
//        addItem(CHEMICAL_USEAGE);

        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (CHEMICAL_USEAGE.equals(itemId)) {
                manageChemicalView();
            }
        }
    }

    private void manageChemicalView() {
        main.content.setSecondComponent(new DashboardChemicalsMenu(main, LANDING_TAB));

    }
}
