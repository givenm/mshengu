/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.util;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.util.SequenceService;

/**
 *
 * @author Ferox
 */
public class SequenceFacade {
    private final static SpringContext ctx = new SpringContext();
     public static SequenceService getSequenceListService(){
         return ctx.getBean(SequenceService.class);
     }  
}
