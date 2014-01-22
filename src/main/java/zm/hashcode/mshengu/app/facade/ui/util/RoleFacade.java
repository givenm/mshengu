/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.ui.util;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.ui.util.RoleService;

/**
 *
 * @author lucky
 */
public class RoleFacade {
    private final static SpringContext ctx = new SpringContext();
     public static RoleService getRoleService(){
         return ctx.getBean(RoleService.class);
     }  
}
