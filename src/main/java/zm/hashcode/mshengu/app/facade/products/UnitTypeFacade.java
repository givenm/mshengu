/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.products;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.products.UnitTypeService;

/**
 *
 * @author Luckbliss
 */
public class UnitTypeFacade {

    private final static SpringContext ctx = new SpringContext();

    public static UnitTypeService getUnitTypeService() {
        return ctx.getBean(UnitTypeService.class);
    }
}
