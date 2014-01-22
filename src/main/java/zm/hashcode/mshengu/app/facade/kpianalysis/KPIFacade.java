/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.kpianalysis;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.kpianalysis.KPIService;

/**
 *
 * @author Luckbliss
 */
public class KPIFacade {
    private final static SpringContext ctx = new SpringContext();

    public static KPIService getKPIService() {
        return ctx.getBean(KPIService.class);
    }
}
