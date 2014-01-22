/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.demographics;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.demographics.LanguageProficiencyService;

/**
 *
 * @author lucky
 */
public class LanguageProficiencyFacade {
    private final static SpringContext ctx = new SpringContext();
     public static LanguageProficiencyService getLanguageProficiencyService(){
         return ctx.getBean(LanguageProficiencyService.class);
     }  
}
