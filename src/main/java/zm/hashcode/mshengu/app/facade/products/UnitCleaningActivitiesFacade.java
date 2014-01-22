/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.products;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.products.UnitCleaningActivitiesService;

/**
 *
 * @author Luckbliss
 */
public class UnitCleaningActivitiesFacade {

    private final static SpringContext ctx = new SpringContext();

    public static UnitCleaningActivitiesService getUnitCleaningActivitiesService() {
        return ctx.getBean(UnitCleaningActivitiesService.class);
    }
}
