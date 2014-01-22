/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.fleet;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.fleet.OperationalAllowanceService;

/**
 *
 * @author geek
 */
public class OperationalAllowanceFacade {

    private final static SpringContext ctx = new SpringContext();

    public static OperationalAllowanceService getOperationalAllowanceService() {
        return ctx.getBean(OperationalAllowanceService.class);
    }
}
