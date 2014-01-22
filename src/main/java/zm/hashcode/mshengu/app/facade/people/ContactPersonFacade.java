/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.people;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.people.ContactPersonService;

/**
 *
 * @author Ferox
 */
public class ContactPersonFacade {

    private final static SpringContext ctx = new SpringContext();

    public static ContactPersonService getContactPersonService() {
        return ctx.getBean(ContactPersonService.class);
    }
}
