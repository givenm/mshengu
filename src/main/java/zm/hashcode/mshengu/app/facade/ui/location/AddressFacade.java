/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.location;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.location.AddressService;

/**
 *
 * @author Ferox
 */
public class AddressFacade {
    private final static SpringContext ctx = new SpringContext();
     public static AddressService getAddressService(){
         return ctx.getBean(AddressService.class);
     }  
}
