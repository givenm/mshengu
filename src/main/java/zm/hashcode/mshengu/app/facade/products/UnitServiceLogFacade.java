/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.products;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.products.UnitServiceLogService;

/**
 *
 * @author Luckbliss
 */
public class UnitServiceLogFacade {

    private final static SpringContext ctx = new SpringContext();

    public static UnitServiceLogService getUnityServiceLogService() {
        return ctx.getBean(UnitServiceLogService.class);
    }
}
