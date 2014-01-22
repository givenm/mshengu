/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.util;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.util.AccidentTypeService;

/**
 *
 * @author lucky
 */
public class AccidentTypeListFacade {
    private final static SpringContext ctx = new SpringContext();
     public static AccidentTypeService getAccidentTypeListService(){
         return ctx.getBean(AccidentTypeService.class);
     }  
}
