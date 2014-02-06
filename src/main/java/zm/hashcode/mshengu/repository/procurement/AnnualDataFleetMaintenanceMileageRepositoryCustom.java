/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.procurement;

import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;

/**
 *
 * @author Colin
 */
public interface AnnualDataFleetMaintenanceMileageRepositoryCustom {

    public List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileageBetweenTwoDates(Date from, Date to);

    public List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileageByTruckBetweenTwoDates(Truck truck, Date from, Date to);

    public List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileageByTruckForMonth(Truck truck, Date month);
}
