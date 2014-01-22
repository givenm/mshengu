/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations.tables;

import com.vaadin.ui.Table;
import java.util.Date;
import zm.hashcode.mshengu.client.web.MshenguMain;

/**
 *
 * @author boniface
 */
public class RegistrationBodyTable extends Table {
   

    private final MshenguMain main;

    public RegistrationBodyTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Name", String.class, null);
        addContainerProperty("Description", String.class, null);
           addContainerProperty("Core Activity", String.class, null);
        addContainerProperty("Active", String.class, null);
         addContainerProperty("Date ", Date.class, null);
        
//         // Add Data Columns
//        List<RegistrationBody> registrationBodys = LocationFacade.getRegistrationBodyModelService().findAll();
//        for (RegistrationBody registrationBody : registrationBodys) {
//            addItem(new Object[]{registrationBody.getName(),
//                                registrationBody.getDescription(),
//                                registrationBody.getCoreActivity(),
//                                registrationBody.getActive(),
//                                registrationBody.getAsOfDate()}, registrationBody.getId());
//        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);

        


    }
}
