/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.procurement;

import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;

/**
 *
 * @author Colin
 */
public interface AnnualDataFleetMaintenanceCostService {

    public List<AnnualDataFleetMaintenanceCost> findAll();

    public void persist(AnnualDataFleetMaintenanceCost value);

    public void merge(AnnualDataFleetMaintenanceCost value);

    public AnnualDataFleetMaintenanceCost findById(String id);

    public void delete(AnnualDataFleetMaintenanceCost value);

    public List<AnnualDataFleetMaintenanceCost> getMonthlyMaintenanceCostBtnTwoDates(Date start, Date end);
}
