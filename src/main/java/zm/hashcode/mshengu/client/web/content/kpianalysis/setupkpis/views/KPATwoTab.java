/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIFacade;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIItemFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis.KPIMenu;
import zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.forms.KPIForm;
import zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.models.KPIBean;
import zm.hashcode.mshengu.client.web.content.kpianalysis.setupkpis.tables.KPITable;
import zm.hashcode.mshengu.domain.kpianalysis.KPI;
import zm.hashcode.mshengu.domain.kpianalysis.KPIItem;

/**
 *
 * @author Luckbliss
 */
public class KPATwoTab extends VerticalLayout implements
        Button.ClickListener {

    private final MshenguMain main;
    private final KPITable table;
    private final KPIForm form;
    private KPI kpi;
    private int counter = 0;

    public KPATwoTab(MshenguMain app) {
        main = app;
        kpi = getKpi();
        table = new KPITable(getKPIItems(kpi), main);
        table.getTab(kpi.getTab(), this);
        form = new KPIForm(main, kpi);
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
        updateMessage();
    }
    
     public String getKPAName(){
        return kpi.getName();
    }

    private KPI getKpi() {
        KPI kpi = KPIFacade.getKPIService().findByTab("two");
        if (kpi != null) {
            return kpi;
        } else {
            List<KPIItem> items = getItems();
            KPI newkpi = new KPI.Builder("empty")
                    .tab("two")
                    .items(items)
                    .build();
            KPIFacade.getKPIService().persist(newkpi);
            return newkpi;
        }
    }

    private List<KPIItem> getItems() {
        List<KPIItem> items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int number = i + 1;
            KPIItem kPIItem = new KPIItem.Builder("empty")
                    .detailedDescription("empty")
                    .measureType("empty")
                    .uom("empty")
                    .kpiNumber(number)
                    .build();
            KPIItemFacade.getKPIItemService().persist(kPIItem);
            items.add(kPIItem);
        }
        return items;
    }

    private List<KPIItem> getKPIItems(KPI kpi) {
        for (KPIItem item : kpi.getItems()) {
            if (!item.getShortDescription().equalsIgnoreCase("empty")) {
                counter += 1;
            }
        }
        return kpi.getItems();
    }

    private void updateMessage() {
        form.message.setValue(counter + " " + form.msg);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.edit) {
            edit();
        } else if (source == form.update) {
            saveForm(form.binder);
        } else if (source == form.cancel) {
            getHome();
        }
    }

    private void saveForm(FieldGroup binder) {
        try {
            binder.commit();
            KPIFacade.getKPIService().merge(getEntity(binder));
            getHome();
            Notification.show("Record Updated!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private KPI getEntity(FieldGroup binder) {
        final KPIBean bean = ((BeanItem<KPIBean>) binder.getItemDataSource()).getBean();

        final KPI newkpi = new KPI.Builder(bean.getName())
                .kpi(kpi)
                .build();
        return newkpi;
    }

    private void edit() {
        form.update.setVisible(true);
        form.edit.setVisible(false);
        form.name.setReadOnly(false);
    }

    private void getHome() {
        main.content.setSecondComponent(new KPIMenu(main, "SETUP"));
    }

    private void addListeners() {
        form.edit.addClickListener((Button.ClickListener) this);
        form.update.addClickListener((Button.ClickListener) this);
        form.cancel.addClickListener((Button.ClickListener) this);
    }
}
