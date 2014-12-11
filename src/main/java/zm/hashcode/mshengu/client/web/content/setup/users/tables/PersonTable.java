/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.users.tables;

import com.vaadin.ui.Table;
import java.util.ArrayList;
import java.util.List;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author boniface
 */
public class PersonTable extends Table {

    private final MshenguMain main;

    public PersonTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Username", String.class, null);
        addContainerProperty("First Name", String.class, null);
        addContainerProperty("Last Name", String.class, null);
        addContainerProperty("Enabled", Boolean.class, null);


        List<Person> personlist = PersonFacade.getPersonService().findAllUsers();
        for (Person person : personlist) {
            addItem(new Object[]{person.getUsername(),
                person.getFirstname(),
                person.getLastname(),
                person.isEnable()
            }, person.getId());
        }
//         Allow selecting items from the table.
        setNullSelectionAllowed(false);
//
        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}
