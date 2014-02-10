/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.position;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.position.PositionTypeService;

/**
 *
 * @author lucky
 */
public class PositionTypeFacade {
    private final static SpringContext ctx = new SpringContext();
     public static PositionTypeService getPositionTypeService(){
         return ctx.getBean(PositionTypeService.class);
     }  
}
