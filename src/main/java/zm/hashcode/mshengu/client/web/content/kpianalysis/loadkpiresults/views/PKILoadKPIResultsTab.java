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
            table.loadTable(getKPIItems(frommonth, fromyear, tomonth, toyear));
        } else {
            Notification.show("Enter all values", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private List<KPI> getKPIItems(String frommonth, String fromyear, String tomonth, String toyear) {
        List<KPI> items = KPIFacade.getKPIService().findAll();
        List<KPI> newItems = new ArrayList<>();
        for (KPI kpi : items) {
            List<KPIItem> kPIItems = kpi.getItems();
            List<KPIItem> kpiis = new ArrayList<>();
            for (KPIItem kPIItem : kPIItems) {
                List<KPIValues> values = kPIItem.getValues();
                List<KPIValues> kpivs = new ArrayList<>();
                for (KPIValues value : values) {
                    if (Integer.parseInt(fromyear) >= value.getYear() && Integer.parseInt(toyear) <= value.getYear()) {
                        for (int i = Integer.parseInt(fromyear); i <= Integer.parseInt(toyear); i++) {
                            if (i == Integer.parseInt(fromyear)) {
                                if (getMonthNumber(frommonth) >= getMonthNumber(value.getMonth())) {
                                    kpivs.add(value);
                                }
                            } else if (i > Integer.parseInt(fromyear) && i < Integer.parseInt(fromyear)) {
                                kpivs.add(value);
                            } else if (i == Integer.parseInt(toyear)) {
                                if(getMonthNumber(value.getMonth()) < getMonthNumber(tomonth)){
                                    kpivs.add(value);
                                }
                            }
                        }
                    }
                }
                if(!kpivs.isEmpty()){
                    kpiis.add(kPIItem);
                }
            }
            if(!kpiis.isEmpty()){
                newItems.add(kpi);
            }
        }
        return newItems;
    }

    private int getMonthNumber(String month) {
        int number = 0;
        if (month.equals("January")) {
            number = 1;
        } else if (month.equals("February")) {
            number = 2;
        } else if (month.equals("March")) {
            number = 3;
        } else if (month.equals("April")) {
            number = 4;
        } else if (month.equals("May")) {
            number = 5;
        } else if (month.equals("June")) {
            number = 6;
        } else if (month.equals("July")) {
            number = 7;
        } else if (month.equals("August")) {
            number = 8;
        } else if (month.equals("Septenber")) {
            number = 9;
        } else if (month.equals("October")) {
            number = 10;
        } else if (month.equals("November")) {
            number = 11;
        } else if (month.equals("December")) {
            number = 12;
        }
        return number;
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