/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.util;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.util.DisciplineActionTypeService;

/**
 *
 * @author lucky
 */
public class DisciplineActionTypeListFacade {
    private final static SpringContext ctx = new SpringContext();
     public static DisciplineActionTypeService getDisciplineActionTypeListService(){
         return ctx.getBean(DisciplineActionTypeService.class);
     }  
}
