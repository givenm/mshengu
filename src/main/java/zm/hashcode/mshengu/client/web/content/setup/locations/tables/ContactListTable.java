/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.location.ContactFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.location.Contact;

/**
 *
 * @author boniface
 */
public class ContactListTable extends Table {

    private final MshenguMain main;

    public ContactListTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Contact List Name", String.class, null);


        // Add Data Columns
        List<Contact> contacts = ContactFacade.getContactService().findAll();
        for (Contact contact : contacts) {
            addItem(new Object[]{contact.getName()}, contact.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}
