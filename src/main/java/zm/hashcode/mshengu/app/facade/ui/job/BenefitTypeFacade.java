/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.job;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.job.BenefitTypeService;

/**
 *
 * @author lucky
 */
public class BenefitTypeFacade {

    private final static SpringContext ctx = new SpringContext();
    public static BenefitTypeService getBenefitTypeService() {
        return ctx.getBean(BenefitTypeService.class);
    }
}
