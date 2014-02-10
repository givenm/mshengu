/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.location;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.location.LocationService;

/**
 *
 * @author lucky
 */
public class LocationFacade {
    private final static SpringContext ctx = new SpringContext();
     public static LocationService getLocationService(){
         return ctx.getBean(LocationService.class);
     }  
}
