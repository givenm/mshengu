/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.PurchaseMenu;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.form.ApprovedRequestsForm;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.table.ApprovedRequestsTable;
import zm.hashcode.mshengu.domain.procurement.Request;

/**
 *
 * @author Luckbliss
 */
public class ApprovedRequestsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final MshenguMain main;
    private ApprovedRequestsTable table;
    private ApprovedRequestsForm form;

    public ApprovedRequestsTab(MshenguMain app) {
        table = new ApprovedRequestsTable(app, this);
        form = new ApprovedRequestsForm();
        this.main = app;
        setSizeFull();
        addComponent(form);
        addComponent(table);
        addListeners();
    }

    public void clearTab() {
        table = new ApprovedRequestsTable(main, this);
        setSizeFull();
        addComponent(table);
        getHome();
    }

    private void getHome() {
        main.content.setSecondComponent(new PurchaseMenu(main, "APPROVED_REQUESTS"));
    }

    private void getValues() {
        if (form.month.getValue() != null && form.year.getValue() != null && form.supplier.getValue() != null) {
            String supplierId = form.supplier.getValue().toString();
            table.removeAllItems();
            String month = form.month.getValue().toString();
            String year = form.year.getValue().toString();
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.MONTH, getMonth(month));
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            // Reposotory get Requests with InvoiceNum notNull for Specified Date for Service Provider
            List<Request> requests = null;
            if (supplierId.equalsIgnoreCase("all")) {
                requests = RequestFacade.getRequestService().getApprovedRequests(cal.getTime());
            } else {

                requests = RequestFacade.getRequestService().getApprovedRequestsBySupplier(supplierId, cal.getTime());
            }
            table.loadTable(requests);
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
        form.supplier.addValueChangeListener((Property.ValueChangeListener) this);
        form.month.addValueChangeListener((Property.ValueChangeListener) this);
        form.year.addValueChangeListener((Property.ValueChangeListener) this);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
