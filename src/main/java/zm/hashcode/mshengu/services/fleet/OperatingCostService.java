/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.services.fleet;

import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Ferox
 */
public interface OperatingCostService {

    public List<OperatingCost> findAll();

    public void persist(OperatingCost truck);

    public void merge(OperatingCost truck);

    public OperatingCost findById(String id);

    public void delete(OperatingCost truck);

    public Truck findOperationallCostTruck(String operationalCostId);

    public List<OperatingCost> getOperatingCostByTruckByMonth(Truck truck, Date month);

    public List<OperatingCost> getOperatingCostBtnTwoDates(Date start, Date end);

    public List<OperatingCost> getOperatingCostByTruckBetweenTwoDates(Truck truck, Date from, Date to);
}
