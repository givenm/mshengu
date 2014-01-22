package zm.hashcode.mshengu.app.util.predicates.serviceprovivers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.common.base.Predicate;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author boniface
 */
public class PreferedVendorPredicate implements Predicate<ServiceProvider> {

    /**
     *
     * @param ServiceProvider
     * @return
     */
    @Override
    public boolean apply(ServiceProvider serviceProvider) {
        if (true == getPrefered(serviceProvider)) {
            return true;
        }
        return false;
    }

    private boolean getPrefered(ServiceProvider serviceProvider) {
        if (serviceProvider != null) {
            return serviceProvider.isPreferedVendor();
        }
        return false;
    }


}
