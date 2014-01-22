/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.fleet;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.fleet.OperatingCostService;

/**
 *
 * @author Ferox
 */
public class OperatingCostFacade {

    private final static SpringContext ctx = new SpringContext();

    public static OperatingCostService getOperatingCostService() {
        return ctx.getBean(OperatingCostService.class);
    }
}
