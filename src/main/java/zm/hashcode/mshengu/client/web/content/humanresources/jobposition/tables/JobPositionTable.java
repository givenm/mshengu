/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.humanresources.jobposition.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.ui.util.JobPositionFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.ui.util.JobPosition;

/**
 *
 * @author Ferox
 */
public class JobPositionTable extends Table {

    private final MshenguMain main;

    public JobPositionTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("Position", String.class, null);


        // Add Data Columns
        List<JobPosition> jobPositionList = JobPositionFacade.getJobPositionService().findAll();
        for (JobPosition jobPosition : jobPositionList) {
            addItem(new Object[]{jobPosition.getName(),}, jobPosition.getId());
        }
        // Allow selecting items from the table.
        setNullSelectionAllowed(false);

        setSelectable(true);
        // Send changes in selection immediately to server.
        setImmediate(true);




    }
}