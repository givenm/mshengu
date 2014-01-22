/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.customer;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.customer.CustomerService;

/**
 *
 * @author Luckbliss
 */
public class CustomerFacade {

    private final static SpringContext ctx = new SpringContext();

    public static CustomerService getCustomerService() {
        return ctx.getBean(CustomerService.class);
    }
}
