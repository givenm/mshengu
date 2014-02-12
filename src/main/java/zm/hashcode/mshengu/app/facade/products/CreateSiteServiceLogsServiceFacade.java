
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.products;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.fieldservices.CreateSiteServiceLogsService;

/**
 *
 * @author Luckbliss
 */
public class CreateSiteServiceLogsServiceFacade {

    private final static SpringContext ctx = new SpringContext();

    public static CreateSiteServiceLogsService getCreateSiteServiceLogsService() {
        return ctx.getBean(CreateSiteServiceLogsService.class);
    }
}
