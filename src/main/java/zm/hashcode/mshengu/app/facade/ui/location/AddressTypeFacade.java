/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.location;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.location.AddressTypeService;

/**
 *
 * @author lucky
 */
public class AddressTypeFacade {
    private final static SpringContext ctx = new SpringContext();
     public static AddressTypeService getAddressTypeService(){
         return ctx.getBean(AddressTypeService.class);
     }  
}
