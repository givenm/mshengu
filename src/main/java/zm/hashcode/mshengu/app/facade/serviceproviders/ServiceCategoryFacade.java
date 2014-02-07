/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.facade.serviceproviders;

import zm.hashcode.mshengu.app.conf.SpringContext;
import zm.hashcode.mshengu.services.serviceprovider.ServiceCategoryService;

/**
 *
 * @author Ferox
 */
public class ServiceCategoryFacade {

    private final static SpringContext ctx = new SpringContext();

    public static ServiceCategoryService getServiceCategoryService() {
        return ctx.getBean(ServiceCategoryService.class);
    }
}
