/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.products;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.products.SiteServiceLogService;

/**
 *
 * @author Luckbliss
 */
public class SiteServiceLogFacade {

    private final static SpringContext ctx = new SpringContext();

    public static SiteServiceLogService getSiteServiceLogService() {
        return ctx.getBean(SiteServiceLogService.class);
    }
}
