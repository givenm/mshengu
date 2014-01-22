/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.password;

import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.Menu;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.password.tab.PasswordTab;

/**
 *
 * @author boniface
 */
public class PasswordMenu extends Menu {

    public PasswordMenu(MshenguMain app, String selectedTab) {
        super(app, selectedTab);

        final VerticalLayout changePasswordTab = new VerticalLayout();
        changePasswordTab.setMargin(true);
        changePasswordTab.addComponent(new PasswordTab(getMain()));

        getTab().addTab(changePasswordTab, "Change Password", null);

        switch (selectedTab) {
            case "LANDING":
                getTab().setSelectedTab(changePasswordTab);
                break;
        }
        addComponent(getTab());

    }
}
