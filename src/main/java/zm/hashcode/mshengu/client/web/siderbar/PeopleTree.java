/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.siderbar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.users.UsersMenu;

/**
 *
 * @author boniface
 */
public class PeopleTree extends Tree implements ItemClickEvent.ItemClickListener {

    private final MshenguMain main;
    public static final Object MANAGE_USERS = "Manage People";
    private static final String LANDING_TAB = "LANDING";

    public PeopleTree(MshenguMain main) {
        this.main = main;
        addItem(MANAGE_USERS);

        //Add Listeners
        addItemClickListener((ItemClickEvent.ItemClickListener) this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId != null) {
            if (MANAGE_USERS.equals(itemId)) {
                manageUsersView();
            }
        }
    }

    private void manageUsersView() {
        main.content.setSecondComponent(new UsersMenu(main, LANDING_TAB));

    }
}

