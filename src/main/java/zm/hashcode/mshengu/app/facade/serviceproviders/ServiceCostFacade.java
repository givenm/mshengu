/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.serviceproviders;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.fleet.ServiceCostService;

/**
 *
 * @author Ferox
 */
public class ServiceCostFacade {

    private final static SpringContext ctx = new SpringContext();

    public static ServiceCostService getServiceCostService() {
        return ctx.getBean(ServiceCostService.class);
    }
}
