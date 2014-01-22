/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.siderbar;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.locations.LocationMenu;
import zm.hashcode.mshengu.client.web.content.setup.mailnotification.MailNotificationMenu;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.OfficeUtilsMenu;
import zm.hashcode.mshengu.client.web.content.setup.users.UsersMenu;

/**
 *
 * @author boniface
 */
public class SetupTree extends Tree implements ItemClickEvent.ItemClickListener {

    private final MshenguMain main;
    public static final Object MANAGE_OFFICE_UTILS = "Office Utilities";
    public static final Object NOTIFICATIONS = "Notifications";
    public static final Object MANAGE_USERS = "Users Management";
    public static final Object MANAGE_LOCATIONS = "Manage Locations";
    private static final String LANDING_TAB = "LANDING";

    public SetupTree(MshenguMain main) {
        this.main = main;
        addItem(MANAGE_OFFICE_UTILS);
        addItem(NOTIFICATIONS);
        addItem(MANAGE_LOCATIONS);
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
            } else if (MANAGE_OFFICE_UTILS.equals(itemId)) {
                manageContractTypesView();
            } else if (MANAGE_LOCATIONS.equals(itemId)) {
                manageLocationsView();
            } else if (NOTIFICATIONS.equals(itemId)) {
                manageNotificationsView();
            }
        }
    }

    private void manageContractTypesView() {
        main.content.setSecondComponent(new OfficeUtilsMenu(main, LANDING_TAB));

    }

    private void manageLocationsView() {
        main.content.setSecondComponent(new LocationMenu(main, LANDING_TAB));
    }

    private void manageUsersView() {
        main.content.setSecondComponent(new UsersMenu(main, LANDING_TAB));

    }
    
    private void manageNotificationsView() {
        main.content.setSecondComponent(new MailNotificationMenu(main, LANDING_TAB));
    }
}
