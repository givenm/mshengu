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
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.form.PaymentForm;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.table.PaymentTable;

/**
 *
 * @author Luckbliss
 */
public class PaymentTab extends VerticalLayout implements Property.ValueChangeListener {

    private PaymentForm form;
    private PaymentTable table;
    private MshenguMain main;

    public PaymentTab(MshenguMain main) {
        setSizeFull();
        form = new PaymentForm();
        table = new PaymentTable();
        this.main = main;
        addComponent(form);
        addComponent(table);
        addListeners();
        getGrandTotal();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == form.month) {
            getValues();
        } else if (property == form.year) {
            getValues();
        }
    }

    private void getValues() {
        if (form.month.getValue().toString().equalsIgnoreCase("all")) {
            form.year.setReadOnly(true);
            table.removeAllItems();
            table.loadTable(null, "all");
            getGrandTotal();
            form.currentdate.setValue("All Outstanding Amounts");
        } else if (form.month.getValue() != null && form.year.getValue() != null) {
            form.year.setReadOnly(false);
            table.removeAllItems();
            String month = form.month.getValue().toString();
            String year = form.year.getValue().toString();
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.MONTH, getMonth(month));
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            table.loadTable(cal.getTime(), null);
            getGrandTotal();
            getCurrentDate();
        } else {
            form.year.setReadOnly(false);
            Notification.show("Enter all values", Notification.Type.TRAY_NOTIFICATION);
        }
    }

    private int getMonth(String month) {
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i]))
            return i;
        }
        return 0;
    }

    private void addListeners() {
        //Register Button Listeners;
        form.month.addValueChangeListener((Property.ValueChangeListener) this);
        form.year.addValueChangeListener((Property.ValueChangeListener) this);
    }

    public void getGrandTotal() {
        form.grandTotal.setValue(form.total + table.getGrandTotal());
    }

    public void getCurrentDate() {
        form.currentdate.setValue(form.month.getValue().toString() + " " + form.year.getValue().toString());
    }
}
