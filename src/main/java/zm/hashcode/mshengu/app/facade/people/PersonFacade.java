/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.people;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.people.PersonService;

/**
 *
 * @author lucky
 */
public class PersonFacade {

    private final static SpringContext ctx = new SpringContext();

    public static PersonService getPersonService() {
        return ctx.getBean(PersonService.class);
    }
}
