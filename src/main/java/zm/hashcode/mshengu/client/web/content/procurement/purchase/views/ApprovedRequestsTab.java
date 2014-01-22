/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.purchase.views;

import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.PurchaseMenu;
import zm.hashcode.mshengu.client.web.content.procurement.purchase.table.ApprovedRequestsTable;

/**
 *
 * @author Luckbliss
 */
public class ApprovedRequestsTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private static MshenguMain main;
    private ApprovedRequestsTable table;

    public ApprovedRequestsTab(MshenguMain app) {
        table = new ApprovedRequestsTable(this);
        ApprovedRequestsTab.main = app;
        setSizeFull();
        addComponent(table);
    }

    public void clearTab() {
        table = new ApprovedRequestsTable(this);
        setSizeFull();
        addComponent(table);
        getHome();
    }

    private void getHome() {
        main.content.setSecondComponent(new PurchaseMenu(main, "APPROVED_REQUESTS"));
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
