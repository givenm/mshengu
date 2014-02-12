/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.office;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.office.OfficeTypeService;

/**
 *
 * @author lucky
 */
public class OfficeTypeFacade {
    private final static SpringContext ctx = new SpringContext();
     public static OfficeTypeService getOfficeTypeService(){
         return ctx.getBean(OfficeTypeService.class);
     }  
}
