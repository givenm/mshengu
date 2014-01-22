/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.education;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.education.DegreeService;

/**
 *
 * @author lucky
 */
public class DegreeFacade {
    private final static SpringContext ctx = new SpringContext();
     public static DegreeService getDegreeService(){
         return ctx.getBean(DegreeService.class);
     }  
}
