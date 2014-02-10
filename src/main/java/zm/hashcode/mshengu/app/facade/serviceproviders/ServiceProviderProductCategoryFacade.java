/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.serviceproviders;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderProductCategoryService;

/**
 *
 * @author Ferox
 */
public class ServiceProviderProductCategoryFacade {

    private final static SpringContext ctx = new SpringContext();

    public static ServiceProviderProductCategoryService getServiceProviderProductCategoryService() {
        return ctx.getBean(ServiceProviderProductCategoryService.class);
    }
}
