/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.util;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.util.CostCentreTypeService;

/**
 *
 * @author Luckbliss
 */
public class CostCentreTypeFacade {
     private final static SpringContext ctx = new SpringContext();
     public static CostCentreTypeService getCostCentreTypeService(){
         return ctx.getBean(CostCentreTypeService.class);
     } 
}
