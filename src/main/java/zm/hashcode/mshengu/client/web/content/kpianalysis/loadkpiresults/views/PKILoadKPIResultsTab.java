/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.views;

import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import zm.hashcode.mshengu.app.facade.kpianalysis.KPIFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis.KPIMenu;
import zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.forms.LoadResultsForm;
import zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.tables.LoadResultsTable;
import zm.hashcode.mshengu.domain.kpianalysis.KPI;
import zm.hashcode.mshengu.domain.kpianalysis.KPIItem;
import zm.hashcode.mshengu.domain.kpianalysis.KPIValues;

/**
 *
 * @author Ferox
 */
public class PKILoadKPIResultsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private LoadResultsTable table;
    private LoadResultsForm form;

    public PKILoadKPIResultsTab(MshenguMain app) {
        main = app;
        form = new LoadResultsForm();
        table = new LoadResultsTable(main);
        setSizeFull();
        addComponent(form);
        addComponent(new Label("<br>", ContentMode.HTML));
        addComponent(table);
        addListeners();
    }

    private void getValues() {
        if (form.frommonth.getValue() != null && form.fromyear.getValue() != null
                && form.tomonth.getValue() != null && form.toyear.getValue() != null) {
            String frommonth = form.frommonth.getValue().toString();
            String fromyear = form.fromyear.getValue().toString();
            String tomonth = form.tomonth.getValue().toString();
            String toyear = form.toyear.getValue().toString();
        } else {
            Notification.show("Enter all values", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private List<KPI> getKPIItems(String frommonth, String fromyear, String tomonth, String toyear) {
        List<KPI> items = KPIFacade.getKPIService().findAll();
        for(KPI kpi: items){
            List<KPIItem> kPIItems = kpi.getItems();
            for(KPIItem kPIItem: kPIItems){
                List<KPIValues> values = kPIItem.getValues();
//                Range
            }
        }
        return items;
    }

    private void addListeners() {
        //Register Button Listeners;
        form.frommonth.addValueChangeListener((Property.ValueChangeListener) this);
        form.tomonth.addValueChangeListener((Property.ValueChangeListener) this);
        form.toyear.addValueChangeListener((Property.ValueChangeListener) this);
        form.fromyear.addValueChangeListener((Property.ValueChangeListener) this);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == form.frommonth) {
            getValues();
        } else if (property == form.fromyear) {
            getValues();
        } else if (property == form.tomonth) {
            getValues();
        } else if (property == form.toyear) {
            getValues();
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new KPIMenu(main, "LOAD_KPI"));
    }
}