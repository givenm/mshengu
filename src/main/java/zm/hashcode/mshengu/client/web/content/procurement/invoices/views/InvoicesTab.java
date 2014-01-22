/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.views;

import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.form.InvoicesForm;
import zm.hashcode.mshengu.client.web.content.procurement.requestforquote.form.RFQForm;

/**
 *
 * @author Luckbliss
 */
public class InvoicesTab extends VerticalLayout {

    private InvoicesForm form;
    private MshenguMain main;

    public InvoicesTab(MshenguMain main) {
        setSizeFull();
        form = new InvoicesForm();
        this.main = main;
        addComponent(form);
    }
}
