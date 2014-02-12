/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.position;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.position.DepartureReasonService;

/**
 *
 * @author lucky
 */
public class DepartureReasonFacade {
    private final static SpringContext ctx = new SpringContext();
     public static DepartureReasonService getDepartureReasonService(){
         return ctx.getBean(DepartureReasonService.class);
     }  
}
