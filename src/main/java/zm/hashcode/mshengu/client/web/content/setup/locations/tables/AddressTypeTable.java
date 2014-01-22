/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.locations.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.location.AddressTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.location.AddressType;

/**
 *
 * @author boniface
 */
public class AddressTypeTable extends Table {
   

    private final MshenguMain main;

    public AddressTypeTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Address Type", String.class, null);
        
        
         // Add Data Columns
        List<AddressType> addressTypes = AddressTypeFacade.getAddressTypeService().findAll();
        for (AddressType addressType : addressTypes) {
            addItem(new Object[]{addressType.getAddressTypeName()}, addressType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);

        


    }
}
