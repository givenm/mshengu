/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.setup.officeutils.paymentmethod;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.setup.officeutils.paymentmethod.views.PaymentMethodTab;

/**
 *
 * @author Ferox
 */
public class PaymentMethodMenu extends VerticalLayout {

    private MshenguMain main;
    private TabSheet tab;

    public PaymentMethodMenu(MshenguMain app, String selectedTab) {
        main = app;

         VerticalLayout paymentMethodTab = new VerticalLayout();
        paymentMethodTab.setMargin(true);
        paymentMethodTab.addComponent(new PaymentMethodTab(main));

        tab = new TabSheet();
        tab.setHeight("100%");
        tab.setWidth("100%");
        tab.addTab(paymentMethodTab, "Payment Method", null); 
    
        
        if (selectedTab.equals("LANDING")) {
            tab.setSelectedTab(paymentMethodTab);
        } 
        addComponent(tab);
    }
}