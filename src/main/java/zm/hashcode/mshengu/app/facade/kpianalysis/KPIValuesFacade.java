/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.kpianalysis;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.kpianalysis.KPIValuesService;

/**
 *
 * @author Luckbliss
 */
public class KPIValuesFacade {
    private final static SpringContext ctx = new SpringContext();

    public static KPIValuesService getKPIValuesService() {
        return ctx.getBean(KPIValuesService.class);
    }
}
