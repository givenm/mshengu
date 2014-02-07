/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.procurement;

import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.MaintenanceSpendBySupplier;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author Colin
 */
public interface MaintenanceSpendBySupplierRepositoryCustom {

    public List<MaintenanceSpendBySupplier> getMaintenanceSpendBetweenTwoDates(Date from, Date to);

    public List<MaintenanceSpendBySupplier> getMaintenanceSpendByTruckBetweenTwoDates(Truck truck, Date from, Date to);

    public List<MaintenanceSpendBySupplier> getMaintenanceSpendByTruckForMonth(Truck truck, Date month);

    public List<MaintenanceSpendBySupplier> getMaintenanceSpendBySupplierBetweenTwoDates(ServiceProvider serviceProvider, Date from, Date to);

    public List<MaintenanceSpendBySupplier> getMaintenanceSpendBySupplierForMonth(ServiceProvider serviceProvider, Date month);
}
