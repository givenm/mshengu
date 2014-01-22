/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.mailnotification.tables;

import com.vaadin.ui.Table;
import zm.hashcode.mshengu.app.facade.external.MailNotificationsFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.UITableIconHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.external.MailNotifications;

/**
 *
 * @author Ferox
 */
public class RecipientTable extends Table {

    private final MshenguMain main;
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
    private UITableIconHelper iconHelper = new UITableIconHelper();

    public RecipientTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Notification Name", String.class, null);
        addContainerProperty("Email Address", String.class, null);

        // Add Data Columns
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
    //table.loadRecipients(form.parentId.getValue().toString());

    public void loadRecipients(String notificationId) {
        // Add Data Columns
        setImmediate(false);
        removeAllItems();
        MailNotifications mailNotifications = MailNotificationsFacade.getMailNotificationsService().findById(notificationId);
        if (mailNotifications != null) {
            if (mailNotifications.getEmailList() != null) {
                for (String email : mailNotifications.getEmailList()) {
                    addItem(new Object[]{mailNotifications.getName(),
                                email,}, email);
                }
            }

            setImmediate(true);
        }
    }
}