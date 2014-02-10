/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.products;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.products.MobileAppService;

/**
 *
 * @author Luckbliss
 */
public class MobileAppFacade {

    private final static SpringContext ctx = new SpringContext();

    public static MobileAppService getMobileService() {
        return ctx.getBean(MobileAppService.class);
    }
}
