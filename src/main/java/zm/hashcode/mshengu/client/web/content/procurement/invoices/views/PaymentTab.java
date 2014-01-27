/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
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
        if (form.month.getValue() != null && form.year.getValue() != null) {
            table.removeAllItems();
            String month = form.month.getValue().toString();
            String year = form.year.getValue().toString();
            table.loadTable(month, year);
            getGrandTotal();
        } else {
            Notification.show("Enter all values", Notification.Type.TRAY_NOTIFICATION);

        }
    }

    private void addListeners() {
        //Register Button Listeners;
        form.month.addValueChangeListener((Property.ValueChangeListener) this);
        form.year.addValueChangeListener((Property.ValueChangeListener) this);
    }

    public void getGrandTotal() {
        form.grandTotal.setValue(form.total + table.getGrandTotal());
    }
}
