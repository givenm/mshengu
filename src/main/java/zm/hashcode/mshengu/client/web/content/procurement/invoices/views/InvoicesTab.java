/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import zm.hashcode.mshengu.app.facade.procurement.RequestFacade;
import zm.hashcode.mshengu.app.facade.serviceproviders.ServiceProviderFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.form.InvoicesForm;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.table.InvoiceTable;
import zm.hashcode.mshengu.domain.procurement.Request;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author Luckbliss
 */
public class InvoicesTab extends VerticalLayout implements Property.ValueChangeListener {

    private InvoicesForm form;
    private MshenguMain main;
    private InvoiceTable table;
    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();

    public InvoicesTab(MshenguMain main) {
        setSizeFull();
        form = new InvoicesForm();
        table = new InvoiceTable(this, main);
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
            final ServiceProvider serviceProvider = ServiceProviderFacade.getServiceProviderService().findById(supplierId);
            // Reposotory get Requests with InvoiceNum notNull for Specified Date for Service Provider
            List<Request> requests = RequestFacade.getRequestService().getTransactedRequestsByServiceProviderByMonth(serviceProvider, dateTimeFormatHelper.getDate(Integer.parseInt(year), Integer.parseInt(month)));
            table.loadTable(requests, month, year);
            getGrandTotal();
        } else {
            Notification.show("Enter all values", Notification.Type.TRAY_NOTIFICATION);

        }
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
