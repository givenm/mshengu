/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.views.InvoiceMismatchTab;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.views.InvoicesTab;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.views.PaymentTab;

/**
 *
 * @author Luckbliss
 */
public class InvoicesMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;
    private InvoicesTab invoicesTab;
    private PaymentTab paymentTab;
    private InvoiceMismatchTab invoiceMismatchTab;

    public InvoicesMenu(MshenguMain app, String selectedTab) {
        main = app;
        invoicesTab = new InvoicesTab(app);
        paymentTab = new PaymentTab(app);
        invoiceMismatchTab = new InvoiceMismatchTab(app);
        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(invoicesTab, "Invoices For Payment", null);
        tab.addTab(paymentTab, "Payments Due", null);
        tab.addTab(invoiceMismatchTab, "Mismatched Invoices", null);
        addComponent(tab);
    }
}
