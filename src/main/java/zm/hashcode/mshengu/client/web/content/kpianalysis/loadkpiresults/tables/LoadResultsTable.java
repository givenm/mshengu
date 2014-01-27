/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.tables;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.util.List;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIItemFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.settargets.forms.EditKPIForm;
import zm.hashcode.mshengu.domain.kpianalysis.KPIItem;

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
        
        loadTable();
    }

    private void loadTable() {
        List<KPIItem> items = KPIItemFacade.getKPIItemService().findAll();
        for (KPIItem item : items) {
            addItem(new Object[]{
                item.getShortDescription(),
                item.getUom(),
                "N/A",}, item.getId());
        }
    }
}
