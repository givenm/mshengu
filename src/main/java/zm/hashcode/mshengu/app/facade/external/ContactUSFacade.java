/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.external;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.external.ContactUSService;

/**
 *
 * @author Ferox
 */
public class ContactUSFacade {

    private final static SpringContext ctx = new SpringContext();

    public static ContactUSService getContactUSService() {
        return ctx.getBean(ContactUSService.class);
    }
}
