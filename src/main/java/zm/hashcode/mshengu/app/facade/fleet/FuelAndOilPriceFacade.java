/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.fleet;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.fleet.FuelAndOilPriceService;

/**
 *
 * @author geek
 */
public class FuelAndOilPriceFacade {

    private final static SpringContext ctx = new SpringContext();

    public static FuelAndOilPriceService getFuelAndOilPriceService() {
        return ctx.getBean(FuelAndOilPriceService.class);
    }
}
