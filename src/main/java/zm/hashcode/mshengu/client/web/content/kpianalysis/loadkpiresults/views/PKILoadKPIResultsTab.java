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
import javax.swing.JOptionPane;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.kpianalysis.kpianalysis.KPIMenu;
import zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.forms.LoadResultsForm;
import zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.models.LoadAllKPIResults;
import zm.hashcode.mshengu.client.web.content.kpianalysis.loadkpiresults.tables.LoadResultsTable;

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
        if (form.fromdate.getValue() != null && form.todate.getValue() != null) {
//            LoadAllKPIResults results = new LoadAllKPIResults(form.fromdate.getValue(), form.todate.getValue());
//            results.getAllKPIItems();
        } else {
            Notification.show("Enter all values", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private void addListeners() {
        //Register Button Listeners;
        form.fromdate.addValueChangeListener((Property.ValueChangeListener) this);
        form.todate.addValueChangeListener((Property.ValueChangeListener) this);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == form.fromdate) {
            getValues();
        } else if (property == form.todate) {
            getValues();
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new KPIMenu(main, "LOAD_KPI"));
    }
}