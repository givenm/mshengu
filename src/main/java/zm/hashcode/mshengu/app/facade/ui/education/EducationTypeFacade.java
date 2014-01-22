/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.education;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.education.EducationTypeService;

/**
 *
 * @author lucky
 */
public class EducationTypeFacade {
    private final static SpringContext ctx = new SpringContext();
     public static EducationTypeService getEducationTypeService(){
         return ctx.getBean(EducationTypeService.class);
     }  
}
