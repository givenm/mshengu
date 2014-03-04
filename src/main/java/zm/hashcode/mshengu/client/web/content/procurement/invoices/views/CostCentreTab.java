/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.form.CostCentreForm;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.table.CostCentreTable;

/**
 *
 * @author Luckbliss
 */
public class CostCentreTab extends VerticalLayout implements Property.ValueChangeListener {

    private CostCentreForm form;
    private MshenguMain main;
    private CostCentreTable table;

    public CostCentreTab(MshenguMain main) {
        setSizeFull();
        form = new CostCentreForm();
        table = new CostCentreTable();
        this.main = main;
        addComponent(form);
        addComponent(table);
        addListeners();
        getGrandTotal();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == form.costcentre) {
            getValues();
        } else if (property == form.month) {
            getValues();
        } else if (property == form.year) {
            getValues();
        }
    }

    private void getValues() {
        if (form.month.getValue() != null && form.year.getValue() != null && form.costcentre.getValue() != null) {
            String costcentreId = form.costcentre.getValue().toString();
            table.removeAllItems();
            String month = form.month.getValue().toString();
            String year = form.year.getValue().toString();
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.MONTH, getMonth(month));
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            table.loadTable(costcentreId, cal.getTime());
            getGrandTotal();
            getCurrentDate();
        } else {
            Notification.show("Enter all values", Notification.Type.TRAY_NOTIFICATION);

        }
    }

    private int getMonth(String month) {
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i])) {
                return i;
            }
        }
        return 0;
    }

    private void addListeners() {
        //Register Button Listeners
        form.costcentre.addValueChangeListener((Property.ValueChangeListener) this);
        form.month.addValueChangeListener((Property.ValueChangeListener) this);
        form.year.addValueChangeListener((Property.ValueChangeListener) this);
    }

    public void getGrandTotal() {
        form.mtdTotal.setValue(form.total + table.getGrandTotal());
    }

    public void getCurrentDate() {
        form.currentdate.setValue(form.month.getValue().toString() + " " + form.year.getValue().toString());
    }
}
