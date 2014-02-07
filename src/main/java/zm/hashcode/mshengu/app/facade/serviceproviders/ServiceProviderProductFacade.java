/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.serviceproviders;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderProductService;

/**
 *
 * @author Ferox
 */
public class ServiceProviderProductFacade {

    private final static SpringContext ctx = new SpringContext();

    public static ServiceProviderProductService getServiceProviderProductService() {
        return ctx.getBean(ServiceProviderProductService.class);
    }
}
