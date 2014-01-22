/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.procurement;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.procurement.ResponseToRFQService;

/**
 *
 * @author Luckbliss
 */
public class ResponseToRFQFacade {

    private final static SpringContext ctx = new SpringContext();

    public static ResponseToRFQService getResponseToRFQService() {
        return ctx.getBean(ResponseToRFQService.class);
    }
}
