/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.serviceproviders;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.external.IncomingRFQService;

/**
 *
 * @author Ferox
 */
public class QuoteRequestsFacade {

    private final static SpringContext ctx = new SpringContext();

    public static IncomingRFQService getQuoteRequestService() {
        return ctx.getBean(IncomingRFQService.class);
    }
}
