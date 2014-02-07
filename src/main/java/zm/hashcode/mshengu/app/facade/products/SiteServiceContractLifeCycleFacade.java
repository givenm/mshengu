/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.products;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.products.SiteServiceContractLifeCycleService;

/**
 *
 * @author Luckbliss
 */
public class SiteServiceContractLifeCycleFacade {

    private final static SpringContext ctx = new SpringContext();

    public static SiteServiceContractLifeCycleService getSiteServiceContractService() {
        return ctx.getBean(SiteServiceContractLifeCycleService.class);
    }
}
