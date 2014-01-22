/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.demographics;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.demographics.LanguageService;

/**
 *
 * @author lucky
 */
public class LanguageFacade {
    private final static SpringContext ctx = new SpringContext();
     public static LanguageService getLanguageService(){
         return ctx.getBean(LanguageService.class);
     }  
}
