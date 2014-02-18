/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.serviceprovider;

import java.util.List;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author Colin
 */
public interface ServiceProviderRepositoryCustom {

    public ServiceProvider findByVendorNumber(String vendorNumber);

    public List<ServiceProvider> getVehicleMaintenanceServiceProvders();
}
