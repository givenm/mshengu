/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement;

import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.MaintenanceSpendBySupplier;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;

/**
 *
 * @author Colin
 */
public interface MaintenanceSpendBySupplierService {

    public List<MaintenanceSpendBySupplier> findAll();

    public void persist(MaintenanceSpendBySupplier value);

    public void merge(MaintenanceSpendBySupplier value);

    public MaintenanceSpendBySupplier findById(String id);

    public void delete(MaintenanceSpendBySupplier value);

//    public List<MaintenanceSpendBySupplier> getMonthlyMileageCostBtnTwoDates(Date start, Date end);
    public List<MaintenanceSpendBySupplier> getMaintenanceSpendBetweenTwoDates(Date from, Date to);

    public List<MaintenanceSpendBySupplier> getMaintenanceSpendByTruckBetweenTwoDates(Truck truck, Date from, Date to);

    public List<MaintenanceSpendBySupplier> getMaintenanceSpendByTruckForMonth(Truck truck, Date month);

    public List<MaintenanceSpendBySupplier> getMaintenanceSpendBySupplierBetweenTwoDates(ServiceProvider serviceProvider, Date from, Date to);

    public List<MaintenanceSpendBySupplier> getMaintenanceSpendBySupplierForMonth(ServiceProvider serviceProvider, Date month);
}
