/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.mailnotification;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.mailnotification.views.MailNotificationsTab;
import zm.hashcode.mshengu.client.web.content.setup.mailnotification.views.RecipientsTab;

/**
 *
 * @author Ferox
 */
public class MailNotificationMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private MailNotificationsTab mailNotificationsTab;
    private RecipientsTab recipientsTab;

    public MailNotificationMenu(MshenguMain app, String selectedTab) {
        main = app;

        mailNotificationsTab = new MailNotificationsTab(main);
        recipientsTab = new RecipientsTab(main);


        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(recipientsTab, "Notification Recipients", null);
        tab.addTab(mailNotificationsTab, "Notifications Names", null);


            if (selectedTab.equals("LANDING")) {
                tab.setSelectedTab(recipientsTab);
            } else if (selectedTab.equals("NOTIFICATIONS")) {
                tab.setSelectedTab(mailNotificationsTab);
            } 
        
        addComponent(tab);
    }

}