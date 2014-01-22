/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.sequence.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.util.SequenceFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.util.Sequence;

/**
 *
 * @author Ferox
 */
public class SequenceTable extends Table {

    private final MshenguMain main;

    public SequenceTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Sequence Name", String.class, null);
        addContainerProperty("Sequence Type", String.class, null);
        addContainerProperty("Sequence Naming Code ", String.class, null);
        addContainerProperty("Sequence Value", Integer.class, null);


        // Add Data Columns
        List<Sequence> sequenceList = SequenceFacade.getSequenceListService().findAll();
        for (Sequence sequence : sequenceList) {
            addItem(new Object[]{sequence.getName(),
                        sequence.getSequenceTypeName(),
                        sequence.getNamingCode(),
                        sequence.getValue(),}, sequence.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }

}