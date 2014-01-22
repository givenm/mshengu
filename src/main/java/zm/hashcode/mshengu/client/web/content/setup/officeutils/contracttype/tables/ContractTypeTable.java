/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.contracttype.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.customer.ContractTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.customer.ContractType;

/**
 *
 * @author Ferox
 */
public class ContractTypeTable extends Table {

    private final MshenguMain main;

    public ContractTypeTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Contract Type", String.class, null);


        // Add Data Columns
        List<ContractType> contractTypeList = ContractTypeFacade.getContractTypeService().findAll();
        for (ContractType contractType : contractTypeList) {
            addItem(new Object[]{contractType.getType(),}, contractType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}