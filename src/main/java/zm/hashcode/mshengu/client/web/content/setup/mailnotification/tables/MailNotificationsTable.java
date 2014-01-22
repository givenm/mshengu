/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.mailnotification.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.external.MailNotificationsFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.UITableIconHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.external.MailNotifications;

/**
 *
 * @author Ferox
 */
public class MailNotificationsTable extends Table {

    private final MshenguMain main;
    private DateTimeFormatHelper formatHelper = new DateTimeFormatHelper();
    private UITableIconHelper iconHelper = new UITableIconHelper();

    public MailNotificationsTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Notification Name", String.class, null);
        addContainerProperty("Sequence Name", String.class, null);
        addContainerProperty("Sequence Code", String.class, null);
        addContainerProperty("Sequence Conter", Integer.class, null);

        // Add Data Columns
        List<MailNotifications> mailNotificationsList = MailNotificationsFacade.getMailNotificationsService().findAll();
        for (MailNotifications mailNotifications : mailNotificationsList) {
            addItem(new Object[]{mailNotifications.getName(),
                                mailNotifications.getSequenceName(),
                                mailNotifications.getSequenceNameCode(),
                                mailNotifications.getSequenceValue(),}, mailNotifications.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}