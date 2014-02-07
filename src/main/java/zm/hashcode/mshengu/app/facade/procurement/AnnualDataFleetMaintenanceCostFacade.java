/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.procurement;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.procurement.AnnualDataFleetMaintenanceCostService;

/**
 *
 * @author Colin
 */
public class AnnualDataFleetMaintenanceCostFacade {

    private final static SpringContext ctx = new SpringContext();

    public static AnnualDataFleetMaintenanceCostService getAnnualDataFleetMaintenanceCostService() {
        return ctx.getBean(AnnualDataFleetMaintenanceCostService.class);
    }
}
