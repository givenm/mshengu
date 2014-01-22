/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.products;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.products.SiteUnitService;

/**
 *
 * @author Luckbliss
 */
public class SiteUnitFacade {

    private final static SpringContext ctx = new SpringContext();

    public static SiteUnitService getSiteUnitService() {
        return ctx.getBean(SiteUnitService.class);
    }
}
