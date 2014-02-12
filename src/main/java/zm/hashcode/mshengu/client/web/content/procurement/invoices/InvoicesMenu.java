/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.views.InvoicePaidTab;
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
    private InvoicePaidTab invoiceMismatchTab;

    public InvoicesMenu(MshenguMain app, String selectedTab) {
        main = app;
        invoicesTab = new InvoicesTab(app);
        paymentTab = new PaymentTab(app);
        invoiceMismatchTab = new InvoicePaidTab(app);
        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(invoicesTab, "Invoices For Payment", null);
        tab.addTab(invoiceMismatchTab, "Invoices Paid", null);
        tab.addTab(paymentTab, "Suppliers Payments Due", null);
        addComponent(tab);

        switch (selectedTab) {
            case "LANDING":
                tab.setSelectedTab(invoicesTab);
                break;
            case "PAID":
                tab.setSelectedTab(invoiceMismatchTab);
                break;
            case "PAYMENT":
                tab.setSelectedTab(paymentTab);
                break;
        }
    }
}
