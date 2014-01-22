/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.customer;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.customer.InvoiceService;

/**
 *
 * @author Luckbliss
 */
public class InvoiceFacade {

    private final static SpringContext ctx = new SpringContext();

    public static InvoiceService getInvoiceService() {
        return ctx.getBean(InvoiceService.class);
    }
}
