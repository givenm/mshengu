/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.position;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.position.PositionService;

/**
 *
 * @author lucky
 */
public class PositionFacade {
    private final static SpringContext ctx = new SpringContext();
     public static PositionService getPositionService(){
         return ctx.getBean(PositionService.class);
     }  
}
