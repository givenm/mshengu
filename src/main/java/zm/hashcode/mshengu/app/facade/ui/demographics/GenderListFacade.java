/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.demographics;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.demographics.GenderService;

/**
 *
 * @author lucky
 */
public class GenderListFacade {
    private final static SpringContext ctx = new SpringContext();
     public static GenderService getGenderListService(){
         return ctx.getBean(GenderService.class);
     }  
}
