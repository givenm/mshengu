/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.util;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.util.CountryService;

/**
 *
 * @author lucky
 */
public class CountryFacade {

    private final static SpringContext ctx = new SpringContext();

    public static CountryService getCountryService() {
        return ctx.getBean(CountryService.class);
    }
}
