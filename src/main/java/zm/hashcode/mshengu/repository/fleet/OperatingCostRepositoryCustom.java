/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.repository.fleet;

import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Colin
 */
public interface OperatingCostRepositoryCustom {

    public List<OperatingCost> findOperatingCostBetweenTwoDates(Date from, Date to);

    public List<OperatingCost> getOperatingCostByTruckBetweenTwoDates(Truck truck, Date from, Date to);

    public List<OperatingCost> getOperatingCostByTruckByMonth(Truck truck, Date month);
}
