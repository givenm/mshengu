/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.sequence.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.util.SequenceTypeFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.util.SequenceType;

/**
 *
 * @author Ferox
 */
public class SequenceTypeTable extends Table {

    private final MshenguMain main;

    public SequenceTypeTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Sequence Type Name", String.class, null);
        addContainerProperty("Sequence Type Description", String.class, null);
        

        // Add Data Columns
        List<SequenceType> sequenceList = SequenceTypeFacade.getSequenceTypeListService().findAll();
        for (SequenceType sequenceType : sequenceList) {
            addItem(new Object[]{sequenceType.getName(),
                sequenceType.getDescription(),
            }, sequenceType.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }


    }