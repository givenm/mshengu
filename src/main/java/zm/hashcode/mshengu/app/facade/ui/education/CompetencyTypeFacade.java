/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.education;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.education.CompetencyTypeService;

/**
 *
 * @author lucky
 */
public class CompetencyTypeFacade {
    private final static SpringContext ctx = new SpringContext();
     public static CompetencyTypeService getCompetencyTypeService(){
         return ctx.getBean(CompetencyTypeService.class);
     }  
}
