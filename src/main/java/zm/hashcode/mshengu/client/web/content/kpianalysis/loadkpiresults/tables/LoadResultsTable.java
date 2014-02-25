/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.kpianalysis.KPA;

/**
 *
 * @author Luckbliss
 */
public class LoadResultsTable extends Table {

    private final MshenguMain main;

    public LoadResultsTable(MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("KPI Name", String.class, null);
        addContainerProperty("UOM", String.class, null);
        addContainerProperty("Date", String.class, null);
    }

    public void loadTable( List<KPA> items) {
        for (KPA item : items) {
            addItem(new Object[]{
                item.getName(),
                item.getItems().size() + "",
                "N/A",}, item.getId());
        }
    }
}
