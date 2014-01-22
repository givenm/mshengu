/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.procurement.invoices.views;

import com.vaadin.ui.VerticalLayout;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.procurement.invoices.form.PaymentForm;

/**
 *
 * @author Luckbliss
 */
public class PaymentTab extends VerticalLayout{
    private PaymentForm form;
    private MshenguMain main;

    public PaymentTab(MshenguMain main) {
        setSizeFull();
        form = new PaymentForm();
        this.main = main;
        addComponent(form);
    }
}
