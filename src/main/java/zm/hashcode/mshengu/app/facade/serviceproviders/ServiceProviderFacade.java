/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.serviceproviders;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderService;

/**
 *
 * @author Ferox
 */
public class ServiceProviderFacade {

    private final static SpringContext ctx = new SpringContext();

    public static ServiceProviderService getServiceProviderService() {
        return ctx.getBean(ServiceProviderService.class);
    }
}
