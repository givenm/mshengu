/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.staff.tables;

import com.vaadin.ui.Table;
import org.springframework.util.StringUtils;
import zm.hashcode.mshengu.app.facade.people.PersonFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.domain.people.Person;

/**
 *
 * @author Ferox
 */
public class StaffContactPersonTable extends Table {

    private final MshenguMain main;

    public StaffContactPersonTable(MshenguMain main) {
        this.main = main;
        setSizeFull();


        addContainerProperty("Contact Person Name", String.class, null);
        addContainerProperty("Cell Number", String.class, null);
        addContainerProperty("Telephone Number", String.class, null);
        addContainerProperty("Email Address", String.class, null);
        addContainerProperty("Relationship Number", String.class, null);

  
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
    
    public void loadContactPerson(String parentId){
              // Add Data Columns
        removeAllItems();
        Person staff = PersonFacade.getPersonService().findById(parentId);

        if(!StringUtils.isEmpty(staff)){
            if(!StringUtils.isEmpty(staff.getContactPerson())){
        for (ContactPerson contactPerson : staff.getContactPerson()) {
            String cPersonName = "N/A";
            String cPersonCell = "N/A";
            String cPersonTelephone = "N/A";
            String cPersonEmailAddress = "N/A";
            String cPersonRelationship = "N/A";
            if (contactPerson != null) {
                cPersonName = contactPerson.getFirstName() + " " + contactPerson.getLastName();
                cPersonCell = contactPerson.getMainNumber();
                cPersonTelephone = contactPerson.getOtherNumber();
                cPersonEmailAddress = contactPerson.getEmailAddress();
                cPersonRelationship = contactPerson.getPosition();
            }
            addItem(new Object[]{cPersonName,
                cPersonCell,
                cPersonTelephone,
                cPersonEmailAddress,
                cPersonRelationship,}, contactPerson.getId());
        }
            }
        }
    }
}
