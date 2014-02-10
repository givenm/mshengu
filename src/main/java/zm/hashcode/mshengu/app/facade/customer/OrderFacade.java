/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.customer;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.customer.OrderService;

/**
 *
 * @author Luckbliss
 */
public class OrderFacade {

    private final static SpringContext ctx = new SpringContext();

    public static OrderService getOrderService() {
        return ctx.getBean(OrderService.class);
    }
}
