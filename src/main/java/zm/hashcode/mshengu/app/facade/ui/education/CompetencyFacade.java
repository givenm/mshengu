/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.education;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.education.CompetencyService;

/**
 *
 * @author lucky
 */
public class CompetencyFacade {
    private final static SpringContext ctx = new SpringContext();
     public static CompetencyService getCompetencyService(){
         return ctx.getBean(CompetencyService.class);
     }  
}
