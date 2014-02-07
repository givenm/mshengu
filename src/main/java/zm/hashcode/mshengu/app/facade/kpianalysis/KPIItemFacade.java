/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.kpianalysis;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.kpianalysis.KPIItemService;

/**
 *
 * @author Luckbliss
 */
public class KPIItemFacade {
    private final static SpringContext ctx = new SpringContext();

    public static KPIItemService getKPIItemService() {
        return ctx.getBean(KPIItemService.class);
    }
}
