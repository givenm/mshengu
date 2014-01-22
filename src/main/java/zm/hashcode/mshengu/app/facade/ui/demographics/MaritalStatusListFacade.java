/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.demographics;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.demographics.MaritalStatusService;

/**
 *
 * @author lucky
 */
public class MaritalStatusListFacade {
    private final static SpringContext ctx = new SpringContext();
     public static MaritalStatusService getMaritalStatusListService(){
         return ctx.getBean(MaritalStatusService.class);
     }  
}
