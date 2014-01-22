/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.procurement;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.procurement.QuoteNumberService;

/**
 *
 * @author Luckbliss
 */
public class QuoteNumberFacade {

    private final static SpringContext ctx = new SpringContext();

    public static QuoteNumberService getQuoteNumberService() {
        return ctx.getBean(QuoteNumberService.class);
    }
}
