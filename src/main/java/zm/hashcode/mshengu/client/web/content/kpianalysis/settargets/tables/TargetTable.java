/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.settargets.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.util.List;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIItemFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis.KPIMenu;
import zm.hashcode.mshengu.client.web.content.kpianalysis.settargets.forms.EditKPIForm;
import zm.hashcode.mshengu.client.web.content.kpianalysis.settargets.views.TargetsTab;
import zm.hashcode.mshengu.domain.kpianalysis.KPIItem;

/**
 *
 * @author Luckbliss
 */
public class TargetTable extends Table {

    private final MshenguMain main;
    private final TargetsTab tab;

    public TargetTable(MshenguMain main, TargetsTab tab) {
        this.main = main;
        this.tab = tab;
        setWidth(100, Unit.PERCENTAGE);
        setHeight("150");

        addContainerProperty("KPI Name", String.class, null);
        addContainerProperty("Actual", String.class, null);
        addContainerProperty("Edit Targets", String.class, null);
        addContainerProperty("UOM", String.class, null);
        addContainerProperty("Result", String.class, null);
        addContainerProperty("Weighting", String.class, null);
        addContainerProperty("Measure Type", String.class, null);
        addContainerProperty("Edit", Button.class, null);
        addContainerProperty("Reset", Button.class, null);
    }

    public void loadTable(List<KPIItem> items) {
        for (KPIItem item : items) {
            Button edit = new Button("Edit");
            edit.setData(item.getId());
            edit.setStyleName(Reindeer.BUTTON_LINK);
            edit.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    String itemId = event.getButton().getData().toString();
                    KPIItem item = KPIItemFacade.getKPIItemService().findById(itemId);
                    EditKPIForm form = new EditKPIForm(main, item);
                    tab.removeAllComponents();
                    tab.addComponent(form);
                }
            });
            Button reset = new Button("Reset");
            reset.setData(item.getId());
            reset.setStyleName(Reindeer.BUTTON_LINK);
            reset.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    String itemId = event.getButton().getData().toString();
                    KPIItem item = KPIItemFacade.getKPIItemService().findById(itemId);
                    final KPIItem newkpiitem = new KPIItem.Builder(item.getShortDescription())
                            .kpiitem(item)
                            .editTargets(null)
                            .weighting(null)
                            .build();
                    KPIItemFacade.getKPIItemService().merge(newkpiitem);
                    getHome();
                }
            });
            addItem(new Object[]{
                item.getShortDescription(),
                "N/A",
                item.getEditTargets(),
                item.getUom(),
                "N/A",
                item.getWeighting(),
                item.getMeasureType(),
                edit,
                reset,}, item.getId());
        }
    }
    
    private void getHome() {
        main.content.setSecondComponent(new KPIMenu(main, "TARGET"));
    }
}
