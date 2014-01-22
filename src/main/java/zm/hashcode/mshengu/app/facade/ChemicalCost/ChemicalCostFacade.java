/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ChemicalCost;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.chemical.ChemicalCostService;

/**
 *
 * @author geek
 */
public class ChemicalCostFacade {

    private final static SpringContext ctx = new SpringContext();

    public static ChemicalCostService getChemicalCostService() {
        return ctx.getBean(ChemicalCostService.class);
    }
}
