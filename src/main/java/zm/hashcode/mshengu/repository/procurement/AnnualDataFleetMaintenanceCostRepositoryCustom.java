/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.procurement;

import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;

/**
 *
 * @author Colin
 */
public interface AnnualDataFleetMaintenanceCostRepositoryCustom {

    public List<AnnualDataFleetMaintenanceCost> getAnnualDataCostBetweenTwoDates(Date from, Date to);

    public List<AnnualDataFleetMaintenanceCost> getAnnualDataCostByTruckBetweenTwoDates(Truck truck, Date from, Date to);

    public List<AnnualDataFleetMaintenanceCost> getAnnualDataCostByTruckForMonth(Truck truck, Date month);
}
