/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.util;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.util.SequenceTypeService;

/**
 *
 * @author Ferox
 */
public class SequenceTypeFacade {
    private final static SpringContext ctx = new SpringContext();
     public static SequenceTypeService getSequenceTypeListService(){
         return ctx.getBean(SequenceTypeService.class);
     }  
}
