/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote.views;

import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.table.ViewQuoteTable;

/**
 *
 * @author Luckbliss
 */
public class RFQListTab extends VerticalLayout {

    private ViewQuoteTable table;
    public MshenguMain main;

    public RFQListTab(MshenguMain main) {
        this.main = main;
        setSizeFull();
        table = new ViewQuoteTable(main, this);
        addComponent(table);
    }
}
