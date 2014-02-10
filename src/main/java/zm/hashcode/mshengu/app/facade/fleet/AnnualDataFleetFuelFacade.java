/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.fleet;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.fleet.AnnualDataFleetFuelService;

/**
 *
 * @author ColinWa
 */
public class AnnualDataFleetFuelFacade {

    private final static SpringContext ctx = new SpringContext();

    public static AnnualDataFleetFuelService getAnnualDataService() {
        return ctx.getBean(AnnualDataFleetFuelService.class);
    }
}
