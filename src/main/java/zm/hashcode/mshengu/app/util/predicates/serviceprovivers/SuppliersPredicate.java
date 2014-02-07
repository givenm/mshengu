/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.predicates.serviceprovivers;

import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderCategory;

/**
 *
 * @author Luckbliss
 */
public class SuppliersPredicate implements Predicate<ServiceProvider> {

    /**
     *
     * @param ServiceProvider
     * @return
     */
    @Override
    public boolean apply(ServiceProvider serviceProvider) {
        if ("SUPPLIER".equalsIgnoreCase(getCategoryName(serviceProvider))) {
            return true;
        }
        return false;
    }

    private String getCategoryName(ServiceProvider serviceProvider) {
        if (serviceProvider.getServiceProviderCategory() != null) {
            return getServiceProviderCategoy(serviceProvider.getServiceProviderCategory());
        }
        return null;
    }

    private String getServiceProviderCategoy(ServiceProviderCategory providerCategory) {
        if (providerCategory != null) {
            return providerCategory.getCategoryName();
        }
        return null;
    }
}
