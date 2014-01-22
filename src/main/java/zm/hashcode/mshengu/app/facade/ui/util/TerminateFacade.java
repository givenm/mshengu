/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.util;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.util.TerminateService;

/**
 *
 * @author Luckbliss
 */
public class TerminateFacade {

    private final static SpringContext ctx = new SpringContext();

    public static TerminateService getTerminateService() {
        return ctx.getBean(TerminateService.class);
    }
}
