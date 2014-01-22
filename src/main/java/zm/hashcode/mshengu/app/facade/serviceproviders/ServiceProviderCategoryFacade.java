/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.serviceproviders;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderCategoryService;

/**
 *
 * @author Ferox
 */
public class ServiceProviderCategoryFacade {

    private final static SpringContext ctx = new SpringContext();

    public static ServiceProviderCategoryService getServiceProviderCategoryService() {
        return ctx.getBean(ServiceProviderCategoryService.class);
    }
}
