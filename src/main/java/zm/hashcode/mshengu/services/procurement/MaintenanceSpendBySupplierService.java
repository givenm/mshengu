/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement;

import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.domain.procurement.MaintenanceSpendBySupplier;

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

    public List<MaintenanceSpendBySupplier> getMonthlyMileageCostBtnTwoDates(Date start, Date end);
}
