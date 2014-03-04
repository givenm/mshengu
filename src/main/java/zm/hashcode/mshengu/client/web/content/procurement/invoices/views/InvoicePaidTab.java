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
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.form.InvoicePaidForm;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.table.InvoicePaidTable;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class InvoicePaidTab extends VerticalLayout implements Property.ValueChangeListener {

    private InvoicePaidForm form;
    private MshenguMain main;
    private InvoicePaidTable table;

    public InvoicePaidTab(MshenguMain main) {
        setSizeFull();
        form = new InvoicePaidForm();
        table = new InvoicePaidTable(this, main);
        this.main = main;
        addComponent(form);
        addComponent(table);
        addListeners();
        getGrandTotal();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == form.supplier) {
            getValues();
        } else if (property == form.month) {
            getValues();
        } else if (property == form.year) {
            getValues();
        }
    }

    private void getValues() {
        if (form.month.getValue() != null && form.year.getValue() != null && form.supplier.getValue() != null) {
            String supplierId = form.supplier.getValue().toString();
            table.removeAllItems();
            String month = form.month.getValue().toString();
            String year = form.year.getValue().toString();
            List<Request> requests = null;
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.MONTH, getMonth(month));
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            if (supplierId.equalsIgnoreCase("all")) {
                requests = RequestFacade.getRequestService().getProcessedRequestsWithPaymentDate(cal.getTime());
            } else {
                requests = RequestFacade.getRequestService().getServiceProviderProcessedRequestsWithPaymentDate(supplierId, cal.getTime());
            }
            table.loadTable(requests);
            getGrandTotal();
        } else {
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
        //Register Button Listeners
        form.supplier.addValueChangeListener((Property.ValueChangeListener) this);
        form.month.addValueChangeListener((Property.ValueChangeListener) this);
        form.year.addValueChangeListener((Property.ValueChangeListener) this);
    }

    public void getGrandTotal() {
        form.mtdTotal.setValue(form.total + table.getGrandTotal());
    }
}
