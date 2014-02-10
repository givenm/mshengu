/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.fleet;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.fleet.TruckService;

/**
 *
 * @author Luckbliss
 */
public class TruckFacade {

    private final static SpringContext ctx = new SpringContext();

    public static TruckService getTruckService() {
        return ctx.getBean(TruckService.class);
    }
}
