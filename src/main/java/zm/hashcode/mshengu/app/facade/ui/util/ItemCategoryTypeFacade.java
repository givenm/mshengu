/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.util;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.util.ItemCategoryTypeService;

/**
 *
 * @author Luckbliss
 */
public class ItemCategoryTypeFacade {
    private final static SpringContext ctx = new SpringContext();
     public static ItemCategoryTypeService getItemCategoryTypeService(){
         return ctx.getBean(ItemCategoryTypeService.class);
     } 
}
