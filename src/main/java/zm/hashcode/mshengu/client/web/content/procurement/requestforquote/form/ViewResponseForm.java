/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import zm.hashcode.mshengu.app.facade.procurement.RequestForQuoteFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.RFQMenu;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.table.ViewResponseTable;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.views.RFQListTab;
import zm.hashcode.mshengu.domain.procurement.RequestForQuote;

/**
 *
 * @author Luckbliss
 */
public class ViewResponseForm extends FormLayout {

    public Button cancel = new Button("Cancel");
    public final MshenguMain main;

    public ViewResponseForm(MshenguMain main, final RFQListTab tab, String rfqId) {
        setSizeFull();
        this.main = main;
        cancel.setSizeFull();
        GridLayout layout = new GridLayout(3, 4);
        layout.setSizeFull();

        ViewResponseTable table = new ViewResponseTable(main, tab, rfqId, this);

        layout.addComponent(table, 0, 0, 2, 0);
        layout.addComponent(new Label("<br>", ContentMode.HTML), 0, 1);
        layout.addComponent(cancel, 0, 2, 2, 2);

        addComponent(layout);
        cancel();
    }

    private void cancel() {
        cancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getHome();
            }
        });
    }

    private void getHome() {
        main.content.setSecondComponent(new RFQMenu(main, "LIST_RFQ"));
    }
}
