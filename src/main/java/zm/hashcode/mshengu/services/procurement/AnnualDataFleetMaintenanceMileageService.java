/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement;

import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;

/**
 *
 * @author Colin
 */
public interface AnnualDataFleetMaintenanceMileageService {

    public List<AnnualDataFleetMaintenanceMileage> findAll();

    public void persist(AnnualDataFleetMaintenanceMileage value);

    public void merge(AnnualDataFleetMaintenanceMileage value);

    public AnnualDataFleetMaintenanceMileage findById(String id);

    public void delete(AnnualDataFleetMaintenanceMileage value);

//    public List<AnnualDataFleetMaintenanceMileage> getMonthlyMileageBtnTwoDates(Date start, Date end);
    public List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileageByTruckBetweenTwoDates(Truck truck, Date from, Date to);

    public List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileageByTruckForMonth(Truck truck, Date month);

    public List<AnnualDataFleetMaintenanceMileage> getAnnualDataMileageBetweenTwoDates(Date from, Date to);
}
