/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.util;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.util.CostCentreCategoryTypeService;

/**
 *
 * @author Luckbliss
 */
public class CostCentreCategoryTypeFacade {
    private final static SpringContext ctx = new SpringContext();
     public static CostCentreCategoryTypeService getCostCentreCategoryTypeService(){
         return ctx.getBean(CostCentreCategoryTypeService.class);
     } 
}
