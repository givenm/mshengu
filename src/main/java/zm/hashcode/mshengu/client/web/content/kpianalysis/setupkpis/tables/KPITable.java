/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.tables;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Reindeer;
import java.util.List;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIItemFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis.KPIMenu;
import zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.forms.KPIItemForm;
import zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.views.KPAFiveTab;
import zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.views.KPAFourTab;
import zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.views.KPAOneTab;
import zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.views.KPAThreeTab;
import zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.views.KPATwoTab;
import zm.hashcode.mshengu.domain.kpianalysis.KPIItem;

/**
 *
 * @author Luckbliss
 */
public class KPITable extends Table {

    private final MshenguMain main;
    private KPAOneTab oneTab = null;
    private KPATwoTab twoTab = null;
    private KPAThreeTab threeTab = null;
    private KPAFourTab fourTab = null;
    private KPAFiveTab fiveTab = null;
    private String kpa;

    public KPITable(List<KPIItem> items, MshenguMain main) {
        this.main = main;
        setSizeFull();

        addContainerProperty("KPI Description", String.class, null);
        addContainerProperty("UOM", String.class, null);
        addContainerProperty("Measure Type", String.class, null);
        addContainerProperty("Edit", Button.class, null);
        addContainerProperty("Delete", Button.class, null);

        loadTable(items);
    }

    public final void loadTable(List<KPIItem> items) {
        for (KPIItem item : items) {
            Button edit = new Button("Edit");
            edit.setData(item.getId());
            edit.setStyleName(Reindeer.BUTTON_LINK);
            edit.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    saveEdit(event);
                }
            });
            Button delete = new Button("Delete");
            delete.setData(item.getId());
            delete.setStyleName(Reindeer.BUTTON_LINK);
            delete.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    saveDelete(event);
                }
            });
            addItem(new Object[]{
                item.getShortDescription(),
                item.getUom(),
                item.getMeasureType(),
                edit,
                delete,}, item.getId());
        }
    }

    private void saveEdit(Button.ClickEvent event) {
        String itemId = event.getButton().getData().toString();
        displayForm(itemId);
    }

    private void saveDelete(Button.ClickEvent event) {
        String itemId = event.getButton().getData().toString();
        KPIItem item = KPIItemFacade.getKPIItemService().findById(itemId);
        KPIItem kPIItem = new KPIItem.Builder("empty")
                .id(item.getId())
                .detailedDescription("empty")
                .measureType("empty")
                .uom("empty")
                .build();
        KPIItemFacade.getKPIItemService().merge(kPIItem);
        getHome();
    }

    private void getHome() {
        main.content.setSecondComponent(new KPIMenu(main, "SETUP"));
    }

    public void getTab(String name, Object object) {
        if (name.equalsIgnoreCase("one")) {
            oneTab = (KPAOneTab) object;
        } else if (name.equalsIgnoreCase("two")) {
            twoTab = (KPATwoTab) object;
        } else if (name.equalsIgnoreCase("three")) {
            threeTab = (KPAThreeTab) object;
        } else if (name.equalsIgnoreCase("four")) {
            fourTab = (KPAFourTab) object;
        } else if (name.equalsIgnoreCase("five")) {
            fiveTab = (KPAFiveTab) object;
        }
    }

    public void displayForm(String itemId) {
        KPIItem item = KPIItemFacade.getKPIItemService().findById(itemId);
        KPIItemForm kPIItemForm = null;
        if (oneTab != null) {
            kPIItemForm = new KPIItemForm(main, item, oneTab.getKPAName());
            oneTab.removeAllComponents();
            oneTab.addComponent(kPIItemForm);
        } else if (twoTab != null){
            kPIItemForm = new KPIItemForm(main, item, twoTab.getKPAName());
            twoTab.removeAllComponents();
            twoTab.addComponent(kPIItemForm);
        } else if (threeTab != null){
            kPIItemForm = new KPIItemForm(main, item, threeTab.getKPAName());
            threeTab.removeAllComponents();
            threeTab.addComponent(kPIItemForm);
        } else if (fourTab != null){
            kPIItemForm = new KPIItemForm(main, item, fourTab.getKPAName());
            fourTab.removeAllComponents();
            fourTab.addComponent(kPIItemForm);
        } else if (fiveTab != null){
            kPIItemForm = new KPIItemForm(main, item, fiveTab.getKPAName());
            fiveTab.removeAllComponents();
            fiveTab.addComponent(kPIItemForm);
        }
    }
}
